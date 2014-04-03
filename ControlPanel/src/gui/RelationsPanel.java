/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 *
 * @author SIMON
 */
public class RelationsPanel extends GridPane{
    
    private DomeinController controller = new DomeinController();
    private MainPanel main;
    
    private List<Treasure> treasureList;
    private List<Monster> monsterList;
    private List<Treasure> monsterTreasureList;
    
    private ListView<Treasure> treasureListView;
    private ListView<Treasure> monsterTreasureListView;
    private ListView<Monster> monsterListView;
    private ComboBox monstercb;
    
    private Button add;
    private Button delete;
    private Button update;
    
    private Label treasurelbl;
    private Label monsterlbl;
    private Label monsterTreasurelbl;
    
    private int treasureID = -1;
    private int monsterTreasureID = -1;
    
    public RelationsPanel(MainPanel main) {
        this.main = main;
        buildGui();
    }
    
    private void UpdateChoiceBox(){
        //updaten van de choicebox               
        monsterList.clear();
        monstercb.getItems().clear();
        monsterList.addAll(controller.searchAllMonsters());
        for(int i = 0; i < monsterList.size() ;i++){ 
            monstercb.getItems().add(monsterList.get(i));
        }
        monstercb.getSelectionModel().select(null);
        
        treasureList.clear();
        monsterTreasureList.clear();
        
    }
    
    private void UpdateList(int monsterID){
        //update treasureList
        treasureList.clear();
        treasureList.addAll(controller.searchAllTreasures());
        treasureListView.setItems(FXCollections.observableList(treasureList));
        
        
        //update monsterTreasureList
        monsterTreasureList.clear();
        monsterTreasureList.addAll(controller.searchAllTreasuresFromMonster(monsterID));
        monsterTreasureListView.setItems(FXCollections.observableList(monsterTreasureList));
        
    }
    
    //zet Stringversie om naar ID in de int vorm
    private int StringToIntID(String string, int beginwaarde){
        int ID;
        String hulp = "";
        
        
        
        for(int i=beginwaarde; i<string.length();i++){
            Character character = string.charAt(i);
        if (Character.isDigit(character)) {
            hulp += character;
        }
        }
        ID = Integer.parseInt(hulp);
        return ID;
    }
    
    private void buildGui(){
        
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints();
        getColumnConstraints().addAll(col1, col2);
        
        //array list declareren
        treasureList = new ArrayList<>();
        monsterList = new ArrayList<>();
        monsterTreasureList = new ArrayList<>();
        
        //listview declareren
        treasureListView = new ListView<>();
        monsterTreasureListView = new ListView<>();
        
        //choicebox declareren
        monstercb = new ComboBox();
        
        //buttons declareren
        update = new Button("Update monsters");
        delete = new Button("Remove");
        add = new Button("Add");
        
        //labels declareren
        monsterlbl = new Label("                                             "
                +"Choose monster:");
        treasurelbl = new Label("Treasure list");
        monsterTreasurelbl = new Label("Connected treasures");
        
        //alles in grid op plaats zetten
            //de knoppen
        add(update,0,0);
        add(add, 1, 3);
        add(delete, 1, 4);
            //de labels
        add(monsterlbl, 0, 1);
        add(treasurelbl, 2, 2);
        add(monsterTreasurelbl, 0 , 2);
            //de choicebox
        add(monstercb, 1, 1, 5, 1);
            //de list
        add(monsterTreasureListView, 0 , 3, 1, 5);
        add(treasureListView, 2 , 3, 1, 5);
        
        //button en choicebox een grote geven
            //buttons
        update.setMinWidth(150);
        update.setMaxWidth(150);
        delete.setMinWidth(100);
        delete.setMaxWidth(100);
        add.setMinWidth(100);
        add.setMaxWidth(100);
            //combobox
        monstercb.setMinWidth(220);
        monstercb.setMaxWidth(220);
        
        //testding
        
        
        //combobox al laten invullen
        UpdateChoiceBox();
        
        //event handelers
            //update knop, update monster list
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                monstercb.getSelectionModel().clearSelection();
                UpdateChoiceBox();
            }
        }
        );
        
            //monster geselecteerd, update de treasure lists
        monstercb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                try{
                    UpdateList(StringToIntID(monstercb.getValue().toString(), 2)); 
                }
                catch(NullPointerException npe){
                   // UpdateChoiceBox();
                }
            }
        }
        );
        
            //treasure selecteren in treasureList
        treasureListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                try{
                    if(treasureListView.getSelectionModel().getSelectedItems().toString().equals("[]")){
                        treasureID = -1;
                    }
                    else{
                        treasureID = StringToIntID(treasureListView.getSelectionModel().getSelectedItems().toString(), 3);
                    }
                }
                catch(NumberFormatException nfe){
                    treasureID = -1;
                }
            }
 
      }
      );
        
            //treasure selecteren in monsterTreasureList
        monsterTreasureListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                try{
                    if(monsterTreasureListView.getSelectionModel().getSelectedItems().toString().equals("[]")){
                        monsterTreasureID = -1;
                    }
                    else{
                        monsterTreasureID = StringToIntID(monsterTreasureListView.getSelectionModel().getSelectedItems().toString(), 3);
                    }
                }
                catch (NumberFormatException nfe){
                    monsterTreasureID = -1;
                }
            }
 
      }
      );
        
            //add knop, geselecteerde treasure linken aan monster
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    //test of treasure geselecteerd is
                    if(treasureID == -1){
                        throw new EmptyArgumentException();
                    }
       
                    //testen of schat al niet gelinked is met treasure
                    int test;
                    for(int i=0; i<monsterTreasureList.size() ;i++){                      
                        test = StringToIntID(monsterTreasureList.get(i).toString(),2);
                        if(test == treasureID){
                            throw new InvalidIDException();
                        }
                    }
                    
                    //link maken tussen monster en treasure
                    controller.addTreasureToMonster(StringToIntID(monstercb.getValue().toString(), 2), treasureID);
                }
                catch(EmptyArgumentException eae){
                    main.setMessage("No treasure is selected! \nPlease select a treasure from 'Treasures list' before adding!");
                    main.setMessageColor(Color.RED);
                }
                catch(InvalidIDException iie){
                    main.setMessage("Treasure is already linked to that monster! \nPlease select a diffrent treasure from 'Treasure List'!");
                }
                finally{
                    UpdateList(StringToIntID(monstercb.getValue().toString(), 2));
                }
            }
        }
        );
        
            //Remove knop, geselecteerde treasure verwijderen van het monster
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    //test of treasure geselcteerd is
                    if(monsterTreasureID == -1){
                        throw new EmptyArgumentException();
                    }
                    
                    //de link tussen monster en treasure verwijderen
                    controller.removeTreasureFromMonster(StringToIntID(monstercb.getValue().toString(), 2) , monsterTreasureID);
                }
                catch(EmptyArgumentException eae){
                    main.setMessage("No treasure is selected! \nPlease select a treasure from 'Connected treasures' before removing!");
                    main.setMessageColor(Color.RED);
                }
                finally{
                    UpdateList(StringToIntID(monstercb.getValue().toString(), 2));
                }
            }
        }
        );
            
        
        
        
        
        
    }
    
}
