package me.trent.utils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Labeled;
import me.trent.App;
import me.trent.Storage;
import me.trent.gui.GUI;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void log(String s){
     System.out.println(s);
    }


    public static List<Node> getAllLabeled(Parent root){
        List<Node> nodes = new ArrayList<>();
        root.getChildrenUnmodifiable().stream().forEach(n -> {
            if (n instanceof Labeled) nodes.add(n);
            if (n instanceof Parent) nodes.addAll( getAllLabeled((Parent)n) ); });
        return nodes;
    }

    //public static String translateString(String s, Lang oldLang, Lang newLang){
    //    TranslateOptions translateOptions = new TranslateOptions.Builder()
    //            .addText(s)
    //            .source(oldLang.getAbbreviation())
    //            .target(newLang.getAbbreviation())
    //            .build();
    //    //TranslationResult translationResult = App.getInstance().getService().translate(translateOptions).execute().getResult();
    //    //return translationResult.getTranslations().get(0).getTranslation();
    //    return s;
    //}

    public static boolean collided(double x, double y, double x2, double y2, int offset){
        if (x == x2 && y == y2){
            return true;
        }

        double newX = x-x2;
        double newY = y-y2;

        if (0 > newX || 0 > newY){
            return false;
        }

        if (offset >= newX && offset >= newY){
            return true;
        }

        return false;
    }

    public static GUI getGUI(String name){
        for (GUI gui : Storage.guiList){
            if (gui.getAssetName().equalsIgnoreCase(name)){
                return gui;
            }
        }
        return null;
    }

    public static File getHomeFolder(){
        try {
            return new File(App.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    public static File getCurrentFolder(){
        try {
            return new File(App.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


}