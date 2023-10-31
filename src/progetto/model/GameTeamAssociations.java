package progetto.model;

import java.util.*;
//These two libraries are needed to simplify the pattern of unused characters within the input
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* This object is a complex Data Structure created to store the associations between games and teams.
 * Since teams have particular years of development , Associations need also to store each year.
 * I chose a Hashmap with a map inside to make this sort of links between Game and Team Objects */
public class GameTeamAssociations {

    //Creating an HashMap to store associations between Games and Teams
    private final HashMap<String, Map<String, List<Integer>>> teamsToGames;

    public GameTeamAssociations() {
        teamsToGames = new HashMap<>();
    }

    //This is the reading used to pass input data to the store method "addAssociation"
    public static void ReadingData(GameTeamAssociations teamsToGames,
                                   Scanner input,
                                   int newGames){
        String line;
        for (int i = 0; i < newGames; i++) {
            line = input.nextLine();
            String[] mapData = line.split(" -> ");
            String gameCode = mapData[0];
            String teamData = mapData[1];
            teamsToGames.addAssociation(gameCode, teamData);
        }

    }

    //This method fills the Data Structure with the input data
    public void addAssociation(String gameCode, String teamData) {
        //The second map is created in the store method
        Map<String, List<Integer>> teamDevelopmentYears = new HashMap<>();

        //Data is split by "," , after the split we need to extract the number of years in the "()"
        String[] teamEntries = teamData.split(",");

        //Streaming data input and iterating each entry deleting unnecessary characters
        Arrays.stream(teamEntries).forEach(teamEntry -> {
            String[] parts = teamEntry.trim().split("\\s*\\(.*?\\)\\s*");
            String teamCode = parts[0];
            List<Integer> years = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\((.*?)\\)");
            Matcher matcher = pattern.matcher(teamEntry);
            while (matcher.find()) {
                String yearString = matcher.group(1);
                String[] yearArray = yearString.split(",");
                Arrays.stream(yearArray).map(Integer::parseInt).forEach(years::add);
            }
            teamDevelopmentYears.put(teamCode, years);
        });
        //After all the trimming , we selected each data to store, now we store them in the Object
        teamsToGames.put(gameCode, teamDevelopmentYears);
    }

    //Method to call each time we need the object
    public HashMap<String, Map<String, List<Integer>>> getAssociations() {
        return teamsToGames;
    }


    //This method helps us count the data withing the second map for each game
    public int getNumberOfTeamsForGame(String gameCode) {
        Map<String, List<Integer>> teamDevelopmentYears = teamsToGames.get(gameCode);

        if (teamDevelopmentYears != null) {
            return teamDevelopmentYears.size();
        }

        return 0; // Ritorna 0 se il gioco non è presente nella mappa.
    }

    //Same method as before but gives us the specific team
    public List<String> getTeamsForGame(String gameCode) {
        Map<String, List<Integer>> teamDevelopmentYears = teamsToGames.get(gameCode);

        if (teamDevelopmentYears != null) {
            return new ArrayList<>(teamDevelopmentYears.keySet());
        }
        //Returning an empty list if the game is not in the map
        return Collections.emptyList();
    }

    //Method using string builder to create a string , similar to our input , in order to get a smaller Associations object
    //We need it in the task3Utils.java
    public static String mapToString(Map<String, List<Integer>> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("(");
            for (Integer value : entry.getValue()) {
                sb.append(value).append(",");
            }
            sb.setLength(sb.length() - 1); //Removing the last ","
            sb.append("),");
        }
        sb.setLength(sb.length() - 1); //Removing the last ","
        return sb.toString();
    }

}
