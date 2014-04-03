/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Monster;
import domein.Treasure;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author pieterjan, Steve, Simon en Robin
 */
public class MainPanel extends BorderPane {

    private DomeinController controller;
    protected FlowPane monsterFlowPanel, monsterFunctionPanel, treasureFlowPanel, treasureFunctionPanel, linkPanel, showPanel;
    private BorderPane innerTab;
    protected Label message;

    /**
     * Create the main panel
     *
     * @param controller Domain controller
     */
    public MainPanel(DomeinController controller) {
        this.controller = controller;
        buildGui();
    }

    /**
     * Builds the GUI
     */
    private void buildGui() {
        ToolBar toolbar = new ToolBar();
        toolbar.setStyle("-fx-background-color: #d2d2d2");
        message = new Label("Succesfully started");
        message.setTextFill(Color.BLACK);
        toolbar.getItems().add(message);
        setBottom(toolbar);

        TabPane center = new TabPane();
        center.setPrefSize(600, 600);
        center.setTabMinHeight(20);

        Tab monsters = new Tab();
        monsters.setClosable(false);
        monsters.setText("Monsters");
        initializeMonstersTab(monsters);

        Tab treasures = new Tab();
        treasures.setClosable(false);
        treasures.setText("Treasures");
        initializeTreasuresTab(treasures);

        Tab relations = new Tab();
        relations.setClosable(false);
        relations.setText("Relations");
        initializeRelationsTab(relations);

        center.getTabs().addAll(monsters, treasures, relations);
        setCenter(center);
    }

    /**
     * Creates the Monster Panel
     *
     * @param tab Tab where the Panel should be initialized.
     */
    private void initializeMonstersTab(Tab tab) {
        monsterFunctionPanel = new FlowPane(Orientation.VERTICAL);
        monsterFunctionPanel.setPadding(new Insets(10));
        monsterFunctionPanel.setHgap(10);
        monsterFunctionPanel.setVgap(10);

        monsterFlowPanel = new FlowPane(Orientation.HORIZONTAL);
        monsterFlowPanel.setPadding(new Insets(10));
        monsterFlowPanel.setHgap(10);
        monsterFlowPanel.setVgap(10);

        // create a scrollpane incase scrolling is needed
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // No horizontal scrollbar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scrollbar if needed
        scroll.setContent(monsterFlowPanel);

        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                monsterFlowPanel.setPrefWidth(bounds.getWidth());
                monsterFlowPanel.setPrefHeight(bounds.getHeight());
            }
        });

        // add Monster panel
        DetailMonster detail = new DetailMonster(controller);
        MonsterPanel monsterPanel = new MonsterPanel(controller, detail, this);
        monsterFunctionPanel.getChildren().addAll(monsterPanel);

        // show all Monsters in the database on launch
        showAllMonsters(monsterFlowPanel);

        // stackpane to combine scrollbar & flowpane
        StackPane root = new StackPane();
        root.getChildren().addAll(scroll);

        innerTab = new BorderPane();
        innerTab.setLeft(monsterFunctionPanel);
        innerTab.setCenter(root);

        // adds the root to the tab
        tab.setContent(innerTab);
    }

    /**
     * Show all monsters in the database
     *
     * @param panel The flowpane where the monsterPanels should be added.
     */
    private void showAllMonsters(FlowPane panel) {
        List<Monster> allMonsters = controller.searchAllMonsters();

        for (Monster monster : allMonsters) {
            MonsterPanel monsterPanel = new MonsterPanel(controller, monster, this);
            panel.getChildren().addAll(monsterPanel);
        }
    }

    /**
     * Creates the Treasures Panel
     *
     * @param tab Tab where the Panel should be initialized.
     */
    private void initializeTreasuresTab(Tab tab) {
        treasureFunctionPanel = new FlowPane(Orientation.VERTICAL);
        treasureFunctionPanel.setPadding(new Insets(10));
        treasureFunctionPanel.setHgap(10);
        treasureFunctionPanel.setVgap(10);

        treasureFlowPanel = new FlowPane(Orientation.HORIZONTAL);
        treasureFlowPanel.setPadding(new Insets(10));
        treasureFlowPanel.setHgap(10);
        treasureFlowPanel.setVgap(10);

        // create a scrollpane incase scrolling is needed
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // No horizontal scrollbar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scrollbar if needed
        scroll.setContent(treasureFlowPanel);

        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                treasureFlowPanel.setPrefWidth(bounds.getWidth());
                treasureFlowPanel.setPrefHeight(bounds.getHeight());
            }
        });

        // add Monster panel
        DetailTreasure detail = new DetailTreasure(controller);
        TreasurePanel treasurePanel = new TreasurePanel(controller, detail, this);
        treasureFunctionPanel.getChildren().addAll(treasurePanel);

        // show all Monsters in the database on launch
        showAllTreasures(treasureFlowPanel);

        // stackpane to combine scrollbar & flowpane
        StackPane root = new StackPane();
        root.getChildren().addAll(scroll);

        innerTab = new BorderPane();
        innerTab.setLeft(treasureFunctionPanel);
        innerTab.setCenter(root);

        // adds the root to the tab
        tab.setContent(innerTab);
    }

    /**
     * Show all treasures in the database
     *
     * @param panel The flowpane where the treasurePanels should be added.
     */
    private void showAllTreasures(FlowPane panel) {
        List<Treasure> allTreasures = controller.searchAllTreasures();

        for (Treasure treasure : allTreasures) {
            TreasurePanel treasurePanel = new TreasurePanel(controller, treasure, this);
            panel.getChildren().addAll(treasurePanel);
        }
    }

//    private void initializeRelationsTab(Tab tab) {
//
//        linkPanel = new FlowPane(Orientation.VERTICAL);
//        linkPanel.setPadding(new Insets(10));
//        linkPanel.setHgap(10);
//        linkPanel.setVgap(10);
//
//        showPanel = new FlowPane(Orientation.HORIZONTAL);
//        showPanel.setPadding(new Insets(10));
//        showPanel.setHgap(10);
//        showPanel.setVgap(10);
//
//        AddTrMoID other1 = new AddTrMoID(this);
//        RemoveTrMoID other2 = new RemoveTrMoID(this);
//        ShowTrMoID other3 = new ShowTrMoID(this);
//
//        linkPanel.getChildren().addAll(other1);
//        linkPanel.getChildren().addAll(other2);
//        showPanel.getChildren().addAll(other3);
//
//        innerTab = new BorderPane();
//        innerTab.setLeft(linkPanel);
//        innerTab.setCenter(showPanel);
//
//        tab.setContent(innerTab);
//    }
    
    private void initializeRelationsTab(Tab tab) {
        linkPanel = new FlowPane(Orientation.HORIZONTAL);
        linkPanel.setPadding(new Insets(10));
        linkPanel.setHgap(10);
        linkPanel.setVgap(10);
        RelationsPanel r = new RelationsPanel(this);
        linkPanel.getChildren().addAll(r);
        innerTab = new BorderPane();
        innerTab.setCenter(linkPanel);
  
        tab.setContent(innerTab);
    }

    /**
     * Set text of message-label
     *
     * @param m new text of message-label
     */
    public void setMessage(String m) {
        message.setText(m);
    }

    /**
     * Set textcolor of message-label
     *
     * @param color new textcolor of message-label
     */
    public void setMessageColor(Color color) {
        message.setTextFill(color);
    }

    /**
     * Give the FlowPanel that contains the monsters
     *
     * @return the monsterFlowPanel from the MainPanel
     */
    public FlowPane getMonsterFlowPanel() {
        return monsterFlowPanel;
    }

    /**
     * Give the FlowPanel that contains the treasures
     *
     * @return the treasureFlowPanel from the MainPanel
     */
    public FlowPane getTreasureFlowPanel() {
        return treasureFlowPanel;
    }
}
