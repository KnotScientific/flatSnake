import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Timer;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javax.swing.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class VidPlayer extends Application {
    ToggleButton loop;
    ToggleButton test;
    int popupcounter = 1;
    int playcounter = 1;
    Stage window;
    ToggleButton fullscreen;
    ToggleButton playButton;
    ToggleButton mute;
    ToggleButton light;
    Button forward;
    Button stopbutton;
    Button reverse;
    Button skip;
    Button back;
    MediaPlayer mediaPlayer;
    private Label time;
    Duration duration;
    Button fullScreenButton;
    Scene scene;
    Media media;
    double width;
    double height;
    MediaView mediaView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //window
        window = primaryStage;
        window.setTitle("flatSnake");


        Media media = new Media(new File("E:/Downloads/videoplayback.mp4").toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        playcounter=playcounter+1;
        MediaView mediaView = new MediaView(mediaPlayer);

        Rectangle r = new Rectangle();
        r.setX(500);
        r.setY(60);
        r.setWidth(210);
        r.setHeight(60);
        r.setArcWidth(10);
        r.setArcHeight(10);
       // r.setFill(Color.LIGHTGRAY);
        //
        r.setFill(Color.SILVER);
        VBox rec = new VBox();
        rec.getChildren().addAll(r);
        rec.setAlignment(Pos.BOTTOM_CENTER);
        rec.setPadding(new Insets(3,3,3,3));
        //Volume Slider
        double sliderWidth = 120;
        final Slider slider = new Slider();
        slider.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        slider.setMin(0);
        slider.setMax(50);
        slider.setMinWidth(sliderWidth);
        slider.setMaxWidth(sliderWidth);
        final ProgressBar pb = new ProgressBar(0);
        pb.setMinWidth(100);
        pb.setMaxWidth(115);
        pb.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                pb.setProgress(new_val.doubleValue() / 50);
            }
        });
        StackPane pane = new StackPane();
        pane.getChildren().addAll(pb, slider);


        //Mute Button
        mute = new ToggleButton();
        mute.getStylesheets().add(this.getClass().getResource(
                "icon.css"
        ).toExternalForm());
        mute.getStyleClass().add("mute");


        light = new ToggleButton();
        light.getStylesheets().add(this.getClass().getResource(
                "icon.css"
        ).toExternalForm());
        light.getStyleClass().add("light");


        fullscreen = new ToggleButton();
        fullscreen.getStylesheets().add(this.getClass().getResource(
                "icon.css"
        ).toExternalForm());
        fullscreen.getStyleClass().add("fullscreen");
        fullscreen.setOnAction((ActionEvent e) -> {
            if (window.isFullScreen()) {
                window.setFullScreen(false);
            } else {
                window.setFullScreen(true);
            }
        });

        final HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(fullscreen,light,mute,pane);





        loop = new ToggleButton("    ");
        loop.getStylesheets().add(this.getClass().getResource(
                "icon.css"
        ).toExternalForm());
        loop.getStyleClass().add("loop");
        loop.setOpacity(0.5);


        skip = new Button("    ");
        skip.setOpacity(0.5);
        skip.getStylesheets().add(this.getClass().getResource(
                "imagetogglebutton.css"
        ).toExternalForm());
        skip.getStyleClass().add("skip");


        back = new Button("    ");
        back.setOpacity(0.5);
        back.getStylesheets().add(this.getClass().getResource(
                "imagetogglebutton.css"
        ).toExternalForm());
        back.getStyleClass().add("back");


        reverse = new Button("    ");
        reverse.setOpacity(0.5);
        reverse.getStylesheets().add(this.getClass().getResource(
                "imagetogglebutton.css"
        ).toExternalForm());
        reverse.getStyleClass().add("reverse");


        playButton = new ToggleButton("    ");
        playButton.setOpacity(0.5);
        playButton.setRotate(0.0);
        playButton.setOnAction(event-> System.out.print(66))  ;
        playButton.getStylesheets().add(this.getClass().getResource(
                "icon.css"
        ).toExternalForm());
        playButton.setOnAction(event-> {
            playcounter++;
            if (playcounter%2==0) {
                System.out.print("play");
                mediaPlayer.play();
            }
            else {
                System.out.print("play");
                mediaPlayer.pause();
            }

        })  ;

        stopbutton = new Button("    ");
        stopbutton.setOpacity(0.5);
        stopbutton.setOnAction(event-> System.out.print(45))  ;
        stopbutton.getStylesheets().add(this.getClass().getResource(
                "imagetogglebutton.css"
        ).toExternalForm());
        stopbutton.getStyleClass().add("stopbutton");
        stopbutton.setStyle("-fx-background-radius: 0");


        test= new ToggleButton("    ");
        test.setRotate(45.0);
        test.getStylesheets().add(this.getClass().getResource(
                "imagetogglebutton.css"
        ).toExternalForm());
        test.getStyleClass().add(".toggle-button");
        test.setMinSize(10, 18); test.setMaxSize(80, 25);
        test.setOpacity(0.5);
        test.setStyle("-fx-background-radius: 0");
        test.setOnAction(event-> {
            popupcounter++;
            if (popupcounter%2==0) {
                // test.setText("Pop In");
                test.setRotate(0.0);
            }
            else {
                //  test.setText("Pop Out");
                test.setRotate(45.0);
            }
            System.out.print(popupcounter);
            System.out.print("OP is dumb");
            window.setAlwaysOnTop(popupcounter%2==0);
        })  ;



        forward= new Button("    ");
        forward.setOpacity(0.5);
        forward.setAlignment(Pos.BASELINE_RIGHT);
        forward.getStylesheets().add(this.getClass().getResource(
                "imagetogglebutton.css"
        ).toExternalForm());
        forward.getStyleClass().add("forward");



        HBox layout = new HBox(0);
        layout.setPadding(new Insets(0));
        // layout.getChildren().add(stopbutton);
        layout.getChildren().add(loop);
        layout.getChildren().add(back);
        layout.getChildren().add(reverse);
        layout.getChildren().add(playButton);
        layout.getChildren().add(forward);
        layout.getChildren().add(skip);
        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.getChildren().add(test);
        /*layout.getChildren().add(volumeControl);
        volumeControl.setPadding(new Insets(-10)); */
        VBox controls = new VBox();
        controls.getChildren().add(layout);
        controls.getChildren().add(hb);
        hb.setMaxWidth(180.0);
        hb.setPadding(new Insets(5,5,5,8));
        controls.setAlignment(Pos.BOTTOM_CENTER);
        StackPane ctrl = new StackPane();
        ctrl.getChildren().addAll(rec, controls);
        //controls.setStyle("-fx-background-color: ");


       // forwardButton.setOnAction((ActionEvent e) -> {
          //  mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));
       // });


        playButton.setOnMouseMoved(event -> {
            playButton.setOpacity(1.0);
            test.setOpacity(0.5);
            stopbutton.setOpacity(0.5);
            forward.setOpacity(0.5);
            reverse.setOpacity(0.5);
            skip.setOpacity(0.5);
            back.setOpacity(0.5);
            loop.setOpacity(0.5);
        } );
        playButton.setOnMouseExited(event -> {
            playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0);
        } );
        stopbutton.setOnMouseMoved(event -> {
            playButton.setOpacity(0.5);
            test.setOpacity(0.5);
            stopbutton.setOpacity(1.0);
            forward.setOpacity(0.5);
            reverse.setOpacity(0.5);
            skip.setOpacity(0.5);
            back.setOpacity(0.5);
            loop.setOpacity(0.5);
        } );
        stopbutton.setOnMouseExited(event -> {
            playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0);
        } );
        test.setOnMouseMoved(event -> {
            playButton.setOpacity(0.5);
            test.setOpacity(1.0);
            stopbutton.setOpacity(0.5);
            forward.setOpacity(0.5);
            reverse.setOpacity(0.5);
            skip.setOpacity(0.5);
            back.setOpacity(0.5);
            loop.setOpacity(0.5);
        } );
        test.setOnMouseExited(event -> {
            playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0);
        } );
        forward.setOnMouseMoved(event -> {
            playButton.setOpacity(0.5);
            test.setOpacity(0.5);
            stopbutton.setOpacity(0.5);
            forward.setOpacity(1.0);
            reverse.setOpacity(0.5);
            skip.setOpacity(0.5);
            back.setOpacity(0.5);
            loop.setOpacity(0.5);
        } );
        forward.setOnMouseExited(event -> {
            playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0);
        } );
        reverse.setOnMouseMoved(event -> {
            playButton.setOpacity(0.5);
            test.setOpacity(0.5);
            stopbutton.setOpacity(0.5);
            forward.setOpacity(0.5);
            reverse.setOpacity(1.0);
            skip.setOpacity(0.5);
            back.setOpacity(0.5);
            loop.setOpacity(0.5);
        } );
        reverse.setOnMouseExited(event -> {
            playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0);
        } );
        back.setOnMouseMoved(event -> {
            playButton.setOpacity(0.5);
            test.setOpacity(0.5);
            stopbutton.setOpacity(0.5);
            forward.setOpacity(0.5);
            reverse.setOpacity(0.5);
            skip.setOpacity(0.5);
            back.setOpacity(1.0);
            loop.setOpacity(0.5);
        } );
        back.setOnMouseExited(event -> {
            playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0);
        } );
        skip.setOnMouseMoved(event -> {
            playButton.setOpacity(0.5);
            test.setOpacity(0.5);
            stopbutton.setOpacity(0.5);
            forward.setOpacity(0.5);
            reverse.setOpacity(0.5);
            skip.setOpacity(1.0);
            back.setOpacity(0.5);
            loop.setOpacity(0.5);
        } );
        skip.setOnMouseExited(event -> {
          /*  playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0); */
            controls.setOpacity(0.0);
        } );
        loop.setOnMouseMoved(event -> {
            playButton.setOpacity(0.5);
            test.setOpacity(0.5);
            stopbutton.setOpacity(0.5);
            forward.setOpacity(0.5);
            reverse.setOpacity(0.5);
            skip.setOpacity(0.5);
            back.setOpacity(0.5);
            loop.setOpacity(1.0);
        } );
        loop.setOnMouseExited(event -> {
            playButton.setOpacity(0.0);
            test.setOpacity(0.0);
            stopbutton.setOpacity(0.0);
            forward.setOpacity(0.0);
            reverse.setOpacity(0.0);
            skip.setOpacity(0.0);
            back.setOpacity(0.0);
            loop.setOpacity(0.0);
            controls.setOpacity(0.0);
        } );
        stopbutton.setOnMousePressed(event -> {
            stopbutton.setStyle("-fx-background-size: 12px;");
        } );
        stopbutton.setOnMouseReleased(event -> {
            stopbutton.setStyle("-fx-background-size: 16px;");

        });
        reverse.setOnMousePressed(event -> {
            reverse.setStyle("-fx-background-size: 12px;");
            mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
        } );
        reverse.setOnMouseReleased(event -> {
            reverse.setStyle("-fx-background-size: 20px;");
        });
        forward.setOnMousePressed(event -> {
            forward.setStyle("-fx-background-size: 12px;");
            mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));

        } );
        forward.setOnMouseReleased(event -> {
            forward.setStyle("-fx-background-size: 20px;");
        });
        skip.setOnMousePressed(event -> {

            mediaPlayer.seek(mediaPlayer.getTotalDuration());
            mediaPlayer.stop();

            skip.setStyle("-fx-background-size: 12px;");

        } );
        skip.setOnMouseReleased(event -> {
            skip.setStyle("-fx-background-size: 14px;");

        });
        back.setOnMousePressed(event -> {
            back.setStyle("-fx-background-size: 12px;");
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.stop();
        } );
        back.setOnMouseReleased(event -> {
            back.setStyle("-fx-background-size: 14px;");

        });




       VBox vv = new VBox();
       vv.getChildren().addAll(mediaView);
        StackPane VIDEO = new StackPane();
        VIDEO.getChildren().addAll(vv, ctrl);

        VIDEO.setStyle("-fx-background-color: Black");
       // scene = new Scene(vv, 600, 600);
        //scene.setFill(Color.BLACK);
      /*  BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mediaView);
        borderPane.setBottom(controls); */
        Scene scene = new Scene(VIDEO, 800, 500);
        window.setMinHeight(200);
        window.setMinWidth(355.56);
        window.setScene(scene);
        window.sizeToScene();
        window.show();

        //pane.getChildren().addAll(pb, slider);
    }

}