package progetto.model;

import java.util.*;

//Creating a Team Object with the information provided within the exam files("Traccia A", "Esempi_A")
public class Team {
    //Declaring private variables is better than declaring them public
    //In order to get them visible we need getter/setter methods
    private String team_code;
    private String team_skills;
    private int max_games_;
    private int num_devs;

    //The Object constructor
    public Team(String code, String skills, int maxGames, int numDevs){

        this.team_code = code;
        this.team_skills = skills;
        this.max_games_ = maxGames;
        this.num_devs = numDevs;

    }

    /*
     *Project Lombok can be a useful library to prevent 'code bloat'!
     *with this library getter and setter methods can be generated during compilation with a simple annotation
     *for example writing '@Getter' and '@Setter' helps us avoid writing all these methods.
     *Annotation processor (APT) libraries save a lot of time and are helpful for lazy programmers like me
     */

    public String getTeam_code() { return team_code;}

    public void setTeam_code(String team_code) { this.team_code = team_code;}

    public String getTeam_skills() { return team_skills;}

    public void setTeam_skills(String team_skills) { this.team_skills = team_skills;}

    public int getMax_games() { return max_games_;}

    public void setMax_games(int max_games_) { this.max_games_ = max_games_;}

    public int getNum_devs() { return num_devs;}

    public void setNum_devs(int num_devs) { this.num_devs = num_devs;}

    //Using a method to read data from lines of input
    //At the same time creating an Object that will be stored in a team_list
    public static Team ReadingData(String data) {

        //Using an array of strings to help us analyze data divided by "," .
        //We store them in the variables then we return directly the constructor with those variables
        String[] teams_Data = data.split(",");
        String code = teams_Data[0];
        String skills = teams_Data[1];
        int maxGames = Integer.parseInt(teams_Data[2]);
        int numDevs = Integer.parseInt(teams_Data[3]);

        return new Team(code, skills, maxGames, numDevs);
    }

    //With this method we "simply" compare the team Code with the Object we stored before
    //It's a finder that returns the exact object we want simply searching with the code
    public static Team fromCode(ArrayList<Team> team_List, String teamCode) {
        return team_List.stream()
        		.filter(team -> teamCode.equals(team.getTeam_code()))
        		.findFirst()
        		.orElse(null);
   
    }

}
