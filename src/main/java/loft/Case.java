package loft;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;

/**
 *
 * @author paulyves
 */
public class Case {
    protected int abs;
    protected int ord;
    protected List<Nourriture> contenu;
    protected List<Neuneu> habitant;
    protected Loft loft;

    public Case(int abs, int ord, Loft loft) {
        this.abs = abs;
        this.ord = ord;
        this.loft = loft;
    }
    
    public Case getGauche(){
        if(this.abs==0){
            return null;
        }else{
            List<Case> map = loft.getMap();
            for(Case uneCase : map){
                if(this.ord==uneCase.getOrd() && this.abs-1==uneCase.getAbs()){
                    return uneCase;
                }
            }
            return null;
        }
    }
    
    public Case getDroite(){
        if(this.abs==Loft.W-1){
            return null;
        }else{
            List<Case> map = loft.getMap();
            for(Case uneCase : map){
                if(this.ord==uneCase.getOrd() && this.abs+1==uneCase.getAbs()){
                    return uneCase;
                }
            }
            return null;
        }
    }
    
    public Case getHaut(){
        if(this.ord==0){
            return null;
        }else{
            List<Case> map = loft.getMap();
            for(Case uneCase : map){
                if(this.ord-1==uneCase.getOrd() && this.abs==uneCase.getAbs()){
                    return uneCase;
                }
            }
            return null;
        }
    }
    
    public Case getBas(){
        if(this.ord==Loft.H-1){
            return null;
        }else{
            List<Case> map = loft.getMap();
            for(Case uneCase : map){
                if(this.ord+1==uneCase.getOrd() && this.abs==uneCase.getAbs()){
                    return uneCase;
                }
            }
            return null;
        }
    }
    

    public int getAbs() {
        return abs;
    }

    public int getOrd() {
        return ord;
    }

    public List<Nourriture> getContenu() {
        return contenu;
    }

    public List<Neuneu> getHabitant() {
        return habitant;
    }
    
    public Loft getLoft() {
        return loft;
    }
    
    public void addHabitant(Neuneu newHabitant){
        this.habitant.add(newHabitant);
    }
    
    public String toString(){
        String message = new String();
        if(!contenu.isEmpty()){
            message += "Il y a à manger :";
            for(Nourriture food : contenu){
                message += " "+food.toString()+" ";
            }
        }
        if(!habitant.isEmpty()){
            message += "Il y a des gens :";
            for(Neuneu occupant : habitant){
                message += " "+occupant.getNom()+" ";
            }
        }
        return message;
    }

    public boolean hasAliment(Neuneu neuneu){
        for(Nourriture nourriture : getContenu()){
            if(neuneu.)
        }
    }
}
