/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dzp.game.mechanics;

/**
 *
 * @author Madara1233
 */
 public enum Occupator {
    EMPTY(0), ROAD(1), IMMOVABLE(2), TOWER(3), NEXUS(4);
    private final int value;

    private Occupator(int value) {
        this.value = value;
    }
    
    public static Occupator forNumber(int i) {
        switch(i) {
            case 0 : {
                return Occupator.EMPTY;
            }
            case 1 : {
                return Occupator.ROAD;
            }
            case 2 : {
                return Occupator.IMMOVABLE;
            }
            case 3 : {
                return Occupator.TOWER;
            }
            case 4 : {
                return Occupator.NEXUS;
            }
            default : {
                return null;
            }
        }
    }
    
}
