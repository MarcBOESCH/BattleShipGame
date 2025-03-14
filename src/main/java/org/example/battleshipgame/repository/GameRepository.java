package org.example.battleshipgame.repository;

import org.example.battleshipgame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
