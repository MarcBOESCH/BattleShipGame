package org.example.battleshipgame.service;

import jakarta.transaction.Transactional;
import org.example.battleshipgame.model.Player;
import org.example.battleshipgame.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Player createPlayer(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        Player player = new Player(name);
        return playerRepository.save(player);
    }

    public Player getById(Long id) {
        return  playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with id: " + id + "does not exist"));
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    public Player changeName(Long playerId, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player with id: " + playerId + "does not exist"));
        player.setName(newName);
        return playerRepository.save(player);
    }

    @Transactional
    public void deletePlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with id: " + id + "does not exist"));
        playerRepository.delete(player);
    }


}
