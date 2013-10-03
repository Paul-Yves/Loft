package loft;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class Lapin extends Neuneu {
    public Lapin(String nom, int energie, Case position) {
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
        keys.add(Type.JUNK);
        keys.add(Type.SAIN);
        keys.add(Type.POISON);
        keys.add(Type.NORMAL);
        return keys;
    }

    public void deplace(){
        if(this.energie>80){
            if(this.position.getBas()!=null && this.position.getBas().getHabitant().size()==1){
                this.position=this.position.getBas();
                this.reproduction(this.position.getBas().getHabitant().get(0));
            }
            else if(this.position.getHaut()!=null && this.position.getBas().getHabitant().size()==1){
                this.position=this.position.getHaut();
                this.reproduction(this.position.getHaut().getHabitant().get(0));
            }
            else if(this.position.getGauche()!=null && this.position.getBas().getHabitant().size()==1){
                this.position=this.position.getGauche();
                this.reproduction(this.position.getGauche().getHabitant().get(0));
            }
            else if(this.position.getDroite()!=null && this.position.getBas().getHabitant().size()==1){
                this.position=this.position.getDroite();
                this.reproduction(this.position.getDroite().getHabitant().get(0));
            }
        }else if(!this.position.getContenu().isEmpty() && this.position.hasAliment(this)){
            this.mange(this.position.bestFood(this));
        }
    }

    public void mange(Nutriment nutriment){

    }

    public Neuneu reproduction(Neuneu neuneu){
        this.energie -= 30;
        neuneu.energie -=60;
        return new Lapin(neuneu.nom + "-" + this.nom, 100, this.position);
    }
}
