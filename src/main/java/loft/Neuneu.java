package loft;

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
}
