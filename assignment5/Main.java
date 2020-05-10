/* CRITTERS Main.java
 * EE422C Project 5 submission by
 * 
 * Jianchen Gu
 * jg68927
 * 16295
 * Deepanshi Sharma
 * ds52384
 * 16295
 * Slip days used: 0
 * Spring 2020
 */


package assignment5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import assignment5.Critter;

import java.io.File;

import assignment5.Animation;

public class Main extends Application {

    /* Gets the package name.  The usage assumes that Critter and its
       subclasses are all in the same package. */
    static String myPackage; // package of Critter file.

    /* Critter cannot be in default pkg. */
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
    
    // GridPane to contain the display 
    // Static so it can be accessed by other files like Critter.java
    public static GridPane display = new GridPane();
	// Tabbed panel for Controls, Animation, RunStats, and Parameters
    public static TabPane tabs = new TabPane();
    // cell size in the Grid
    public final static int gridWidthPixels = 700;
    public final static int gridHeightPixels = 700;
    public static int cellWidth = gridWidthPixels/Params.WORLD_WIDTH; 
    public static int cellHeight = gridHeightPixels/Params.WORLD_HEIGHT; 
    
    /**
     * Main method.
     *
     * @param args 
     * 
     */
    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
    /**
     * 
     */
    public void start(Stage primaryStage) {
    	
    	// Container for the Display and Controls
    	StackPane rootPane = new StackPane();
    	
        // Initial population of the grid as empty.
    	Critter.displayWorld(display);
    	
    	Tab control = new Tab("Controls");
    	control.setStyle("-fx-background-color: linear-gradient(to bottom right, #FFAC81, #EFE9AE); ");
    	control.setClosable(false);
    	ControlsUI controlUI = new ControlsUI();

    	Tab animation = new Tab("Animation");
    	animation.setStyle("-fx-background-color: linear-gradient(to bottom right, #80CED7, #CCF5AC); ");
    	animation.setClosable(false);
    	Animation animateUI = new Animation();
    	
    	Tab runStats = new Tab();
    	runStats.setStyle("-fx-background-color: linear-gradient(to bottom right, #C585B3, #FF928B); ");
    	runStats.setText("Run Stats");
    	runStats.setClosable(false);
    	StatsUI statsUI = new StatsUI();

    	Tab parameters = new Tab("Parameters");
    	parameters.setStyle("-fx-background-color: linear-gradient(to bottom right, #7BDFF2, #CDC1FF); ");
    	parameters.setClosable(false);
    	ParametersUI parametersUI = new ParametersUI();
    	     
        // set tabs contents to contain different UIs used for controls, animation, runStats, parameters
        controlUI.getControlsUI().setPadding(new Insets(10, 0, 0, 0));
        control.setContent(controlUI.getControlsUI());
    	tabs.getTabs().add(control);
    	
    	animateUI.getAnimationUI().setPadding(new Insets(10, 0, 0, 0));
    	animation.setContent(animateUI.getAnimationUI());
    	tabs.getTabs().add(animation);
    	
    	statsUI.getStatsUI().setPadding(new Insets(10, 0, 0, 0));
        runStats.setContent(statsUI.getStatsUI());
    	tabs.getTabs().add(runStats);
    	
    	parametersUI.getParametersUI().setPadding(new Insets(10, 0, 0, 0));
    	parameters.setContent(parametersUI.getParametersUI());
    	tabs.getTabs().add(parameters);
    

    	// Set controls GridPane offsetted to the right side to avoid overlap with display GridPane
    	StackPane.setMargin(tabs, new Insets(8, 8, 8, cellWidth*Params.WORLD_WIDTH + 25)); 
    	
        primaryStage.setScene(new Scene(rootPane, 1400, 900));
        // Add both the display GridPane and the controls GridPane
        rootPane.getChildren().addAll(display,tabs);
        rootPane.setStyle("-fx-background-color: #bbced4");

        // show the javaFX window
        primaryStage.show();
        
        // play the ocean waves mp3 file
        String musicFile = "src/oceanWaves.mp3";    
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
              mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
        
    }

}

