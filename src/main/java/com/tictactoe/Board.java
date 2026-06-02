package com.tictactoe;

public class Board {

    public static final int SIZE = 3;
    public static final char EMPTY = ' ';

    private final char[][] cells = new char[SIZE][SIZE];

    public Board() {
        clear();
    }


    public boolean isCellEmpty(int x, int y) {
        validateCoordinates(x, y);
        return cells[x][y] == EMPTY;
    }


    public void place(int x, int y, char marker) {
        validateCoordinates(x, y);
        if (cells[x][y] != EMPTY) {
            throw new IllegalStateException("Cell (" + x + "," + y + ") is not empty.");
        }
        cells[x][y] = marker;
    }


    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    public void clear() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = EMPTY;
            }
        }
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    0   1   2").append(System.lineSeparator());
        sb.append("  +---+---+---+").append(System.lineSeparator());
        for (int i = 0; i < SIZE; i++) {
            sb.append(i).append(" |");
            for (int j = 0; j < SIZE; j++) {
                sb.append(" ").append(cells[i][j]).append(" |");
            }
            sb.append(System.lineSeparator());
            sb.append("  +---+---+---+").append(System.lineSeparator());
        }
        return sb.toString();
    }


    public void print() {
        System.out.print(toString());
    }


    public char getCell(int x, int y) {
        validateCoordinates(x, y);
        return cells[x][y];
    }

    private void validateCoordinates(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            throw new IndexOutOfBoundsException(
                    "Coordinates (" + x + "," + y + ") are out of bounds.");
        }
    }
}