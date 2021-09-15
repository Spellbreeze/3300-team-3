package com.draobemag.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.ui.FXGLButton;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SimpleGameApp extends GameApplication {
    private Entity player;
    @Override
    protected void initSettings(GameSettings settings)
    {
        settings.setWidth(800);
        settings.setHeight(300);
        settings.setTitle("Board Game Prototype");
    }

    @Override
    protected void initGame()
    {
        Entity boardTiles[] = new Entity[20];
        for (int i = 0; i < 20; i++)
        {
            boardTiles[i] = makeTile(30+35*i, 200);
        }
        player = makePlayer(45,210);

    }

    @Override
    protected void initUI()
    {
        // 1. create any JavaFX or FXGL UI object
        FXGLButton moveOne = new FXGLButton("Forward 1");
        FXGLButton moveThree = new FXGLButton("Forward 3");
        moveOne.setStyle("-fx-background-color: blue");
        moveThree.setStyle("-fx-background-color: blue");
        moveOne.setOnAction(e -> player.translateX(35));
        moveThree.setOnAction(e -> player.translateX(105));

        // 2. add the UI object to game scene (easy way) at 100, 100
        FXGL.addUINode(moveOne, 100, 100);
        FXGL.addUINode(moveThree, 500, 100);
    }

    private Entity makePlayer(double x, double y)
    {
        return FXGL.entityBuilder()
                .at(x,y)
                .type(EntityType.PLAYER)
                .viewWithBBox(new Circle(5, Color.BLUE))
                .buildAndAttach();
    }

    private Entity makeTile(double x, double y)
    {
        return FXGL.entityBuilder()
                .at(x,y)
                .type(EntityType.TILE)
                .viewWithBBox(new Rectangle(30,20, Color.RED))
                .buildAndAttach();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
