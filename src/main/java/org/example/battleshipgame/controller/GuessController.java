package org.example.battleshipgame.controller;

import org.example.battleshipgame.model.Guess;
import org.example.battleshipgame.model.Position;
import org.example.battleshipgame.service.GuessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games/{gameId}/players/{playerId}/guess")
public class GuessController {

    private final GuessService guessService;

    public GuessController(GuessService guessService) {
        this.guessService = guessService;
    }

    @PostMapping
    public ResponseEntity<Guess> makeGuess(@PathVariable Long gameId,
                                           @PathVariable Long playerId,
                                           @RequestBody Position position) {
        Guess guess = guessService.makeGuess(gameId, playerId, position);
        return ResponseEntity.ok(guess);
    }

    @GetMapping
    public ResponseEntity<List<Guess>> getAllGuesses(@PathVariable Long gameId, @PathVariable Long playerId) {
        List<Guess> guesses = guessService.getAllGuesses(gameId, playerId);
        return ResponseEntity.ok(guesses);
    }
}
