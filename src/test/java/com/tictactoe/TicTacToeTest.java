package com.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
    }



    @Test
    void constructor_initializesPlayersAndBoard() {
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertNotNull(game.getBoard());
        assertEquals('X', game.getPlayer1().getMarker());
        assertEquals('O', game.getPlayer2().getMarker());
    }

    @Test
    void constructor_setsPlayer1AsCurrentPlayer() {
        assertSame(game.getPlayer1(), game.getCurrentPlayer());
    }


    @Test
    void switchCurrentPlayer_swapsFromPlayer1ToPlayer2() {
        game.switchCurrentPlayer();
        assertSame(game.getPlayer2(), game.getCurrentPlayer());
    }

    @Test
    void switchCurrentPlayer_swapsBackToPlayer1() {
        game.switchCurrentPlayer();
        game.switchCurrentPlayer();
        assertSame(game.getPlayer1(), game.getCurrentPlayer());
    }

    @Test
    void switchCurrentPlayer_changesCurrentPlayer() {
        Player before = game.getCurrentPlayer();
        game.switchCurrentPlayer();
        assertNotSame(before, game.getCurrentPlayer());
    }


    @Test
    void hasWinner_falseForEmptyBoard() {
        assertFalse(game.hasWinner());
    }

    @Test
    void hasWinner_trueForFullRow() {
        Board board = game.getBoard();
        board.place(0, 0, 'X');
        board.place(0, 1, 'X');
        board.place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_trueForFullColumn() {
        Board board = game.getBoard();
        board.place(0, 1, 'X');
        board.place(1, 1, 'X');
        board.place(2, 1, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_trueForMainDiagonal() {
        Board board = game.getBoard();
        board.place(0, 0, 'X');
        board.place(1, 1, 'X');
        board.place(2, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_trueForAntiDiagonal() {
        Board board = game.getBoard();
        board.place(0, 2, 'X');
        board.place(1, 1, 'X');
        board.place(2, 0, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_falseWhenLineBelongsToOpponent() {
        Board board = game.getBoard();
        board.place(0, 0, 'O');
        board.place(0, 1, 'O');
        board.place(0, 2, 'O');
        assertFalse(game.hasWinner());
    }

    @Test
    void hasWinner_falseForMixedLine() {
        Board board = game.getBoard();
        board.place(0, 0, 'X');
        board.place(0, 1, 'O');
        board.place(0, 2, 'X');
        assertFalse(game.hasWinner());
    }


    @Test
    void start_announcesWinnerOnCompletedGame() {
        // X(1,1) -> O(0,0) -> X(1,0) -> O(0,1) -> X(1,2) wins on row 1.
        String input = String.join("\n",
                "1", "1",
                "0", "0",
                "1", "0",
                "0", "1",
                "1", "2",
                "");

        String output = runGame(input);
        assertTrue(output.contains("Player X wins!"),
                "Expected an X-win message in the output, but got:\n" + output);
    }

    @Test
    void start_printsThanksForPlayingAtEnd() {
        String input = String.join("\n",
                "0", "0",
                "1", "0",
                "0", "1",
                "1", "1",
                "0", "2",
                "");
        String output = runGame(input);
        assertTrue(output.contains("Thanks for playing"),
                "Expected farewell at the end of the game, got:\n" + output);
    }

    private String runGame(String input) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()))) {
            System.setOut(new PrintStream(out));
            game.start(scanner);
        } finally {
            System.setOut(original);
        }
        return out.toString();
    }
}