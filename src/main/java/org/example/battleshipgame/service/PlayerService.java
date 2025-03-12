package org.example.battleshipgame.service;

import jakarta.transaction.Transactional;
import org.example.battleshipgame.model.Player;
import org.example.battleshipgame.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository _playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        _playerRepository = playerRepository;
    }

    @Transactional
    public Player createPlayer(String name) {
        Player player = new Player(name);
        return _playerRepository.save(player);
    }

    public Player getById(Long id) {
        return  _playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with id: " + id + "does not exist"));
    }

    public List<Player> getAllPlayers() {
        return _playerRepository.findAll();
    }

    @Transactional
    public Player changeName(Long playerId, String newName) {
        Player player = _playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player with id: " + playerId + "does not exist"));
        player.setName(newName);
        return _playerRepository.save(player);
    }

    @Transactional
    public void deletePlayerById(Long id) {
        Player player = _playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with id: " + id + "does not exist"));
        _playerRepository.delete(player);
    }


}
