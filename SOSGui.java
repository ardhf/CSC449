package com.example.sosgui;    // Java program to create RadioButton and add it to the stage
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.sosgui.GameLogic.*;

class EditableButton extends Button {

    public static Boolean isUser1Turn = true;

    public EditableButton(Text currTurn) {
        setOnMouseClicked(e -> {
            // Guard clause to make sure that the move the user is attempting is a valid move
            if(!Objects.equals(getText(), "")){
                System.out.println("Invalid Move");
                return;
            }

            // Will put the players S or O in the spot they chose
            if(isUser1Turn){
                setText(RadioButtons.getUser1Move());
                currTurn.setText("Red Player's Turn");
            } else{
                setText(RadioButtons.getUser2Move());
                currTurn.setText("Blue Player's Turn");
            }

            checkWin(isUser1Turn, true);

            isUser1Turn = !isUser1Turn;
        });
    }
}

class RadioButtons extends RadioButton {
    public static String pubUser1Move = "";
    public static String pubUser2Move = "";
    public void setUser1Move(String user1Move){
        pubUser1Move = user1Move;
    }

    public static String getUser1Move(){
        return pubUser1Move;
    }

    public void setUser2Move(String user2Move){
        pubUser2Move = user2Move;
    }

    public static String getUser2Move(){
        return pubUser2Move;
    }

    public RadioButtons(String text) {
        setText(text);
        // Initializes the move for both users to be S
        setUser1Move("S");
        setUser2Move("S");
        // Will activate whenever a radioButton is clicked
        setOnMouseClicked(e -> {
            // Gets the vBox that holds the radio buttons and sees if it contains the "Blue Player" or "Red Player"
            if((getParent().getChildrenUnmodifiable()).toString().contains("Blue Player")){
                // Gets the text value of the selected radioButton
                if(isSelected()){
                    // Sets the move of the user to their selection
                    setUser1Move(text);
                }
            } else {
                if(isSelected()){
                    setUser2Move(text);
                }
            }
        });
    }
}

public class SOSGui extends Application {

    // launch the application
    public void start(Stage s) {
        // set title for the window
        s.setTitle("SOS Game");

        // Creates a toggle group for the game selection
        final ToggleGroup gameSelectTG = new ToggleGroup();
        Text gameSelectText = new Text(50, 50, "SOS");
        // Creates radioButtons for the game selection
        RadioButton simpleGame = new RadioButton("Simple Game");
        RadioButton generalGame = new RadioButton("General Game");
        // Sets the default selection to a simple game
        simpleGame.setSelected(true);
        // Adds the game selections to a toggle group
        simpleGame.setToggleGroup(gameSelectTG);
        generalGame.setToggleGroup(gameSelectTG);

        // The box that houses the option for simple or general game
        HBox gameSelectBox = new HBox();
        gameSelectBox.setAlignment(Pos.CENTER);
        gameSelectBox.getChildren().addAll(gameSelectText, simpleGame, generalGame);
        gameSelectBox.setSpacing(10);

        // Box to house the board size elements
        Label boardSizeLabel = new Label("Board Size:");
        TextField boardSizeTB = new TextField();
        boardSizeTB.setPrefWidth(35);

        // HorizontalBox that lays children out in horizontal row
        HBox topRightBox = new HBox();
        topRightBox.setAlignment(Pos.CENTER);
        topRightBox.getChildren().addAll(boardSizeLabel, boardSizeTB);
        topRightBox.setSpacing(10);

        // Creates a toggle group for the User 1 radioButtons
        final ToggleGroup user1TG = new ToggleGroup();
        //Make text to say who is the player and S or O
        Text User1Text = new Text(50, 50, "Blue Player");
        RadioButtons User1S = new RadioButtons("S");
        RadioButtons User1O = new RadioButtons("O");
        // Initializes the S to be selected for user1
        User1S.setSelected(true);
        // Add the radioButtons to the user 2 toggle group
        User1S.setToggleGroup(user1TG);
        User1O.setToggleGroup(user1TG);

        // Make a vertical box to allow left user to select S or O
        VBox leftUserBox = new VBox();
        leftUserBox.setAlignment(Pos.CENTER);
        leftUserBox.getChildren().addAll(User1Text, User1S, User1O);
        leftUserBox.setSpacing(10);

        // Creates a toggle group for the User 2 radioButtons
        final ToggleGroup user2TG = new ToggleGroup();
        //Make text to say who is the player and S or O
        Text User2Text = new Text(50, 50, "Red Player");
        RadioButtons User2S = new RadioButtons("S");
        RadioButtons User2O = new RadioButtons("O");
        // Initializes the S to be selected for user1
        User2S.setSelected(true);
        // Add the radioButtons to the user 2 toggle group
        User2S.setToggleGroup(user2TG);
        User2O.setToggleGroup(user2TG);

        // Make a vertical box to allow right user to select S or O
        VBox rightUserBox = new VBox();
        rightUserBox.setAlignment(Pos.CENTER);
        rightUserBox.getChildren().addAll(User2Text, User2S, User2O);
        rightUserBox.setSpacing(10);

        // Parent grid 3x3 to house all UI elements
        GridPane parentGrid = new GridPane();
        ColumnConstraints parentCol1 = new ColumnConstraints();
        parentCol1.setPercentWidth(25);
        ColumnConstraints parentCol2 = new ColumnConstraints();
        parentCol2.setPercentWidth(50);
        ColumnConstraints parentCol3 = new ColumnConstraints();
        parentCol3.setPercentWidth(25);

        RowConstraints parentRow1 = new RowConstraints();
        parentRow1.setPercentHeight(20);
        RowConstraints parentRow2 = new RowConstraints();
        parentRow2.setPercentHeight(60);
        RowConstraints parentRow3 = new RowConstraints();
        parentRow3.setPercentHeight(20);

        parentGrid.getColumnConstraints().addAll(parentCol1, parentCol2, parentCol3);
        parentGrid.getRowConstraints().addAll(parentRow1, parentRow2, parentRow3);

        GridPane gameGrid = new GridPane();

        // Creates text and a HBox in order to display turn and center in the grid pane
        Text currTurnText = new Text("Blue Player's Turn");
        HBox currTurnBox = new HBox();
        currTurnBox.setAlignment(Pos.CENTER);
        currTurnBox.getChildren().add(currTurnText);
        currTurnBox.setSpacing(10);

        AtomicInteger boardSize = new AtomicInteger(3);

        // Creates the board size variable
        boardSize = new AtomicInteger(3);

        // Creates the new game button and the game grid
        Button newGameBtn = new Button("New Game");
        AtomicInteger finalBoardSize = boardSize;
        newGameBtn.setOnAction(e -> {
            finalBoardSize.set(Integer.parseInt(boardSizeTB.getText()));
            GameLogic.CreateBoard(finalBoardSize.intValue(), currTurnText, gameGrid);
        });

        HBox newGameBtnBox = new HBox();
        newGameBtnBox.setAlignment(Pos.CENTER);
        newGameBtnBox.getChildren().add(newGameBtn);
        newGameBtnBox.setSpacing(10);

        // Adds the element into the grid (column, row)
        parentGrid.add(gameSelectBox, 1, 0);
        parentGrid.add(topRightBox, 2, 0);

        parentGrid.add(leftUserBox, 0, 1);
        parentGrid.add(gameGrid, 1, 1);
        parentGrid.add(rightUserBox, 2, 1);

        parentGrid.add(currTurnBox, 1, 2);
        parentGrid.add(newGameBtnBox, 2, 2);

        // create a scene
        // Scene(tile, width, height)
        Scene sc = new Scene(parentGrid, 800, 450);

        // set the scene
        s.setScene(sc);

        s.show();

    }

    public static void main(String[] args)
    {
        // launch the application
        launch(args);
    }
}
