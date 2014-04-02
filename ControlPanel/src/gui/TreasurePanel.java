/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Treasure;
import exceptions.*;
import gui.Dialog.Response;
import java.io.File;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Robin
 */
public class TreasurePanel extends GridPane {

    private DomeinController controller;
    private static Image iDefaultAvatar = new Image(Main.class.getResourceAsStream("/images/icons/default.png"));
    private MainPanel main;
    private DetailTreasure detail;
    private Treasure treasure;
    private ImageView ivAvatar, ivPower, ivDefense, ivSpeed, ivAwareness, ivValue;
    private static Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private static Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private static Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private static Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
    private static Image iValue = new Image(Main.class.getResourceAsStream("/images/icons/Value.png"));
    private TextField txfId, txfName, txfPower, txfDefense, txfSpeed, txfAwareness, txfAvatar, txfValue, txfDescription;
    private Label lblId, lblName, lblPower, lblDefense, lblSpeed, lblAwareness, lblAvatar, lblValue, lblDescription, message;
    private ChoiceBox cbAvatars;
    private final static File folder = new File("./src/images/treasures/");
    private static File[] listOfFiles = folder.listFiles();

    /**
     *
     * @param controller
     * @param detail
     * @param main
     */
    public TreasurePanel(DomeinController controller, DetailTreasure detail, MainPanel main) //DetailTreasure details,
    {
        this.controller = controller;
        this.detail = detail;
        this.main = main;
        settings();
        buildAddPane();
    }

    /**
     *
     * @param controller
     * @param treasure
     * @param main
     */
    public TreasurePanel(DomeinController controller, Treasure treasure, MainPanel main) {
        this.controller = controller;
        this.main = main;
        this.treasure = treasure;
        settings();
        buildTreasurePane(treasure);
    }

    private void settings() {
        setStyle("-fx-border: 4px solid; -fx-border-color: #9a9a9a;");
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        lblId = new Label("ID");
        lblName = new Label("Name");
        lblValue = new Label("Value");
        lblDescription = new Label("Description");
        lblPower = new Label("Power");
        lblDefense = new Label("Defense");
        lblSpeed = new Label("Speed");
        lblAwareness = new Label("Awareness");
        lblAvatar = new Label("Avatar");

        txfName = new TextField();
        txfValue = new TextField();
        txfDescription = new TextField();
        txfPower = new TextField();
        txfDefense = new TextField();
        txfSpeed = new TextField();
        txfAwareness = new TextField();
        txfAvatar = new TextField();
    }

    private ChoiceBox FillAvatarChoiceBox() {
        ChoiceBox avatars = new ChoiceBox();

        for (File avatar : listOfFiles) {
            if (avatar.isFile()) {
                avatars.getItems().add(avatar.getName());
            }
        }
        return avatars;
    }

    /**
     *
     */
    public void buildAddPane() {
        ivAvatar = new ImageView(iDefaultAvatar);
        ivAvatar.setFitHeight(80);
        ivAvatar.setFitWidth(80);

        txfId = new TextField("Auto-generated");
        txfId.setDisable(true);
        txfId.setStyle("-fx-background-color: #d3d3d3");

        txfName.setPromptText("Up to " + Treasure.getMAX_NAME() + " characters");
        txfValue.setPromptText("Positive integer");
        txfDescription.setPromptText("Up to " + Treasure.getMAX_DESCRIPTION() + " characters");
        txfPower.setPromptText("Between " + Treasure.getMIN() + " and " + Treasure.getMAX());
        txfDefense.setPromptText("Between " + Treasure.getMIN() + " and " + Treasure.getMAX());
        txfSpeed.setPromptText("Between " + Treasure.getMIN() + " and " + Treasure.getMAX());
        txfAwareness.setPromptText("Between " + Treasure.getMIN() + " and " + Treasure.getMAX());
        cbAvatars = FillAvatarChoiceBox();

        final Button addBtn = new Button("Add");

        add(ivAvatar, 0, 0, 1, 2);
        add(detail, 1, 0, 1, 2);

        add(lblId, 0, 3);
        add(txfId, 1, 3);

        add(lblName, 0, 4);
        add(txfName, 1, 4);

        add(lblValue, 0, 5);
        add(txfValue, 1, 5);

        add(lblDescription, 0, 6);
        add(txfDescription, 1, 6);

        add(lblPower, 0, 7);
        add(txfPower, 1, 7);

        add(lblDefense, 0, 8);
        add(txfDefense, 1, 8);

        add(lblSpeed, 0, 9);
        add(txfSpeed, 1, 9);

        add(lblAwareness, 0, 10);
        add(txfAwareness, 1, 10);

        add(lblAvatar, 0, 11);
        add(cbAvatars, 1, 11);

        add(addBtn, 1, 15);
        GridPane.setHalignment(addBtn, HPos.CENTER);

        txfName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfValue.requestFocus();
            }
        }
        );

        txfName.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.setName(txfName.getText());
                if (txfName.getText().length() == 0) {
                    detail.setName("Name");
                }
            }
        });

        txfValue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfDescription.requestFocus();
            }
        }
        );

        txfValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.setValue(txfValue.getText());
                if (txfValue.getText().length() == 0) {
                    detail.setValue("0");
                }
            }
        });
        txfDescription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txfPower.requestFocus();
            }
        }
        );

        txfDescription.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.setDescription(txfDescription.getText());
                if (txfDescription.getText().length() == 0) {
                    detail.setDescription("Description");
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
                detail.setPower(txfPower.getText());
                if (txfPower.getText().length() == 0) {
                    detail.setPower("0");
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
                detail.setDefense(txfDefense.getText());
                if (txfDefense.getText().length() == 0) {
                    detail.setDefense("0");
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
                detail.setSpeed(txfSpeed.getText());
                if (txfSpeed.getText().length() == 0) {
                    detail.setSpeed("0");
                }
            }
        });

        txfAwareness.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbAvatars.requestFocus();
                cbAvatars.show();
            }
        }
        );

        txfAwareness.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                detail.setAwareness(txfAwareness.getText());
                if (txfAwareness.getText().length() == 0) {
                    detail.setAwareness("0");
                }
            }
        });

        cbAvatars.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
                if (newVal != null) {
                    Image newImg = new Image(Main.class.getResourceAsStream("/images/treasures/" + newVal));
                    ivAvatar.setImage(newImg);
                }

                addBtn.requestFocus();
            }
        }
        );

        cbAvatars.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent t
                    ) {
                        KeyCode keyCode = t.getCode();
                        if (keyCode == KeyCode.ENTER) {
                            addBtn.requestFocus();
                        }
                    }
                }
        );

        addBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        Treasure treasure = new Treasure();

                        String name = txfName.getText();
                        String valueS = txfValue.getText();
                        String description = txfDescription.getText();
                        String powerS = txfPower.getText();
                        String defenseS = txfDefense.getText();
                        String speedS = txfSpeed.getText();
                        String awarenessS = txfAwareness.getText();
                        String avatar = null;
                        if (!cbAvatars.getSelectionModel().isSelected(-1)) {
                            avatar = cbAvatars.getSelectionModel().selectedItemProperty().getValue().toString();
                        }

                        int power, defense, speed, awareness, value;

                        boolean exceptionOccurred = false;

                        try {
                            treasure.setName(name);
                        } catch (EmptyArgumentException | OutOfRangeException e) {
                            txfName.setText("");
                            detail.setName("Name");
                            exceptionOccurred = true;
                            main.setMessage(e.getMessage());
                            main.setMessageColor(Color.RED);
                            txfName.requestFocus();
                        }

                        try {
                            if (valueS.length() == 0) {
                                throw new EmptyArgumentException();
                            }
                            value = Integer.parseInt(valueS);
                            treasure.setValue(value);
                        } catch (EmptyArgumentException | OutOfRangeException e) {
                            txfValue.setText("");
                            detail.setValue("0");
                            if (!exceptionOccurred) {
                                exceptionOccurred = true;
                                main.setMessage(e.getMessage());
                                main.setMessageColor(Color.RED);
                                txfValue.requestFocus();
                            }
                        } catch (NumberFormatException nfe) {

                            txfValue.setText("");
                            detail.setValue("0");
                            if (!exceptionOccurred) {
                                txfValue.requestFocus();
                                main.setMessage("All stats have to be integers!");
                                main.setMessageColor(Color.RED);
                                exceptionOccurred = true;
                            }
                        }

                        try {
                            treasure.setDescription(description);
                        } catch (EmptyArgumentException | OutOfRangeException e) {
                            txfDescription.setText("");
                            detail.setDescription("Description");
                            if (!exceptionOccurred) {

                                main.setMessage(e.getMessage());
                                main.setMessageColor(Color.RED);
                                txfDescription.requestFocus();
                                exceptionOccurred = true;
                            }
                        }
                        try {
                            if (powerS.length() == 0) {
                                throw new EmptyArgumentException();
                            }
                            power = Integer.parseInt(powerS);
                            treasure.setPower(power);
                        } catch (EmptyArgumentException | OutOfRangeException e) {
                            txfPower.setText("");
                            detail.setPower("0");
                            if (!exceptionOccurred) {
                                txfPower.requestFocus();
                                main.setMessage(e.getMessage());
                                main.setMessageColor(Color.RED);
                                exceptionOccurred = true;
                            }
                        } catch (NumberFormatException nfe) {

                            txfPower.setText("");
                            detail.setPower("0");
                            if (!exceptionOccurred) {
                                txfPower.requestFocus();
                                main.setMessage("All stats have to be integers!");
                                main.setMessageColor(Color.RED);
                                exceptionOccurred = true;
                            }
                        }
                        try {

                            if (defenseS.length() == 0) {
                                throw new EmptyArgumentException();
                            }
                            defense = Integer.parseInt(defenseS);
                            treasure.setDefense(defense);
                        } catch (EmptyArgumentException | OutOfRangeException e) {

                            txfDefense.setText("");
                            detail.setDefense("0");
                            if (!exceptionOccurred) {
                                txfDefense.requestFocus();
                                main.setMessage(e.getMessage());
                                main.setMessageColor(Color.RED);
                                exceptionOccurred = true;
                            }
                        } catch (NumberFormatException nfe) {
                            txfDefense.setText("");
                            detail.setDefense("0");
                            if (!exceptionOccurred) {
                                txfDefense.requestFocus();
                                main.setMessage("All stats have to be integers!");
                                main.setMessageColor(Color.RED);
                                exceptionOccurred = true;
                            }
                        }
                        try {

                            if (speedS.length() == 0) {
                                throw new EmptyArgumentException();
                            }
                            speed = Integer.parseInt(speedS);
                            treasure.setSpeed(speed);
                        } catch (EmptyArgumentException | OutOfRangeException e) {
                            txfSpeed.setText("");
                            detail.setSpeed("0");
                            if (!exceptionOccurred) {
                                txfSpeed.requestFocus();
                                main.setMessage(e.getMessage());
                                main.setMessageColor(Color.RED);
                                exceptionOccurred = true;
                            }
                        } catch (NumberFormatException nfe) {
                            txfSpeed.setText("");
                            detail.setSpeed("0");
                            if (!exceptionOccurred) {
                                main.setMessage("All stats have to be integers!");
                                main.setMessageColor(Color.RED);
                                txfSpeed.requestFocus();
                                exceptionOccurred = true;
                            }
                        }
                        try {

                            if (awarenessS.length() == 0) {
                                throw new EmptyArgumentException();
                            }
                            awareness = Integer.parseInt(awarenessS);
                            treasure.setAwareness(awareness);
                        } catch (EmptyArgumentException | OutOfRangeException e) {
                            txfAwareness.setText("");
                            detail.setAwareness("0");
                            if (!exceptionOccurred) {
                                main.setMessage(e.getMessage());
                                main.getMessage().setTextFill(Color.RED);
                                txfAwareness.requestFocus();
                                exceptionOccurred = true;
                            }
                        } catch (NumberFormatException nfe) {
                            txfAwareness.setText("");
                            detail.setAwareness("0");
                            if (!exceptionOccurred) {
                                main.setMessage("All stats have to be integers!");
                                main.setMessageColor(Color.RED);
                                txfAwareness.requestFocus();
                                exceptionOccurred = true;
                            }
                        }

                        try {
                            treasure.setAvatar(avatar);
                        } catch (ImageNotSelectedException e) {
                            if (!exceptionOccurred) {
                                main.setMessage(e.getMessage());
                                main.setMessageColor(Color.RED);
                                txfAvatar.requestFocus();
                                exceptionOccurred = true;
                            }
                        }
                        if (!exceptionOccurred) {
                            if (controller.addTreasure(treasure)) {
                                txfName.setText("");
                                detail.setName("Name");
                                txfDescription.setText("");
                                detail.setDescription("Description");
                                txfPower.setText("");
                                detail.setPower("0");
                                txfDefense.setText("");
                                detail.setDefense("0");
                                txfSpeed.setText("");
                                detail.setSpeed("0");
                                txfAwareness.setText("");
                                detail.setAwareness("0");
                                txfValue.setText("");
                                detail.setValue("0");
                                cbAvatars.getSelectionModel().clearSelection();//selectFirst();
                                ivAvatar.setImage(iDefaultAvatar);

                                //getChildren().remove(ivAvatar);
                                // ivAvatar.setImage(iDefaultAvatar); //= new ImageView(iDefaultAvatar);
                                // ivAvatar.setFitHeight(80);
                                // ivAvatar.setFitWidth(80);
                                //add(ivAvatar, 0, 0, 1, 2);
                                main.setMessage("Treasure has been added!");
                                main.setMessageColor(Color.GREEN);
                                List<Treasure> treasures = controller.searchAllTreasures();
                                main.getTreasureFlowPanel().getChildren().addAll(new TreasurePanel(controller, treasures.get(treasures.size() - 1), main));     //Enkel nodig indien ID ook correct moet worden meegegeven
                            }
                        }
                    }
                }
        );
    }

    /**
     *
     * @param treasure
     */
    public void buildTreasurePane(final Treasure treasure) {
        final int Id = treasure.getId();
        final String avatar = treasure.getAvatar();
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

        ivValue = new ImageView(iValue);
        ivValue.setFitHeight(24);
        ivValue.setFitWidth(24);

        Image icon = new Image(Main.class
                .getResourceAsStream("/images/treasures/" + avatar));
        ivAvatar = new ImageView(icon);

        ivAvatar.setFitHeight(
                80);
        ivAvatar.setFitWidth(
                80);

        lblId = new Label("(#" + String.valueOf(treasure.getId()) + ")");
        lblName = new Label(treasure.getName());

        lblName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblDescription = new Label("\"" + treasure.getDescription() + "\"");

        lblDescription.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
        lblPower = new Label(String.valueOf(treasure.getPower()), ivPower);
        lblDefense = new Label(String.valueOf(treasure.getDefense()), ivDefense);
        lblSpeed = new Label(String.valueOf(treasure.getSpeed()), ivSpeed);
        lblAwareness = new Label(String.valueOf(treasure.getAwareness()), ivAwareness);
        lblValue = new Label(String.valueOf(treasure.getValue()), ivValue);
        lblAvatar = new Label(avatar);
        cbAvatars = FillAvatarChoiceBox();

        List items = cbAvatars.getItems();
        for (int i = 0;
                i < items.size();
                i++) //Of beter gewoon geselecteerde item meegeven aan buildTreasurePane
        {
            if (items.get(i).toString().equals(lblAvatar.getText())) {
                cbAvatars.getSelectionModel().select(items.get(i));
            }
        }

        txfName.setOpacity(
                0);
        cbAvatars.setOpacity(
                0);
        txfPower.setOpacity(
                0);
        txfSpeed.setOpacity(
                0);
        txfAwareness.setOpacity(
                0);
        txfDefense.setOpacity(
                0);
        txfValue.setOpacity(
                0);
        txfDescription.setOpacity(
                0);

        txfPower.setMaxWidth(
                45);
        txfDefense.setMaxWidth(
                45);
        txfSpeed.setMaxWidth(
                45);
        txfAwareness.setMaxWidth(
                45);
        txfValue.setMaxWidth(
                45);

        HBox padding1 = new HBox();

        padding1.setMinWidth(
                20);
        HBox padding2 = new HBox();

        padding2.setMinWidth(
                20);

        final Button updateBtn = new Button("Modify");
        final Button deleteBtn = new Button("Delete");

        /* VBox nameDescription=new VBox();
         nameDescription.getChildren().add(lblName);
         nameDescription.getChildren().add(lblDescription);*/
        add(ivAvatar, 0, 0, 1, 2);
        add(lblName, 1, 0, 3, 1);
        add(lblId, 5, 0);
        add(lblDescription, 1, 1, 5, 1);
        add(lblPower, 1, 2);
        add(lblDefense, 1, 3);
        add(padding1, 2, 2, 1, 2);
        add(lblSpeed, 3, 2);
        add(lblAwareness, 3, 3);
        add(padding2, 4, 2, 1, 2);
        add(lblValue, 5, 2);

        add(txfName, 1, 0, 5, 1);
        add(txfDescription, 1, 1, 5, 1);
        add(txfPower, 1, 2);
        add(txfDefense, 1, 3);
        add(txfSpeed, 3, 2);
        add(txfAwareness, 3, 3);
        add(txfValue, 5, 2);
        add(cbAvatars, 1, 4, 5, 1);

        add(updateBtn, 0, 2);
        setHalignment(updateBtn, HPos.CENTER);

        add(deleteBtn, 0, 3);
        setHalignment(deleteBtn, HPos.CENTER);

        txfName.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        txfDescription.requestFocus();
                    }
                }
        );

        txfDescription.setOnAction(
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
                        txfValue.requestFocus();
                    }
                }
        );

        txfValue.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        cbAvatars.requestFocus();
                        cbAvatars.show();
                    }
                }
        );

        cbAvatars.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number value, Number newValue
                    ) {
                        String temp = cbAvatars.getItems().get(newValue.intValue()).toString();
                        Image newImg = new Image(Main.class.getResourceAsStream("/images/treasures/" + temp));
                        ivAvatar.setImage(newImg);
                        updateBtn.requestFocus();
                    }
                }
                );

        cbAvatars.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent t
                    ) {
                        KeyCode keyCode = t.getCode();
                        if (keyCode == KeyCode.ENTER) {
                            updateBtn.requestFocus();
                        }
                    }
                }
        );

        /*
         txfAvatar.setOnAction(
         new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event
         ) {
         updateBtn.requestFocus();
         }
         }
         );*/

        /*Image img = new Image(Main.class.getResourceAsStream("/images/treasures/" + avatar));
         final ImageView view = new ImageView(img);
         GridPane.setHalignment(view, HPos.CENTER);  */         //CENTER
        updateBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        if (updateBtn.getText().equals("Modify")) {
                            txfName.setText(lblName.getText());
                            String temp = lblDescription.getText();
                            txfDescription.setText(temp.substring(1, temp.length() - 1));

                            txfValue.setText(lblValue.getText());
                            txfPower.setText(lblPower.getText());
                            txfSpeed.setText(lblSpeed.getText());
                            txfDefense.setText(lblDefense.getText());
                            txfAwareness.setText(lblAwareness.getText());
                            //txfAvatar.setText(lblAvatar.getText());

                            txfName.setOpacity(1);
                            cbAvatars.setOpacity(1);
                            txfDescription.setOpacity(1);
                            txfValue.setOpacity(1);
                            txfPower.setOpacity(1);
                            txfSpeed.setOpacity(1);
                            txfAwareness.setOpacity(1);
                            txfDefense.setOpacity(1);

                            updateBtn.setText("Update");
                            txfName.requestFocus();
                            main.setMessage("Same rules as adding apply!");
                            main.setMessageColor(Color.GREEN);
                        } else {
                            String nameU = txfName.getText();
                            String valueU = txfValue.getText();
                            String descriptionU = txfDescription.getText();
                            String powerU = txfPower.getText();
                            String defenseU = txfDefense.getText();
                            String speedU = txfSpeed.getText();
                            String awarenessU = txfAwareness.getText();
                            String avatarU = cbAvatars.getSelectionModel().getSelectedItem().toString();
                            int valueUi, powerUi, defenseUi, speedUi, awarenessUi;
                            boolean succes = false;
                            boolean exceptionOccurred = false;

                            try {
                                treasure.setName(nameU);
                            } catch (EmptyArgumentException | OutOfRangeException e) {
                                txfName.setText(lblName.getText());
                                exceptionOccurred = true;
                                main.setMessage(e.getMessage());
                                main.setMessageColor(Color.RED);
                                txfName.requestFocus();
                            }

                            try {
                                if (valueU.length() == 0) {
                                    throw new EmptyArgumentException();
                                }
                                valueUi = Integer.parseInt(valueU);
                                treasure.setValue(valueUi);
                            } catch (EmptyArgumentException | OutOfRangeException e) {
                                txfValue.setText(lblValue.getText());
                                if (!exceptionOccurred) {
                                    exceptionOccurred = true;
                                    main.setMessage(e.getMessage());
                                    main.setMessageColor(Color.RED);
                                    txfValue.requestFocus();
                                }
                            } catch (NumberFormatException nfe) {

                                txfValue.setText(lblValue.getText());
                                if (!exceptionOccurred) {
                                    txfValue.requestFocus();
                                    main.setMessage("All stats have to be integers!");
                                    main.setMessageColor(Color.RED);
                                    exceptionOccurred = true;
                                }
                            }

                            try {
                                treasure.setDescription(descriptionU);
                            } catch (EmptyArgumentException | OutOfRangeException e) {
                                String temp = lblDescription.getText();
                                txfDescription.setText(temp.substring(1, temp.length() - 1));
                                if (!exceptionOccurred) {
                                    main.setMessage(e.getMessage());
                                    main.setMessageColor(Color.RED);
                                    txfDescription.requestFocus();
                                    exceptionOccurred = true;
                                }
                            }
                            try {
                                if (powerU.length() == 0) {
                                    throw new EmptyArgumentException();
                                }
                                powerUi = Integer.parseInt(powerU);
                                treasure.setPower(powerUi);
                            } catch (EmptyArgumentException | OutOfRangeException e) {
                                txfPower.setText(lblPower.getText());
                                if (!exceptionOccurred) {
                                    txfPower.requestFocus();
                                    main.setMessage(e.getMessage());
                                    main.setMessageColor(Color.RED);
                                    exceptionOccurred = true;
                                }
                            } catch (NumberFormatException nfe) {

                                txfPower.setText(lblPower.getText());
                                if (!exceptionOccurred) {
                                    txfPower.requestFocus();
                                    main.setMessage("All stats have to be integers!");
                                    main.setMessageColor(Color.RED);
                                    exceptionOccurred = true;
                                }
                            }
                            try {

                                if (defenseU.length() == 0) {
                                    throw new EmptyArgumentException();
                                }
                                defenseUi = Integer.parseInt(defenseU);
                                treasure.setDefense(defenseUi);
                            } catch (EmptyArgumentException | OutOfRangeException e) {

                                txfDefense.setText(lblDefense.getText());
                                if (!exceptionOccurred) {
                                    txfDefense.requestFocus();
                                    main.setMessage(e.getMessage());
                                    main.setMessageColor(Color.RED);
                                    exceptionOccurred = true;
                                }
                            } catch (NumberFormatException nfe) {
                                txfDefense.setText(lblDefense.getText());
                                if (!exceptionOccurred) {
                                    txfDefense.requestFocus();
                                    main.setMessage("All stats have to be integers!");
                                    main.setMessageColor(Color.RED);
                                    exceptionOccurred = true;
                                }
                            }
                            try {

                                if (speedU.length() == 0) {
                                    throw new EmptyArgumentException();
                                }
                                speedUi = Integer.parseInt(speedU);
                                treasure.setSpeed(speedUi);
                            } catch (EmptyArgumentException | OutOfRangeException e) {
                                txfSpeed.setText(lblSpeed.getText());
                                if (!exceptionOccurred) {
                                    txfSpeed.requestFocus();
                                    main.setMessage(e.getMessage());
                                    main.setMessageColor(Color.RED);
                                    exceptionOccurred = true;
                                }
                            } catch (NumberFormatException nfe) {
                                txfSpeed.setText(lblSpeed.getText());
                                if (!exceptionOccurred) {
                                    main.setMessage("All stats have to be integers!");
                                    main.setMessageColor(Color.RED);
                                    txfSpeed.requestFocus();
                                    exceptionOccurred = true;
                                }
                            }
                            try {

                                if (awarenessU.length() == 0) {
                                    throw new EmptyArgumentException();
                                }
                                awarenessUi = Integer.parseInt(awarenessU);
                                treasure.setAwareness(awarenessUi);
                            } catch (EmptyArgumentException | OutOfRangeException e) {
                                txfAwareness.setText(lblAwareness.getText());
                                if (!exceptionOccurred) {
                                    main.setMessage(e.getMessage());
                                    main.setMessageColor(Color.RED);
                                    txfAwareness.requestFocus();
                                    exceptionOccurred = true;
                                }
                            } catch (NumberFormatException nfe) {
                                txfAwareness.setText(lblAwareness.getText());
                                if (!exceptionOccurred) {
                                    main.setMessage("All stats have to be integers!");
                                    main.setMessageColor(Color.RED);
                                    txfAwareness.requestFocus();
                                    exceptionOccurred = true;
                                }
                            }

                            /* try {*/           //kan geen exception meer optreden
                            treasure.setAvatar(avatarU);
                            /* } catch (EmptyArgumentException | OutOfRangeException | InvalidImageException | ImageNotFoundException e) {
                             if (!exceptionOccurred) {
                             main.setMessage(e.getMessage());
                             main.getMessage().setTextFill(Color.RED);
                             txfAvatar.requestFocus();
                             exceptionOccurred = true;
                             }
                             //txfAvatar.setText(lblAvatar.getText());
                             cbAvatars.getSelectionModel().selectFirst();
                             }*/
                            if (!exceptionOccurred) {
                                if (controller.updateTreasure(treasure)) {

                                    if (!lblAvatar.getText().equals(avatarU)) {
                                        //getChildren().remove(ivAvatar);
                                        Image newImg = new Image(Main.class.getResourceAsStream("/images/treasures/" + avatarU));
                                        ivAvatar.setImage(newImg);
//ivAvatar = new ImageView(newImg);
                                        //ivAvatar.setFitHeight(80);
                                        // ivAvatar.setFitWidth(80);
                                        // add(ivAvatar, 0, 0, 1, 2);
                                    }

                                    lblName.setText(txfName.getText());
                                    lblDescription.setText("\"" + txfDescription.getText() + "\"");
                                    lblValue.setText(txfValue.getText());
                                    lblPower.setText(txfPower.getText());
                                    lblSpeed.setText(txfSpeed.getText());
                                    lblDefense.setText(txfDefense.getText());
                                    lblAwareness.setText(txfAwareness.getText());
                                    lblAvatar.setText(avatarU);

                                    txfName.setOpacity(0);
                                    cbAvatars.setOpacity(0);
                                    txfDescription.setOpacity(0);
                                    txfValue.setOpacity(0);
                                    txfPower.setOpacity(0);
                                    txfSpeed.setOpacity(0);
                                    txfAwareness.setOpacity(0);
                                    txfDefense.setOpacity(0);

                                    main.setMessage("Treasure has been updated!");
                                    main.setMessageColor(Color.GREEN);
                                    updateBtn.setText("Modify");

                                    txfName.setText("");
                                    txfDescription.setText("");
                                    txfValue.setText("");
                                    txfPower.setText("");
                                    txfSpeed.setText("");
                                    txfDefense.setText("");
                                    txfAwareness.setText("");
                                }
                            }
                        }
                    }
                }
        );

        final TreasurePanel pane = this;

        deleteBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        boolean succes = false;
                        int unconnected = controller.isUnconnectedTreasure(Id);
                        if (unconnected == 1) {
                            succes = controller.deleteTreasure(Id);
                        } else if (unconnected == 0) {
                            /*Response answer=FXOptionPane.showConfirmDialog(null, "Treasure is still connected with monster(s)!\n"
                             + "Do you want to delete it anyway?", "Break all links?");
                             if(answer.equals(Response.YES))
                             {
                             succes = controller.deleteTreasure(Id);
                             }*/
                            Response answer = Dialog.showConfirmationDialog(null, "Treasure is still connected with monster(s)!\n"
                                    + "Do you want to delete it anyway?", "Break all links?");
                            if (answer == Response.YES) {
                                succes = controller.deleteTreasure(Id);
                            }
                        }

                        if (succes) {
                            main.setMessage("Treasure has been deleted!");
                            main.setMessageColor(Color.GREEN);
                            main.getTreasureFlowPanel().getChildren().removeAll(pane);
                        }
                    }
                }
        );
        //panel.getChildren().addAll(this);
    }
}
