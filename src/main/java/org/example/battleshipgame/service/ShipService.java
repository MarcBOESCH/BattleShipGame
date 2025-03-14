package org.example.battleshipgame.service;

import org.example.battleshipgame.model.Game;
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
    private final ShipRepository shipRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public ShipService(ShipRepository shipRepository, PlayerRepository playerRepository, GameRepository gameRepository) {
        this.shipRepository = shipRepository;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public Ship placeShip(Long gameId, Long playerId, List<Position> positions) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player with id: " + playerId + " not found"));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game with id: " + gameId + "not found"));
        Long idPlayer1 = (game.getPlayer1() != null) ? game.getPlayer1().getId() : null;
        Long idPlayer2 = (game.getPlayer2() != null) ? game.getPlayer2().getId() : null;

        if (!playerId.equals(idPlayer1) && !playerId.equals(idPlayer2) ) {
            throw new RuntimeException("Player with id: " + playerId + " is not part of this game");
        }

        validateShipPlacement(player, positions);

        Ship ship = new Ship(player, positions);
        player.addShip(ship);

        return shipRepository.save(ship);
    }

    private void validateShipPlacement(Player player, List<Position> positions) {

        // Validates that the ship is in the 10x10 map
        for (Position pos: positions) {
            if (pos.getRowPos() < 0 || pos.getRowPos() >= 10 || pos.getColPos() < 0 || pos.getColPos() >= 10) {
                throw new RuntimeException("Ship position out of bound: " + pos);
            }
        }

        for (Ship placedShip: player.getShips()) {
            for (Position placedShipPos: placedShip.getPositions()) {
                for (Position newShipPos: positions) {
                    if (placedShipPos.getRowPos() == newShipPos.getRowPos() && placedShipPos.getColPos() == newShipPos.getColPos()) {
                        throw new RuntimeException("There already is another ship placed at position: " + newShipPos);
                    }
                }
            }
        }


    }
}
