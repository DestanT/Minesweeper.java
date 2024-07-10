package com.destan.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private Cell[][] grid;
    private int rows;
    private int cols;
    private List<int[]> allCoordinates;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        this.allCoordinates = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
                allCoordinates.add(new int[]{i, j});
            }
        }
    }

    public void initialiseBoard(int mines) {
        placeMines(mines);
        calculateAdjacentMines();
    }

    private void placeMines(int mines) {
        Collections.shuffle(allCoordinates);

        for (int i = 0; i < mines; i++) {
            int[] coordinate = allCoordinates.get(i);
            grid[coordinate[0]][coordinate[1]].setMine(true);
        }
    }

    private void calculateAdjacentMines() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isMine()) {
                    int adjacentMines = 0;

                    for (int k = 0; k < 8; k++) {
                        int xOff = i + dx[k];
                        int yOff = j + dy[k];

                        if (isInBounds(xOff, yOff)) {
                            if (grid[xOff][yOff].isMine()) {
                                adjacentMines++;
                            }
                        }
                    }

                    grid[i][j].setAdjacentMines(adjacentMines);
                }
            }
        }
    }

    private boolean isInBounds(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    public Cell getCell(int x, int y) {
        if (isInBounds(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    public void uncoverCell(int row, int col) {
        Cell cell = getCell(row, col);
        if (cell != null && !cell.isRevealed() && !cell.isFlagged()) {
            cell.reveal();
            System.out.println("Revealing cell at (" + row + ", " + col + ")");
            
            if (cell.getAdjacentMines() == 0 && !cell.isMine()) {
                uncoverAdjacentCells(row, col);
            }
        }
    }

    private void uncoverAdjacentCells(int row, int col) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            
            if (isInBounds(newRow, newCol)) {
                Cell adjacentCell = getCell(newRow, newCol);
                if (adjacentCell != null && !adjacentCell.isRevealed() && !adjacentCell.isFlagged()) {
                    adjacentCell.reveal();

                    if (adjacentCell.getAdjacentMines() == 0 && !adjacentCell.isMine()) {
                        uncoverAdjacentCells(newRow, newCol);
                    }
                }
            }
        }
    }
}
