package com.destan.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MinesweeperController {
    @FXML
    private GridPane grid;

    private Board board;

    @FXML
    public void initialize() {
        int size = 10;
        int numBombs = 10;

        board = new Board(numBombs, size);
        board.initialiseCoordinates();
        board.deployBombs();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button button = new Button();
                button.setPrefSize(40, 40);
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> handleCellClick(finalRow, finalCol));
                grid.add(button, col, row);
            }
        }
    }

    private void handleCellClick(int x, int y) {
        Cell cell = board.getCell(x, y);
        if (cell.getBomb()) {
            System.out.println("Bomb! Game Over.");
            // Additional game over logic
        } else {
            System.out.println("Safe Cell: " + x + ", " + y);
            // Additional logic to reveal cell and calculate neighbours
        }
    }
}
