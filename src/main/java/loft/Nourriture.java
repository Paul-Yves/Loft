package loft;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */
public class Nourriture extends Nutriment{
    /**
     * Represente la quantite de cette nourriture presente
     */
    public int quantite;

    public Nourriture(String nom, int quantite, String type){
        this.nom=nom;
        this.quantite=quantite;
        this.type=new Type(type);
    }
}
