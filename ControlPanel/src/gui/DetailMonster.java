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

    //pro
    protected Label lblDetailName, lblDetailPower,
            /**
             *
             */
            lblDetailDefense,
            /**
             *
             */
            lblDetailSpeed,
            /**
             *
             */
            lblDetailAwareness;
    private Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));

    /**
     *
     * @param controller
     */
    public DetailMonster(DomeinController controller) {
        this.controller = controller;
        buildPanel();
    }

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

}
