package progetto.methods;

import java.util.*;


import progetto.model.Game;
import progetto.model.Team;
import progetto.model.GameTeamAssociations;

//This Class contains all the methods that are needed in the Task2 Class
//In this way the program is well written , more modular and easy to maintain
public class task2Utils {

    //Creating a method to find if there are at least p teams working in q games
    public static boolean findTeamsInGames(GameTeamAssociations teamsToGames, int p, int q){
        int titlesWithPTeams = 0;

        for (String gameCode : teamsToGames.getAssociations().keySet()) {
            int numberOfTeams = teamsToGames.getNumberOfTeamsForGame(gameCode);
            if (numberOfTeams >= p) {
                titlesWithPTeams++;
            }
        }

        return titlesWithPTeams >= q;
    }


    public static boolean findTitlesWithSharedTeams(GameTeamAssociations associations, int q) {
        int matchingTitles = 0;

        // Ottieni la mappa delle associazioni tra giochi e team
        HashMap<String, Map<String, List<Integer>>> teamsToGames = associations.getAssociations();

        // Itera attraverso ogni coppia di giochi
        for (String gameCode1 : teamsToGames.keySet()) {
            for (String gameCode2 : teamsToGames.keySet()) {
                if (!gameCode1.equals(gameCode2)) {
                    Map<String, List<Integer>> teams1 = teamsToGames.get(gameCode1);
                    Map<String, List<Integer>> teams2 = teamsToGames.get(gameCode2);

                    // Verifica se ci sono team in comune
                    boolean hasSharedTeams = false;

                    for (String teamCode : teams1.keySet()) {
                        if (teams2.containsKey(teamCode) && teams1.get(teamCode).equals(teams2.get(teamCode))) {
                            hasSharedTeams = true;
                            break;
                        }
                    }

                    // Incrementa il contatore se ci sono team in comune con lo stesso tempo di sviluppo
                    if (hasSharedTeams) {
                        matchingTitles++;
                    }
                }
            }
        }

        return matchingTitles >= q;
    }



}
