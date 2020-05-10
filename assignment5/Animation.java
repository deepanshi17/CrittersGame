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

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import assignment5.Main;

public class Animation {

	GridPane animateUI;
	public int stepCount;
	boolean playing = false;
	AnimationTimer timer;

	public Animation() {
		animateUI = new GridPane();

        // Text for spacing
        Text spaceUtility = new Text("");
		Text field = new Text("  step(s) per frame");
		TextField steps = new TextField();

		Button quitBtn = new Button("Quit");
		Button start = new Button();
		start.setText("Start animation");
		
		Button pause = new Button();
		pause.setText("Pause animation");
		pause.setDisable(true);



		animateUI.add(steps, 10, 6);
		animateUI.add(field, 11, 6);
		animateUI.add(start, 10, 7);
		animateUI.add(pause, 15, 7);
		animateUI.add(spaceUtility, 10, 8);
		animateUI.add(quitBtn, 10, 9);

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for(Tab t : Main.tabs.getTabs()) {
					if(!((t.getText().equals("Animation")) || (t.getText().equals("Run Stats")))) {
						t.setDisable(true);
					}
				}
				if(steps.getText().isEmpty()) stepCount = 1;
				else stepCount = Integer.parseInt(steps.getText());
				if(playing == true) {
					timer.stop();
					playing = false;
				}
				pause.setDisable(false);
				playing = true;
				timer = new Timer();
				timer.start();
			}
		});
		
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				stepCount = 0;
				timer.stop();
				for(Tab t : Main.tabs.getTabs()) {
					t.setDisable(false);
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

	public GridPane getAnimationUI() {
		return animateUI;
	}

	private class Timer extends AnimationTimer {
		private long last;
		public Timer() {
			last = 0;
		}

		@Override
		public void handle(long now) {
			if (now > (last + 1000000000)) {
				Critter.displayWorld(Main.display);
				for (int i = 0; i < stepCount; i++) {
					Critter.worldTimeStep();
				}

				StatsUI.updateStats();

				last = now;
			}
		}
	}

}
