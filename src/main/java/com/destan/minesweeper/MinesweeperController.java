package com.destan.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MinesweeperController {
    @FXML
    private GridPane grid;
    private Board board;
    private int rows;
    private int cols;
    private int mines;

    @FXML
    public void initialize() {
        this.rows = 20;
        this.cols = 20;
        this.mines = 50;

        board = new Board(rows, cols);
        board.initialiseBoard(mines);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button button = new Button();
                button.setPrefSize(40, 40);
                int finalRow = i;
                int finalCol = j;
                button.setOnAction(e -> handleCellClick(finalRow, finalCol));
                grid.add(button, j, i);
            }
        }
    }

    private void handleCellClick(int x, int y) {
        Cell cell = board.getCell(x, y);
        // System.out.println("x: " + x + ", y: " + y);
        if (cell.isMine()) {
            // board.uncoverCell(x, y);
            // updateGrid();
            System.out.println("Bomb! Game Over.");
            // Additional game over logic
        } else {
            board.uncoverCell(x, y);
            updateGrid();
        }
    }

    private void updateGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = board.getCell(i, j);
                Button button = (Button) getNodeFromGridPane(grid, i, j);

                if (cell.isRevealed()) {
                    if (cell.isMine()) {
                        button.setText("M");
                    } else if (cell.getAdjacentMines() > 0) {
                        button.setText(String.valueOf(cell.getAdjacentMines()));
                    } else {
                        button.setText("");
                    }
                    button.setDisable(true);
                }
            }
        }
    }

    private javafx.scene.Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }
}
