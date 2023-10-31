package progetto.methods;

import java.util.*;

import progetto.model.Game;
import progetto.model.Team;
import progetto.model.GameTeamAssociations;

//This Class contains all the methods that are needed in the Task3 Class
//In this way the program is well written , more modular and easy to maintain
public class task3Utils {

    //A method created to check if there are teams unassigned to the new game list
    public static boolean findUnassignedTeams(ArrayList<Team> team_List,
                                              ArrayList<Game> newGame_List, GameTeamAssociations teamsToGames) {

        //Calculate how many new team to games associations there are
        int newAssociationsSize = teamsToGames.getAssociations().size() - newGame_List.size();

        //Getting only the new team to games associations
        Map<String, Map<String, List<Integer>>> newAssociations = new HashMap<>();
        int count = 0;
        for (Map.Entry<String, Map<String, List<Integer>>> entry : teamsToGames.getAssociations().entrySet()) {
            if (count >= newAssociationsSize) {
                newAssociations.put(entry.getKey(), entry.getValue());
            }
            count++;
        }

        //Iterating through the team list and checking if each team is in the new teams to games association
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
                return true; //Team not associated found
            }
        }

        return false; //All teams have been associated, returning false

    }

    //Creating a method to check if the new games in the list have a developing time that overlaps with the other games
    public static boolean findNewGamesTimeOverlap(ArrayList<Game> game_List, ArrayList<Game> newGame_List) {
       //Iterating through the new game list to get developing time
        for (Game newGame : newGame_List) {
            int newGameStartYear = newGame.getGame_start_year();
            int newGameEndYear = newGame.getGame_finish_year();
            //Iterating through the original game list
            for (Game existingGame : game_List) {
                int existingGameStartYear = existingGame.getGame_start_year();
                int existingGameEndYear = existingGame.getGame_finish_year();

                //Check if the overlapping of dev time conditions are met
                if (!((newGameStartYear >= existingGameStartYear && newGameStartYear <= existingGameEndYear) ||
                        (newGameEndYear >= existingGameStartYear && newGameEndYear <= existingGameEndYear) ||
                        (newGameStartYear <= existingGameStartYear && newGameEndYear >= existingGameEndYear))) {
                    return true; //Overlapping found , returning false
                }
            }
        }

        return false; //No overlapping has been found
    }

    //Same method for the task 1, in this case we are checking underfunded games in the new game list
    public static boolean findContainsUnderfundedGames(ArrayList<Game> newGame_List,
                                                        GameTeamAssociations teamsToGames) {
        int newAssociationsSize = teamsToGames.getAssociations().size() - newGame_List.size();
        //We need only the new associations of the new game list
        GameTeamAssociations newAssociations = new GameTeamAssociations();
        int count = 0;
        //We are storing only the new associations by giving a counter and the size of the new GameTeamAssociations object
        for (Map.Entry<String, Map<String, List<Integer>>> entry : teamsToGames.getAssociations().entrySet()) {
            if (count >= newAssociationsSize) {
                String gameCode = entry.getKey();
                //In order to store new associations we are changing the data of the map to strings
                String teamData = GameTeamAssociations.mapToString(entry.getValue());
                newAssociations.addAssociation(gameCode, teamData);
            }
            count++;
        }
        //Calling the Underfunded method of task1Utils.java
        int newUnderfunded = task1Utils.isUnderfunded(newGame_List, newAssociations);

        return newUnderfunded > 0;
    }

}
