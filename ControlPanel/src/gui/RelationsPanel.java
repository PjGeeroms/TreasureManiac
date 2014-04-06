
package gui;

import domein.DomeinController;
import domein.Monster;
import domein.Treasure;
import exceptions.EmptyArgumentException;
import exceptions.InvalidIDException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RelationsPanel extends GridPane {
    
    //link naar controller en main
    private DomeinController controller = new DomeinController();
    private MainPanel main;
    
    //test of monster is geselecteerd
    private int monsterTest = -1;
    
    //alle grafische onderdelen
    private Label cblbl;
    private Label treasurelbl;
    private Label monsterTreasurelbl;
    private Label updatelbl;
    
    private ComboBox monstercb;
    
    private Button update;
    private Button remove;
    private Button add;
   
    private VBox monsterimg;
    private VBox treasureimg; 
    //private VBox treasures;
    
    //private VBox monsterTreasures;
    private ListView<GridPane> monsterListView;
    private ListView<GridPane> treasureListView;
    private ListView<GridPane> monsterTreasureListView;
    
    //alle lijsten
    private List<Monster> monsterList;
    private List<Treasure> treasureList;
    private List<Treasure> monsterTreasureList;
    
    //voert GUI uit
    public RelationsPanel(MainPanel main) {
        this.main = main;
        buildGui();
    }
    private Label Maaklbl(){
        Label lbl = new Label("hello");
        return lbl;
}
    
    private void buildGui(){
        
        //declareren van alle elementen in de GUI
            //combobox
        monstercb = new ComboBox();
            //de buttons
        add = new Button("Add\ntreasure");
        remove = new Button("Remove\ntreasure");
        update = new Button("Update");
            //de Labels
        updatelbl = new Label("When monster or treasure has\nbeen added, please update here:");
        cblbl = new Label("Choose monster:");
        treasurelbl = new Label("All treasures");
        monsterTreasurelbl = new Label("Connected treasures");
            //Lists declareren
        treasureListView = new ListView<>();
        monsterTreasureListView = new ListView<>();
        
        monsterList = new ArrayList<>();
        treasureList = new ArrayList<>();
        monsterTreasureList = new ArrayList<>();
            //Vbox declareren
        monsterimg = new VBox();
        
        //de grafische elementen in grid plaatsen
        GridPane pane1 = new GridPane();
        GridPane pane2 = new GridPane();
        
            //pane 1, monster, update
        pane1.add(update, 1, 0);
        pane1.add(updatelbl, 0, 0);
        pane1.add(cblbl, 0, 1);
        pane1.add(monsterimg, 2, 0, 1, 2);
        pane1.add(monstercb, 1, 1);
        
            //pane 2, treasure lists, add, remove
        pane2.add(add, 1, 1);
        pane2.add(remove, 1, 2);
        pane2.add(treasurelbl, 0, 0);
        pane2.add(monsterTreasurelbl, 2, 0);
        pane2.add(treasureListView, 0, 1, 1, 5);
        pane2.add(monsterTreasureListView, 2, 1, 1, 5);
            
        //pane 1 en 2 toeveogen aan hoofdpane
        add(pane1, 0, 0);
        add(pane2, 0, 1);
        
        //de GUI elementen aanpassen
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        
        pane2.setPadding(new Insets(10));
        pane2.setHgap(10);
        pane2.setVgap(10);
        
        pane1.setPadding(new Insets(10));
        pane1.setHgap(50);
        pane1.setVgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints();
        getColumnConstraints().addAll(col1, col2);
        
        //de lists
        treasureListView.setMinWidth(400);
        treasureListView.setMaxWidth(400);
        monsterTreasureListView.setMinWidth(400);
        monsterTreasureListView.setMaxWidth(400);
        
        //de buttons
        add.setMaxSize(80, 80);
        remove.setMaxSize(80, 80);
        update.setMaxSize(60, 60);
        
        add.setMinSize(80, 80);
        remove.setMinSize(80, 80);
        update.setMinSize(60, 60);

        //de combobox
        monstercb.setMaxWidth(200);
        monstercb.setMinWidth(200);        
        
        //opstartmethoden
        FillMonsterImageDefault();
        FillComboBox();
        FillTreasureList();
        
        
        //actionevents
            //de remove knop de link te verwijderen tussen schat en monster
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    int index = monsterTreasureListView.getSelectionModel().getSelectedIndex();
                        if(index == -1){
                            throw new EmptyArgumentException();
                        }
                        else{
                            controller.removeTreasureFromMonster(monsterList.get(monstercb.getSelectionModel().getSelectedIndex()), monsterTreasureList.get(index));
                            main.setMessage("Link has been destroyed succesfully!");
                            main.setMessageColor(Color.BLACK);
                            FillMonsterTreasureList(monsterList.get(monstercb.getSelectionModel().getSelectedIndex()));
                        }
                }
                catch(EmptyArgumentException eae){
                    main.setMessage("No treasure is selected from 'all treasures' list!\nPlease select treasure before removing!");
                    main.setMessageColor(Color.RED);
                }
            }
        }
        );
        
            //de add knop om shatten en monsters te linken
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    int index = treasureListView.getSelectionModel().getSelectedIndex();
                    if(monsterTest == -1){
                        throw new EmptyArgumentException();
                    }
                    if(index == -1){
                        throw new EmptyArgumentException();
                    }
                    for(int i = 0; i < monsterTreasureList.size(); i++){
                        if(monsterTreasureList.get(i).getId() == treasureList.get(index).getId()){
                            throw new InvalidIDException();
                        }
                    }
                    controller.addTreasureToMonster(monsterList.get(monstercb.getSelectionModel().getSelectedIndex()), treasureList.get(index));
                    FillMonsterTreasureList(monsterList.get(monstercb.getSelectionModel().getSelectedIndex()));
                    main.setMessage("Link between monster and treasure has been estavlished!");
                    main.setMessageColor(Color.BLACK);
                }
                catch (EmptyArgumentException eae){
                    main.setMessage("No treasure and/or monster is selected from 'connected treasures' list and/or the combobox!\nPlease select treasure and/or monster before adding!");
                    main.setMessageColor(Color.RED);
                }
                catch (InvalidIDException iide){
                    main.setMessage("Treasure is already linked to that monster!");
                    main.setMessageColor(Color.RED);
                }
            }
        }
        );
        
            //klikken op de update button
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FillComboBox();
                FillTreasureList();
                FillMonsterImageDefault();
            }
        }
        );
        
            //monster geselecteerd, update de monstertreasurelist en treasurelist, vul monsterimg
        monstercb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                if (monsterTest == 1){
                    FillMonsterTreasureList(monsterList.get(monstercb.getSelectionModel().getSelectedIndex()));
                    FillMonsterImage(monsterList.get(monstercb.getSelectionModel().getSelectedIndex()));
                }
                if (monsterTest == -1){
                    monsterTreasureListView.getItems().clear();
                }
            }
        }
        );
    }
    
    //de methoden
    
    //maakt treasure panes aan om in de lijsten te zetten
    private GridPane TreasurePane(Treasure treasure){
        GridPane treasurePane = new GridPane();
        
        Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
        Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
        Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
        Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
        Image iValue = new Image(Main.class.getResourceAsStream("/images/icons/Value.png"));
        Image iAvatar = new Image(Main.class.getResourceAsStream("/images/treasures/"+treasure.getAvatar()));
        
        ImageView speedimgv = new ImageView(iSpeed);
        ImageView awarenessimgv = new ImageView(iAwareness);
        ImageView defenseimgv = new ImageView(iDefense);
        ImageView powerimgv = new ImageView(iPower);
        ImageView avatarimgv = new ImageView(iAvatar);
        ImageView valueimgv = new ImageView(iValue);
        
        Label power = new Label("" + treasure.getPower());
        Label defense = new Label("" + treasure.getDefense());
        Label speed = new Label("" + treasure.getSpeed());
        Label awareness = new Label("" + treasure.getAwareness());
        Label value = new Label("" + treasure.getValue());
        Label name = new Label("" + treasure.getName());
        Label id = new Label(" #  " + treasure.getId());
        
        valueimgv.setFitHeight(15);
        speedimgv.setFitHeight(15);
        awarenessimgv.setFitHeight(15);
        defenseimgv.setFitHeight(15);
        powerimgv.setFitHeight(15);
        avatarimgv.setFitHeight(50);
        
        valueimgv.setFitWidth(15);
        speedimgv.setFitWidth(15);
        awarenessimgv.setFitWidth(15);
        defenseimgv.setFitWidth(15);
        powerimgv.setFitWidth(15);
        avatarimgv.setFitWidth(50);
        
        treasurePane.setPadding(new Insets(15));
        treasurePane.setHgap(10);
        treasurePane.setVgap(10);
        
        treasurePane.add(name, 0, 0, 7, 1);
        treasurePane.add(avatarimgv, 0, 1, 1, 2);
        treasurePane.add(id, 1, 1, 2, 1);
        treasurePane.add(powerimgv, 3, 1);
        treasurePane.add(power, 4, 1);
        treasurePane.add(speedimgv, 5, 1);
        treasurePane.add(speed, 6, 1);
        treasurePane.add(valueimgv, 1, 2);
        treasurePane.add(value, 2, 2);
        treasurePane.add(defenseimgv, 3, 2);
        treasurePane.add(defense, 4, 2);
        treasurePane.add(awarenessimgv, 5, 2);
        treasurePane.add(awareness, 6, 2);
        
        
        return treasurePane;
    }
    
    //vult de monsterTreasureListView
    private void FillMonsterTreasureList(Monster monster){
        monsterTreasureList.clear();
        monsterTreasureList.addAll(controller.searchAllTreasureFromMonster(monster));
        List<GridPane> helpList = new ArrayList<>();
        helpList.clear();
        for(int i = 0; i < monsterTreasureList.size(); i++){
            helpList.add(TreasurePane(monsterTreasureList.get(i)));
        }
        monsterTreasureListView.setItems(FXCollections.observableList(helpList));
    }
    
    //vult de treasureListView
    private void FillTreasureList(){
        treasureList.clear();
        treasureList.addAll(controller.searchAllTreasures());
        List<GridPane> helpList = new ArrayList<>();
        helpList.clear();
        for(int i = 0; i < treasureList.size(); i++){
            helpList.add(TreasurePane(treasureList.get(i)));
        }
        treasureListView.setItems(FXCollections.observableList(helpList));
    }
    
    //vult de combobox
    private void FillComboBox(){
        monsterTest = -1;
        monsterList.clear();
        monstercb.getItems().clear();
        monsterList.addAll(controller.searchAllMonsters());
        for(int i = 0; i < monsterList.size() ;i++){ 
            monstercb.getItems().add(monsterList.get(i));
        }
        monstercb.getSelectionModel().select(null);
        monsterTest = 1;
    }
    
    //vult de monsterimg met default, of gegeven monster
    private void FillMonsterImage(Monster monster){
        
        GridPane pane = new GridPane();
        
        Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
        Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
        Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
        Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
        Image iAvatar = new Image(Main.class.getResourceAsStream("/images/monsters/"+monster.getAvatar()));
        
        ImageView speedimgv = new ImageView(iSpeed);
        ImageView awarenessimgv = new ImageView(iAwareness);
        ImageView defenseimgv = new ImageView(iDefense);
        ImageView powerimgv = new ImageView(iPower);
        ImageView avatarimgv = new ImageView(iAvatar);
        
        speedimgv.setFitHeight(15);
        awarenessimgv.setFitHeight(15);
        defenseimgv.setFitHeight(15);
        powerimgv.setFitHeight(15);
        avatarimgv.setFitHeight(100);
        speedimgv.setFitWidth(15);
        awarenessimgv.setFitWidth(15);
        defenseimgv.setFitWidth(15);
        powerimgv.setFitWidth(15);
        avatarimgv.setFitWidth(100);
        pane.setPadding(new Insets(15));
        pane.setHgap(10);
        pane.setVgap(10);
        
        Label id = new Label(" #  "+monster.getId());
        Label awareness = new Label (""+monster.getAwareness());
        Label speed = new Label (""+monster.getSpeed());
        Label defense = new Label (""+monster.getDefense());
        Label power = new Label (""+monster.getPower());
        
        pane.add(avatarimgv, 0, 0, 4, 1);
        pane.add(id, 0, 1);
        pane.add(powerimgv, 0, 2);
        pane.add(power, 1, 2);
        pane.add(defense, 1, 3);
        pane.add(defenseimgv, 0, 3);
        pane.add(speedimgv, 2, 2);
        pane.add(speed, 3, 2);
        pane.add(awareness, 3, 3);
        pane.add(awarenessimgv, 2, 3);
        
        monsterimg.getChildren().clear();
        monsterimg.getChildren().add(pane);    
    }
    
    private void FillMonsterImageDefault(){
        
        GridPane pane = new GridPane();
        
        Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
        Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
        Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
        Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
        Image iAvatar = new Image(Main.class.getResourceAsStream("/images/monsters/default.png"));
        
        ImageView speedimgv = new ImageView(iSpeed);
        ImageView awarenessimgv = new ImageView(iAwareness);
        ImageView defenseimgv = new ImageView(iDefense);
        ImageView powerimgv = new ImageView(iPower);
        ImageView avatarimgv = new ImageView(iAvatar);
        
        speedimgv.setFitHeight(15);
        awarenessimgv.setFitHeight(15);
        defenseimgv.setFitHeight(15);
        powerimgv.setFitHeight(15);
        avatarimgv.setFitHeight(100);
        speedimgv.setFitWidth(15);
        awarenessimgv.setFitWidth(15);
        defenseimgv.setFitWidth(15);
        powerimgv.setFitWidth(15);
        avatarimgv.setFitWidth(100);
        pane.setPadding(new Insets(15));
        pane.setHgap(10);
        pane.setVgap(10);
        
        Label id = new Label(" #  ?");
        Label awareness = new Label ("?");
        Label speed = new Label ("?");
        Label defense = new Label ("?");
        Label power = new Label ("?");
        
        pane.add(avatarimgv, 0, 0, 4, 1);
        pane.add(id, 0, 1);
        pane.add(powerimgv, 0, 2);
        pane.add(power, 1, 2);
        pane.add(defense, 1, 3);
        pane.add(defenseimgv, 0, 3);
        pane.add(speedimgv, 2, 2);
        pane.add(speed, 3, 2);
        pane.add(awareness, 3, 3);
        pane.add(awarenessimgv, 2, 3);
        
        monsterimg.getChildren().clear();
        monsterimg.getChildren().add(pane);
    }
}