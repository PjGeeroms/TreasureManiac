/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Monster;
import exceptions.EmptyArgumentException;
import exceptions.ImageNotSelectedException;
import exceptions.InvalidImageException;
import exceptions.OutOfRangeException;
import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author pieterjan
 */
public class MonsterPanel extends GridPane {

    private MainPanel main;
    private DetailMonster detail;
    private DomeinController controller;
    private Monster monster;
    private ImageView ivAvatar, ivPower, ivDefense, ivSpeed, ivAwareness;
    private Image iDefaultAvatar = new Image(Main.class.getResourceAsStream("/images/monsters/default.png"));
    private Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
    private TextField txfId, txfName, txfPower, txfDefense, txfSpeed, txfAwareness, txfAvatar;
    private ChoiceBox cbAvatars;
    private Label lblId, lblName, lblPower, lblDefense, lblSpeed, lblAwareness, lblAvatar, message;
    //protected Label lblDetailName, lblDetailPower, lblDetailDefense, lblDetailSpeed, lblDetailAwareness;

    /**
     * MonsterPanel constructor, creates the addMonsterPanel
     *
     * @param controller The domaincontroller
     * @param detail The detailPane that should be added at the top.
     * @param main The mainPanel where the MonsterPanel will get attached on.
     * @param message The error message at the bottom
     */
    public MonsterPanel(DomeinController controller, DetailMonster detail, MainPanel main, Label message) {
        this.controller = controller;
        this.detail = detail;
        this.main = main;
        this.message = message;
        settings();
        buildPane();
    }

    /**
     * MonsterPanel constructor, creates the panels for edit/delete
     *
     * @param controller The domaincontroller
     * @param monster Monster used for data to fill the panel
     * @param main The mainPanel where the MonsterPanel will get attached on.
     * @param message The error message at the bottom
     */
    public MonsterPanel(DomeinController controller, Monster monster, MainPanel main, Label message) {
        this.controller = controller;
        this.main = main;
        this.message = message;
        this.monster = monster;
        settings();
        //addPane(monster); // old panel
        monsterView(monster); // new panel
    }

    /**
     * General settings for both Panels
     */
    private void settings() {
        setStyle("-fx-border: 4px solid; -fx-border-color: #9a9a9a;");
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        lblId = new Label("ID");
        lblName = new Label("Name");
        lblPower = new Label("Power");
        lblDefense = new Label("Defense");
        lblSpeed = new Label("Speed");
        lblAwareness = new Label("Awareness");
        lblAvatar = new Label("Avatar");

        txfName = new TextField();
        txfPower = new TextField();
        txfDefense = new TextField();
        txfSpeed = new TextField();
        txfAwareness = new TextField();
        txfAvatar = new TextField();
    }

    private ChoiceBox FillAvatarChoiceBox() {
        File folder = new File("./src/images/monsters/");
        File[] listOfFiles = folder.listFiles();
        ChoiceBox avatars = new ChoiceBox();

        for (File avatar : listOfFiles) {
            if (avatar.isFile()) {
                avatars.getItems().add(avatar.getName());
            }
        }
        return avatars;
    }

    /**
     * Build the Pane to add monsters
     */
    private void buildPane() {
        ivAvatar = new ImageView(iDefaultAvatar);
        ivAvatar.setFitHeight(80);
        ivAvatar.setFitWidth(80);

        txfId = new TextField("Auto-generated");
        txfId.setDisable(true);
        txfId.setStyle("-fx-background-color: #d3d3d3");

        txfName.setPromptText("Up to " + Monster.getMAX_NAME() + " characters");
        txfPower.setPromptText("Between " + Monster.getMIN() + " and " + Monster.getMAX());
        txfDefense.setPromptText("Between " + Monster.getMIN() + " and " + Monster.getMAX());
        txfSpeed.setPromptText("Between " + Monster.getMIN() + " and " + Monster.getMAX());
        txfAwareness.setPromptText("Between " + Monster.getMIN() + " and " + Monster.getMAX());
        //txfAvatar.setPromptText("max" + (Monster.getMAX_AVATAR() - 4) + "chars.png/.jpg");
        cbAvatars = FillAvatarChoiceBox();
        cbAvatars.getSelectionModel().selectFirst();

        final Button addBtn = new Button("Add");

        add(ivAvatar, 0, 0, 1, 2);
        add(detail, 1, 0, 1, 2);

        add(lblId, 0, 3);
        add(txfId, 1, 3);

        add(lblName, 0, 4);
        add(txfName, 1, 4);

        add(lblPower, 0, 5);
        add(txfPower, 1, 5);

        add(lblDefense, 0, 6);
        add(txfDefense, 1, 6);

        add(lblSpeed, 0, 7);
        add(txfSpeed, 1, 7);

        add(lblAwareness, 0, 8);
        add(txfAwareness, 1, 8);

        add(lblAvatar, 0, 9);
        add(cbAvatars, 1, 9);

        add(addBtn, 1, 15);
        /*monsterPane.add(methodBtn, 0, 16, 2, 1);*/
        setHalignment(addBtn, HPos.CENTER);

        txfName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfPower.requestFocus();
            }
        }
        );

        txfName.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.lblDetailName.setText(txfName.getText());
                if (detail.lblDetailName.getText().length() == 0) {
                    detail.lblDetailName.setText("Name");
                }
            }
        });

        txfPower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfDefense.requestFocus();
            }
        }
        );

        txfPower.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.lblDetailPower.setText(txfPower.getText());
                if (txfPower.getText().length() == 0) {
                    detail.lblDetailPower.setText("0");
                }
            }
        });

        txfDefense.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfSpeed.requestFocus();
            }
        }
        );

        txfDefense.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.lblDetailDefense.setText(txfDefense.getText());
                if (txfDefense.getText().length() == 0) {
                    detail.lblDetailDefense.setText("0");
                }
            }
        });

        txfSpeed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfAwareness.requestFocus();
            }
        }
        );

        txfSpeed.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.lblDetailSpeed.setText(txfSpeed.getText());
                if (txfSpeed.getText().length() == 0) {
                    detail.lblDetailSpeed.setText("0");
                }
            }
        });

        txfAwareness.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfAvatar.requestFocus();
            }
        }
        );

        txfAwareness.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.lblDetailAwareness.setText(txfAwareness.getText());
                if (txfAwareness.getText().length() == 0) {
                    detail.lblDetailAwareness.setText("0");
                }
            }
        });

        txfAvatar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBtn.requestFocus();
            }
        }
        );

        cbAvatars.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number value, Number newValue) {
                String temp = cbAvatars.getItems().get(newValue.intValue()).toString();
                iDefaultAvatar = new Image(Main.class.getResourceAsStream("/images/monsters/" + temp));
                ivAvatar.setImage(iDefaultAvatar);
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int ID = 0;
                    String name = txfName.getText();
                    String powerS = txfPower.getText();
                    String defenseS = txfDefense.getText();
                    String speedS = txfSpeed.getText();
                    String awarenessS = txfAwareness.getText();
                    String avatar = cbAvatars.getItems().get(cbAvatars.getSelectionModel().selectedIndexProperty().getValue()).toString();

                    int power, defense, speed, awareness;

                    if (powerS.length() == 0) {
                        throw new EmptyArgumentException();
                    }
                    power = Integer.parseInt(powerS);

                    if (defenseS.length() == 0) {
                        throw new EmptyArgumentException();
                    }
                    defense = Integer.parseInt(defenseS);

                    if (speedS.length() == 0) {
                        throw new EmptyArgumentException();
                    }
                    speed = Integer.parseInt(speedS);

                    if (awarenessS.length() == 0) {
                        throw new EmptyArgumentException();
                    }
                    awareness = Integer.parseInt(awarenessS);

                    Monster monster = new Monster(ID, name, power, defense, speed, awareness, avatar);
                    boolean succes = controller.addMonster(monster);
                    if (succes) {
                        message.setText("Monster has been added!");
                        message.setTextFill(Color.GREEN);
                        MonsterPanel monsterPanel = new MonsterPanel(controller, monster, main, message);
                        main.monsterFlowPanel.getChildren().addAll(monsterPanel);
                    }

                    // reset Detail label
                    detail.lblDetailName.setText("Name");
                    detail.lblDetailPower.setText("0");
                    detail.lblDetailDefense.setText("0");
                    detail.lblDetailSpeed.setText("0");
                    detail.lblDetailAwareness.setText("0");
                } catch (EmptyArgumentException | OutOfRangeException | ImageNotSelectedException e) {
                    message.setText(e.getMessage());
                    message.setTextFill(Color.RED);
                } catch (NumberFormatException nfe) {
                    message.setText("All stats have to be integers!");
                    message.setTextFill(Color.RED);
                } finally {
                    txfName.setText("");
                    txfPower.setText("");
                    txfDefense.setText("");
                    txfSpeed.setText("");
                    txfAwareness.setText("");
                    cbAvatars.getSelectionModel().selectFirst();
                    txfName.requestFocus();
                }
            }
        });
    }

    private void monsterView(Monster monster) {
        final int Id = monster.getId();
        final String avatar = monster.getAvatar();
        ivPower = new ImageView(iPower);
        ivPower.setFitHeight(24);
        ivPower.setFitWidth(24);

        ivDefense = new ImageView(iDefense);
        ivDefense.setFitHeight(24);
        ivDefense.setFitWidth(24);

        ivSpeed = new ImageView(iSpeed);
        ivSpeed.setFitHeight(24);
        ivSpeed.setFitWidth(24);

        ivAwareness = new ImageView(iAwareness);
        ivAwareness.setFitHeight(24);
        ivAwareness.setFitWidth(24);

        Image icon = new Image(Main.class.getResourceAsStream("/images/monsters/" + monster.getAvatar()));
        ivAvatar = new ImageView(icon);
        ivAvatar.setFitHeight(80);
        ivAvatar.setFitWidth(80);

        lblId = new Label("#" + String.valueOf(monster.getId()));
        lblName = new Label(monster.getName());
        lblName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblPower = new Label(String.valueOf(monster.getPower()), ivPower);
        lblDefense = new Label(String.valueOf(monster.getDefense()), ivDefense);
        lblSpeed = new Label(String.valueOf(monster.getSpeed()), ivSpeed);
        lblAwareness = new Label(String.valueOf(monster.getAwareness()), ivAwareness);

        txfName.setOpacity(0);
        txfAvatar.setOpacity(0);
        txfPower.setOpacity(0);
        txfSpeed.setOpacity(0);
        txfAwareness.setOpacity(0);
        txfDefense.setOpacity(0);

        txfPower.setMaxWidth(40);
        txfDefense.setMaxWidth(40);
        txfSpeed.setMaxWidth(40);
        txfAwareness.setMaxWidth(40);

        HBox padding1 = new HBox();
        padding1.setMinWidth(20);

        final Button btnUpdate = new Button("Modify");
        final Button btnDelete = new Button("Delete");

        add(ivAvatar, 0, 0);
        add(lblName, 1, 0, 3, 1);
        add(lblPower, 1, 1);
        add(lblDefense, 1, 2);
        add(padding1, 2, 1, 1, 2);
        add(lblSpeed, 3, 1);
        add(lblAwareness, 3, 2);

        add(txfName, 1, 0, 3, 1);
        add(txfPower, 1, 1);
        add(txfDefense, 1, 2);
        add(txfSpeed, 3, 1);
        add(txfAwareness, 3, 2);
        add(txfAvatar, 1, 3, 3, 1);

        add(btnUpdate, 0, 1);
        setHalignment(btnUpdate, HPos.CENTER);
        add(btnDelete, 0, 2);
        setHalignment(btnDelete, HPos.CENTER);

        txfName.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfPower.requestFocus();
                    }
                }
        );

        txfPower.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfDefense.requestFocus();
                    }
                }
        );

        txfDefense.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfSpeed.requestFocus();
                    }
                }
        );

        txfSpeed.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfAwareness.requestFocus();
                    }
                }
        );

        txfAwareness.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfAvatar.requestFocus();
                    }
                }
        );

        txfAvatar.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        btnUpdate.requestFocus();
                    }
                }
        );

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (btnUpdate.getText().equals("Modify")) {
                    txfName.setText(lblName.getText());
                    txfPower.setText(lblPower.getText());
                    txfSpeed.setText(lblSpeed.getText());
                    txfDefense.setText(lblDefense.getText());
                    txfAwareness.setText(lblAwareness.getText());
                    txfAvatar.setText(avatar);

                    txfName.setOpacity(1);
                    txfAvatar.setOpacity(1);

                    txfPower.setOpacity(1);
                    txfSpeed.setOpacity(1);
                    txfAwareness.setOpacity(1);
                    txfDefense.setOpacity(1);

                    btnUpdate.setText("Update");
                    txfAvatar.requestFocus();
                    message.setText("Same rules as adding apply!");
                    message.setTextFill(Color.GREEN);
                } else {
                    String nameU = txfName.getText();
                    String powerU = txfPower.getText();
                    String defenseU = txfDefense.getText();
                    String speedU = txfSpeed.getText();
                    String awarenessU = txfAwareness.getText();
                    String avatarU = txfAvatar.getText();
                    int powerUi, defenseUi, speedUi, awarenessUi;
                    boolean succes = false;

                    try {

                        if (powerU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        powerUi = Integer.parseInt(powerU);

                        if (defenseU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        defenseUi = Integer.parseInt(defenseU);

                        if (speedU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        speedUi = Integer.parseInt(speedU);

                        if (awarenessU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        awarenessUi = Integer.parseInt(awarenessU);

                        Monster monsterU = new Monster(Id, nameU, powerUi, defenseUi, speedUi, awarenessUi, avatarU);
                        succes = controller.updateMonster(monsterU);
                    } catch (EmptyArgumentException | OutOfRangeException | ImageNotSelectedException e) {
                        message.setText(e.getMessage());
                        message.setTextFill(Color.RED);
                    } catch (NumberFormatException nfe) {
                        message.setText("All stats have to be integers!");
                        message.setTextFill(Color.RED);
                    } finally {
                        if (succes) {
                            if (!lblAvatar.getText().equals(avatarU)) {
                                if (getChildren().contains(ivAvatar)) {
                                    getChildren().remove(ivAvatar);
                                } else {
                                    getChildren().remove(getChildren().size() - 1);       //Laatst toegevoegde node is een nieuwe afbeelding
                                }

                                Image newImg = new Image(Main.class.getResourceAsStream("/images/monsters/" + avatarU));

                                ivAvatar = new ImageView(newImg);
                                ivAvatar.setFitHeight(80);
                                ivAvatar.setFitWidth(80);
                                add(ivAvatar, 0, 0);
                                //setHalignment(newView, HPos.CENTER);
                            }

                            lblName.setText(txfName.getText());
                            lblPower.setText(txfPower.getText());
                            lblSpeed.setText(txfSpeed.getText());
                            lblDefense.setText(txfDefense.getText());
                            lblAwareness.setText(txfAwareness.getText());
                            lblAvatar.setText(txfAvatar.getText());

                            txfName.setOpacity(0);
                            txfAvatar.setOpacity(0);

                            txfPower.setOpacity(0);
                            txfSpeed.setOpacity(0);
                            txfAwareness.setOpacity(0);
                            txfDefense.setOpacity(0);

                            message.setText("Monster has been updated!");
                            message.setTextFill(Color.GREEN);
                            btnUpdate.setText("Modify");

                            txfName.setText("");
                            txfPower.setText("");
                            txfSpeed.setText("");
                            txfDefense.setText("");
                            txfAwareness.setText("");
                            txfAvatar.setText("");
                        } else {
                            txfName.setText(lblName.getText());
                            txfPower.setText(lblPower.getText());
                            txfSpeed.setText(lblSpeed.getText());
                            txfDefense.setText(lblDefense.getText());
                            txfAwareness.setText(lblAwareness.getText());
                            txfAvatar.setText(lblAvatar.getText());
                            txfAvatar.requestFocus();
                        }
                    }
                }
            }
        });

        final MonsterPanel monsterPanel = this;
        btnDelete.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        boolean succes = controller.deleteMonster(Id);     //eerst db leegmaken

                        if (succes) {
                            message.setText("Monster has been deleted!");
                            message.setTextFill(Color.GREEN);
                            main.monsterFlowPanel.getChildren().removeAll(monsterPanel);
                        }
                    }
                }
        );
    }

    /**
     * Builds the Pane to edit/delete monsters
     *
     * @param monster
     */
    private void addPane(Monster monster) {
        final Text txtId, txtName, txtPower, txtDefense, txtSpeed, txtAwareness, txtAvatar;
        final int Id = monster.getId();
        String name = monster.getName();
        int power = monster.getPower();
        int defense = monster.getDefense();
        int speed = monster.getSpeed();
        int awareness = monster.getAwareness();
        String avatar = monster.getAvatar();
        Image icon = new Image(Main.class.getResourceAsStream("/images/monsters/" + avatar));
        ivAvatar = new ImageView(icon);

        txtId = new Text("(#" + Id + ")");
        txtId.setStyle("-fx-background-color: white");
        txtName = new Text(name);
        txtPower = new Text(power + "");
        txtDefense = new Text(defense + "");
        txtSpeed = new Text(speed + "");
        txtAwareness = new Text(awareness + "");
        txtAvatar = new Text(avatar);
        final Button updateBtn = new Button("Modify");
        final Button deleteBtn = new Button("Delete");

        add(ivAvatar, 0, 8, 2, 1);
        setHalignment(ivAvatar, HPos.CENTER);           //CENTER

        txtAvatar.setFont(Font.font("Arial", FontPosture.ITALIC, 10));
        add(txtAvatar, 0, 9, 2, 1);
        setHalignment(txtAvatar, HPos.CENTER);

        txtName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        add(txtName, 0, 1);
        add(txtId, 1, 1);

        add(lblPower, 0, 3);
        add(txtPower, 1, 3);

        add(lblDefense, 0, 4);
        add(txtDefense, 1, 4);

        add(lblSpeed, 0, 5);
        add(txtSpeed, 1, 5);

        add(lblAwareness, 0, 6);
        add(txtAwareness, 1, 6);

        add(txfAvatar, 0, 9, 2, 1);
        setHalignment(txfAvatar, HPos.CENTER);

        add(txfName, 0, 1);
        add(txfPower, 1, 3);
        add(txfDefense, 1, 4);
        add(txfSpeed, 1, 5);
        add(txfAwareness, 1, 6);

        txfName.setOpacity(0);
        txfAvatar.setOpacity(0);
        txfPower.setOpacity(0);
        txfSpeed.setOpacity(0);
        txfAwareness.setOpacity(0);
        txfDefense.setOpacity(0);

        add(updateBtn, 0, 15);
        setHalignment(updateBtn, HPos.CENTER);
        add(deleteBtn, 1, 15);
        setHalignment(deleteBtn, HPos.CENTER);

        txfName.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfPower.requestFocus();
                    }
                }
        );

        txfPower.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfDefense.requestFocus();
                    }
                }
        );

        txfDefense.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfSpeed.requestFocus();
                    }
                }
        );

        txfSpeed.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfAwareness.requestFocus();
                    }
                }
        );

        txfAwareness.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfAvatar.requestFocus();
                    }
                }
        );

        txfAvatar.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        updateBtn.requestFocus();
                    }
                }
        );

        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (updateBtn.getText().equals("Modify")) {
                    txfName.setText(txtName.getText());
                    txfPower.setText(txtPower.getText());
                    txfSpeed.setText(txtSpeed.getText());
                    txfDefense.setText(txtDefense.getText());
                    txfAwareness.setText(txtAwareness.getText());
                    txfAvatar.setText(txtAvatar.getText());

                    txfName.setOpacity(1);
                    txfAvatar.setOpacity(1);

                    txfPower.setOpacity(1);
                    txfSpeed.setOpacity(1);
                    txfAwareness.setOpacity(1);
                    txfDefense.setOpacity(1);

                    updateBtn.setText("Update");
                    txfAvatar.requestFocus();
                    message.setText("Same rules as adding apply!");
                    message.setTextFill(Color.GREEN);
                } else {
                    String nameU = txfName.getText();
                    String powerU = txfPower.getText();
                    String defenseU = txfDefense.getText();
                    String speedU = txfSpeed.getText();
                    String awarenessU = txfAwareness.getText();
                    String avatarU = txfAvatar.getText();
                    int powerUi, defenseUi, speedUi, awarenessUi;
                    boolean succes = false;

                    try {

                        if (powerU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        powerUi = Integer.parseInt(powerU);

                        if (defenseU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        defenseUi = Integer.parseInt(defenseU);

                        if (speedU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        speedUi = Integer.parseInt(speedU);

                        if (awarenessU.length() == 0) {
                            throw new EmptyArgumentException();
                        }
                        awarenessUi = Integer.parseInt(awarenessU);

                        Monster monsterU = new Monster(Id, nameU, powerUi, defenseUi, speedUi, awarenessUi, avatarU);
                        succes = controller.updateMonster(monsterU);
                    } catch (EmptyArgumentException | OutOfRangeException | ImageNotSelectedException e) {
                        message.setText(e.getMessage());
                        message.setTextFill(Color.RED);
                    } catch (NumberFormatException nfe) {
                        message.setText("All stats have to be integers!");
                        message.setTextFill(Color.RED);
                    } finally {
                        if (succes) {
                            if (!txtAvatar.getText().equals(avatarU)) {
                                if (getChildren().contains(ivAvatar)) {
                                    getChildren().remove(ivAvatar);
                                } else {
                                    getChildren().remove(getChildren().size() - 1);       //Laatst toegevoegde node is een nieuwe afbeelding
                                }

                                Image newImg = new Image(Main.class.getResourceAsStream("/images/monsters/" + avatarU));
                                ImageView newView = new ImageView(newImg);
                                add(newView, 0, 13, 2, 1);
                                setHalignment(newView, HPos.CENTER);
                            }

                            txtName.setText(txfName.getText());
                            txtPower.setText(txfPower.getText());
                            txtSpeed.setText(txfSpeed.getText());
                            txtDefense.setText(txfDefense.getText());
                            txtAwareness.setText(txfAwareness.getText());
                            txtAvatar.setText(txfAvatar.getText());

                            txfName.setOpacity(0);
                            txfAvatar.setOpacity(0);

                            txfPower.setOpacity(0);
                            txfSpeed.setOpacity(0);
                            txfAwareness.setOpacity(0);
                            txfDefense.setOpacity(0);

                            message.setText("Monster has been updated!");
                            message.setTextFill(Color.GREEN);
                            updateBtn.setText("Modify");

                            txfName.setText("");

                            txfPower.setText("");
                            txfSpeed.setText("");
                            txfDefense.setText("");
                            txfAwareness.setText("");
                            txfAvatar.setText("");
                        } else {
                            txfName.setText(txtName.getText());
                            txfPower.setText(txtPower.getText());
                            txfSpeed.setText(txtSpeed.getText());
                            txfDefense.setText(txtDefense.getText());
                            txfAwareness.setText(txtAwareness.getText());
                            txfAvatar.setText(txtAvatar.getText());
                            txfAvatar.requestFocus();
                        }
                    }
                }
            }
        });

        final MonsterPanel monsterPanel = this;
        deleteBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        boolean succes = controller.deleteMonster(Id);     //eerst db leegmaken

                        if (succes) {
                            message.setText("Monster has been deleted!");
                            message.setTextFill(Color.GREEN);
                            main.monsterFlowPanel.getChildren().removeAll(monsterPanel);
                        }
                    }
                }
        );

    }
}
