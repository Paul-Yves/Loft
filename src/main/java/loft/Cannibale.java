package loft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Représente le neuneu canibale
 * @author paul-yves, mario
 */
public class Cannibale extends Neuneu {
    /**
     * constructeur a l'initialisation
     * @param nom
     * @param energie
     * @param position 
     */
    public Cannibale(String nom, int energie, Case position) {
        this.nom=nom;
        this.energie=energie;
        this.position=position;
    }
    
    /**
     * constructeur à la naissance
     * @param nom
     * @param energie
     * @param position
     * @param generation 
     */
    public Cannibale(String nom, int energie, Case position, int generation) {
        this.nom=nom;
        this.energie=energie;
        this.position=position;
        this.generation=generation+1;
    }

    /**
     * Gets the regime of the neuneu
     * @return A HashSet with the regime.
     */
    public Set<String> getRegime(){
        HashSet<String> keys = new HashSet<String>();
        keys.add(Type.ALCOOL);
        keys.add(Type.NEUNEU);
        keys.add(Type.JUNK);
        keys.add(Type.SAIN);
        keys.add(Type.POISON);
        keys.add(Type.NORMAL);
        return keys;
    }

    /**
     * Execute les taches d'un tour de neuneu: repérage des cases proches, déplacement, se nourir ou reproduire
     */
    public void deplace(){
        //reperage
        Case caseBut = this.position.closerFood(this);
        if (caseBut==null){
            caseBut = this.position.closerNeuneu(this);
        }
        int deltaX = 0;
        int deltaY = 0;
        if(caseBut!=null){
            deltaX = caseBut.getAbs()-this.position.getAbs();
            deltaY = caseBut.getOrd()-this.position.getOrd();
        }
        //le cannibale reste sur place si il reste de la nourriture
        if(this.position!=caseBut){
            //deplacement eventuel
            Case randomDir = this.direction(caseBut, deltaX, deltaY);
            if(randomDir==null){
                randomDir=this.position;
            }
            this.position.getHabitant().remove(this);
            randomDir.getHabitant().add(this);
            this.position = randomDir;
            this.energie-=10;
        }
        //se reproduit ou mange
        if(this.energie >= 70 && this.position.getHabitant().size()>1){
            position.getLoft().ajoutLofteur(this.reproduction(this.position.otherNeuneu(this)));
        }else{
            if(this.position.otherNeuneu(this)!=null){
                this.mange(this.position.otherNeuneu(this));
            }
            if(this.position.hasAliment(this)){
                this.mange(this.position.bestFood(this));
            }
        }
    }

    /**
     * Fais manger un aliment à this
     * @param nutriment L'aliment a manger
     */
    public void mange(Nutriment nutriment){
        if(nutriment instanceof Nourriture){
            ((Nourriture) nutriment).quantite -=1;
            this.energie += Type.getValeur().get(nutriment.getType().getType());
            if(((Nourriture) nutriment).quantite == 0){
                this.position.getContenu().remove(nutriment);
            }
        }else if (nutriment instanceof Neuneu){
            System.out.println("OH OmNomNom!");
            this.energie += Type.getValeur().get(nutriment.getType().getType());
            Neuneu victime = (Neuneu) nutriment;
            victime.energie -= 120;
        }
    }

    /**
     * Fais se reproduire this (père) avec l'argument neuneu
     * @param neuneu la mère
     * @return Un neuneu nouveau né qui porte la race du père
     */
    public Neuneu reproduction(Neuneu neuneu){
        this.energie -= 60;
        neuneu.energie -=60;
        return new Cannibale(this.nom, 100, this.position, this.generation);
    }
}
