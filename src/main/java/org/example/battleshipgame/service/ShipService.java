package org.example.battleshipgame.service;

import org.example.battleshipgame.model.Player;
import org.example.battleshipgame.model.Position;
import org.example.battleshipgame.model.Ship;
import org.example.battleshipgame.repository.GameRepository;
import org.example.battleshipgame.repository.PlayerRepository;
import org.example.battleshipgame.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {
    private final ShipRepository _shipRepository;
    private final PlayerRepository _playerRepository;

    public ShipService(ShipRepository shipRepository, PlayerRepository playerRepository) {
        _shipRepository = shipRepository;
        _playerRepository = playerRepository;
    }

    public Ship placeShip(Long playerId, List<Position> positions) {
        Player player = _playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player with id: " + playerId + "is not in this game"));

        validateShipPlacement(player, positions);

        Ship ship = new Ship(player, positions);
        player.addShip(ship);

        return _shipRepository.save(ship);
    }

    private void validateShipPlacement(Player player, List<Position> positions) {

        // Validates that the ship is in the 10x10 map
        for (Position pos: positions) {
            if (pos.getRow() < 0 || pos.getRow() >= 10 || pos.getColumn() < 0 || pos.getColumn() >= 10) {
                throw new RuntimeException("Ship position out of bound: " + pos);
            }
        }

        for (Ship placedShip: player.getShips()) {
            for (Position placedShipPos: placedShip.getPositions()) {
                for (Position newShipPos: positions) {
                    if (placedShipPos.getRow() == newShipPos.getRow() && placedShipPos.getColumn() == newShipPos.getColumn()) {
                        throw new RuntimeException("There already is another ship placed at position: " + newShipPos);
                    }
                }
            }
        }
    }
}
