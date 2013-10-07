package loft;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author paulyves
 */
public class Loft {
    protected ArrayList<Neuneu> lofteurs = new ArrayList<Neuneu>();
    protected ArrayList<Case> map = new ArrayList<Case>();
    protected ArrayList<Neuneu> nouveauxNes = new ArrayList<Neuneu>();
    
    public static final int W = 30;
    public static final int H = 30;
    
    /**
     * ajoute un lofteur en cours de jeu
     * @param newLofteur 
     */
    public void ajoutLofteur(Neuneu newLofteur){
        this.nouveauxNes.add(newLofteur);
        newLofteur.getPosition().getHabitant().add(newLofteur);
    }
    
    /**
     * 
     */
    public Loft() throws FileNotFoundException {
        //initialisation des cases
        for(int i=0; i<Loft.W; i++){
            for (int j=0; j<Loft.H; j++){
                Case aCase = new Case(i,j,this);
                this.map.add(aCase);
            }
        }
        //liste des noms de lofteurs
        ArrayList<String> listeNom = this.readNom();
        
        //creation des lofteurs
        for(String nom : listeNom){
            int random = (int)(Math.random() * (5)) + 1;
            int randomPos = (int)(Math.random() * this.map.size());
            Case randomCase = this.map.get(randomPos);
            Neuneu newNeuneu;
            if(random == 1){
                newNeuneu = new Erratique(nom, 100, randomCase);
            }else if(random == 2){
                newNeuneu = new Vorace(nom, 100, randomCase);
            }else if(random == 3){
                newNeuneu = new Cannibale(nom, 100, randomCase);
            }else if(random == 4){
                newNeuneu = new Lapin(nom, 100, randomCase);
            }else{
                newNeuneu = new Zombie(nom, 50, randomCase);
            }
            randomCase.addHabitant(newNeuneu);
            lofteurs.add(newNeuneu);
        }
        
        //creation de la nourriture
        for(Case aCase : map){
            int dice100 = (int)(Math.random() * 100);
            ArrayList<String> listTypes = new ArrayList<String>();
            listTypes.add("alcool");
            listTypes.add("junk");
            listTypes.add("sain");
            listTypes.add("poison");
            listTypes.add("normal");
            if(dice100<5){
                int dice5 = (int)(Math.random() * 5);
                int dice6 = (int)(Math.random() * 6);
                Nourriture bouffe = new Nourriture(listTypes.get(dice5), dice6+1, listTypes.get(dice5));
                aCase.getContenu().add(bouffe);
            }
        }
            
    }
    
    public ArrayList<String> readNom() throws FileNotFoundException{
        ArrayList<String> listeNom = new ArrayList<String>();
        Scanner scanner = new Scanner(new FileInputStream(
                this.getClass().getResource("nom.csv").getFile()));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            listeNom.add(scanner.next());
        }
        scanner.close();
        return listeNom;
    }
    
    public void tourDeNeuneus(){
        ArrayList<Neuneu> morts = new ArrayList<Neuneu>();
        //le tour
        //déplacement des neuneus
        for (Neuneu aNeuneu : this.lofteurs) {
            aNeuneu.deplace();
        }
        //listage des victimes
        for (Neuneu aNeuneu : this.lofteurs) {
            if (aNeuneu.getEnergie() <= 0) {
                aNeuneu.getPosition().habitant.remove(aNeuneu);
                morts.add(aNeuneu);
            }
        }
        //morts des neuneus
        for (Neuneu neuneuMort : morts){
            this.lofteurs.remove(neuneuMort);
        }
        //ajout des nouveaux nés
        for (Neuneu newborn : nouveauxNes){
            this.lofteurs.add(newborn);
        }
        this.nouveauxNes.clear();
    }
    
    public void affiche(){
        System.out.println("Nouveau tour!");
        for (Case uneCase : this.map){
            System.out.println(uneCase.toString());
        }
    }

    public List<Neuneu> getLofteurs() {
        return lofteurs;
    }

    public List<Case> getMap() {
        return map;
    }
    
    
}
