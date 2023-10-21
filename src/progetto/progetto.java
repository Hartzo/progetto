package progetto;
import java.util.*;

//Importing other packages containing classes with similar features is a better way to structure the project
import progetto.model.*;
import progetto.task.*;

public class progetto {
    //The main method is used to create what we need and store data from input
    //After we store the data we call each of the tasks of the exam project
    public static void main(String[] args) {

        //Creating an Array of Objects for each class of the project is a must for storing data
        ArrayList<Game> game_List = new ArrayList<>();
        ArrayList<Team> team_List = new ArrayList<>();
        //Creating an HashMap to store associations between Games and Teams
        HashMap<String, List<String>> teamsToGames = new HashMap<>();

        //Opening a Scanner with a try command is more professional, remembering to close the scanner is a good practice
        try (Scanner input = new Scanner(System.in)) {

            //I chose a 'do-while' so we can call each task without closing the program
            //if we enter an empty string the program closes
            do {
                //Clearing the ArrayList at the start of each interaction
                game_List.clear();
                team_List.clear();
                teamsToGames.clear();
                //Starting to input data
                String line = input.nextLine();

                //Naming the array of strings "rows" to remember how many lines of input are going to be afterwards
                String[] rows = line.split(" ");
                int numGames = Integer.parseInt(rows[0]); //Simply storing numbers of the text as integers
                int numTeams = Integer.parseInt(rows[1]);

                //Storing Games Data reading n lines as many as numGames
                for(int i = 0; i < numGames; i++) {

                    line = input.nextLine();
                    game_List.add(Game.ReadingData(line));

                }
                //Storing Teams Data reading n lines as many as numTeams
                for(int i = 0; i < numTeams; i++) {

                    line = input.nextLine();
                    team_List.add(Team.ReadingData(line));

                }

                //Reading and storing "Teams-to-Games" associations into the HashMap
                for (int i = 0; i < numGames; i++) {
                    line = input.nextLine();
                    String[] map_Data = line.split(" -> ");
                    String gameCode = map_Data[0];
                    String[] teamCodes = map_Data[1].split(",");

                    //Adding data to the HashMap
                    teamsToGames.put(gameCode, Arrays.asList(teamCodes));
                }

                //Reading last line to identify which task the program has to run
                String[] tasks = input.nextLine().split(" ");
                //Calling each run method of the tasks ; task 2 and 3 have further data to collect
                //Using 'equalsIgnoreCase()' method as muscle memory ,we don't really need it in this program
                if (tasks[0].equalsIgnoreCase("TASK1"))
                    Task1.run(game_List, team_List);

                else if (tasks[0].equalsIgnoreCase("TASK2")) {
                    int p = Integer.parseInt(tasks[1]);
                    int q = Integer.parseInt(tasks[2]);
                    Task2.run(game_List, team_List, p , q);
                }

                else if (tasks[0].equalsIgnoreCase("TASK3")) {
                    int newGames = Integer.parseInt(tasks[1]);
                    //Creating a new array of objects for the new games
                    ArrayList<Game> newGame_List = new ArrayList<>();
                    //Same 'for-loop' as before , we need to pass new games
                    //Having a 'Reading' method makes the code easy to read
                    for(int i = 0; i < newGames; i++) {
                        line = input.nextLine();
                        newGame_List.add(Game.ReadingData(line));
                    }
                    Task3.run(game_List, team_List, newGame_List);
                }

            } while (input.hasNextLine());

        //In the Exam track the input is considered always correct, so we don't need to look up for exceptions
        //By the way a nice 'try-catch' for the scanner is a fancy way to close the program while printing an error
        } catch (Exception e) {
            System.err.println("Error!" + e.getMessage());
            System.err.println("Closing program.");
        }

    }

}
