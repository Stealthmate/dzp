package com.dzp.game.mechanics;

public class Nexus {
    
    private static final int maxHP = 3;
    private int HP;
    
    public Nexus() {
        HP = maxHP;
    }
    
    public boolean isAlive() {
        return HP>0;
    }
    
    public void strike() {
        
    }
}