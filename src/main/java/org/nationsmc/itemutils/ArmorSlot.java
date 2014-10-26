package org.nationsmc.itemutils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Tim [calebbfmv]
 * Created by Tim [calebbfmv] on 10/24/2014.
 */
public enum ArmorSlot {

    HELMET("helm", "helmet", "hat", "head", "1"),
    CHESTPLATE("chest", "chestplate", "chest_plate", "2"),
    PANTS("leggings", "pants", "legs", "3"),
    BOOTS("boots", "feet", "shoes", "4");

    private String[] vars;
    private static HashMap<String, ArmorSlot> slots = new HashMap<>();

    static {
        Arrays.asList(values()).forEach((slot) -> Arrays.asList(slot.vars).forEach((var) -> slots.put(var, slot)));
    }

    public void addVarToMemory(String var){
        slots.put(var, this);
    }

    private ArmorSlot(String... vars){
        this.vars = vars;
    }

    public static ArmorSlot getFromVar(String var){
        return slots.get(var);
    }

}
