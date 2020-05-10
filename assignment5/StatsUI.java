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

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StatsUI{
	public static GridPane statsUI;
	public static ListView<CheckBox> listView;
	public static ListView<Label> labelView;
	public static ListView<Node> pair;

	

	public StatsUI() {
		statsUI = new GridPane();
		
		Label statsTitle = new Label("Run Stats");
		statsUI.add(statsTitle, 0, 0);
		
		List<Class<?>> classList = getClassList();
		
		listView = new ListView<CheckBox>();
		labelView = new ListView<Label>();
		
		for (Class<?> c : classList) {
			CheckBox critter = new CheckBox();
			ListView<Node> pair = new ListView<>();
			critter.setText(c.getSimpleName());
			listView.getItems().add(critter);
			pair.getItems().add(critter);
			Label stats = new Label();
//			if (c.getSimpleName().equals("Goblin")) {
//				try {
//					stats.setText(Goblin.runStats(Goblin.getInstances(c.getSimpleName())));
//				} catch (InvalidCritterException e) {
//					e.printStackTrace();
//				}
//			} else {
			try {
				stats.setText(Critter.runStats(Critter.getInstances(c.getSimpleName())));
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
//			}
//			stats.setStyle("-fx-font-size: 50%; ");
			stats.setVisible(false);
			labelView.getItems().add(stats);
			critter.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if(critter.isSelected()) stats.setVisible(true);
					else stats.setVisible(false);
				}
			});
		}
		
        // Text for spacing
        Text spaceUtility = new Text("");
		Button quitBtn = new Button("Quit");
		// onClick method for quitting program
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
		
//		Hbox hbox = new Hbox()
		statsUI.add(listView, 0, 10);
		statsUI.add(labelView, 10, 10);
		statsUI.add(spaceUtility, 0, 11);
		statsUI.add(quitBtn, 0, 12);
		updateStats();
	}
		
		
//		
//		lv.setCellFactory(CheckBoxListCell.forListView(new Callback<Class<?>, ObservableValue<Boolean>>() {
//            @Override
//            public ObservableValue<Boolean> call(Class<?> c) {
//                BooleanProperty observable = new SimpleBooleanProperty();
//                observable.addListener((obs, wasSelected, isNowSelected) -> 
//                    System.out.println("Check box for "+c.toString()+" changed from "+wasSelected+" to "+isNowSelected)
//                );
//                return observable ;
//            }
//        }));
//		
//		statsUI.add(lv, 10, 10);
//	}
		
//		for (Critter c : Critter.population) {
//			try {
//				Class<?>[] parameters = { List.class };
//				List<Critter> critters = Critter.getInstances(c.toString());
//				Method runStats = Class.forName(Main.myPackage + "." + c.toString()).getMethod("runStats", parameters);
//				runStats.invoke(null, critters);
//			} catch (InvalidCritterException | ClassNotFoundException | NoSuchMethodException | SecurityException
//					| InvocationTargetException | IllegalArgumentException | IllegalAccessException
//					| NoClassDefFoundError e) {
//				System.out.println(e);
//			}
//		}
//
//	}

	public GridPane getStatsUI() {
		return statsUI;
	}
	
	static void updateStats() {
		List<Critter> population = null;
		for(int i = 0; i < labelView.getItems().size(); i++) {
			try {
				population = Critter.getInstances(listView.getItems().get(i).getText());
			} catch (InvalidCritterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (listView.getItems().size() > 0 && (listView.getItems().get(i).getText()).equals("Goblin")) {
				labelView.getItems().get(i).setText(Goblin.runStats(population));
			} else
				labelView.getItems().get(i).setText(Critter.runStats(population));
		}
	}

	static List<Class<?>> getClassList() {
		List<Class<?>> classList = new ArrayList<>();
		String[] paths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
		for (String path : paths) {
			if (path.endsWith(".jar")) {
				try {
					File jar = new File(path);
					JarInputStream is = new JarInputStream(new FileInputStream(jar));
					JarEntry next = is.getNextJarEntry();
					while (next != null) {
						if (next.getName().endsWith(".class") && next.getName().contains("assignment5")) {
							String thisPath = next.getName().substring(0, next.getName().length() - 6);
							thisPath = thisPath.replaceAll("[\\|/]", ".");
							Class<?> isClass = Class.forName(thisPath);
							if (!isClass.getName().equals("assignment5.Critter$TestCritter")
									&& isClass.getSuperclass().getName().equals(Critter.class.getName())
									|| isClass.getSuperclass().getName().equals(Critter.TestCritter.class.getName())) {
								classList.add(isClass);
							}
						}
						next = is.getNextJarEntry();
					}
					is.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				try {
					File base = new File(path + File.separatorChar + "assignment5");
					for (File f : base.listFiles()) {
						if (f.getName().endsWith(".class")) {
							String name = f.getName().substring(0, f.getName().length() - 6);
							Class<?> isClass = Class.forName("assignment5." + name);
							if (!isClass.getName().equals("assignment5.Critter$TestCritter")
									&& isClass.getSuperclass().getName().equals(Critter.class.getName())
									|| isClass.getSuperclass().getName().equals(Critter.TestCritter.class.getName())) {
								classList.add(isClass);
							}
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return classList;
	}
}
