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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ParametersUI {
	
	private GridPane parametersUI;
	
	public ParametersUI(){
		// create new GridPane
		parametersUI = new GridPane();
		
		// Params Text/Textfields
        Text walkEnergyCostTxt = new Text("Walk Energy Cost  ");
        Text runEnergyCostTxt = new Text("Run Energy Cost  ");
        Text restEnergyCostTxt = new Text("Rest Energy Cost  ");
        Text lookEnergyCostTxt = new Text("Look Energy Cost  ");
        Text minReproduceEnergyTxt = new Text ("Min Reproduce Energy  ");
        Text refreshCloverCountTxt = new Text ("Refresh Clover Count  ");
        Text photosynthesisEnergyTxt = new Text ("Photosynthesis Energy  ");
        Text startEnergyTxt = new Text ("Start Energy  ");
        Text worldWidthTxt = new Text("World Width  ");
        Text worldHeightTxt = new Text("World Height  ");
        Text spaceUtility = new Text("");
        
        TextField walkEnergyCostTf = new TextField();
        TextField runEnergyCostTf = new TextField();
        TextField restEnergyCostTf = new TextField();
        TextField lookEnergyCostTf = new TextField();
        TextField minReproduceEnergyTf = new TextField();
        TextField refreshCloverCountTf = new TextField();
        TextField photosynthesisEnergyTf = new TextField();
        TextField startEnergyTf = new TextField();
        TextField worldWidthTf = new TextField();
        TextField worldHeightTf = new TextField();
        
        // Add Text/Textfields to Parameters GridPane
        parametersUI.add(walkEnergyCostTxt, 10, 0);
        parametersUI.add(walkEnergyCostTf, 11, 0);
        parametersUI.add(runEnergyCostTxt, 10, 1);
        parametersUI.add(runEnergyCostTf, 11, 1);
        parametersUI.add(restEnergyCostTxt, 10, 2);
        parametersUI.add(restEnergyCostTf, 11, 2);
        parametersUI.add(lookEnergyCostTxt, 10, 3);
        parametersUI.add(lookEnergyCostTf, 11, 3);
        parametersUI.add(minReproduceEnergyTxt, 10, 4);
        parametersUI.add(minReproduceEnergyTf, 11, 4);
        parametersUI.add(refreshCloverCountTxt, 10, 5);
        parametersUI.add(refreshCloverCountTf, 11, 5);
        parametersUI.add(photosynthesisEnergyTxt, 10, 6);
        parametersUI.add(photosynthesisEnergyTf, 11, 6);
        parametersUI.add(startEnergyTxt, 10, 7);
        parametersUI.add(startEnergyTf, 11, 7);
        parametersUI.add(worldWidthTxt, 10, 8);
        parametersUI.add(worldWidthTf, 11, 8);
        parametersUI.add(worldHeightTxt, 10, 9);
        parametersUI.add(worldHeightTf, 11, 9);
        parametersUI.add(spaceUtility, 10, 10);
        
        // Button to change walk energy cost
    	Button walkEnergyCostBtn = new Button("Change Value");
    	Button runEnergyCostBtn = new Button("Change Value");
    	Button restEnergyCostBtn = new Button("Change Value");
    	Button lookEnergyCostBtn = new Button("Change Value");
    	Button minReproduceEnergyBtn = new Button("Change Value");
    	Button refreshCloverCountBtn = new Button("Change Value");
    	Button photosynthesisEnergyBtn = new Button("Change Value");
    	Button startEnergyBtn = new Button("Change Value");
    	Button worldWidthBtn = new Button("Change Value");
    	Button worldHeightBtn = new Button("Change Value");
    	Button quitBtn = new Button("Quit");
    	
    	// Add the button to the container.
        parametersUI.add(walkEnergyCostBtn, 12, 0);
        parametersUI.add(runEnergyCostBtn, 12, 1);
        parametersUI.add(restEnergyCostBtn, 12, 2);
        parametersUI.add(lookEnergyCostBtn, 12, 3);
        parametersUI.add(minReproduceEnergyBtn, 12, 4);
        parametersUI.add(refreshCloverCountBtn, 12, 5);
        parametersUI.add(photosynthesisEnergyBtn, 12, 6);
        parametersUI.add(startEnergyBtn, 12, 7);
        parametersUI.add(worldWidthBtn, 12, 8);
        parametersUI.add(worldHeightBtn, 12, 9);
        parametersUI.add(quitBtn, 10, 11);

        // onClick method for changing walk energy cost
        walkEnergyCostBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.WALK_ENERGY_COST = Integer.parseInt(walkEnergyCostTf.getText());
    			} catch(Exception e) {
    				errorHandler(walkEnergyCostTf.getText());
    			}
            }
        });
        
        // onClick method for changing walk energy cost
        runEnergyCostBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.RUN_ENERGY_COST = Integer.parseInt(runEnergyCostTf.getText());
    			} catch(Exception e) {
    				errorHandler(runEnergyCostTf.getText());
    			}
            }
        });
        
        // onClick method for changing rest energy cost
        restEnergyCostBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.REST_ENERGY_COST = Integer.parseInt(restEnergyCostTf.getText());
    			} catch(Exception e) {
    				errorHandler(restEnergyCostTf.getText());
    			}
            }
        });
        
        // onClick method for changing look energy cost
        lookEnergyCostBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.LOOK_ENERGY_COST = Integer.parseInt(lookEnergyCostTf.getText());
    			} catch(Exception e) {
    				errorHandler(lookEnergyCostTf.getText());
    			}
            }
        });
        
        // onClick method for changing min reproduce energy cost
        minReproduceEnergyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.MIN_REPRODUCE_ENERGY = Integer.parseInt(minReproduceEnergyTf.getText());
    			} catch(Exception e) {
    				errorHandler(minReproduceEnergyTf.getText());
    			}
            }
        });
        
        // onClick method for changing clover refresh count
        refreshCloverCountBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.REFRESH_CLOVER_COUNT = Integer.parseInt(refreshCloverCountTf.getText());
    			} catch(Exception e) {
    				errorHandler(refreshCloverCountTf.getText());
    			}
            }
        });
        
        // onClick method for changing photosynthesis energy
        photosynthesisEnergyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.PHOTOSYNTHESIS_ENERGY_AMOUNT = Integer.parseInt(photosynthesisEnergyTf.getText());
    			} catch(Exception e) {
    				errorHandler(photosynthesisEnergyTf.getText());
    			}
            }
        });
        
        // onClick method for changing clover refresh count
        startEnergyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.START_ENERGY = Integer.parseInt(startEnergyTf.getText());
    			} catch(Exception e) {
    				errorHandler(startEnergyTf.getText());
    			}
            }
        });
        
        // onClick method for changing world width
        worldWidthBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.WORLD_WIDTH = Integer.parseInt(worldWidthTf.getText());
    				Main.cellWidth = Main.gridWidthPixels/Params.WORLD_WIDTH;
    				Main.tabs.setPadding(new Insets(0, 0, 0, Params.WORLD_WIDTH));
    				Critter.displayWorld(Main.display);
    			} catch(Exception e) {
    				errorHandler(worldWidthTf.getText());
    			}
            }
        });
        
        // onClick method for changing world height
        worldHeightBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
    				Params.WORLD_HEIGHT = Integer.parseInt(worldHeightTf.getText());
    				Main.cellHeight = Main.gridHeightPixels/Params.WORLD_HEIGHT;
    				Critter.displayWorld(Main.display);
    			} catch(Exception e) {
    				errorHandler(worldHeightTf.getText());
    			}
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
	
	public GridPane getParametersUI() {
		return parametersUI;
	}
}
