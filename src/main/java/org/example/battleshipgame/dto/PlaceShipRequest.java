package org.example.battleshipgame.dto;

import org.example.battleshipgame.model.Position;

import java.util.List;

public class PlaceShipRequest {

    private List<Position> positions;

    public PlaceShipRequest() {

    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
