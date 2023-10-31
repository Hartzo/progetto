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
        //Iterating through the associations and calling a method to get the exact number of teams for each game
        for (String gameCode : teamsToGames.getAssociations().keySet()) {
            int numberOfTeams = teamsToGames.getNumberOfTeamsForGame(gameCode);
            if (numberOfTeams >= p) {
                titlesWithPTeams++;
            }
        }

        return titlesWithPTeams >= q;
    }

    //Creating a method to find if there are games ,sharing at least one team ,with the same developing time
    public static boolean findGamesWithSharedTeams(GameTeamAssociations teamsToGames, int q, ArrayList<Game> game_List) {
        //Creating a map to store each team for the games List
        HashMap<String, List<String>> teamsInGames = new HashMap<>();

        for(String gameCode : teamsToGames.getAssociations().keySet()) {
            List<String> teamsCode = teamsToGames.getTeamsForGame(gameCode);

            teamsInGames.put(gameCode, teamsCode);
        }
        //Now we store each game with its developing time
        HashMap<String, Integer> gamesYearsDev = new HashMap<>();

        for(String gameCode : teamsInGames.keySet()) {

            int finishYear = Game.fromCode(game_List, gameCode).getGame_finish_year();
            int startYear = Game.fromCode(game_List, gameCode).getGame_start_year();
            gamesYearsDev.put(gameCode, (finishYear - startYear));
        }

        //Counter for the number of games with the same team and the same dev time
        int sharedTeamsAndDevelopmentCounter = 0;

        //Iterating through the games
        for (String gameCode : teamsInGames.keySet()) {
            List<String> teamsCode = teamsInGames.get(gameCode);
            int gameDevelopmentYears = gamesYearsDev.get(gameCode);

            //Confronting the games
            for (String otherGameCode : teamsInGames.keySet()) {
                if (!gameCode.equals(otherGameCode)) {
                    List<String> otherTeamsCode = teamsInGames.get(otherGameCode);
                    int otherGameDevelopmentYears = gamesYearsDev.get(otherGameCode);

                    //Check if at least one team is in common and dev time is the same
                    boolean hasCommonTeam = false;

                    for (String teamCode : teamsCode) {
                        if (otherTeamsCode.contains(teamCode)) {
                            hasCommonTeam = true;
                            break;
                        }
                    }

                    if (hasCommonTeam && gameDevelopmentYears == otherGameDevelopmentYears) {
                        sharedTeamsAndDevelopmentCounter++;
                        break; //If conditions are set we exit the loop
                    }
                }
            }
        }

        //This is our result with the last check with q games
        return sharedTeamsAndDevelopmentCounter >= q;

    }

    //Creating a method to check if there is a number of underfunded games between p and q
    public static boolean findUnderfundedGames(ArrayList<Game> game_List,
                                               GameTeamAssociations teamsToGames, int p , int q) {

        int numUnderfundedGames = task1Utils.isUnderfunded(game_List, teamsToGames);

        return numUnderfundedGames >= p && numUnderfundedGames <= q;

    }

    //Creating a method to check if the teams associated to a game have common skills
    public static boolean findGamesWithSharedTSkill(GameTeamAssociations teamsToGames, int p, ArrayList<Team> team_List) {
        //Counter to keep track of the number of games with teams with common skills
        int titlesWithSharedSkills = 0;

        //Iterate through games
        for (String gameCode : teamsToGames.getAssociations().keySet()) {
            //Getting the associated teams
            List<String> teamsCode = teamsToGames.getTeamsForGame(gameCode);

            //Check if there are at least two teams associated to a game
            if (teamsCode.size() >= 2) {
                //Check if at least two games share a common skill
                boolean sharedSkillsFound = false;
                for (int i = 0; i < teamsCode.size() - 1; i++) {
                    for (int j = i + 1; j < teamsCode.size(); j++) {
                        String teamCode1 = teamsCode.get(i);
                        String teamCode2 = teamsCode.get(j);
                        Team team1 = Team.fromCode(team_List, teamCode1);
                        Team team2 = Team.fromCode(team_List, teamCode2);

                        //Calling the method to verify the strings of skills
                        if (team1.haveSharedSkills(team2)) {
                            sharedSkillsFound = true;
                            break;
                        }
                    }
                    if (sharedSkillsFound) {
                        break;
                    }
                }

                if (sharedSkillsFound) {
                    titlesWithSharedSkills++;
                }
            }
        }

        //The task-point requires if the teams that are sharing the same skill are at least p
        return titlesWithSharedSkills >= p;
    }

}
