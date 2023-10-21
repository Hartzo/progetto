package progetto.model;

//Creating a Game Object with the information provided within the exam files("Traccia A", "Esempi_A")
public class Game {
    //Declaring private variables is better than declaring them public
    //In order to get them visible we need getter/setter methods
    private String game_code;
    private String game_title;
    private String game_genre;
    private int game_start_year;
    private int game_finish_year;
    private int max_devs_perBudget;
    private int min_teams_perBudget;

    //The Object constructor
    public Game(String code, String titles, String genres, int StartYear,
                int FinishYear, int maxDevs, int minTeams){

        this.game_code = code;
        this.game_title = titles;
        this.game_genre = genres;
        this.game_start_year = StartYear;
        this.game_finish_year = FinishYear;
        this.max_devs_perBudget = maxDevs;
        this.min_teams_perBudget = minTeams;

    }

    /*
     *Project Lombok can be a useful library to prevent 'code bloat'!
     *with this library getter and setter methods can be generated during compilation with a simple annotation
     *for example writing '@Getter' and '@Setter' helps us avoid writing all these methods.
     *Annotation processor (APT) libraries save a lot of time and are helpful for lazy programmers like me
     */

    public String getGame_code() { return game_code;}

    public void setGame_code(String game_code) { this.game_code = game_code;}

    public String getGame_title() { return game_title;}

    public void setGame_title(String game_title) { this.game_title = game_title;}

    public String getGame_genre() { return game_genre;}

    public void setGame_genre(String game_genre) { this.game_genre = game_genre;}

    public int getGame_start_year() { return game_start_year;}

    public void setGame_start_year(int game_start_year) { this.game_start_year = game_start_year;}

    public int getGame_finish_year() { return game_finish_year;}

    public void setGame_finish_year(int game_finish_year) { this.game_finish_year = game_finish_year;}

    public int getMax_devs_perBudget() { return max_devs_perBudget;}

    public void setMax_devs_perBudget(int max_devs_perBudget) {
        this.max_devs_perBudget = max_devs_perBudget;
    }

    public int getMin_teams_perBudget() { return min_teams_perBudget;}

    public void setMin_teams_perBudget(int min_teams_perBudget) {
        this.min_teams_perBudget = min_teams_perBudget;
    }

    //Using a method to read data from lines of input
    //At the same time creating an Object that will be stored in a game_list
    public static Game ReadingData(String data) {

        //Using an array of strings to help us analyze data divided by "," .
        //We store them in the variables then we return directly the constructor with those variables
        String[] games_Data = data.split(",");
        String code = games_Data[0];
        String titles = games_Data[1];
        String genres = games_Data[2];
        int StartYear = Integer.parseInt(games_Data[3]);
        int FinishYear = Integer.parseInt(games_Data[4]);
        int maxDevs = Integer.parseInt(games_Data[5]);
        int minTeams = Integer.parseInt(games_Data[6]);

        return new Game(code, titles, genres, StartYear, FinishYear, maxDevs, minTeams);
    }

}
