package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Robin De Haes
 */
public class DetailTreasure extends GridPane {

    private DomeinController controller;
    private ImageView ivPower, ivDefense, ivSpeed, ivAwareness, ivValue;

    protected Label lblDetailName, lblDetailDescription, lblDetailPower, lblDetailDefense, lblDetailSpeed, lblDetailAwareness, lblDetailValue;
    private static Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private static Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private static Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private static Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));
    private static Image iValue = new Image(Main.class.getResourceAsStream("/images/icons/Value.png"));

    /**
     * Constructor for DetailTreasure
     *
     * @param controller the DomeinController that will be used for
     * communication purposes
     */
    public DetailTreasure(DomeinController controller) {
        this.controller = controller;
        buildPanel();
    }

    /**
     * The method for building the actual DetailTreasurePanel, used in
     * {@link #DetailTreasure(domein.DomeinController) DetailTreasure-constructor}
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

        ivValue = new ImageView(iValue);
        ivValue.setFitHeight(24);
        ivValue.setFitWidth(24);

        lblDetailName = new Label("Name");
        lblDetailName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblDetailDescription = new Label("\"Description\"");
        lblDetailDescription.setFont(Font.font("Arial", FontPosture.ITALIC, 16));

        lblDetailPower = new Label("0", ivPower);
        lblDetailDefense = new Label("0", ivDefense);
        lblDetailSpeed = new Label("0", ivSpeed);
        lblDetailAwareness = new Label("0", ivAwareness);
        lblDetailValue = new Label("0", ivValue);
        HBox padding1 = new HBox();
        padding1.setMinWidth(20);
        HBox padding2 = new HBox();
        padding2.setMinWidth(40);

        add(lblDetailName, 0, 0, 6, 1);
        add(lblDetailDescription, 0, 1, 6, 2);
        add(lblDetailPower, 0, 3);
        add(lblDetailDefense, 0, 4);
        add(padding1, 1, 3, 1, 2);
        add(lblDetailSpeed, 2, 3);
        add(lblDetailAwareness, 2, 4);
        add(padding2, 3, 4, 1, 2);
        add(lblDetailValue, 5, 3);
    }

    /**
     * Set the text of the name-label
     *
     * @param lblDetailName new text in name-label
     */
    public void setName(String lblDetailName) {
        this.lblDetailName.setText(lblDetailName);
    }

    /**
     * Set the text of the description-label
     *
     * @param lblDetailDescription new text in description-label
     */
    public void setDescription(String lblDetailDescription) {
        this.lblDetailDescription.setText("\"" + lblDetailDescription + "\"");
    }

    /**
     * Set the text of the power-label
     *
     * @param lblDetailPower new text in power-label
     */
    public void setPower(String lblDetailPower) {
        this.lblDetailPower.setText("" + lblDetailPower);
    }

    /**
     * Set the text of the defense-label
     *
     * @param lblDetailDefense new text in defense-label
     */
    public void setDefense(String lblDetailDefense) {
        this.lblDetailDefense.setText(lblDetailDefense + "");
    }

    /**
     * Set the text of the speed-label
     *
     * @param lblDetailSpeed new text in speed-label
     */
    public void setSpeed(String lblDetailSpeed) {
        this.lblDetailSpeed.setText("" + lblDetailSpeed);
    }

    /**
     * Set the text of the awareness-label
     *
     * @param lblDetailAwareness new text in awareness-label
     */
    public void setAwareness(String lblDetailAwareness) {
        this.lblDetailAwareness.setText("" + lblDetailAwareness);
    }

    /**
     * Set the text of the value-label
     *
     * @param lblDetailValue new text in value-label
     */
    public void setValue(String lblDetailValue) {
        this.lblDetailValue.setText(lblDetailValue);
    }
}
