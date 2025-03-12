package org.example.battleshipgame.repository;

import org.example.battleshipgame.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
}
