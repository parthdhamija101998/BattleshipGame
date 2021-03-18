package sample.View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Controller.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class View extends Application {

    int BOARDSIZE = 7;
    HBox[][] gameBoard = new HBox[BOARDSIZE][BOARDSIZE];
    Image image;
    BackgroundSize backgroundSize;
    BackgroundImage bgimage;
    Background background;
    Pane root;
    Scene scene;
    GridPane gridPane;
    Image shipImage1;
    Image shipImage2;
    ImageView shipImageView1;
    ImageView shipImageView2;
    Pane shipPane1;
    HBox shipPane2;
    Boolean disable = false;
    public void start(Stage primaryStage) throws FileNotFoundException {

        primaryStage.setTitle("BattleShip");
        image = new Image(new FileInputStream("src/images/board.jpg"));
        backgroundSize = new BackgroundSize(90, 90, true, true, true, false);
        bgimage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(bgimage);
        root = new Pane();
        root.setBackground(background);
        scene = new Scene(root,1024,863);

        gridPane = new GridPane();
        root.getChildren().add(gridPane);

        shipImage1 = new Image(new FileInputStream("src/images/ship.png"));
        shipImage2 = new Image(new FileInputStream("src/images/miss.png"));
        shipImageView1 = new ImageView(shipImage1);
        shipImageView2 = new ImageView(shipImage2);
        shipPane1 = new Pane();
        shipPane1.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        double VERTHEIGHT = 95;
        double HORIZWIDTH = 95;

        int i = 0;
        int j = 0;

        for( i = 0; i<BOARDSIZE; i++){
            for( j=0; j<BOARDSIZE; j++){
                gameBoard[i][j] = new HBox();
                gameBoard[i][j].setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                gameBoard[i][j].setMinSize(HORIZWIDTH,VERTHEIGHT);
                gameBoard[i][j].setMaxSize(HORIZWIDTH,VERTHEIGHT);
                gameBoard[i][j].setPadding(new Insets(3,3,3,3));
                gameBoard[i][j].setId(""+j+i);
                gridPane.add(gameBoard[i][j],i,j);
            }
        }

        System.out.println(gridPane.getChildren());

        shipPane2 = new HBox();
        shipPane2.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        shipPane1.getChildren().add(shipImageView1);
        shipPane2.getChildren().add(shipImageView2);
        shipPane1.setMinSize(HORIZWIDTH,VERTHEIGHT);
        shipPane1.setMaxSize(HORIZWIDTH,VERTHEIGHT);
        shipPane2.setMinSize(HORIZWIDTH,VERTHEIGHT);
        shipPane2.setAlignment(Pos.CENTER);



        shipImageView1.relocate(3,35);
        primaryStage.setResizable(false);
        gridPane.relocate(172,97);
        primaryStage.show();
        primaryStage.setScene(scene);
    }

    public void displayHit(Boolean hit, String item) {
        if(disable==true){

        }else{
            BackgroundSize backgroundSize = new BackgroundSize(900,
                    700,
                    true,
                    true,
                    true,
                    false);
            BackgroundImage image1 = new BackgroundImage(shipImage1,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);

            BackgroundImage image2 = new BackgroundImage(shipImage2,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);

            String[] itemArr = item.split("");
            for(int i = 0; i<BOARDSIZE; i++) {
                for (int j = 0; j < BOARDSIZE; j++) {
                    if((i==Integer.parseInt(itemArr[1])) && (j==Integer.parseInt(itemArr[0]))){
                        Background x = gameBoard[i][j].getBackground();
                        if(x == null){
                            if(hit == true){
                                gameBoard[i][j].setBackground(new Background(image1));
                            }else {
                                gameBoard[i][j].setBackground(new Background(image2));
                            }
                        }
                    }
                }
            }
        }

    }

    public void addListener(Stage primaryStage, Controller controller) {
        for(int i = 0; i<BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                gameBoard[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String source = "" + mouseEvent.getSource();
                        boolean hit = controller.processGuess(source.substring(8, 10));
                        displayHit(hit,source.substring(8, 10));
                    }
                });
            }
        }
    }

    public void disableListeners() {
        this.disable = true;
        for(int i = 0; i<BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                gameBoard[i][j].removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler());
            }
        }
    }

    public EventHandler eventHandler(){
        EventHandler handler = new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle (javafx.scene.input.MouseEvent event){
                event.consume();
            }
        };
        return handler;
    }
}
