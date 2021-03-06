package loft;

import java.util.ArrayList;
import loft.Nutriment;
import loft.Neuneu;
import loft.Case;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 10:20
 * Classe de neuneus cherchant à se nourrir en priorite
 */
public class Vorace extends Neuneu {
    public Vorace(String nom, int energie, Case position) {
        this.nom=nom;
        this.energie=energie;
        this.position=position;
    }
    public Vorace(String nom, int energie, Case position, int generation) {
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
        keys.add(Type.JUNK);
        keys.add(Type.SAIN);
        keys.add(Type.POISON);
        keys.add(Type.NORMAL);
        return keys;
    }

    public void deplace(){
        //reperage
        Case caseBut = this.position.closerFood(this);
        int deltaX = 0;
        int deltaY = 0;
        if(caseBut!=null){
            deltaX = caseBut.getAbs()-this.position.getAbs();
            deltaY = caseBut.getOrd()-this.position.getOrd();
        }
        //le vorace reste sur place si il reste de la nourriture
        if(this.position!=caseBut){
            Case randomDir = this.direction(caseBut, deltaX, deltaY);
            if (randomDir == null) {
                randomDir = this.position;
            }
            this.position.getHabitant().remove(this);
            randomDir.getHabitant().add(this);
            this.position = randomDir;
            this.energie-=10;
        }
        //se reproduit ou mange
        if(this.energie >= 70 && this.position.getHabitant().size()>1){
            position.getLoft().ajoutLofteur(this.reproduction(this.position.otherNeuneu(this)));
        }else if(this.position.hasAliment(this)){
            this.mange(this.position.bestFood(this));
        }
    }

    public void mange(Nutriment nutriment){
        if(nutriment instanceof Nourriture){
            ((Nourriture) nutriment).quantite -=1;
            this.energie += Type.getValeur().get(nutriment.getType().getType());
            if(((Nourriture) nutriment).quantite == 0){
                this.position.getContenu().remove(nutriment);
            }
        }
    }

    public Neuneu reproduction(Neuneu neuneu){
        this.energie -= 60;
        neuneu.energie -=60;
        return new Vorace(this.nom, 100, this.position, this.generation);
    }
}
