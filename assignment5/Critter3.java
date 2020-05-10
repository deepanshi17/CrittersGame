package assignment5;
/*
 * CRITTERS Critter3.java
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

import java.util.List;


/**
 * Powerful Sphinx
 * Has ability to fly three spaces at a time instead of walking or running.
 * It carefully assesses the situation (rolling a dice) before deciding whether to fight or fly away. 
 */
public class Critter3 extends Critter {

    @Override
    public void doTimeStep() {
    }

    @Override
    public boolean fight(String opponent) {
        if (!opponent.equals("@")) {
        	int decision = Critter.getRandomInt(1);
        	if(decision == 1) {
        		return true;
        	} 
        	else {
        		fly(Critter.getRandomInt(7));
        		return false;
        	}
        }
        else return true;
    }

    @Override
    public String toString() {
        return "3";
    }

    public void test(List<Critter> l) {
    }
    
    @Override
	public CritterShape viewShape() {
		return Critter.CritterShape.DIAMOND;
	}
	
	@Override
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.CADETBLUE;
    }
	
	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.WHITE;
    }
}
