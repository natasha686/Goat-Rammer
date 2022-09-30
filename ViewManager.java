package com.example.demo1;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ViewManager {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    Image GOAT = new Image("https://images-ext-2.discordapp.net/external/e9Bk-njtAugB8r9h8IDbpu7qkOkJrue8A-YIkAxWjA4/%3Fwidth%3D467%26height%3D587/https/media.discordapp.net/attachments/966768040463175710/966769822853644288/unknown.png?width=467&height=586", 200, 200, false,true);




    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        startButton();
        createBackground();
    }


    public Stage getMainStage(){

        return mainStage;
    }

    private GoatRammerButton startButton() {
        GoatRammerButton button = new GoatRammerButton("Good Luck!");
        mainPane.getChildren().add(button);

        button.setLayoutX(350);
        button.setLayoutY(350);

        button.setOnAction(new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage, GOAT);
            }
        });

        return button;
    }


     private void createBackground(){
         Image backgroundImage = new Image("https://media.discordapp.net/attachments/966768040463175710/967924938004656139/unknown.png", 1550, 800, false,true);
         BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
         mainPane.setBackground(new Background(background));
    }
}