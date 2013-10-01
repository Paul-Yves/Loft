package loft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class Erratique extends Neuneu {
    public Erratique(String nom, int energie, Case position) {
        this.nom=nom;
        this.energie=energie;
        this.position=position;
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
        //deplacement
        ArrayList<Case> directions = new ArrayList<Case>();
        if (this.position.getGauche() != null){
            directions.add(this.position.getGauche());
        }
        if (this.position.getHaut()!= null){
            directions.add(this.position.getHaut());
        }
        if (this.position.getDroite()!= null){
            directions.add(this.position.getDroite());
        }
        if (this.position.getBas()!= null){
            directions.add(this.position.getBas());
        }
        Case randomDir = directions.get((int)(Math.random() * directions.size()));
        this.position.getHabitant().remove(this);
        randomDir.getHabitant().add(this);
        this.position = randomDir;
        this.energie-=10;
        //mange
        if(randomDir.hasAliment(this)){
            this.mange(randomDir.bestFood(this));
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
        return new Erratique("toto", 100, this.position);
    }
}
