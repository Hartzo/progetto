package progetto.methods;

import java.util.*;

import progetto.model.Game;
import progetto.model.Team;
import progetto.model.GameTeamAssociations;

public class task3Utils {
	
	public static boolean findUnassignedTeams(ArrayList<Team> team_List, 
			ArrayList<Game> newGame_List, GameTeamAssociations teamsToGames) {
		
		// Calcola quante nuove associazioni di team ai giochi ci sono
	    int newAssociationsSize = teamsToGames.getAssociations().size() - newGame_List.size();

	    // Ottieni solo le nuove associazioni di team ai giochi
	    Map<String, Map<String, List<Integer>>> newAssociations = new HashMap<>();
	    int count = 0;
	    for (Map.Entry<String, Map<String, List<Integer>>> entry : teamsToGames.getAssociations().entrySet()) {
	        if (count >= newAssociationsSize) {
	            newAssociations.put(entry.getKey(), entry.getValue());
	        }
	        count++;
	    }

	    // Itera sulla lista dei team e verifica se ciascun team è presente nelle nuove associazioni
	    for (Team team : team_List) {
	        String teamCode = team.getTeam_code();
	        boolean isAssigned = false;

	        for (Map<String, List<Integer>> gameAssociations : newAssociations.values()) {
	            if (gameAssociations.containsKey(teamCode)) {
	                isAssigned = true;
	                break;
	            }
	        }

	        if (!isAssigned) {
	            return true; // Team non assegnato trovato, restituisci true.
	        }
	    }

	    return false; // Tutti i team sono assegnati, restituisci false.
        
    }

    public static boolean findNewGamesTimeOverlap(ArrayList<Game> game_List, ArrayList<Game> newGame_List) {
    	for (Game newGame : newGame_List) {
            int newGameStartYear = newGame.getGame_start_year();
            int newGameEndYear = newGame.getGame_finish_year();

            for (Game existingGame : game_List) {
                int existingGameStartYear = existingGame.getGame_start_year();
                int existingGameEndYear = existingGame.getGame_finish_year();

                // Controlla le condizioni di sovrapposizione del tempo di sviluppo
                if (!((newGameStartYear >= existingGameStartYear && newGameStartYear <= existingGameEndYear) ||
                    (newGameEndYear >= existingGameStartYear && newGameEndYear <= existingGameEndYear) ||
                    (newGameStartYear <= existingGameStartYear && newGameEndYear >= existingGameEndYear))) {
                    return true; // Sovrapposizione trovata, restituisci false
                }
            }
        }

        return false; // Nessuna sovrapposizione trovata
    }

    public static boolean findContainsUnderfundedTitles(ArrayList<Game> newGame_List, 
    		GameTeamAssociations teamsToGames) {
    	int newAssociationsSize = teamsToGames.getAssociations().size() - newGame_List.size();
        GameTeamAssociations newAssociations = new GameTeamAssociations();
        int count = 0;
        for (Map.Entry<String, Map<String, List<Integer>>> entry : teamsToGames.getAssociations().entrySet()) {
            if (count >= newAssociationsSize) {
                String gameCode = entry.getKey();
                String teamData = GameTeamAssociations.mapToString(entry.getValue());
                newAssociations.addAssociation(gameCode, teamData);
            }
            count++;
        }

        int newUnderfunded = task1Utils.isUnderfunded(newGame_List, newAssociations);

        return newUnderfunded > 0;
    }

}
