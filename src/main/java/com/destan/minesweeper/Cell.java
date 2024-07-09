package com.destan.minesweeper;

public class Cell {
    private boolean bomb = false;
    private boolean flag = false;
    private boolean revealed = false;
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean getBomb() {
        return this.bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }
}
