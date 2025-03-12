package org.example.battleshipgame.dto;

import org.example.battleshipgame.model.Position;

import java.util.List;

public class PlaceShipRequest {

    private List<Position> _positions;

    public PlaceShipRequest() {

    }

    public List<Position> getPositions() {
        return _positions;
    }

    public void setPositions(List<Position> positions) {
        _positions = positions;
    }
}
