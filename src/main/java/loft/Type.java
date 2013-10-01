package loft;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mario
 * Date: 30/09/13
 * Time: 09:12
 * To change this template use File | Settings | File Templates.
 */
public class Type {
    private String type;

    public static final String ALCOOL = "alcool";
    public static final String NEUNEU = "neuneu";
    public static final String JUNK = "junk";
    public static final String SAIN = "sain";
    public static final String POISON = "poison";
    public static final String NORMAL = "normal";

    public Type(String type){
        if(getDefaultKeys().contains(type)){
            this.type=type;
        }else{
            this.type=NORMAL;
        }
    }

    /**
     * Gets all the known keys in a HashSet
     * @return A HashSet with all the known keys.
     */
    public static Set<String> getDefaultKeys(){
        HashSet<String> keys = new HashSet<String>();
        keys.add(ALCOOL);
        keys.add(NEUNEU);
        keys.add(JUNK);
        keys.add(SAIN);
        keys.add(POISON);
        keys.add(NORMAL);
        return keys;
    }

    /**
     * Gets all the Attraits for each types
     * @return A Map with type keys and attrait value
     */
    public static Map<String, Integer> getAttrait(){
        HashMap<String, Integer> ret = new HashMap<String, Integer>();
        ret.put(ALCOOL, 2);
        ret.put(NEUNEU, 2);
        ret.put(JUNK, 2);
        ret.put(SAIN, 2);
        ret.put(POISON, 2);
        ret.put(NORMAL, 2);
        return ret;
    }

    /**
     * Gets all the nutrivalues for each types
     * @return A Map with type keys and nutrivalue value
     */
    public static Map<String, Integer> getValeur(){
        HashMap<String, Integer> ret = new HashMap<String, Integer>();
        ret.put(ALCOOL, 2);
        ret.put(NEUNEU, 2);
        ret.put(JUNK, 2);
        ret.put(SAIN, 2);
        ret.put(POISON, 2);
        ret.put(NORMAL, 2);
        return ret;
    }

    /**
     * Gets all the representations for each types
     * @return A Map with type keys and representation value
     */
    public static Map<String, String> getRepresentation(){
        HashMap<String, String> ret = new HashMap<String, String>();
        ret.put(ALCOOL, "AL");
        ret.put(NEUNEU, "NE");
        ret.put(JUNK, "JU");
        ret.put(SAIN, "SA");
        ret.put(POISON, "PO");
        ret.put(NORMAL, "NO");
        return ret;
    }

    public String getType() {
        return type;
    }
}
