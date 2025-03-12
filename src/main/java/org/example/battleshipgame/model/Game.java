package org.example.battleshipgame.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus gameStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private Player player2;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Guess> guesses = new ArrayList<Guess>();

    private int currentPlayer;

    public Game() {

    }

    public Game(GameStatus gameStatus, Player player1, Player player2) {
        this.gameStatus = gameStatus;
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = 1;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 0) ? 2 : 1;
    }
}
