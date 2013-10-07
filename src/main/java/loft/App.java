package loft;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println( "Hello World!" );
        FenetreJeu coucou = new FenetreJeu();
        coucou.main(null);
    }
}
