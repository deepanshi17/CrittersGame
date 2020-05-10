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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ControlsUI {
	
	private GridPane controlUI;
	
	public ControlsUI(){
		controlUI = new GridPane();
		
		// MAKE CRITTERS SECTION
    	// Text/TextFields
    	Text makeCritterTxt = new Text("Make Critters");
    	Text critterNameTxt = new Text("  Critter Name");
    	Text numCrittersTxt = new Text("  # of Critters");
    	
    	TextField critterNameTf = new TextField();
    	TextField numCrittersTf = new TextField();

    	// Add Text/Textfields to Controls GridPane
    	controlUI.add(makeCritterTxt, 10, 0);
    	controlUI.add(critterNameTf, 10, 1);
    	controlUI.add(critterNameTxt, 11, 1);
    	controlUI.add(numCrittersTf, 10, 2);
    	controlUI.add(numCrittersTxt, 11, 2);
    	
    	// Button to add Critters
    	Button makeCritterBtn = new Button("Make Critter(s)");
    	
    	// Add the button to the container
        controlUI.add(makeCritterBtn, 10, 3);

        // onClick method for adding new Critters
        makeCritterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
	            	for (int i = 0; i < Integer.parseInt(numCrittersTf.getText()); i++) {
	            		try {
	    					Critter.createCritter(critterNameTf.getText());
	            		} 
	        			catch(InvalidCritterException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
	        				errorHandler(critterNameTf.getText());
	            		}
	            	}
	            	StatsUI.updateStats();
	            	Critter.displayWorld(Main.display);
            	}
            	catch(Exception e) {
            		errorHandler(numCrittersTf.getText());
            	}
            }
        });

        
        // DO TIME STEP SECTION
        // Text/TextFields
        Text spaceStep = new Text("");
    	Text doTimeStepTxt = new Text("Do Time Step");
    	Text numTimeStepsTxt = new Text("  # of Time Step(s)");
    	
    	TextField numTimeStepsTf = new TextField();

    	// Add Text/Textfields to Controls GridPane
    	controlUI.add(spaceStep, 10, 4);
    	controlUI.add(doTimeStepTxt, 10, 5);
    	controlUI.add(numTimeStepsTf, 10, 6);
    	controlUI.add(numTimeStepsTxt, 11, 6);
    	
    	// Button to do time step(s)
    	Button doTimeStepBtn = new Button("Do Time Step(s)");
    	
    	// Add the button to the container
        controlUI.add(doTimeStepBtn, 10, 7);

        // onClick method for doing time step(s)
        doTimeStepBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
    			try {
    				for (int i = 0; i < Integer.parseInt(numTimeStepsTf.getText()); i++) {
        				Critter.worldTimeStep();
    				}
    				StatsUI.updateStats();
    				Critter.displayWorld(Main.display);
    			} catch(Exception e) {
    				errorHandler(numTimeStepsTf.getText());
    			}
            }
        });
        
        
        // SET SEED VALUE SECTION
        // Text/TextFields
        Text spaceSeed = new Text("");
    	Text setSeedValueTxt = new Text("Set Seed Value");
    	Text seedValueTxt = new Text("  # Seed Value");
    	
    	TextField numSeedValueTf = new TextField();

    	// Add Text/Textfields to Controls GridPane
    	controlUI.add(spaceSeed, 10, 8);
    	controlUI.add(setSeedValueTxt, 10, 9);
    	controlUI.add(numSeedValueTf, 10, 10);
    	controlUI.add(seedValueTxt, 11, 10);
    	
    	// Button to set seed value
    	Button setSeedValueBtn = new Button("Set Seed Value");

    	// Add the button to the container
        controlUI.add(setSeedValueBtn, 10, 11);
        
        // onClick method for setting seed value
        setSeedValueBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Critter.setSeed(Integer.parseInt(numSeedValueTf.getText()));
    				StatsUI.updateStats();
    				Critter.displayWorld(Main.display);
    			} catch(Exception e) {
    				errorHandler(numSeedValueTf.getText());
    			}
            }
        });
        
        
        // UTILITY BUTTONS
        // Text for spacing
        Text spaceUtility = new Text("");
        
        // Add Text/Textfields to controls GridPane
        controlUI.add(spaceUtility, 11, 19);
        
        // Buttons
    	Button resetBtn = new Button("Reset");
    	Button quitBtn = new Button("Quit");
    	
    	// Add the buttons to the container
        controlUI.add(resetBtn, 11, 20);
        controlUI.add(quitBtn, 10, 20);

        // onClick method for resetting display
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Critter.clearWorld();
            	StatsUI.updateStats();
            	Critter.displayWorld(Main.display);
            }
        });

        // onClick method for quitting program
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    	
	}
	
	private static void errorHandler(String error) {
    	System.out.println("error processing: " + error);
    }
	
	public GridPane getControlsUI() {
		return controlUI;
	}
}
