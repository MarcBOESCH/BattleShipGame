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
    private final GameRepository _gameRepository;
    private final PlayerRepository _playerRepository;
    private final ShipRepository _shipRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, ShipRepository shipRepository) {
        _gameRepository = gameRepository;
        _playerRepository = playerRepository;
        _shipRepository = shipRepository;
    }

    @Transactional
    public Game createGame(Player player1, Player player2) {
        Game game = new Game(GameStatus.CREATED, player1, player2);
        game.setCurrentPlayer(1);

        return _gameRepository.save(game);
    }
    @Transactional
    public Player addPlayerToGame(Long gameId, Player player) {
        Game game = _gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Spiel nicht gefunden"));

        Player savedPlayer = _playerRepository.save(player);

        if (game.getPlayer1() == null) {
            game.setPlayer1(savedPlayer);
        } else if (game.getPlayer2() == null) {
            game.setPlayer2(savedPlayer);
        } else {
            throw new IllegalArgumentException("There are two players assigned to this game already!");
        }
        _gameRepository.save(game);
        return savedPlayer;
    }

    @Transactional
    public Ship placeShip(Long playerId, List<Position> positions) {
        Player player = _playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player was not found"));
        Ship ship = new Ship(player, positions);
        player.addShip(ship);
        return _shipRepository.save(ship);
    }

    @Transactional
    public Guess makeGuess(Long gameId, Long guessingPlayerId, Position position) {
        Game game = _gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));

        Player opponent = (game.getPlayer1().getId().equals(guessingPlayerId)) ? game.getPlayer2() : game.getPlayer1();

        Guess guess = new Guess();
        guess.setPosition(position);

        //guess.setHit(ergebnis)

        game.switchPlayer();
        _gameRepository.save(game);
        return guess;
    }

    @Transactional
    public Game startGame(Long gameId) {
        Game game = _gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));

        game.setGameStatus(GameStatus.IN_PROGRESS);
        return _gameRepository.save(game);
    }


}
