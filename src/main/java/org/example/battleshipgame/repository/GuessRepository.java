package org.example.battleshipgame.repository;

import org.example.battleshipgame.model.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuessRepository extends JpaRepository<Guess, Long> {
    List<Guess> findAllByGameIdAndPlayerId(Long gameId, Long playerId);
}
