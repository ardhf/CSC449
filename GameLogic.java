package com.example.sosgui;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.lang.Math;

public class GameLogic {

    // Holds all buttons in an array to test game win
    static EditableButton[] buttonArray = new EditableButton[100];
    static int numOfRows = 0;

    public static boolean CreateBoard(int boardSize, Text currTurnText, GridPane gameGrid){

        if(boardSize < 3){
            return false;
        } else if(boardSize > 10){
            return false;
        } else {

            // Keeps track of the number of buttons in the game
            int count = 0;
            // Will place buttons in each row and column for the user defined board size
            for (int row = 0; row < boardSize; row++) {
                for (int column = 0; column < boardSize; column++) {
                    EditableButton btn = new EditableButton(currTurnText);
                    // Array to keep track of all buttons
                    buttonArray[count] = btn;
                    numOfRows = boardSize;
                    count++;
                    // sets the size of each button so that it fills the gameGrid
                    btn.setPrefSize(500, 500);
                    // Adds a new button at column, row
                    gameGrid.add(btn, column, row);
                }
            }
            // Makes the game grid and makes the rows and columns the same size
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(100.0 / boardSize);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(100.0 / boardSize);
            ColumnConstraints column3 = new ColumnConstraints();
            column3.setPercentWidth(100.0 / boardSize);

            RowConstraints row1 = new RowConstraints();
            row1.setPercentHeight(100.0 / boardSize);
            RowConstraints row2 = new RowConstraints();
            row2.setPercentHeight(100.0 / boardSize);
            RowConstraints row3 = new RowConstraints();
            row3.setPercentHeight(100.0 / boardSize);

            gameGrid.getColumnConstraints().addAll(column1, column2, column3);
            gameGrid.getRowConstraints().addAll(row1, row2, row3);
            return true;
        }
    }

    static boolean checkDraw(){
        int turnNum = 0;
        for(int i = 0; i < (numOfRows * numOfRows); i++){
            if(buttonArray[i].getText().equals("S") || buttonArray[i].getText().equals("O")){
                turnNum++;
            }
            if(turnNum == numOfRows * numOfRows){
                System.out.println("Draw Game");
                return true;
            }
        }
        return false;
    }

    // Will check if the user won
    // param isP1Turn: the player who just made a move, true = p1, false = p2

    static int turnNum = 0;
    public static boolean checkWin(boolean isP1Turn, boolean isSimpleGame){
        // In order to check for a win on a variable board size, we need an abstract method that will work for any size

        // Checks for a horizontal win

        // This prints out the indices for each of the rows
        for(int row = 0; row < (numOfRows*numOfRows); row += numOfRows){
            // checks each button in the row
            for(int butIndex = 0; butIndex < numOfRows - 2; butIndex++){
                if(buttonArray[row + butIndex].getText().equals("S") && buttonArray[row + butIndex + 1].getText().equals("O") && buttonArray[row + butIndex + 2].getText().equals("S")){
                    if(isP1Turn) {
                        System.out.println("Blue player has won");
                        buttonArray[row + butIndex].setTextFill(Color.rgb(0, 0, 255));
                        buttonArray[row + butIndex + 1].setTextFill(Color.rgb(0, 0, 255));
                        buttonArray[row + butIndex + 2].setTextFill(Color.rgb(0, 0, 255));
                        return true;
                    } else {
                        System.out.println("Red player has won");
                        buttonArray[row + butIndex].setTextFill(Color.rgb(255, 0, 0));
                        buttonArray[row + butIndex + 1].setTextFill(Color.rgb(255, 0, 0));
                        buttonArray[row + butIndex + 2].setTextFill(Color.rgb(255, 0, 0));
                        return true;
                    }
                }
            }
        }

        // Checks for a vertical win
        for(int i = 0; i < (numOfRows * numOfRows) - (2 * numOfRows); i++){
            if(buttonArray[i].getText().equals("S") && buttonArray[i + numOfRows].getText().equals("O") && buttonArray[i + numOfRows * 2].getText().equals("S")){
                if(isP1Turn) {
                    System.out.println("Blue player has won");
                    buttonArray[i].setTextFill(Color.rgb(0, 0, 255));
                    buttonArray[i + numOfRows].setTextFill(Color.rgb(0, 0, 255));
                    buttonArray[i + numOfRows * 2].setTextFill(Color.rgb(0, 0, 255));
                    return true;
                } else {
                    System.out.println("Red player has won");
                    buttonArray[i].setTextFill(Color.rgb(255, 0, 0));
                    buttonArray[i + numOfRows].setTextFill(Color.rgb(255, 0, 0));
                    buttonArray[i + numOfRows * 2].setTextFill(Color.rgb(255, 0, 0));
                    return true;
                }
            }
        }

        // Checks for a diagonal win
        for(int i = 0; i < (numOfRows * numOfRows) - (2 * numOfRows); i++){
            if(buttonArray[i].getText().equals("S") && buttonArray[(i + numOfRows) + 1].getText().equals("O") && buttonArray[(i + numOfRows * 2) + 2].getText().equals("S")){
                if(isP1Turn) {
                    System.out.println("Blue player has won");
                    buttonArray[i].setTextFill(Color.rgb(0, 0, 255));
                    buttonArray[(i + numOfRows) + 1].setTextFill(Color.rgb(0, 0, 255));
                    buttonArray[(i + numOfRows * 2) + 2].setTextFill(Color.rgb(0, 0, 255));
                    return true;
                } else {
                    System.out.println("Red player has won");
                    buttonArray[i].setTextFill(Color.rgb(255, 0, 0));
                    buttonArray[(i + numOfRows) + 1].setTextFill(Color.rgb(255, 0, 0));
                    buttonArray[(i + numOfRows * 2) + 2].setTextFill(Color.rgb(255, 0, 0));
                    return true;
                }
            }
        }

        checkDraw();

        return true;
    }

}
