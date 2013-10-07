package loft;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Représente une case du loft
 * @author paulyves,mario
 */
public class Case {
    protected int abs;
    protected int ord;
    protected List<Nourriture> contenu = new ArrayList<Nourriture>();
    protected List<Neuneu> habitant = new ArrayList<Neuneu>();
    protected Loft loft;

    /**
     * Constructeur par defaut
     * @param abs
     * @param ord
     * @param loft 
     */
    public Case(int abs, int ord, Loft loft) {
        this.abs = abs;
        this.ord = ord;
        this.loft = loft;
    }
    
    /**
     * Donne la case de gauche si elle existe
     * @return La Case de gauche, ou null si inexistante
     */
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
    
    /**
     * Donne la case de droite si elle existe
     * @return La Case de droite, ou null si inexistante
     */
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
    
    /**
     * Donne la case du haut
     * @return Case du haut ou null si rien
     */
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
    
    /**
     * Donne la case du bas
     * @return Case du bas null si rien
     */
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
        message += this.abs+" x "+this.ord+" ";
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

    /**
     * Vérifie qu'une case a un aliment dans le régime du neuneu
     * @param neuneu Le neuneu concerné
     * @return Vrai si la nourriture est présente, faux sinon.
     */
    public boolean hasAliment(Neuneu neuneu){
        for(Nourriture nourriture : this.getContenu()){
            if(neuneu.getRegime().contains(nourriture.getType().getType())){
                return true;
            }
        }
        return false;
    }

    /**
     * Donne la case la plus proche, avec de la nourriture qui correspond au régime du neuneu selectionné
     * @param neuneu Le neuneu (et donc régime) concerné
     * @return La case la plus proche avec la nourriture.
     */
    public Case closerFood(Neuneu neuneu){
        HashMap<Case, Integer> possible = new HashMap<Case, Integer>();
        for(Case aCase : this.loft.getMap()){
            if(aCase.hasAliment(neuneu)){
                possible.put(aCase, Math.abs(aCase.abs - this.abs) + Math.abs(aCase.ord - this.ord));
            }
        }
        Case ret = null;
        int distance=10000;
        for(Case aCase : possible.keySet()){
            if(possible.get(aCase)<distance){
                ret = aCase;
                distance = possible.get(aCase);
            }
        }
        return ret;
    }

    /**
     * Donne l'aliment le plus attrayant de la case en fonction du neuneu choisi
     * @param neuneu Le neuneu (et donc régime) concerné
     * @return L'aliment le plus attrayant ou null si pas de nourriture
     */
    public Nourriture bestFood(Neuneu neuneu){
        Nourriture retFood = null;
        Integer attrait = 0;
        for(Nourriture nourriture : getContenu()){
            if(neuneu.getRegime().contains(nourriture.getType().getType())){
                retFood = nourriture;
                attrait = Type.getAttrait().get(nourriture.getType().getType());
                break;
            }
        }
        for(Nourriture nourriture : getContenu()){
            if(neuneu.getRegime().contains(nourriture.getType().getType())){
                if(attrait < Type.getAttrait().get(nourriture.getType().getType())){
                    retFood = nourriture;
                    attrait = Type.getAttrait().get(nourriture.getType().getType());
                }
            }
        }
        return retFood;
    }
    
    /**
     * Permet de trouver un autre neuneu que celui en argument sur une case
     * @param theNeuneu
     * @return Un autre neuneu de la même case, null si il n'y en a pas
     */
    public Neuneu otherNeuneu(Neuneu theNeuneu){
        Neuneu theOther = null;
        for(Neuneu others : this.habitant){
            if(!others.equals(theNeuneu)){
                theOther = others;
            }
        }
        return theOther;
    }
    
    /**
     * Donne la case differente la plus proche contenant un autre neuneu
     * @param theNeuneu
     * @return autre case la plus proche ou se trouve un autre neuneu ou null
     */
    public Case closerNeuneu(Neuneu theNeuneu){
        HashMap<Case, Integer> possible = new HashMap<Case, Integer>();
        for(Case aCase : this.loft.getMap()){
            if(aCase!=this && aCase.getHabitant().size()>0){
                possible.put(aCase, Math.abs(aCase.abs - this.abs) + Math.abs(aCase.ord - this.ord));
            }
        }
        Case ret = null;
        int distance=10000;
        for(Case aCase : possible.keySet()){
            if(possible.get(aCase)<distance){
                ret = aCase;
                distance = possible.get(aCase);
            }
        }
        return ret;
    }
}
