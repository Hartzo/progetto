package progetto.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameTeamAssociations {
	
	//Creating an HashMap to store associations between Games and Teams
	private HashMap<String, Map<String, List<Integer>>> teamsToGames;

    public GameTeamAssociations() {
        teamsToGames = new HashMap<>();
    }

    public void addAssociation(String gameCode, String teamData) {
        Map<String, List<Integer>> teamDevelopmentYears = new HashMap<>();
        
        String[] teamEntries = teamData.split(",");
        
        for (String teamEntry : teamEntries) {
            String[] parts = teamEntry.trim().split("\\s*\\(.*?\\)\\s*");
            String teamCode = parts[0];
            List<Integer> years = new ArrayList<>();

            Pattern pattern = Pattern.compile("\\((.*?)\\)");
            Matcher matcher = pattern.matcher(teamEntry);
            while (matcher.find()) {
                String yearString = matcher.group(1);
                String[] yearArray = yearString.split(",");
                for (String year : yearArray) {
                    years.add(Integer.parseInt(year));
                }
            }

            teamDevelopmentYears.put(teamCode, years);
        }

        teamsToGames.put(gameCode, teamDevelopmentYears);
    }

    public HashMap<String, Map<String, List<Integer>>> getAssociations() {
        return teamsToGames;
    }
    

}
