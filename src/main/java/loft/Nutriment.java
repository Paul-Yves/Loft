package loft;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class Nutriment {
    protected Type type;
    protected String nom;

    /**
     * Gets the type of the nutriment
     * @return The Type of the nutriment
     */
    public Type getType(){
        return type;
    }

    public String getNom() {
        return nom;
    }
}
