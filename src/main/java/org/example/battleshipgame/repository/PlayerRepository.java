package org.example.battleshipgame.repository;

import org.example.battleshipgame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
