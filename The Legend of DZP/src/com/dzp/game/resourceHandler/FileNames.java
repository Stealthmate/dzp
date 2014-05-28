package com.dzp.game.resourceHandler;

public enum FileNames {

    root("res/"),
    epoch("epoch"),
    level("level"),
    mapConfig("mapConfig"),
    mobImage("mob"),
    mapImage("map"),
    towerImage("tower"),
    backgroundImage("bg"),
    fileIndex("config");
    private String value;

    private FileNames(String value) {
        this.value = value;
    }

    public String getName() {
        return this.value;
    }
    
    public static String getPathTo(int e, FileNames c) {
        return root.getName() + epoch.getName() + e + "/" + c.getName();
    }

    public static String getPathTo(int e, int l, FileNames c) {
        return root.getName()
                + epoch.getName() + e + "/"
                + level.getName() + l + "/"
                + c.getName();
    }

    public static String getPathTo(FileNames c) {
        return root.getName() + c.getName();
    }
}
