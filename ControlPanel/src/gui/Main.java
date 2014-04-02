/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import persistentie.MonsterMapper;
import persistentie.TreasureMapper;

/**
 *
 * @author Robin De Haes
 */
public class Main extends Application {

    private static MonsterMapper monsterMapper = new MonsterMapper();
    private static TreasureMapper treasureMapper = new TreasureMapper();
    private static Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private static Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private static Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private static Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
    private static Image logo = new Image(Main.class.getResourceAsStream("/images/icons/Logo_icon.png"));

    /**
     * Launches the GUI
     *
     * @param stage the main stage in which the GUI will be displayed
     */
    @Override
    public void start(Stage stage) {
        DomeinController controller = new DomeinController();
        MainPanel root = new MainPanel(controller);

        DetailMonster detailMonster = new DetailMonster(controller);
        MonsterPanel monsterPanel = new MonsterPanel(controller, detailMonster, root);

        DetailTreasure detailTreasure = new DetailTreasure(controller);
        TreasurePanel treasurePanel = new TreasurePanel(controller, detailTreasure, root);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Control Panel");
        stage.getIcons().add(logo);
        stage.show();
    }

    /**
     * The Main() method is ignored in correctly deployed JavaFX application.
     * Main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores Main().
     *
     * @param args the command line arguments
     */
    public static
            void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
