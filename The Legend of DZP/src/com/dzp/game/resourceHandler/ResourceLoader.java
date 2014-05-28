package com.dzp.game.resourceHandler;

import android.graphics.Bitmap;
import com.dzp.game.DZPgame;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ResourceLoader {

    public static ArrayList<GameWorld> epoches;
    private static ClassLoader cl;
    private static BufferedReader resourceReader;
    private static URL resourceURL;
    public static int epochCount;
    public static int levelCountPerEpoch;
    public static Bitmap titleBackground;
    public static final Integer loaded;
    public static volatile int progress = 0;

    static {
        loaded = 0;
    }

    public static void load() throws Exception {

        cl = ResourceLoader.class.getClassLoader();
        resourceURL = cl.getResource(FileNames.getPathTo(FileNames.fileIndex));
        resourceReader = new BufferedReader(
                new InputStreamReader(
                (BufferedInputStream) resourceURL.openStream()));

        //Bitmap icon = new Bitmap(cl.getResource(FileNames.getPathTo(FileNames.backgroundImage)));
        titleBackground = (Bitmap) DZPgame.get("titleBackground");
        /*titleBackground = new Bitmap(
                icon.getScaledInstance(icon.getWidth(), icon.getHeight() - 64, Bitmap.S));
*/
        synchronized (loaded) {
            progress += 60;
            loaded.notifyAll();
        }

        epoches = new ArrayList();
        String line = resourceReader.readLine();
        File f = new File("E:/Anime and Manga/MMCE_Win32.zip");
        System.out.println(f.getParent());
        epochCount = Integer.parseInt(line);
        line = resourceReader.readLine();
        levelCountPerEpoch = Integer.parseInt(line);
        resourceReader.close();
        for (int i = 1; i <= epochCount; i++) {
            epoches.add(readEpoch(i));
            synchronized (loaded) {
                loaded.notifyAll();
                progress += 8;
            }
        }
    }

    public static void createGame(int e, int l) throws IOException {
        CurrentGame.createGame(epoches.get(e), epoches.get(e).getLevel(l), null);/* Not finished */
    }

    ///Read
    private static GameWorld readEpoch(int n) throws Exception {

        Bitmap tower, map;
        tower = null;

        ArrayList<GameLevel> levels = new ArrayList();

        for (int i = 1; i <= levelCountPerEpoch; i++) {
            levels.add(readLevel(i, n));
        }

        return new GameWorld(tower, levels);

    }

    private static GameLevel readLevel(int n, int e) throws Exception {
        return new GameLevel(
                cl.getResource(FileNames.getPathTo(e, n, FileNames.mapImage)),
                cl.getResource(FileNames.getPathTo(e, n, FileNames.mapConfig)));
    }
}