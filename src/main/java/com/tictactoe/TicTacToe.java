package com.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;

    public TicTacToe() {
        this.player1 = new Player('X');
        this.player2 = new Player('O');
        this.currentPlayer = player1;
        this.board = new Board();
    }


    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            start(scanner);
        }
    }



    public void start(Scanner scanner) {
        boolean playAgain = true;
        while (playAgain) {
            board.clear();
            currentPlayer = player1;
            playRound(scanner);
            playAgain = askPlayAgain(scanner);
        }
        System.out.println("Thanks for playing!");
    }

    private void playRound(Scanner scanner) {
        while (true) {
            System.out.println("Current Player: " + currentPlayer.getMarker());
            board.print();

            int row = readCoordinate(scanner, "row (0-2): ");
            int col = readCoordinate(scanner, "column (0-2): ");

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid coordinates. Please try again.");
                continue;
            }
            if (!board.isCellEmpty(row, col)) {
                System.out.println("Cell already taken. Please try again.");
                continue;
            }

            board.place(row, col, currentPlayer.getMarker());

            if (hasWinner()) {
                board.print();
                System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                return;
            }
            if (board.isFull()) {
                board.print();
                System.out.println("It's a draw!");
                return;
            }
            switchCurrentPlayer();
        }
    }

    private int readCoordinate(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            if (!scanner.hasNext()) {
                return -1;
            }
            scanner.next();
            System.out.print("Please enter a number. " + prompt);
        }
        return scanner.nextInt();
    }
    private boolean askPlayAgain(Scanner scanner) {
        System.out.print("Play another game? (y/n): ");
        if (!scanner.hasNext()) {
            return false;
        }
        String input = scanner.next().trim().toLowerCase();
        return input.startsWith("y");
    }


    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }


    public boolean hasWinner() {
        char m = currentPlayer.getMarker();
        for (int i = 0; i < Board.SIZE; i++) {
            if (board.getCell(i, 0) == m
                    && board.getCell(i, 1) == m
                    && board.getCell(i, 2) == m) {
                return true;
            }
            if (board.getCell(0, i) == m
                    && board.getCell(1, i) == m
                    && board.getCell(2, i) == m) {
                return true;
            }
        }
        if (board.getCell(0, 0) == m
                && board.getCell(1, 1) == m
                && board.getCell(2, 2) == m) {
            return true;
        }
        if (board.getCell(0, 2) == m
                && board.getCell(1, 1) == m
                && board.getCell(2, 0) == m) {
            return true;
        }
        return false;
    }


    Player getCurrentPlayer() {
        return currentPlayer;
    }

    Player getPlayer1() {
        return player1;
    }

    Player getPlayer2() {
        return player2;
    }

    Board getBoard() {
        return board;
    }
}