package progetto.model;

import java.util.*;
import java.util.stream.Collectors;

public class GameTeamAssociations {
	
	//Creating an HashMap to store associations between Games and Teams
	private final HashMap<String, Map<String, List<Integer>>> teamsToGames;

    public GameTeamAssociations() {
        teamsToGames = new HashMap<>();
    }

    public void addAssociation(String gameCode, String teamData) {
        Map<String, List<Integer>> teamDevelopmentYears = new HashMap<>();
        
        String[] teamEntries = teamData.split(",");
        
        for (String teamEntry : teamEntries) {
            String[] parts = teamEntry.trim().split("\\s*\\(.*?\\)\\s*");
            String teamCode = parts[0];
            List<Integer> years;

            if (parts.length > 1) {
                String yearsString = parts[1].replaceAll("[\\(\\)]", "");
                years = Arrays.stream(yearsString.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } else {
                years = Collections.emptyList();
            }

            teamDevelopmentYears.put(teamCode, years);
        }

        teamsToGames.put(gameCode, teamDevelopmentYears);
    }

    public HashMap<String, Map<String, List<Integer>>> getAssociations() {
        return teamsToGames;
    }
    

}
