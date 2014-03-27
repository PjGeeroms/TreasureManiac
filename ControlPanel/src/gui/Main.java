/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Monster;
import domein.Treasure;
import exceptions.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import persistentie.MonsterMapper;
import persistentie.TreasureMapper;

/**
 *
 * @author Robin De Haes
 */
//To do:
//1) Alle images op voorhand inladen en enkel view veranderen
//2) scrollbars
//3) layout wat verbeteren
public class Main extends Application {

    private static MonsterMapper monsterMapper = new MonsterMapper();
    private static TreasureMapper treasureMapper = new TreasureMapper();
    private static Label message;
    private static Label lblDetailName, lblDetailPower, lblDetailDefense, lblDetailSpeed, lblDetailAwareness;
    private static Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private static Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private static Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private static Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
     private static Image logo = new Image(Main.class.getResourceAsStream("/images/icons/Logo_icon.png"));
    
    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        DomeinController controller = new DomeinController();
        MainPanel root = new MainPanel(controller);

        DetailMonster detailMonster = new DetailMonster(controller);
        MonsterPanel monsterPanel = new MonsterPanel(controller, detailMonster, root, message);

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
