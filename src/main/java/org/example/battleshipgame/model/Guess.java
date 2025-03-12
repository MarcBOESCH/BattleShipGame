package org.example.battleshipgame.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guesses")
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Embedded
    private Position position;

    @Column(name = "is_hit")
    private Boolean isHit;

    public Guess() {
    }

    public Guess(Game game, Player player, Position position) {
        this.game = game;
        this.player = player;
        this.position = position;
        isHit = null;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getHit() {
        return isHit;
    }

    public void setHit(Boolean isHit) {
        this.isHit = isHit;
    }
}
