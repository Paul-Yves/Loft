package loft;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;

/**
 * Represente le plateau de jeux, avec cases etc..
 * @author paulyves, mario
 */
public class Plateau extends javax.swing.JPanel {
    private Loft loft;
    /**
     * Taille d'une case en px
     */
    public static final int TAILLECASE = 30;
    /**
     * Creates new form NewJPanel
     */
    public Plateau() throws FileNotFoundException {
        initComponents();
        loft = new Loft();
    }

    public Loft getLoft() {
        return loft;
    }
    
    /**
     * Initialise le plateau au début d'un tour
     * @param g Composant graphique SWING
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.clearRect(0, 0, (Loft.W+5)*TAILLECASE, (Loft.H+5)*TAILLECASE);
        int alea = (int)(Math.random() * 4);
        g.setColor(Color.lightGray);
        //placer les cases
        for (int i = 0; i < Loft.W; i++) {
            for (int j = 0; j < Loft.H; j++) {
                if ((i % 2 == 0 && j % 2 ==1)||(i % 2 == 1 && j % 2 ==0)) {
                    g.fillRect(TAILLECASE * i + TAILLECASE, TAILLECASE * j + TAILLECASE, TAILLECASE, TAILLECASE);
                }
            }
        }
        
        //placer les neuneus
        for(Neuneu lofteur:this.loft.getLofteurs()){
            int abs = lofteur.getPosition().getAbs();
            int ord = lofteur.getPosition().getOrd();
            if (lofteur instanceof Erratique){
                g.setColor(Color.green);
            } else if (lofteur instanceof Vorace){
                g.setColor(Color.blue);
            } else if (lofteur instanceof Lapin){
                g.setColor(new Color(255, 50, 155));
            } else if (lofteur instanceof Cannibale && !(lofteur instanceof Zombie)){
                g.setColor(Color.red);
            } else {
                g.setColor(new Color(50,120,50));
            }
            g.drawString(lofteur.getNom()+lofteur.getGeneration()+":"+lofteur.getEnergie(), (abs+1)*TAILLECASE+TAILLECASE/2, (ord+1)*TAILLECASE+TAILLECASE/2-10);
            g.fillOval((abs+1)*TAILLECASE+TAILLECASE/2, (ord+1)*TAILLECASE+TAILLECASE/2, 10, 10);
        }
        
        //placer la nourriture
        for(Case aCase : this.loft.map){
            for(Nourriture bouffe : aCase.getContenu()){
                g.setColor(Color.BLACK);
                int abs = aCase.getAbs();
                int ord = aCase.getOrd();
                g.drawString(bouffe.getNom()+" :"+bouffe.quantite, (abs+1)*TAILLECASE+TAILLECASE/4, (ord+1)*TAILLECASE+TAILLECASE/2-6);
                g.fillRect((abs+1)*TAILLECASE+TAILLECASE/4, (ord+1)*TAILLECASE+TAILLECASE/2, 10, 10);
            }
        }
        
        //compteur de neuneus
        g.drawString("Il y a : "+this.loft.getLofteurs().size()+" neuneus", 0, TAILLECASE/2);
        
        //fin de partie 
        if (loft.lofteurs.size()<=1){
            g.setColor(Color.white);
            g.clearRect(0, 0, (Loft.W+5)*TAILLECASE, (Loft.H+5)*TAILLECASE);
            g.setColor(Color.black);
            g.drawString("C'est fini, il n'y a plus qu'un lofteur et il va mourir seul", TAILLECASE*4, TAILLECASE*4);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
