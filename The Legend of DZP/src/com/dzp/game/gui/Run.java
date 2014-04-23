package com.dzp.game.gui;

import com.dzp.game.mechanics.EntityManagerThread;
import com.dzp.game.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.dzp.game.resourceHandler.ResourceLoader;
/*import org.lwjgl.LWJGLException;
 import org.lwjgl.opengl.Display;
 import org.lwjgl.opengl.DisplayMode;*/

public class Run {

    public static EntityManagerThread manager;
    public static ResourceLoader resources;
    public static final Dimension ScreenSize = null;
            //Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) throws Exception /*throws LWJGLException **/ {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ResourceLoader.load();
                } catch (Exception ex) {
                    Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("DONE");
               
            }
        }).start();
    }
}