//Vrij lelijk, maar zonder minimise venster -> Van internet gehaald met kleine aanpassingen

/*package gui;

//import com.sun.java.util.jar.pack.Attribute.Layout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXOptionPane {

    private static Image Information = new Image(Main.class.getResourceAsStream("/images/icons/Information.png"));
    private static Image Question = new Image(Main.class.getResourceAsStream("/images/icons/Question.png"));
    private static Image iTitle = (new Image(Main.class.getResourceAsStream("/images/icons/iTitle_icon.png")));
    private static Image qTitle = (new Image(Main.class.getResourceAsStream("/images/icons/qTitle_icon.png")));

    public enum Response {

        NO, YES, CLOSED
    };

    private static Response buttonSelected = Response.CLOSED;

    private static ImageView icon = new ImageView();

    static class Dialog extends Stage {

        public Dialog(String title, Stage owner, Scene scene, String iconFile) {
            setTitle(title);
            initStyle(StageStyle.UTILITY);
            initModality(Modality.APPLICATION_MODAL);
            initOwner(owner);
            setResizable(false);
            setScene(scene);
            icon.setImage(new Image(getClass().getResourceAsStream(iconFile)));
        }

        public void showDialog() {
            sizeToScene();
            centerOnScreen();
            showAndWait();
        }
    }

    static class Message extends Text {

        public Message(String msg) {
            super(msg);
            setWrappingWidth(250);
        }
    }

    public static Response showConfirmDialog(Stage owner, String message, String title) {
        VBox vb = new VBox();
        Scene scene = new Scene(vb);
        final Dialog dial = new Dialog(title, owner, scene, "/images/icons/qTitle_icon.png");
        //dial.getIcons().add(qTitle);
        vb.setPadding(new Insets(10));
        vb.setSpacing(10);
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dial.close();
                buttonSelected = Response.YES;
            }
        });
        Button noButton = new Button("No");
        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dial.close();
                buttonSelected = Response.NO;
            }
        });
        BorderPane bp = new BorderPane();
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        buttons.getChildren().addAll(yesButton, noButton);
        bp.setCenter(buttons);
        HBox msg = new HBox();
        msg.setSpacing(5);
        msg.getChildren().addAll(icon, new Message(message));
        vb.getChildren().addAll(msg, bp);
        dial.showDialog();
        return buttonSelected;
    }

    public static void showMessageDialog(Stage owner, String message, String title) {
        showMessageDialog(owner, new Message(message), title);
    }

    public static void showMessageDialog(Stage owner, Node message, String title) {
        VBox vb = new VBox();
        Scene scene = new Scene(vb);
        final Dialog dial = new Dialog(title, owner, scene, "/images/icons/iTitle_icon.png");
        vb.setPadding(new Insets(10));
        vb.setSpacing(10);
        Button okButton = new Button("OK");
        okButton.setAlignment(Pos.CENTER);
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dial.close();
            }
        });
        BorderPane bp = new BorderPane();
        bp.setCenter(okButton);
        HBox msg = new HBox();
        msg.setSpacing(5);
        msg.getChildren().addAll(icon, message);
        vb.getChildren().addAll(msg, bp);
        dial.showDialog();
    }
}
*/