package me.trent;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {


    private static App instance;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
    }


    public static App getInstance() {
        return instance;
    }
}
