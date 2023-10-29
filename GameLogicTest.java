package com.example.sosgui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void createBoard() {
        // Tests valid board sizes

        assertFalse(GameLogic.CreateBoard(2, null, null));
        assertFalse(GameLogic.CreateBoard(11, null, null));
    }

    @Test
    void checkWinCond(){
        assertFalse(GameLogic.checkDraw());

        assertTrue(GameLogic.checkWin(false, true));
        assertTrue(GameLogic.checkWin(false, false));
    }
}
