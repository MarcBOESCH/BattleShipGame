## Start Application:

* Unpack the zip-project in your workspace
* Go to the project folder and open a terminal
* Type: gradle bootRun
* The application should start now

## API-Documentation:

* The API is documented through SwaggerUI
* Find here: http://localhost:8080/swagger-ui.html

## Available Endpoints:
### Game-Management

**POST /api/games** – Creates new game (with the first player)
**POST /api/games/{gameId}/players** – Add another player to the created game
**POST /api/games/{gameId}/players/{playerId}/ships - Place a ship** 

### Player-Management

**POST /api/players** – Create new player
**GET /api/players/{playerId}** – Get player by id
**PUT /api/players/{playerId}** – Change player name
**DELETE /api/players/{playerId}** – Delete player by id

### Guess-Management

**POST /api/games/{gameId}/players/{playerId}/guesses** – Make a guess
**GET /api/games/{gameId}/players/{playerId}/guesses** – Get a list of all guesses made by a player
