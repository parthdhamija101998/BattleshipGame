package sample.Model;

public class Model {

    int numShips = 3, boardSize = 7, shipLength =  3,shipSunk = 0;
    String[][] shipLocation = {{"1","1","1"},{"0","0","0"},{"0","0","0"}};
    String[][] shipHits = {{"","",""},{"","",""},{"","",""}};
    String[][] boardSizeArr = new String[boardSize][boardSize];

    public int getNumShips() {
        return numShips;
    }

    public void setNumShips(int numShips) {
        this.numShips = numShips;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getShipLength() {
        return shipLength;
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

    public int getShipSunk() {
        return shipSunk;
    }

    public void setShipSunk(int shipSunk) {
        this.shipSunk = shipSunk;
    }

    public String[][] getShipLocation() {
        return shipLocation;
    }

    public void setShipLocation(String[][] shipLocation) {
        this.shipLocation = shipLocation;
    }

    public String[][] getShipHits() {
        return shipHits;
    }

    public void setShipHits(String[][] shipHits) {
        this.shipHits = shipHits;
    }

    public String[][] getBoardSizeArr() {
        return boardSizeArr;
    }

    public void setBoardSizeArr(String[][] boardSizeArr) {
        this.boardSizeArr = boardSizeArr;
    }
}
