package assignment5;
/*
 * CRITTERS Critter1.java
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

/**
 * Dumb Troll
 * Walks along a horizontal line to the right only and only fights if it doesn't see a Critters around it 
 * in any random direction
 */
public class Critter1 extends Critter {

	@Override
    public String toString() {
        return "1";
    }
	
    @Override
    public void doTimeStep() {
    	walk(0);
    }

    @Override
    public boolean fight(String opponent) {
    	String view = look(Critter.getRandomInt(8), false);
    	if (view == null) {
    		return true;
    	}
        return false;
    }

	@Override
	public CritterShape viewShape() {
		return Critter.CritterShape.TRIANGLE;
	}
	
	@Override
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.WHITE;
    }
	
	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.RED;
    }
    
}
