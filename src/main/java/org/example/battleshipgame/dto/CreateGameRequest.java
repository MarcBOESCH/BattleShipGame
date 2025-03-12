package org.example.battleshipgame.dto;

public class CreateGameRequest {
    private String _namePlayer1;
    private String _namePlayer2;

    public CreateGameRequest() {

    }

    public void setNamePlayer1(String namePlayer1) {
        _namePlayer1 = namePlayer1;
    }

    public String getNamePlayer1() {
        return _namePlayer1;
    }

    public void setNamePlayer2(String namePlayer2) {
        _namePlayer2 = namePlayer2;
    }

    public String getNamePlayer2() {
        return _namePlayer2;
    }
}
