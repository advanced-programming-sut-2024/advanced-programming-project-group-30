package view;

import javafx.scene.Scene;

import java.util.Scanner;

public abstract class Menu {
    private Scene currentScene;
    public void run(Scanner scanner){

    }
    protected Scene getCurrentScene() {
        return currentScene;
    }
    protected void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }
}
