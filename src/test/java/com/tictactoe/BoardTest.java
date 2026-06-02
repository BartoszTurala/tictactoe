package com.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void isCellEmpty_returnsTrue_forFreshBoard() {
        assertTrue(board.isCellEmpty(0, 0));
        assertTrue(board.isCellEmpty(2, 2));
    }

    @Test
    void isCellEmpty_returnsFalse_afterPlace() {
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1));
    }

    @Test
    void isCellEmpty_throwsForOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.isCellEmpty(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> board.isCellEmpty(0, 3));
    }

    @Test
    void place_writesMarkerIntoEmptyCell() {
        board.place(0, 1, 'X');
        assertEquals('X', board.getCell(0, 1));
    }

    @Test
    void place_throwsOnOccupiedCell() {
        board.place(0, 0, 'X');
        assertThrows(IllegalStateException.class, () -> board.place(0, 0, 'O'));
    }

    @Test
    void place_throwsOnOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.place(5, 5, 'X'));
    }

    @Test
    void isFull_returnsFalse_forEmptyBoard() {
        assertFalse(board.isFull());
    }

    @Test
    void isFull_returnsFalse_forPartiallyFilled() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        assertFalse(board.isFull());
    }

    @Test
    void isFull_returnsTrue_whenAllCellsAreOccupied() {
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                board.place(x, y, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void clear_resetsAllCellsBackToEmpty() {
        board.place(0, 0, 'X');
        board.place(2, 2, 'O');
        board.clear();
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                assertTrue(board.isCellEmpty(x, y));
            }
        }
    }

    @Test
    void clear_onEmptyBoard_keepsBoardEmpty() {
        board.clear();
        assertFalse(board.isFull());
        assertTrue(board.isCellEmpty(1, 1));
    }

    @Test
    void getCell_returnsEmptyForUnsetCell() {
        assertEquals(Board.EMPTY, board.getCell(0, 0));
    }

    @Test
    void getCell_throwsForOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.getCell(3, 3));
    }

    @Test
    void print_writesNonEmptyOutputToStdout() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try {
            System.setOut(new PrintStream(out));
            board.print();
        } finally {
            System.setOut(original);
        }
        String printed = out.toString();
        assertTrue(printed.length() > 0);
        assertTrue(printed.contains("|"));
    }

    @Test
    void print_includesPlacedMarkers() {
        board.place(0, 0, 'X');
        board.place(2, 2, 'O');

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try {
            System.setOut(new PrintStream(out));
            board.print();
        } finally {
            System.setOut(original);
        }
        String printed = out.toString();
        assertTrue(printed.contains("X"));
        assertTrue(printed.contains("O"));
    }

    @Test
    void toString_includesColumnHeaders_0_1_2() {
        String rendered = board.toString();
        assertTrue(rendered.contains("0"));
        assertTrue(rendered.contains("1"));
        assertTrue(rendered.contains("2"));
    }

    @Test
    void toString_emptyBoard_containsNoMarkers() {
        String rendered = board.toString();
        assertFalse(rendered.contains("X"));
        assertFalse(rendered.contains("O"));
    }

    @Test
    void toString_includesPlacedMarkers() {
        board.place(0, 0, 'X');
        board.place(2, 2, 'O');
        String rendered = board.toString();
        assertTrue(rendered.contains("X"));
        assertTrue(rendered.contains("O"));
    }

    @Test
    void toString_isMultilineGridLayout() {
        String rendered = board.toString();
        // Header + 3 rows + 4 separators -> at least 7 lines
        long lineCount = rendered.lines().count();
        assertTrue(lineCount >= 7, "Expected at least 7 lines, got " + lineCount);
    }

}