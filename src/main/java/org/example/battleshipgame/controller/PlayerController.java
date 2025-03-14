package org.example.battleshipgame.controller;

import org.example.battleshipgame.model.Player;
import org.example.battleshipgame.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody String playerName) {
        Player player = playerService.createPlayer(playerName);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayer(@RequestParam Long playerId) {
        Player player = playerService.getById(playerId);
        return ResponseEntity.ok(player);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<Player> changePlayerName(@RequestParam Long playerId, @RequestBody String newPlayerName) {
        Player updatedPlayer = playerService.changeName(playerId, newPlayerName);
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayer(@RequestParam Long playerId) {
        playerService.deletePlayerById(playerId);
        return ResponseEntity.noContent().build();
    }
}
