package loft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class Cannibale extends Neuneu {
    public Cannibale(String nom, int energie, Case position) {
        this.nom=nom;
        this.energie=energie;
        this.position=position;
    }
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
            ArrayList<Case> directions = new ArrayList<Case>();
            //on rentre une direction si elle est dans la bonne direction 
            //ou si il n'y a pas de closerFood
            if ((this.position.getGauche() != null && deltaX<0) ||
                    (caseBut==null && this.position.getGauche() != null)){
                directions.add(this.position.getGauche());
            }
            if ((this.position.getHaut()!= null && deltaY<0) || 
                    (caseBut==null && this.position.getHaut() != null)){
                directions.add(this.position.getHaut());
            }
            if ((this.position.getDroite()!= null && deltaX>0) || 
                    (caseBut==null && this.position.getDroite() != null)){
                directions.add(this.position.getDroite());
            }
            if ((this.position.getBas()!= null && deltaY>0) || 
                    (caseBut==null && this.position.getBas() != null)){
                directions.add(this.position.getBas());
            }
            //deplacement eventuel
            Case randomDir = directions.get((int)(Math.random() * directions.size()));
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

    public Neuneu reproduction(Neuneu neuneu){
        this.energie -= 60;
        neuneu.energie -=60;
        return new Cannibale(this.nom, 100, this.position, this.generation);
    }
}
