package com.destan.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private int numBombs;
    private int size;
    private Cell[][] grid;
    public List<Cell> coordinates;

    public Board(int numBombs, int size) {
        this.numBombs = numBombs;
        this.size = size;
        this.grid = new Cell[size][size];
        this.coordinates = new ArrayList<>();
    }

    public void initialiseCoordinates() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Cell cell = new Cell(i, j);
                this.grid[i][j] = cell;
                this.coordinates.add(cell);
            }
        }
    }

    public void deployBombs() {
        int currentBomb = 0;
        Collections.shuffle(coordinates);
        while (currentBomb < this.numBombs) {
            this.coordinates.get(currentBomb).setBomb(true);
            currentBomb++;
        }
    }

    public Cell getCell(int x, int y) {
        return this.grid[x][y];
    }
}
