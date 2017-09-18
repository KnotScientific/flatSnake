package application;
import java.io.File;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
public class progress  extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Progress Controls");

        double sliderWidth = 200;

        final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);
        slider.setMinWidth(sliderWidth);
        slider.setMaxWidth(sliderWidth);

        final ProgressBar pb = new ProgressBar(0);
        pb.setMinWidth(100);
        pb.setMaxWidth(195);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                pb.setProgress(new_val.doubleValue() / 50);
            }
        });

        StackPane pane = new StackPane();

        pane.getChildren().addAll(pb, slider);

        final HBox hb = new HBox();
        hb.setSpacing(0);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(pane);

        scene.setRoot(hb);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}