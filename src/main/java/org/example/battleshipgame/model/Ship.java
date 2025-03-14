package org.example.battleshipgame.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

    @ElementCollection
    @CollectionTable(name = "ship_positions", joinColumns = @JoinColumn(name = "ship_id"))
    private List<Position> positions;

    private boolean sunk;

    public Ship() {
    }

    public Ship(Player player, List<Position> positions) {
        this.player = player;
        this.positions = positions;
        sunk = false;
    }


    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }
}
