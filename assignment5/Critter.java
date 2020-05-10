/* CRITTERS Critter.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import assignment5.Critter;
import assignment5.InvalidCritterException;
import assignment5.Params;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*
 * See the PDF for descriptions of the methods and fields in this
 * class.
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    /* START --- NEW FOR PROJECT 5 */
    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR
    }

    /* the default color is white, which I hope makes critters invisible by default
     * If you change the background color of your View component, then update the default
     * color to be the same as you background
     *
     * critters must override at least one of the following three methods, it is not
     * proper for critters to remain invisible in the view
     *
     * If a critter only overrides the outline color, then it will look like a non-filled
     * shape, at least, that's the intent. You can edit these default methods however you
     * need to, but please preserve that intent as you implement them.
     */
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.WHITE;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    protected final String look(int direction, boolean steps) {
    	// decrement look energy
    	energy -= Params.LOOK_ENERGY_COST;
    	
    	// hash current critters, using their coordinates as a key 
    	// and character representation as a value
    	Map<String, String> critterMap = new HashMap<String, String>();
    	// populate map
    	for (Critter c : population) {
    		String key = String.valueOf(c.x_coord_prev) + "," + String.valueOf(c.y_coord_prev);
    		critterMap.put(key, c.toString());
    	}
    	
    	int x_coord_copy = x_coord_prev;
    	int y_coord_copy = y_coord_prev;
    	int velocity = 0;
    	if (steps) {
    		velocity = 2;
    	}
    	else {
    		velocity = 1;
    	}
    	
    	switch(direction) {
	    	case 0:
	    		x_coord_copy += velocity;
	    		break;
	    		
	    	case 1:
	    		x_coord_copy += velocity;
	    		y_coord_copy -= velocity;
	    		break;
	    		
	    	case 2:
	    		y_coord_copy -= velocity;
	    		break;
	    		
	    	case 3:
	    		x_coord_copy -= velocity;
	    		y_coord_copy -= velocity;
				break;
				
	    	case 4:
	    		x_coord_copy -= velocity;
				break;
				
	    	case 5:
	    		x_coord_copy -= velocity;
	    		y_coord_copy += velocity;
				break;
				
	    	case 6:
	    		y_coord_copy += velocity;
				break;
				
	    	case 7:
	    		x_coord_copy += velocity;
	    		y_coord_copy += velocity;
				break;
		}
		
		// correct coordinates based off wrap-around world
		if (x_coord_copy > (Params.WORLD_WIDTH - 1)) {
			x_coord_copy %= Params.WORLD_WIDTH;
		}
		else if (x_coord_copy < 0) {
			x_coord_copy += Params.WORLD_WIDTH;
		}
		
		if (y_coord_copy > (Params.WORLD_HEIGHT - 1)) {
			y_coord_copy %= Params.WORLD_HEIGHT;
		}
		else if (y_coord_copy < 0) {
			y_coord_copy += Params.WORLD_HEIGHT;
		}
    	
    	// check if the decided spot to look at contains a Critter or not
    	if (critterMap.containsKey(String.valueOf(x_coord_copy) + "," + String.valueOf(y_coord_copy))) {
    		return critterMap.get(String.valueOf(x_coord_copy) + "," + String.valueOf(y_coord_copy));
    	}
    	
        return null;
    }

    public static String runStats(List<Critter> critters) {
    	 String statsStr = "  " + critters.size() + " critters as follows -- ";
         Map<String, Integer> critter_count = new HashMap<String, Integer>();
         for (Critter crit : critters) {
             String crit_string = crit.toString();
             critter_count.put(crit_string,
                     critter_count.getOrDefault(crit_string, 0) + 1);
         }
         String prefix = "";
         for (String s : critter_count.keySet()) {
        	 statsStr = statsStr.concat(prefix + s + ":" + critter_count.get(s));
             prefix = ", ";
         }
         return statsStr;
    }


    public static void displayWorld(Object pane) {
    	
    	// write these critters to the GridPane
    	// first clear them
    	((GridPane)pane).getChildren().clear();
    	
    	int height = Params.WORLD_HEIGHT; 
        int width = Params.WORLD_WIDTH;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	if (i < height/4) {
            		if (i == height/4-1 && j%2 == 0) {
            			Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                        s.setFill(Color.rgb(255, 237, 180));
                        s.setStroke(Color.BLACK);
                        Main.display.add(s, j, i);
            		}
            		else {
            			Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                        s.setFill(Color.rgb(42, 182, 251));
                        s.setStroke(Color.BLACK);
                        Main.display.add(s, j, i);
            		}
            	}
            	else if (i <= height/4+1) {
            		Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                    s.setFill(Color.rgb(255, 237, 180));
                    s.setStroke(Color.BLACK);
                    Main.display.add(s, j, i);
            	}
            	else if (i < height/2) {
            		if (i == height/4+2 && j%2 == 1) {
            			Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                        s.setFill(Color.rgb(255, 237, 180));
                        s.setStroke(Color.BLACK);
                        Main.display.add(s, j, i);
            		}
            		else {
            			Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                        s.setFill(Color.rgb(251, 220, 124));
                        s.setStroke(Color.BLACK);
                        Main.display.add(s, j, i);
            		}
            	}
            	else if (i < height*3/4){
            		if (i == (height*3/4-height/2)/2 + height/2) {
            			Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                        s.setFill(Color.rgb(244, 223, 12));
                        s.setStroke(Color.BLACK);
                        Main.display.add(s, j, i);
            		}
            		else {
            			Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                        s.setFill(Color.rgb(82, 83, 88));
                        s.setStroke(Color.BLACK);
                        Main.display.add(s, j, i);
            		}
            	}
            	else {
            		Shape s = new Rectangle(Main.cellWidth,Main.cellHeight);
                    s.setFill(Color.rgb(66, 131, 5));
                    s.setStroke(Color.BLACK);
                    Main.display.add(s, j, i);
            	}
            }
        }

    	// hash current critters, using their coordinates as a key 
    	// and character representation as a value
    	Map<String, Critter> critterMap = new HashMap<String, Critter>();
    	// populate map
    	for (Critter c : population) {
    		String key = String.valueOf(c.x_coord) + "," + String.valueOf(c.y_coord);
    		critterMap.put(key, c);
    	}
    	
        for (int i = 0; i < Params.WORLD_HEIGHT; i++) {
        	for (int j = 0; j < Params.WORLD_WIDTH; j++) {
    			// using a hash map for efficient checking if a critter is currently
    			// at that location
    			// create a key using current coordinates (x, y)
    			String key = String.valueOf(j) + "," + String.valueOf(i);
    			if (critterMap.containsKey(key)) {
    				Shape s = getIcon(critterMap.get(key));
    				((GridPane)pane).add(s, j, i); 
    			}
        	}
        }
    }
    
    /**
     * Helper method to return the shape
     * 
     * @param critter
     * @return
     */
    private static Shape getIcon(Critter critter) {
		Shape s = null;
		Polygon p = null;
		CritterShape crittershape = critter.viewShape();
		Color fillColor = critter.viewColor();
		Color outlineColor = critter.viewOutlineColor();
		
		switch(crittershape) {
			case CIRCLE: 
				s = new Circle((Main.cellWidth-1)/2);
				s.setFill(fillColor); 
				s.setStroke(outlineColor);
				return s;
				
			case SQUARE: 
				s = new Rectangle(Main.cellWidth,Main.cellHeight);
				s.setFill(fillColor);
				s.setStroke(outlineColor);
				return s;
				
		    case TRIANGLE: 
		    	p = new Polygon();
		    	p.getPoints().addAll(new Double[]{
			            1.0, ((double)Main.cellHeight)-2,
			            ((double)Main.cellWidth)-1, ((double)Main.cellHeight)-2,
			            ((double)Main.cellWidth)/2, 0.0 });
					p.setFill(fillColor);
					p.setStroke(outlineColor);
				return p;
				
			case DIAMOND: 
				p = new Polygon();
				p.getPoints().addAll(new Double[]{
		        		0.0, (((double)Main.cellHeight)-1)/2,
		        		(((double)Main.cellWidth)-1.5)/2, ((double)Main.cellHeight)-2,
	 		           ((double)Main.cellWidth)-2, (((double)Main.cellHeight)-1)/2,
	 		          (((double)Main.cellWidth)-1.5)/2, 0.0 });
		    
				p.setFill(fillColor);
				p.setStroke(outlineColor);
				return p;
				
			case STAR: 
				p = new Polygon();
		        p.getPoints().addAll(new Double[]{
		        		((double)Main.cellWidth-1.0)*4.0/24.0, ((double)Main.cellHeight-1.0)*1.0/24.0,
		        		((double)Main.cellWidth-1.0)*7.0/24.0, ((double)Main.cellHeight-1.0)*9.5/24.0,
		        		((double)Main.cellWidth-1.0)*1.0/24.0, ((double)Main.cellHeight-1.0)*16.0/24.0,
		        		((double)Main.cellWidth-1.0)*8.0/24.0, ((double)Main.cellHeight-1.0)*16.0/24.0,
		        		((double)Main.cellWidth-1.0)*12.0/24.0, ((double)Main.cellHeight-1.0)*23.0/24.0,
		        		((double)Main.cellWidth-1.0)*16.0/24.0, ((double)Main.cellHeight-1.0)*16.0/24.0, 
		        		((double)Main.cellWidth-1.0)*23.0/24.0, ((double)Main.cellHeight-1.0)*16.0/24.0,
		        		((double)Main.cellWidth-1.0)*17.0/24.0, ((double)Main.cellHeight-1.0)*9.5/24.0,
		        		((double)Main.cellWidth-1.0)*21.0/24.0, ((double)Main.cellHeight-1.0)*1.0/24.0,
		        		((double)Main.cellWidth-1.0)*12.0/24.0, ((double)Main.cellHeight-1.0)*5.5/24.0});
		    
				p.setFill(fillColor);
				p.setStroke(outlineColor);
				return p;
			}
		return null;
	}

	/* END --- NEW FOR PROJECT 5
			rest is unchanged from Project 4 */

    private int energy = 0;
    
    // boolean to track if Critter has moved in the current timestep
    private boolean hasMoved = false;

    private int x_coord;
    private int y_coord;
    
    // ints to track Critters' positions before moving, to be used by look() 
    // need to respect the simultaneous movement
    private int x_coord_prev;
    private int y_coord_prev;

    static List<Critter> population = new ArrayList<Critter>();
//    private static List<List<Critter>> population2 = new ArrayList<List<Critter>>(); // can be used for matrix implementation
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {
    	Critter new_critter;
    	
    	try {
    		new_critter = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
    	}
    	catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
    		throw new InvalidCritterException(critter_class_name);
    	}
    	
    	new_critter.x_coord = getRandomInt(Params.WORLD_WIDTH);
    	new_critter.y_coord = getRandomInt(Params.WORLD_HEIGHT);
    	new_critter.energy = Params.START_ENERGY;
    	population.add(new_critter);
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *                           Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
    	ArrayList<Critter> instances = new ArrayList<>();
    	try {
    		for(Critter c : population) {
    			if(Class.forName(myPackage + "." + critter_class_name).newInstance().getClass().isInstance(c)) {
    				instances.add(c);
    			}
    		}
    	}
    	catch(ClassNotFoundException | IllegalAccessException | InstantiationException e) {
    		throw new InvalidCritterException(critter_class_name);
    	}
        return instances;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
    	population.clear();
        babies.clear();
        displayWorld(Main.display);
    }

    public static void worldTimeStep() {
    	// update every Critter's hasMoved boolean to false since at the beginning of
    	// every world time step, each Critter has the chance to move once
    	// also update previous x,y coordinates with the most up to date
    	// to be used for simultaneous movement in the doTimeStep() part
    	for (Critter c : population) {
    		c.hasMoved = false;
    		c.x_coord_prev = c.x_coord;
    		c.y_coord_prev = c.y_coord;
    	}

    	
    	for(int i = 0; i < population.size(); i++) {
    		// lets each Critter complete their own time step (ex. walking, running, staying put, etc)
    		population.get(i).doTimeStep();
    		// remove Critter if it's dead (such as from running out of energy when moving)
    		if(population.get(i).energy <= 0) {
    			population.remove(i);
    			i--;
    		}
    	}
    	
    	// resolve encounters
		// winner gains energy, loser is removed
    	for (int i = 0; i < population.size(); i++) {
    		for (int j = 0; j < population.size(); j++) {
    			// same Critter
    			if (i == j) {
    				continue;
    			}
    			// encounter
    			if (population.get(i).x_coord == population.get(j).x_coord && 
    					population.get(i).y_coord == population.get(j).y_coord) {
    				// get the result of the encounter
    				int result = doEncounters(population.get(i),population.get(j));

    				// adjust indices as needed based off result
    				if (result == 1) {
    					// Critter a (i) killed Critter b (j)
    					j--;
    					continue;
    				}
    				else if (result == 2) {
    					// Critter b (j) killed Critter a (i)
    					i--;
    					break;
    				}
    				// if result == 0, that means no fight took place so we can move on with the loop normally
    			}
    			
    		}
    	}
    	
    	// subtract required resting energy cost for each Critter
    	for(int i = 0; i < population.size(); i++) {
    		population.get(i).energy -= Params.REST_ENERGY_COST;
    		// remove Critter if it's dead
    		if(population.get(i).energy <= 0) {
    			population.remove(i);
    			i--;
    		}  		
    	}
    	
		// create a new Clover
    	for (int i = 0; i < Params.REFRESH_CLOVER_COUNT; i++) {
    		try {
    			createCritter("Clover");
    		} catch (InvalidCritterException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	// add the babies to current population
    	population.addAll(babies);
		babies.clear();
    }
    
    /**
     * Resolves 4 different kinds of results from encounters and tells us outcomes needed 
     * 
     * @param a is the first Critter in the encounter
     * @param b is the second Critter in the encounter
     * @return 0 if no fight occurs, 1 if Critter A wins, 2 if Critter B wins
     */
    public static int doEncounters(Critter a, Critter b) {
    	// Critters can NOT run away to another occupied space
    	// handle encounter between 2 Critters
    	
		// flags to check if Critter is to fight or run
    	// if true, that means the Critter wants to fight
    	// 		a) if the Critter actually wants to fight
    	// 		b) if the Critter fails to run away
    	// if false, that means the Critter will die if it's still in the same location
    	// 		a) if the Critter runs away successfully - diff location
    	//  	b) if the Critter doesn't want to fight - same location (this case is Clovers ONLY)
    	// 				^ only Clovers will be in this situation bc other Critters will return true for fight
    	// 				  if they fail to run away
    	//
    	//
		boolean aFight = a.fight(b.toString());
		boolean bFight = b.fight(a.toString());
		
		if (aFight == true && bFight == false) {
			// critter A wants to fight but B doesn't - events happen based off if B runs away or not
			if (a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
				// b is a Clover and will die
				a.energy += (int) b.energy/2;
				population.remove(b);
				return 1;
			}
			else {
				// critter B ran away successfully
				return 0;
			}
		}
		else if (aFight == false && bFight == true) {
			// critter B wants to fight but A doesn't - events happen based off if A runs away or not
			if (a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
				// a is a Clover and will die
				b.energy += (int) a.energy/2;
				population.remove(a);
				return 2;
			}
			else {
				// critter A ran away successfully
				return 0;
			}
		}
		else if (aFight == true && bFight == true) {
			// both want to fight
			return fightSequence(a,b);
		}
		else {
			if (a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
				// forced to fight if both don't want to fight, but still remain in the same spot 
				return fightSequence(a,b);
			}
			else {
				// both critters ran away successfully
				return 0;
			}
		}
		
    }
    
    /**
     * Helper function for doEncounters() to resolve events where 2 Critters must fight
     * 
     * @param a is the first Critter in the fight
     * @param b is the second Critter in the fight
     * @return 1 if Critter A wins, 2 if Critter B wins
     */
    private static int fightSequence(Critter a, Critter b) {
    	int aRoll = getRandomInt(a.energy + 1);
		int bRoll = getRandomInt(b.energy + 1);
		
		if (aRoll > bRoll) {
			// Critter a killed Critter b
			a.energy += (int) b.energy/2;
			population.remove(b);
			return 1;
		}
		else if (aRoll == bRoll) {
			// Critter a wins by default if there's a tie
			a.energy += (int) b.energy/2;
			population.remove(b);
			return 1;
		}
		else {
			// Critter b killed Critter a
			b.energy += (int) a.energy/2;
			population.remove(a);
			return 2;
		}
    }

    public abstract void doTimeStep();

    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }

    protected final void walk(int direction) {
    	// only allows movement once per timestep
    	if (hasMoved == true) {
    		energy -= Params.WALK_ENERGY_COST;
    		return;
    	}
    	// all directions are occupied
    	if (direction == -1) {
    		hasMoved = true;
        	energy -= Params.WALK_ENERGY_COST;
    		return;
    	}
    	// update move flag for the Critter if first time moving this timestep
    	hasMoved = true;
    	energy -= Params.WALK_ENERGY_COST;
    	move(direction, 1);
    }

    protected final void run(int direction) {
    	// only allows movement once per timestep
    	if (hasMoved == true) {
    		energy -= Params.RUN_ENERGY_COST;
    		return;
    	}
    	// all directions are occupied
    	if (direction == -1) {
    		hasMoved = true;
        	energy -= Params.RUN_ENERGY_COST;
        	return;
    	}
    	// update move flag for the Critter if first time moving this timestep
    	hasMoved = true;
    	energy -= Params.RUN_ENERGY_COST;
    	move(direction, 2);
    }
    
    /**
     * 
     * @param direction ONLY expected to be -1 if the Critter invokes fly without a free space to fly to
     * 			^ this is meant to simply subtract the flying energy for attempting to fly
     */
    protected final void fly(int direction) {
    	// only allows movement once per timestep
    	if (hasMoved == true) {
    		energy -= (Params.RUN_ENERGY_COST + 1);
    		return;
    	}
    	// all directions are occupied
    	if (direction == -1) {
    		hasMoved = true;
        	energy -= (Params.RUN_ENERGY_COST + 1);
        	return;
    	}
    	// update move flag for the Critter if first time moving this timestep
    	hasMoved = true;
    	energy -= (Params.RUN_ENERGY_COST + 1);
    	move(direction, 3);
    }
    
    /**
     * finds all empty spaces around a Critter
     * 
     * @param velocity is the how many spaces a Critter plans to move
     * @param reproduce is if the Critter wants to reproduce or not
     * @return arraylist of possible direction, or an empty arraylist if no available empty spots
     */
    protected final ArrayList<Integer> emptySpaces(int velocity, boolean reproduce) {
    	// ArrayList of possible directions
    	ArrayList<Integer> directions = new ArrayList<Integer>();
    	
    	// check if current critter is reproducing
    	// if reproducing, then no need to check hasMovedFlag
    	if (!reproduce) {
    		// only allows movement once per timestep
        	if (hasMoved) {
        		return directions;
        	}
    	}
    	
    	// hash current critters, using their coordinates as a key 
    	// and character representation as a value
    	Map<String, String> critterMap = new HashMap<String, String>();
    	// populate map
    	for (Critter c : population) {
    		String key = String.valueOf(c.x_coord) + "," + String.valueOf(c.y_coord);
    		critterMap.put(key, c.toString());
    	}
    	
    	// potential coordinates
    	int x_coord_right = x_coord + velocity;
    	int x_coord_left = x_coord - velocity;
    	int y_coord_up = y_coord - velocity;
    	int y_coord_down = y_coord + velocity;
    	
    	// correct coordinates based off wrap-around world
    	if (x_coord_right > (Params.WORLD_WIDTH - 1)) {
    		x_coord_right %= Params.WORLD_WIDTH;
    	}
    	if (x_coord_left < 0) {
    		x_coord_left += Params.WORLD_WIDTH;
    	}
    	if (y_coord_up < 0) {
    		y_coord_up += Params.WORLD_HEIGHT;
    	}
    	if (y_coord_down > (Params.WORLD_HEIGHT - 1)) {
    		y_coord_down %= Params.WORLD_HEIGHT;
    	}
    	
    	// check which adjacent directions are free
    	if (!critterMap.containsKey(String.valueOf(x_coord_right) + "," + String.valueOf(y_coord))) {
    		directions.add(0);
    	}
    	if (!critterMap.containsKey(String.valueOf(x_coord_right) + "," + String.valueOf(y_coord_up))) {
    		directions.add(1);
    	}
    	if (!critterMap.containsKey(String.valueOf(x_coord) + "," + String.valueOf(y_coord_up))) {
    		directions.add(2);
    	}
    	if (!critterMap.containsKey(String.valueOf(x_coord_left) + "," + String.valueOf(y_coord_up))) {
    		directions.add(3);
    	}
    	if (!critterMap.containsKey(String.valueOf(x_coord_left) + "," + String.valueOf(y_coord))) {
    		directions.add(4);
    	}
    	if (!critterMap.containsKey(String.valueOf(x_coord_left) + "," + String.valueOf(y_coord_down))) {
    		directions.add(5);
    	}
    	if (!critterMap.containsKey(String.valueOf(x_coord) + "," + String.valueOf(y_coord_down))) {
    		directions.add(6);
    	}
    	if (!critterMap.containsKey(String.valueOf(x_coord_right) + "," + String.valueOf(y_coord_down))) {
    		directions.add(7);
    	}

    	return directions;
    }

    protected final void reproduce(Critter offspring, int direction) {
    	// Confirm that the “parent” critter has energy at least as large asParams.MIN_REPRODUCE_ENERGY else return
    	if (energy < Params.MIN_REPRODUCE_ENERGY) {
			return;
		}
    	
    	// Assign child energy and reassign parent energy
    	offspring.energy = (int) energy/2;
    	energy = (int) Math.ceil(((double)energy)/2);
    	
    	// Assign position adjacent to parent in specified direction
    	offspring.x_coord = x_coord;
    	offspring.y_coord = y_coord;
    	offspring.move(direction, 1);
    	babies.add(offspring);
    }
    
    /**
     * Helper function for walk/run/reproduce to allow for world wrap-around in x and y axes
     * 
     * @param direction is the direction of travel
     * @param velocity is the number of spots a critter wants to move
     */
    private final void move(int direction, int velocity) {
    	
    	switch(direction) {
	    	case 0:
	    		x_coord += velocity;
	    		break;
	    		
	    	case 1:
	    		x_coord += velocity;
	    		y_coord -= velocity;
	    		break;
	    		
	    	case 2:
	    		y_coord -= velocity;
	    		break;
	    		
	    	case 3:
	    		x_coord -= velocity;
				y_coord -= velocity;
				break;
				
	    	case 4:
				x_coord -= velocity;
				break;
				
	    	case 5:
				x_coord -= velocity;
				y_coord += velocity;
				break;
				
	    	case 6:
				y_coord += velocity;
				break;
				
	    	case 7:
				x_coord += velocity;
				y_coord += velocity;
				break;
    	}
    	
    	// correct coordinates based off wrap-around world
    	if (x_coord > (Params.WORLD_WIDTH - 1)) {
    		x_coord %= Params.WORLD_WIDTH;
    	}
    	else if (x_coord < 0) {
    		x_coord += Params.WORLD_WIDTH;
    	}
    	
    	if (y_coord > (Params.WORLD_HEIGHT - 1)) {
    		y_coord %= Params.WORLD_HEIGHT;
    	}
    	else if (y_coord < 0) {
    		y_coord += Params.WORLD_HEIGHT;
    	}
    }

    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }
}
