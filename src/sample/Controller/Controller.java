package sample.Controller;

import javafx.stage.Stage;
import sample.Model.Model;
import sample.View.View;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Controller {

    Model model;
    View view;

    public void create(Stage primaryStage, Model model, View view, Controller controller) throws FileNotFoundException {
        this.model = model;
        this.view=view;
        view.start(primaryStage);
        view.addListener(primaryStage, controller);
    }

    public void generateShipLocations() {
        String[] locations = new String[3];
        String[][] shipLocation = model.getShipLocation();
        for (int i = 0; i < model.getNumShips(); i++) {
            do{
                locations = generateShip();
            }while (this.collision(locations));
            shipLocation[i] = locations;
            System.out.println(Arrays.asList(shipLocation[i]));
        }
        model.setShipLocation(shipLocation);
    }

    private boolean collision(String[] locations) {
        String[][] shipLocation = model.getShipLocation();
        for (int i = 0; i < model.getNumShips(); i++) {
            String[] shipLoc = shipLocation[i];
            for (int j = 0; j < locations.length; j++) {
                if (Arrays.asList(shipLoc).indexOf(locations[j]) >= 0) {
                    model.setShipLocation(shipLocation);
                    return true;
                }
            }
        }
        model.setShipLocation(shipLocation);
        return false;
    }

    private String[] generateShip() {

        double direction = Math.floor(Math.random() * 2);
        int row, col;

        if (direction == 1) { // horizontal
            row = (int) Math.floor(Math.random() * model.getBoardSize());
            col = (int) Math.floor(Math.random() * (model.getBoardSize() - model.getShipLength() + 1));
        } else { // vertical
            row = (int) Math.floor(Math.random() * (model.getBoardSize() - model.getShipLength() + 1));
            col = (int) Math.floor(Math.random() * model.getBoardSize());
        }

        String[] newShipLocations = new String[3];

        for (int i = 0; i < model.getShipLength(); i++) {
            if (direction == 1) {
                newShipLocations[i] = (row + "" + (col + i));
            } else {
                newShipLocations[i] = ((row + i) + "" + col);
            }

        }
        return newShipLocations;
    }

    public boolean processGuess(String item) {
        String[][] shipHits = model.getShipHits();
        int shipSunk = model.getShipSunk();
        boolean hit = false;
        if(shipSunk>=3){
            JOptionPane.showMessageDialog(null,"You have sunk all the ships");
            view.disableListeners();
        }else{

            hit = fire(item);
            int shipsSunk =  0;
            if((shipHits[0][0].equals("hit")) && (shipHits[0][1].equals("hit")) && (shipHits[0][2].equals("hit")) ){
                shipsSunk++;
            }
            if((shipHits[1][0].equals("hit")) && (shipHits[1][1].equals("hit")) && (shipHits[1][2].equals("hit")) ){
                shipsSunk++;
            }
            if((shipHits[2][0].equals("hit")) && (shipHits[2][1].equals("hit")) && (shipHits[2][2].equals("hit")) ){
                shipsSunk++;
            }
            if(shipsSunk != shipSunk){
                JOptionPane.showMessageDialog(null,"You have sunk " + shipsSunk + " ships");
                shipSunk++;
            }
            model.setShipSunk(shipSunk);
        }
        return hit;
    }
    public boolean fire(String item) {
        int boardSize = model.getBoardSize();
        String[][] boardSizeArr = model.getBoardSizeArr();
        String[][] shipLocation = model.getShipLocation();
        String[][] shipHits = model.getShipHits();

        String[] itemArr = item.split("");
        boolean hit = false;
        boolean checkHit = true;
        for(int m = 0; m < boardSize ; ++m){
            for(int n = 0; n < boardSize ; ++n){
                if((m == Integer.parseInt(itemArr[0])) && (n == Integer.parseInt(itemArr[1])) ){
                    if(boardSizeArr[m][n] == null){
                        boardSizeArr[m][n] = "hit";
                        checkHit = false;
                        for(int i = 0; i < shipLocation.length ; ++i){
                            for(int j = 0; j < shipLocation[i].length ; ++j){
                                if(shipLocation[i][j].equals(item)){
                                    shipHits[i][j] = "hit";
                                    hit = true;
                                }
                            }
                        }
                    }else{
                        checkHit = true;
                    }
                }
            }
        }

        if(checkHit == true){
            JOptionPane.showMessageDialog(null, "You have already Hit here!");
        }
        model.setShipLocation(shipLocation);
        model.setShipHits(shipHits);
        model.setBoardSizeArr(boardSizeArr);
        return hit;
    }
}
