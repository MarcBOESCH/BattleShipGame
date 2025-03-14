package org.example.battleshipgame.service;

import jakarta.transaction.Transactional;
import org.example.battleshipgame.model.*;
import org.example.battleshipgame.repository.GameRepository;
import org.example.battleshipgame.repository.PlayerRepository;
import org.example.battleshipgame.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final ShipRepository shipRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, ShipRepository shipRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.shipRepository = shipRepository;
    }

    @Transactional
    public Game createGame(Player player1) {
        if (player1 == null) {
            throw new IllegalArgumentException("The game needs at least one player to start");
        }
        Game game = new Game();
        game.setGameStatus(GameStatus.CREATED);
        game.setPlayer1(player1);
        game.setCurrentPlayer(1);
        return gameRepository.save(game);
    }
    @Transactional
    public Player addPlayerToGame(Long gameId, Player player) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game with id: " + gameId + " not found"));

        Player savedPlayer = playerRepository.save(player);

        if (game.getPlayer1() == null) {
            game.setPlayer1(savedPlayer);
        } else if (game.getPlayer2() == null) {
            game.setPlayer2(savedPlayer);
        } else {
            throw new IllegalArgumentException("There are already two players assigned to this game!");
        }
        gameRepository.save(game);
        return savedPlayer;
    }

    @Transactional
    public Ship placeShip(Long playerId, List<Position> positions) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player with id: " + playerId + " not found"));
        Ship ship = new Ship(player, positions);
        player.addShip(ship);
        return shipRepository.save(ship);
    }

    @Transactional
    public Game startGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game with id: " + gameId + " not found"));
        if (game.getPlayer1() == null || game.getPlayer2() == null) {
            throw new RuntimeException("Game cannot start without two players");
        }
        game.setGameStatus(GameStatus.IN_PROGRESS);
        return gameRepository.save(game);
    }

}
