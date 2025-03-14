package org.example.battleshipgame.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Position {

    private int rowPos;
    private int colPos;

    public Position() {
    }

    public Position(int rowPos, int colPos) {
        this.rowPos = rowPos;
        this.colPos = colPos;
    }

    public int getRowPos() {
        return rowPos;
    }

    public void setRowPos(int row) {
        this.rowPos = row;
    }

    public int getColPos() {
        return colPos;
    }

    public void setColPos(int column) {
        this.colPos = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return rowPos == position.rowPos && colPos == position.colPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowPos, colPos);
    }
}
