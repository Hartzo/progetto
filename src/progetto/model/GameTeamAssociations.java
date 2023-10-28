package progetto.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public int getNumberOfTeamsForGame(String gameCode) {
        Map<String, List<Integer>> teamDevelopmentYears = teamsToGames.get(gameCode);

        if (teamDevelopmentYears != null) {
            return teamDevelopmentYears.size();
        }

        return 0; // Ritorna 0 se il gioco non è presente nella mappa.
    }
    
    public List<String> getTeamsForGame(String gameCode) {
        Map<String, List<Integer>> teamDevelopmentYears = teamsToGames.get(gameCode);

        if (teamDevelopmentYears != null) {
            return new ArrayList<>(teamDevelopmentYears.keySet());
        }

        return Collections.emptyList(); // Ritorna una lista vuota se il gioco non è presente nella mappa.
    }
    
    //PAZZIA TOTALE, QUI SI CREA UNA STRINGA DALLE ASSOCIAZIONI QUINDI BISOGNA METTERE IL GIUSTO INPUT
    public static String mapToString(Map<String, List<Integer>> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("(");
            for (Integer value : entry.getValue()) {
                sb.append(value).append(",");
            }
            sb.setLength(sb.length() - 1); // Rimuovi l'ultima virgola
            sb.append("),");
        }
        sb.setLength(sb.length() - 1); // Rimuovi l'ultima virgola
        return sb.toString();
    }

}
