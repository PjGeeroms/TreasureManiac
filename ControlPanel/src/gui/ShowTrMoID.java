/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Treasure;
import exceptions.EmptyArgumentException;
import exceptions.OutOfRangeException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author SIMON
 */
public class ShowTrMoID extends VBox {

    private DomeinController controller = new DomeinController();
    private MainPanel main;
    private ShowTrMoID details;
    private ListView<Treasure> lijst;
    private TextField txfMonsterID;
    private Button show;

    public ShowTrMoID(MainPanel main) {
        this.main = main;
        buildGui();
    }

    private void buildGui() {
        VBox otherPanel3 = new VBox();
        setPadding(new Insets(10));
        setSpacing(10);

        GridPane grid = new GridPane();

        //titel toevoegen
        getChildren().add(new Label("List of treasures linked to monsters (give monster id)"));
        //textfield toevoegen
        txfMonsterID = new TextField();
        getChildren().add(txfMonsterID);
        //button toevoegen
        show = new Button("show");
        getChildren().add(show);

        //de lijst ophalen
        lijst = new ListView<>();
        otherPanel3.getChildren().add(lijst);
        VBox.setVgrow(lijst, Priority.ALWAYS);
        getChildren().add(lijst);

        //de eventhandlers
        txfMonsterID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                show.requestFocus();
            }
        }
        );

        show.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    String monsterIDS = txfMonsterID.getText();
                    int monsterID;

                    //lege velden exception
                    if (monsterIDS.length() == 0) {
                        throw new EmptyArgumentException();
                    } else {
                        monsterID = Integer.parseInt(monsterIDS);
                    }

                    //Negatief getal exception
                    if (monsterID < 0) {
                        throw new OutOfRangeException();
                    }

                    //lijst updaten
                    lijst.setItems(FXCollections.observableList(controller.searchAllTreasuresFromMonster(monsterID)));

                } catch (EmptyArgumentException eae) {
                    main.setMessage(eae.getMessage());
                    main.setMessageColor(Color.RED);
                } catch (NumberFormatException nfe) {
                    main.setMessage("ID has to be an integer!");
                    main.setMessageColor(Color.RED);

                } catch (OutOfRangeException oore) {
                    main.setMessage(oore.getMessage() + " Must be a positive number!");
                    main.setMessageColor(Color.RED);
                }
            }
        }
        );

    }
}
