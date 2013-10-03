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
public class Cannibale extends Neuneu {
    public Cannibale(String nom, int energie, Case position) {
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

    }

    public void mange(Nutriment nutriment){

    }

    public Neuneu reproduction(Neuneu neuneu){
        this.energie -= 60;
        neuneu.energie -=60;
        return new Cannibale(neuneu.nom + "-" + this.nom, 100, this.position);
    }
}
