package com.example.demo1;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;


public class GoatRammerButton extends Button {

    private final String BUTTON_PRESSED_STYLE = ".fx.background.color: transparent; -fx-background-image: url('https://wallpapercave.com/wp/EICnzUL.jpg');";
    private final String BUTTON_FREE_STYLE = ".fx.background.color: transparent; -fx-background-image: url('https://www.pngmagic.com/product_images/yellow-solid-background.jpg');";

    public GoatRammerButton(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        initializedButtonListeners();
    }

    private void setButtonFont() {
            setFont(Font.font("Impact", 25));
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4); // 4 pixels smaller than released size
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4); // 4 pixels smaller than released size
    }

    private void initializedButtonListeners() {

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });


        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
             public void handle(MouseEvent event) {
                                setEffect(new DropShadow());
                            }
                        });



        setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event){
                    setEffect(null);
                }
            });
        }
    }

