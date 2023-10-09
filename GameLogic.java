package com.example.sosgui;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class GameLogic {
    public static boolean CreateBoard(int boardSize, Text currTurnText, GridPane gameGrid){

        if(boardSize < 3){
            return false;
        } else if(boardSize > 10){
            return false;
        } else {

            for (int row = 0; row < boardSize; row++) {
                for (int column = 0; column < boardSize; column++) {
                    EditableButton btn = new EditableButton(currTurnText);
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
}
