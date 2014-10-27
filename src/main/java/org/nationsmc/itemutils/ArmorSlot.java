package org.nationsmc.itemutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        List<ArmorSlot> slotList = Arrays.asList(values());
        slotList.stream().forEach(armor -> {
            List<String> variables = new ArrayList<>(Arrays.asList(armor.vars));
            variables.stream().forEach(var -> slots.put(var, armor));
        });
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
