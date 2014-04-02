/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author pieterjan
 */
public class DetailMonster extends GridPane {

    private DomeinController controller;
    private ImageView ivPower, ivDefense, ivSpeed, ivAwareness;

    protected Label lblDetailName, lblDetailPower, lblDetailDefense, lblDetailSpeed, lblDetailAwareness;
    private static Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private static Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private static Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private static Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));

    /**
     * Constructor 
     * @param controller the domain controller
     */
    public DetailMonster(DomeinController controller) {
        this.controller = controller;
        buildPanel();
    }

    /**
     * Build the standard panel
     */
    private void buildPanel() {
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

        lblDetailName = new Label("Name");
        lblDetailName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblDetailPower = new Label("0", ivPower);
        lblDetailDefense = new Label("0", ivDefense);
        lblDetailSpeed = new Label("0", ivSpeed);
        lblDetailAwareness = new Label("0", ivAwareness);
        HBox padding1 = new HBox();
        padding1.setMinWidth(20);

        add(lblDetailName, 0, 0, 3, 1);
        add(lblDetailPower, 0, 1);
        add(lblDetailDefense, 0, 2);
        add(padding1, 1, 1, 1, 2);
        add(lblDetailSpeed, 2, 1);
        add(lblDetailAwareness, 2, 2);
    }
        /**
     *
     * @param lblDetailName
     */
    public void setName(String lblDetailName) {
        this.lblDetailName.setText(lblDetailName);
    }

    /**
     *
     * @param lblDetailPower
     */
    public void setPower(String lblDetailPower) {
        this.lblDetailPower.setText("" + lblDetailPower);
    }

    /**
     *
     * @param lblDetailDefense
     */
    public void setDefense(String lblDetailDefense) {
        this.lblDetailDefense.setText(lblDetailDefense + "");
    }

    /**
     *
     * @param lblDetailSpeed
     */
    public void setSpeed(String lblDetailSpeed) {
        this.lblDetailSpeed.setText("" + lblDetailSpeed);
    }

    /**
     *
     * @param lblDetailAwareness
     */
    public void setAwareness(String lblDetailAwareness) {
        this.lblDetailAwareness.setText("" + lblDetailAwareness);
    }


    /**
     *
     * @return
     */
    public Label getLblDetailName() {
        return lblDetailName;
    }


    /**
     *
     * @return
     */
    public Label getLblDetailPower() {
        return lblDetailPower;
    }

    /**
     *
     * @return
     */
    public Label getLblDetailDefense() {
        return lblDetailDefense;
    }

    /**
     *
     * @return
     */
    public Label getLblDetailSpeed() {
        return lblDetailSpeed;
    }

    /**
     *
     * @return
     */
    public Label getLblDetailAwareness() {
        return lblDetailAwareness;
    }
}
