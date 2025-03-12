package org.example.battleshipgame.service;

import jakarta.transaction.Transactional;
import org.example.battleshipgame.model.*;
import org.example.battleshipgame.repository.GameRepository;
import org.example.battleshipgame.repository.GuessRepository;
import org.example.battleshipgame.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class GuessService {

    private final GuessRepository _guessRepository;
    private final GameRepository _gameRepository;
    private final PlayerRepository _playerRepository;



    public GuessService(GuessRepository guessRepository, GameRepository gameRepository, PlayerRepository playerRepository) {
        _guessRepository = guessRepository;
        _gameRepository = gameRepository;
        _playerRepository = playerRepository;
    }

    @Transactional
    public Guess makeGuess(Long gameId, Long playerId, Position position) {
        Game game = _gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game with id: " + gameId + " does not exist"));

        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player guessingPlayer;
        Player opponent;

        // Validates that the guessing player is part of the game
        if (player1 != null && player1.getId().equals(playerId)) {
            guessingPlayer = player1;
            opponent = player2;
        } else if (player2 != null && player2.getId().equals(playerId)) {
            guessingPlayer = player2;
            opponent = player1;
        } else {
            throw new RuntimeException("Player with id: " + playerId + " is not part of the game");
        }

        // Validates that the correct player is making the turn
        int currentTurn = game.getCurrentPlayer();
        if ((currentTurn == 1 && !player1.getId().equals(playerId)) ||
            (currentTurn == 2 && !player2.getId().equals(playerId))) {
            throw new RuntimeException("It´s not your turn!");
        }

        // Checks if any of the opponent´s ships are placed on the guessed position
        boolean isHit = false;
        if (opponent.getShips() != null) {
            for (Ship ship: opponent.getShips()) {
                for (Position pos: ship.getPositions()) {
                    if (pos.getRow() == position.getRow() && pos.getColumn() == position.getColumn()) {
                        isHit = true;
                        // Additonal logic for marking parts of the ship as hit
                        break;
                    }
                }
                if (isHit) {
                    break;
                }
            }
        }

        // Creates and saves the guess
        Guess guess = new Guess();
        guess.setGame(game);
        guess.setPlayer(guessingPlayer);
        guess.setPosition(position);
        guess.setHit(isHit);
        _guessRepository.save(guess);

        if (!isHit) {
            game.switchPlayer();
            _gameRepository.save(game);
        }

        return guess;
    }
}

