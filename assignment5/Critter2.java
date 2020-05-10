package assignment5;
/*
 * CRITTERS Critter2.java
 * EE422C Project 5 submission by
 * 
 * Deepanshi Sharma
 * ds52384
 * 16295
 * Jianchen Gu
 * jg68927
 * 16295
 * Slip days used: 0
 * Spring 2020
 */

import java.util.ArrayList;


/**
 * Cowardly Elf
 * Randomly picks a direction to walk to from a set of empty adjacent spaces
 * Will stay put if there are no spaces to walk to
 * Also always runs when encountering other critters, unless there are no spaces to run to
 */
public class Critter2 extends Critter {
	
	@Override
    public String toString() {
        return "2";
    }

    @Override
    public void doTimeStep() {
    	// tries to move to an empty space ONLY
    	// otherwise, it will stay put but still spends walking energy for attempting
    	ArrayList<Integer> directions = emptySpaces(1, false);  // false for not reproducing
    	if (directions.size() == 0) {
    		walk(-1); // doesn't actually walk, just decrements energy
    	}
    	else {
    		int choice = Critter.getRandomInt(directions.size());
    		walk(directions.get(choice));
    	}
    }

    @Override
    public boolean fight(String opponent) {
    	// make sure it doesn't turn to an occupied spot
    	ArrayList<Integer> directions = emptySpaces(2, false);  // false for not reproducing
    	if (directions.size() == 0) {
    		run(-1); // doesn't actually run, just decrements energy
    		// if the Elf Critter can't escape (from already moving prior or no available space),
    		// then it will fight
    		return true;
    	}
    	else {
    		int choice = Critter.getRandomInt(directions.size());
    		run(directions.get(choice));
            return false;
    	}
    }

    @Override
	public CritterShape viewShape() {
		return Critter.CritterShape.STAR;
	}
	
    @Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.AQUA;
    }
    
	@Override
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.PURPLE;
    }

}
