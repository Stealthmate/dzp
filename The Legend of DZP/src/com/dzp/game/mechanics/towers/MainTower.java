package com.dzp.game.mechanics.towers;

import com.dzp.game.Rectangle;

public abstract class MainTower extends Tower{

    private static final int MainRange = 30;
    private static final int MainMaxHP = 200;
    
    public MainTower(Rectangle position, int level) {
        super(position, MainRange, level, MainMaxHP);
    }

    
}