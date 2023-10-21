package progetto.methods;

import java.util.ArrayList;

import progetto.model.Game;
import progetto.model.Team;

//This Class contains all the methods that are needed in the Task1 Class
//In this way the program is well written , more modular and easy to maintain
public class task1Utils {

    //Creating a method to calculate the sum of all the devs working in the team_List
    public static int getDevs_Sum(ArrayList<Team> team_List) {
        int result = 0;
        for(Team t : team_List) {result += t.getNum_devs();}
        return result;
    }

    //Creating a method to check if a Game is Underfunded, we need it in the Task1 Class
    public static int isUnderfunded(ArrayList<Game> game_List,
    		HashMap<String, List<String>> teamsToGames) {
    	
        int numUnderfunded = 0;
        
        for(Game g : game_List) {
        	int minTeams = g.getMin_teams_perBudget();
        	String gameCode = g.getGame_code();
        	
        	if (teamsToGames.containsKey(gameCode)) {
        		
        		int numTeamsForGame = teamsToGames.get(gameCode).size();
            	
            	if (numTeamsForGame < minTeams) numUnderfunded++;	
        	}
        	
        	else{
        	    //printing an error if the key hasn't been found in the HashMap
        	    System.out.println("No association found for the Game: " + gameCode);
        	}
        }

        return numUnderfunded;
    }
    
    //Creating a method to check if a Game is Overcrowded, we need it in the Task1 Class
    public static int isOvercrowded(ArrayList<Game> game_List, ArrayList<Team> team_List,
    		HashMap<String, List<String>> teamsToGames) {
    	return (int) game_List.stream()
                .filter(g -> teamsToGames.containsKey(g.getGame_code()))
                .filter(g -> {
                    int maxDevs = g.getMax_devs_perBudget();
                    List<String> teamCodes = teamsToGames.get(g.getGame_code());
                    
                    int totalDevsForGame = teamCodes.stream()
                            .map(teamCode -> Team.fromCode(team_List, teamCode))
                            .filter(Objects::nonNull)
                            .mapToInt(Team::getNum_devs)
                            .sum();
                    
                    return totalDevsForGame > maxDevs;
                })
                .count();
    }

}
