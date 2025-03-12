package org.example.battleshipgame.repository;

import org.example.battleshipgame.model.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuessRepository extends JpaRepository<Guess, Long> {
}
