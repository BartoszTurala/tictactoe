package org.example;

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


    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(" -----------").append(System.lineSeparator());
        for (int i = 0; i < SIZE; i++) {
            sb.append("| ");
            for (int j = 0; j < SIZE; j++) {
                sb.append(cells[i][j]).append(" | ");
            }
            sb.append(System.lineSeparator());
            sb.append(" -----------").append(System.lineSeparator());
        }
        System.out.print(sb);
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