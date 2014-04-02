/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Robin
 *
 */

/*
 Stage dialog = new Stage();
 dialog.initStyle(StageStyle.UTILITY);
 Scene scene = new Scene(new Group(new Text(25, 25, "Hello World!")));
 dialog.setScene(scene);
 dialog.show();
 */
public class Dialog {

    private static Response btnPressed;
    private static Image Information = new Image(Main.class.getResourceAsStream("/images/icons/Information.png"));
    private static Image Question = new Image(Main.class.getResourceAsStream("/images/icons/Question.png"));
    private static Image iTitle = (new Image(Main.class.getResourceAsStream("/images/icons/iTitle_icon.png")));
    private static Image qTitle = (new Image(Main.class.getResourceAsStream("/images/icons/qTitle_icon.png")));

    public enum Response {

        NO, YES, CLOSED
    };

    private static Response buildConfirmationDialog(Stage owner, String message, String title) {
        final Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.getIcons().add(qTitle);
        dialog.setTitle(title);
        dialog.setResizable(false);
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        btnPressed = Response.CLOSED;

        FlowPane buttons = new FlowPane(10, 10);
        buttons.setAlignment(Pos.CENTER);
        Button yes = new Button("Yes");
        Button no = new Button("No");
        buttons.getChildren().addAll(yes, no);
        buttons.setPadding(new Insets(0, 0, 10, 0));     //trbl-paddingwaarden

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 5, 0, 5));
        ImageView question = new ImageView(Question);
        question.setFitHeight(60);
        question.setFitWidth(60);
        question.fitWidthProperty();
        hBox.getChildren().addAll(question);
        VBox msg = new VBox();
        msg.getChildren().addAll(new Label(message));
        msg.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(msg);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox, buttons);

        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btnPressed = Response.YES;
                dialog.close();
            }
        });
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btnPressed = Response.NO;
                dialog.close();
            }
        });

        Scene s = new Scene(vBox);
        dialog.setScene(s);
        dialog.sizeToScene();
        dialog.centerOnScreen();
        dialog.showAndWait();

        return btnPressed;
    }

    private static void buildMessageDialog(Stage owner, String message, String title) {
        final Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.getIcons().add(iTitle);
        dialog.setTitle(title);
        dialog.setResizable(false);
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);            //blokkeert invoer naar achtergrondapplicatie

        FlowPane buttons = new FlowPane(10, 10);
        buttons.setAlignment(Pos.CENTER);
        Button ok = new Button("Ok");
        buttons.getChildren().addAll(ok);

        HBox hBox = new HBox();
        ImageView information = new ImageView(Information);
        information.setFitHeight(60);
        information.setFitWidth(60);

        hBox.getChildren().addAll(information);
        VBox msg = new VBox();
        msg.getChildren().addAll(new Label(message));
        msg.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(msg);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox, buttons);
        vBox.setPadding(new Insets(20));

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                dialog.close();
            }
        });

        Scene s = new Scene(vBox);
        dialog.setScene(s);
        dialog.sizeToScene();
        dialog.centerOnScreen();
        dialog.showAndWait();
    }

    public static Response showConfirmationDialog(Stage owner, String message, String title) {
        buildConfirmationDialog(owner, message, title);
        Response answer = btnPressed;
        btnPressed = null;              //Niet echt nodig...
        return answer;
    }

    public static void showMessageDialog(Stage owner, String message, String title) {
        buildMessageDialog(owner, message, title);
    }
}
