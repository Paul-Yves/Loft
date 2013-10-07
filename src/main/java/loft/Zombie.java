/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loft;

import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class Zombie extends Cannibale{

    public Zombie(String nom, int energie, Case position) {
        super(nom, energie, position);
    }

    public Zombie(String nom, int energie, Case position, int generation) {
        super(nom, energie, position, generation);
    }
    
    public void deplace(){
        //reperage
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
        this.energie-=1;
        //se reproduit ou mange
        if(this.position.otherNeuneu(this)!=null){
            this.mange(this.position.otherNeuneu(this));
        }
    }
    
     /**
     * Fais manger un aliment à this
     * @param nutriment L'aliment a manger
     */
    public void mange(Nutriment nutriment){
        if (nutriment instanceof Neuneu && !(nutriment instanceof Zombie)){
            System.out.println("Brains!");
            this.energie += Type.getValeur().get(nutriment.getType().getType());
            Neuneu victime = (Neuneu) nutriment;
            victime.energie = 0;
            this.position.loft.ajoutLofteur(new Zombie("zombie "+victime.nom, 50, this.getPosition()));
        }
    }
}
