package loft;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class Neuneu extends Nutriment{
    protected int energie;
    protected Case position;
    protected Loft loft;
    protected int generation = 0;
    {
        this.type=new Type("neuneu");
    }

    public abstract void deplace();
    public abstract void mange(Nutriment nutriment);
    public abstract Neuneu reproduction(Neuneu neuneu);
    public abstract Set<String> getRegime();

    public int getEnergie() {
        return energie;
    }

    public Case getPosition() {
        return position;
    }

    public Loft getLoft() {
        return loft;
    }

    public int getGeneration() {
        return generation;
    }
    
    protected Case direction(Case caseBut, int deltaX, int deltaY){
        ArrayList<Case> directions = new ArrayList<Case>();
        //on rentre une direction si elle est dans la bonne direction
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
        return (directions.size()==0? null : directions.get((int)(Math.random() * directions.size())));
    }
    
}
