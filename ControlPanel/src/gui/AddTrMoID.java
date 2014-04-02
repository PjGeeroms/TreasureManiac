/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.EmptyArgumentException;
import exceptions.OutOfRangeException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 *
 * @author SIMON
 */
public class AddTrMoID extends GridPane {

    private DomeinController controller = new DomeinController();
    private MainPanel main;
    private TextField txfMonsterID;
    private TextField txfTreasureID;
    private Button add;

    private Label message;

    public AddTrMoID(MainPanel main) {
        this.main = main;
        buildGui();
    }

    private void buildGui() {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints();
        getColumnConstraints().addAll(col1, col2);

        //alle labels toevoegen
        add(new Label("Link treasure id to monster id"), 0, 0, 2, 1);
        add(new Label("Monster ID"), 0, 1);
        add(new Label("Treasure ID"), 0, 2);

        //alle invulvakken toevoegen
        txfMonsterID = new TextField();
        txfTreasureID = new TextField();
        add(txfMonsterID, 1, 1);
        add(txfTreasureID, 1, 2);

        //button toevoegen
        add = new Button("add");
        add(add, 0, 3, 2, 1);

        //de eventhandlers
        txfMonsterID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfTreasureID.requestFocus();
            }
        }
        );

        txfTreasureID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                add.requestFocus();
            }
        }
        );

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                try {
                    String monsterIDS = txfMonsterID.getText();
                    String treasureIDS = txfTreasureID.getText();

                    int monsterID, treasureID;

                    //geen lege velden, anders exception
                    if (monsterIDS.length() == 0) {
                        throw new EmptyArgumentException();
                    }

                    if (treasureIDS.length() == 0) {
                        throw new EmptyArgumentException();
                    } else {
                        monsterID = Integer.parseInt(monsterIDS);
                        treasureID = Integer.parseInt(treasureIDS);
                    }

                    //Negatief getal exception
                    if (treasureID < 0 || monsterID < 0) {
                        throw new OutOfRangeException();
                    }

                    controller.addTreasureToMonster(monsterID, treasureID);
                    Dialog.showMessageDialog(null,"Link between monster and treasure has been established", "Succes!");
                    //JOptionPane.showMessageDialog(null, "Link between monster and treasure has been established", "Succes!", JOptionPane.INFORMATION_MESSAGE);

                    //de exceptions catchen    
                } catch (EmptyArgumentException eae) {
                    main.setMessage(eae.getMessage());
                    main.setMessageColor(Color.RED);
                } catch (NumberFormatException nfe) {
                    main.setMessage("ID has to be an integer!");
                    main.setMessageColor(Color.RED);
                } catch (OutOfRangeException oore) {
                    main.setMessage(oore.getMessage() + " Must be a positive number!");
                    main.setMessageColor(Color.RED);
                } /*catch (MySQLIntegrityConstraintViolationException sqlicve) {
                 main.setMessage("ID is not detected in database!");
                 main.setMessageColor(Color.RED);
                 }*/ //wordt altijd uitgevoerd
                finally {
                    txfMonsterID.setText("");
                    txfTreasureID.setText("");
                }

            }
        }
        );

    }
}
