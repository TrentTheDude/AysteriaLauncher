package me.trent.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.trent.Storage;

import java.io.IOException;

public abstract class GUI {

    private String assetName;
    private Parent parent;
    private Stage stage;

    public GUI(String assetName, boolean show) {
        try {
            this.assetName = assetName;
            this.parent = FXMLLoader.load(getClass().getResource("/assets/gui/" + assetName + ".fxml"));
            this.stage = new Stage();
            this.stage.setScene(new Scene(this.parent));
            this.stage.initStyle(StageStyle.UNDECORATED);
            this.stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/lock.png")));

            registerEvents();

            Storage.guiList.add(this);
            if (show){
                this.stage.show();
            }else{
                this.stage.hide();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void registerEvents(){ }

    public Stage getStage() {
        return stage;
    }

    public Parent getParent() {
        return parent;
    }

    public String getAssetName() {
        return assetName;
    }

}