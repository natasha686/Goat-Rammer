package com.example.demo1;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.util.*;
import javafx.stage.Stage;

public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;


    private AnchorPane endPane;
    private Scene endScene;
    private Stage endStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;
    private ImageView GoatInGame;

    Image GOAT = new Image("https://images-ext-2.discordapp.net/external/e9Bk-njtAugB8r9h8IDbpu7qkOkJrue8A-YIkAxWjA4/%3Fwidth%3D467%26height%3D587/https/media.discordapp.net/attachments/966768040463175710/966769822853644288/unknown.png?width=467&height=586", 200, 200, false, true);
    Image BZZ =  new Image("https://media.discordapp.net/attachments/966768040463175710/967132959939391498/unknown.png?width=788&height=587", 100, 100, false,true);
    Image WOLF = new Image ("https://media.discordapp.net/attachments/966768040463175710/967900454489055232/unknown.png?width=798&height=587",270, 252, false, true);

    private ImageView[] bees;
    private ImageView[] wolf;
    private ImageView[] playerLifes;
 //   private int playerLife;

    Random randomPositionGenerator;
    private AnimationTimer gameTimer;


    private final static double BZZ_RADIUS = 30;

    private final static int GOAT_RADIUS = 70;

    private final static int WOLF_RADIUS = 90;



    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        createBackground();
        initializeEndScene();
        createEndBackground();
        randomPositionGenerator = new Random();
    }

    private void createKeyListeners() {
        ImageView Goat;
        Goat = createGOAT(GOAT);

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                   // isLeftKeyPressed = true;
                   Goat.setLayoutX(Goat.getLayoutX() - 10);

                } else if (event.getCode() == KeyCode.RIGHT) {
                    Goat.setLayoutX(Goat.getLayoutX() + 10);
                }
                else if(event.getCode() == KeyCode.UP){
                    Goat.setLayoutY(Goat.getLayoutY() - 30);
                }
                else if(event.getCode() == KeyCode.DOWN){
                    Goat.setLayoutY(Goat.getLayoutY() + 30);
                }

            }
        });

    }


    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void initializeEndScene() {
        endPane = new AnchorPane();
        endScene = new Scene(endPane, GAME_WIDTH, GAME_HEIGHT);
        endStage = new Stage ();
        endStage.setScene(endScene);
    }

    public void createNewGame(Stage menuStage, Image goatAvatar) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createGameElements();
        createGameLoop();
        gameStage.show();
    }

    private void createGameElements () {
     //  playerLife = 2;
        bees = new ImageView[3];
        for (int i = 0; i < bees.length; i++) {
            bees[i] = new ImageView(BZZ);
            setNewElementPosition(bees[i], true);
            gamePane.getChildren().add(bees[i]);
        }

        wolf = new ImageView [1];
            wolf[0] = new ImageView(WOLF);
            setNewElementPosition(wolf[0], false);
            gamePane.getChildren().add(wolf[0]);


        /*playerLifes = new ImageView[3];

        for(int i = 0; i < playerLifes.length; i++) {
            playerLifes[i] = new ImageView(GOAT);
            playerLifes[i].setLayoutX(800 + (i * 100));
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]);

        }*/
    }

    private void moveGameElements() {

        for(int i = 0; i < bees.length; i++) {
            bees[i].setLayoutY(bees[i].getLayoutY()+7);
            bees[i].setRotate(bees[i].getRotate()+4);
        }


        for(int i = 0; i < wolf.length; i++) {
            wolf[i].setLayoutX(wolf[i].getLayoutX()-7);
        }
    }

    private void checkIfElementAreBehindTheGoatAndRelocated() {
        for(int i = 0; i< bees.length; i++) {
            if(bees[i].getLayoutY() > 900) {
                setNewElementPosition(bees[i], true);
            }
        }
        for(int i = 0; i< wolf.length; i++) {
            if(wolf[i].getLayoutX() < -200) {
                setNewElementPosition(wolf[i], false);
            }
        }

    }

    private void setNewElementPosition(ImageView image, boolean Bees) {
        if(Bees) {
            image.setLayoutX(randomPositionGenerator.nextInt(1550));
            image.setLayoutY(-randomPositionGenerator.nextInt(3200) + 600);
        }
        else {
            image.setLayoutX(1700);
            image.setLayoutY(529);
        }

    }
    private ImageView createGOAT(Image goatAvatar) {
        GoatInGame = new ImageView(goatAvatar);
        GoatInGame.setLayoutX(GAME_WIDTH / 8);
        GoatInGame.setLayoutY(GAME_HEIGHT/1.52);
        gamePane.getChildren().add(GoatInGame);
        return GoatInGame;
    }


    private void createGameLoop() {
        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                moveGameElements();
                checkIfElementAreBehindTheGoatAndRelocated();
                checkIfElementsCollide();

            }

        };
        gameTimer.start();
    }

    private void createBackground() {
        Image backgroundImage = new Image("https://cdn.dribbble.com/users/940134/screenshots/2614011/bg_dribble.png", 1550, 800, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));
    }
    private void createEndBackground(){
        Image endImage = new Image("https://media.discordapp.net/attachments/966768040463175710/967919899370479686/unknown.png", 1550, 800, false, true);
        BackgroundImage endBackground = new BackgroundImage(endImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        endPane.setBackground(new Background(endBackground));
        }


private double calculateDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1-x2, 2)+ Math.pow(y1-y2, 2));
}

   /* private void removeLife() {

        gamePane.getChildren().remove(playerLifes[playerLife]);
     //   System.out.println("Player life " + playerLife);
        playerLife--;
        if(playerLife < 0) {
            gameStage.close();
            gameTimer.stop();
            endStage.show();
        }
    }*/
private void checkIfElementsCollide(){

        int counter = 0;
        for(int i = 0; i<bees.length; i++){
            if(BZZ_RADIUS + GOAT_RADIUS > calculateDistance(GoatInGame.getLayoutX()+49, bees[i].getLayoutX()+20,
                    GoatInGame.getLayoutY()+37, bees[i].getLayoutY() + 20)) {
              //  removeLife();
                gameStage.close();
                gameTimer.stop();
                endStage.show();
            }

            }

    for(int i = 0; i<wolf.length; i++){
        if(WOLF_RADIUS + WOLF_RADIUS > calculateDistance(GoatInGame.getLayoutX()+49, wolf[i].getLayoutX()+20,
                GoatInGame.getLayoutY()+37, wolf[i].getLayoutY() + 20)) {
            gameStage.close();
            gameTimer.stop();
            endStage.show();


        }
    }
}


}
