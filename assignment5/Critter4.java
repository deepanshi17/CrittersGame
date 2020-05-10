package assignment5;
/*
 * CRITTERS Critter4.java
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
import java.util.List;


/**
 * Hydra
 * Spawns a new head (reproduces) every step until it runs out of energy.
 * Always picks a fight. 
 * Reproduces like crazy into free spots only (if no free spots then it
 * can't reproduce) but runs out of energy and dies quickly.
 */

public class Critter4 extends Critter {

    @Override
    public void doTimeStep() {
    	String view = look(0, false);
    	if(view == null) {
    		walk(0);
    	}
    	ArrayList<Integer> openSpaces = emptySpaces(1, true);  // true for reproducing
    	if(openSpaces.size() != 0) {
    		Critter4 spawn = new Critter4();
    		int index = Critter.getRandomInt(openSpaces.size());
    		reproduce(spawn, openSpaces.get(index));
    	}
    }

    @Override
    public boolean fight(String opponent) {
        return true;
    }

    @Override
    public String toString() {
        return "4";
    }

    public void test(List<Critter> l) {
    }
    
    @Override
	public CritterShape viewShape() {
		return Critter.CritterShape.STAR;
	}
	
	@Override
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.YELLOW;
    }
	
	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.rgb(255, 44, 6);
    }
}
