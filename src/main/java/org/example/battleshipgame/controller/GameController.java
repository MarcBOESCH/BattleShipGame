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
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;
    private final PlayerService playerService;
    private final ShipService shipService;
    private final GuessService guessService;


    public GameController(GameService gameService, PlayerService playerService, ShipService shipService, GuessService guessService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.shipService = shipService;
        this.guessService = guessService;
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody CreateGameRequest request) {
        Player player1 = playerService.createPlayer(request.getNamePlayer1());
        Player player2 = playerService.createPlayer(request.getNamePlayer2());

        Game game = gameService.createGame(player1);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping("/{gameId}/players")
    public ResponseEntity<Player> joinGame(@PathVariable Long gameId, @RequestBody String playerName) {
        Player player = playerService.createPlayer(playerName);
        Player savedPlayer = gameService.addPlayerToGame(gameId, player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @PostMapping("/{gameId}/players/{playerId}/ships")
    public ResponseEntity<Ship> placeShip(@PathVariable Long gameId,
                                          @PathVariable Long playerId,
                                          @RequestBody PlaceShipRequest placeShipRequest) {
        Ship ship = shipService.placeShip(gameId, playerId, placeShipRequest.getPositions());
        return new ResponseEntity<>(ship, HttpStatus.CREATED);
    }
}
