package loft;

import java.io.FileNotFoundException;

/**
 * Exec class with main or project
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println( "Hello World! Welcome to the LOFT" );
        FenetreJeu fen = new FenetreJeu();
        fen.main(null);
    }
}
