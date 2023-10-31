package progetto.methods;

import java.util.*;


import progetto.model.Game;
import progetto.model.Team;
import progetto.model.GameTeamAssociations;

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
                                    GameTeamAssociations teamsToGames) {
        return (int) game_List.stream()
                .filter(g -> {
                    Map<String, List<Integer>> teamAssociations;
                    teamAssociations = teamsToGames.getAssociations().get(g.getGame_code());

                    return teamAssociations != null;
                })
                .filter( g-> {
                    int minTeams = g.getMin_teams_perBudget();
                    Map<String, List<Integer>> teamAssociations;
                    teamAssociations = teamsToGames.getAssociations().get(g.getGame_code());
                    int numTeamsForGame = teamAssociations.size();

                    return  numTeamsForGame < minTeams;
                })
                .count();
    }

    //Creating a method to check if a Game is Overcrowded, we need it in the Task1 Class
    public static int isOvercrowded(ArrayList<Game> game_List, ArrayList<Team> team_List,
                                    GameTeamAssociations teamsToGames) {
        return (int) game_List.stream()
                .filter(g -> {
                    Map<String, List<Integer>> teamAssociations;
                    teamAssociations = teamsToGames.getAssociations().get(g.getGame_code());
                    return teamAssociations != null;
                })
                .filter(g -> {
                    int maxDevs = g.getMax_devs_perBudget();
                    Map<String, List<Integer>> teamAssociations;
                    teamAssociations = teamsToGames.getAssociations().get(g.getGame_code());

                    int totalDevsForGame = teamAssociations.keySet().stream()
                            .map(teamCode -> Team.fromCode(team_List, teamCode))
                            .filter(Objects::nonNull)
                            .mapToInt(Team::getNum_devs)
                            .sum();

                    return totalDevsForGame > maxDevs;
                })
                .count();
    }

    public static String findTeamWithMaxDevs(ArrayList<Team> team_List) {
        //Creating a HashMap to store the count of devs for each team
        Map<String, Integer> teamDevsCount = new HashMap<>();

        //Iterating through the team list
        for(Team t :team_List) {
            String teamCode = t.getTeam_code();

            //Updating the count for the max Devs Counter in the HashMap
            //If it doesn't exist, initialize it to 0 and then increment by 1
            teamDevsCount.put(teamCode, teamDevsCount.getOrDefault(teamCode, t.getNum_devs()));
        }
        //Finding the maximum count of Devs among all Teams
        int maxCount = Collections.max(teamDevsCount.values());

        //Creating a list to store team codes with the maximum count of devs
        List<String> teamWithMaxDevs = new ArrayList<>();

        //Iterating through the entries in the HashMap
        for (Map.Entry<String, Integer> entry : teamDevsCount.entrySet()) {
            //If the count of devs for a team matches the maximum count, add it to the list
            if (entry.getValue() == maxCount) {
                teamWithMaxDevs.add(entry.getKey());
            }
        }
        //Sorting the list of teams with the maximum count, using collections makes it simpler
        Collections.sort(teamWithMaxDevs);
        //Check if the list is not empty
        if (!teamWithMaxDevs.isEmpty()) {
            return teamWithMaxDevs.get(0); //Returning the first team code in the sorted list
        } else {
            return "";//If the list is empty, return an empty string as requested from the task
        }
    }

    public static String findTeamWithAllDT(GameTeamAssociations teamsToGames,
                                           ArrayList<Game> game_List) {
        //Creating a HashMap to store the count of teams with all the Developing time
        Map<String, Integer> teamDTCount = new HashMap<>();

        //Iterating through the associations
        for(Game g : game_List) {
            String gameCode = g.getGame_code();

            //Check if the game code exists in the associations
            if(teamsToGames.getAssociations().containsKey(gameCode)) {
                Map<String, List<Integer>> teamsAssociations = teamsToGames.getAssociations().get(gameCode);

                for(String team : teamsAssociations.keySet()) {
                    List<Integer> teamYears = teamsAssociations.get(team);

                    //If there are no years specified, it means the team worked for the whole development time
                    if(teamYears == null || teamYears.isEmpty()) {
                        //Increment the count for this team
                        teamDTCount.put(team, teamDTCount.getOrDefault(team, 0) + 1);
                    }
                }
            }
        }

        //Finding the maximum count of Devs among all Teams
        int maxCount = Collections.max(teamDTCount.values());
        //Creating a list to store team codes with the all developing time
        List<String> teamWithAllDT = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : teamDTCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                teamWithAllDT.add(entry.getKey());
            }
        }

        //Sorting the list of teams with the whole Developing Time , using collections makes it simpler
        Collections.sort(teamWithAllDT);

        //Check if the list is not empty
        if (!teamWithAllDT.isEmpty()) {
            return teamWithAllDT.get(0); //Returning the first team code in the sorted list
        } else {
            return "";//If the list is empty, return an empty string as requested from the task
        }
    }

    public static String findMostFrequentSkill(ArrayList<Team> team_List) {
        //Create a map to store skills and their counts
        Map<String, Integer> skillCount = new HashMap<>();

        //Iterate through the list of teams
        for (Team team : team_List) {
            //Get the team's skills
            String teamSkills = team.getTeam_skills();
            //Split the skills string into individual skills
            String[] skills = teamSkills.split(" ");

            //Count the occurrence of each skill
            for (String skill : skills) {
                skillCount.put(skill, skillCount.getOrDefault(skill, 0) + 1);
            }
        }

        //Variables to track the most frequent skill and its count
        String mostFrequentSkill = "";
        int maxCount = 0;

        //Find the skill with the highest count
        for (Map.Entry<String, Integer> entry : skillCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentSkill = entry.getKey();
            }
        }

        //Return the most frequent skill (or an empty string if no skills are found)
        return mostFrequentSkill;
    }

    //Creating a method to find how many games have at least 5 years of development
    public static int findGamesWith5Years(ArrayList<Game> game_List){
        //Starting with a counter
        int counter = 0;
        //Iterating through all the game list to find the desired time
        for(Game g : game_List){
            //Years of development are the years between the start and the finish of the game development
            int yearsOfDevelopment =(g.getGame_finish_year()) - (g.getGame_start_year());
            //The task point asks the games with at least 5 years of development
            if(yearsOfDevelopment >= 5) counter++;
        }
        //The Counter is our result
        return counter;
    }

    //Creating a method to find how many teams are exceeding the limit of games they can work on
    public static int findTeamWithMaxGames(GameTeamAssociations teamsToGames,
                                           ArrayList<Team> team_List){
        //Starting with a counter
        int counter = 0;

        //Creating a map to keep track how many times a team gets involved in a game
        Map<String, Integer> teamGameCount = new HashMap<>();

        //Iterating through the associations to get how many games the teams are working on
        for (Map<String, List<Integer>> gameAssociations : teamsToGames.getAssociations().values()) {
            for (String teamCode : gameAssociations.keySet()) {
                //Let's increment the counter for each association
                teamGameCount.put(teamCode, teamGameCount.getOrDefault(teamCode, 0) + 1);
            }
        }
        //Iterating through the team list to find the maximum number of games
        for(Team t : team_List){
            int maxGames = t.getMax_games();
            String teamCode = t.getTeam_code();

            if (teamGameCount.containsKey(teamCode) && teamGameCount.get(teamCode) > maxGames) counter++;
        }
        //The Counter is our result
        return counter;
    }

}
