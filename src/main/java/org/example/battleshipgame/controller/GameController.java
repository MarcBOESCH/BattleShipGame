package org.example.battleshipgame.controller;

import org.example.battleshipgame.dto.CreateGameRequest;
import org.example.battleshipgame.dto.PlaceShipRequest;
import org.example.battleshipgame.model.*;
import org.example.battleshipgame.service.GameService;
import org.example.battleshipgame.service.GuessService;
import org.example.battleshipgame.service.PlayerService;
import org.example.battleshipgame.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService _gameService;
    private final PlayerService _playerService;
    private final ShipService _shipService;
    private final GuessService _guessService;


    public GameController(GameService gameService, PlayerService playerService, ShipService shipService, GuessService guessService) {
        _gameService = gameService;
        _playerService = playerService;
        _shipService = shipService;
        _guessService = guessService;
    }

    @PostMapping("/battleship")
    public ResponseEntity<Game> createGame(@RequestBody CreateGameRequest request) {
        Player player1 = _playerService.createPlayer(request.getNamePlayer1());
        Player player2 = _playerService.createPlayer(request.getNamePlayer2());
        Game game = _gameService.createGame(player1, player2);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping("/battleship/{gameId}/players/{playerId}/ships")
    public ResponseEntity<Ship> placeShip(@PathVariable Long gameId,
                                          @PathVariable Long playerId,
                                          @RequestBody PlaceShipRequest placeShipRequest) {
        Ship ship = _shipService.placeShip(playerId, placeShipRequest.getPositions());
        return new ResponseEntity<Ship>(ship, HttpStatus.CREATED);
    }

    @PostMapping("/battleship/{gameId}/players/{playerId}/guesses")
    public ResponseEntity<Guess> makeGuess(@PathVariable Long gameId,
                                           @PathVariable Long playerId,
                                           @RequestBody Position position) {
        Guess guess = _guessService.makeGuess(gameId, playerId, position);
        return ResponseEntity.ok(guess);
    }

}
