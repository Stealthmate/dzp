package com.dzp.game.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

public class LoaderThread extends Thread {
    
    private static final int data = 1142778;
    private JProgressBar progress;
    
    public LoaderThread(JProgressBar progress) {
        this.progress = progress;
    }
    
    @Override
    public void run() {
        
        while (true) {
            synchronized (com.dzp.game.resourceHandler.ResourceLoader.loaded) {
                try {
                    while (progress.getValue() < 100) {
        com.dzp.game.resourceHandler    pkgResources.ResourceLoader.loaded.wait();
          com.dzp.game.resourceHandler  progress.setValue(pkgResources.ResourceLoader.progress);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoaderThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
}
