package progetto.task;

import java.util.*;

//Importing the Utils class in order to use the methods for the task
import progetto.methods.task1Utils;

//Importing the Objects Classes directly into the task
import progetto.model.Game;
import progetto.model.GameTeamAssociations;
import progetto.model.Team;

//This is the Class for the First Task , it has only one method that calls the others
public class Task1 {

    //creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Game> game_List, ArrayList<Team> team_List, 
    		 GameTeamAssociations teamsToGames) {

        //the first point of the task is printing how many objects are in the lists and the sum of devs
        //pretty simple with the size() method
        int num_Games = game_List.size();
        int num_Teams = team_List.size();
        int sum_Devs =  task1Utils.getDevs_Sum(team_List);
        
        //Task point n.2 ,using these two methods we get exactly how many underfunded/overcrowded 
        //games there are in our data
        int numUnderfunded= task1Utils.isUnderfunded(game_List, teamsToGames);
        int numOvercrowded = task1Utils.isOvercrowded(game_List, team_List, teamsToGames);
        
        //Task point n.3 , this method gives us the team code that has the highest number of devs
        String teamWithMaxDevs = task1Utils.findTeamWithMaxDevs(team_List);

        //Now we print the results of the information the TASK1 is asking
        System.out.println(num_Games + " " + num_Teams + " " + sum_Devs);
        System.out.println(numUnderfunded + " " + numOvercrowded);
        System.out.println(teamWithMaxDevs);
      /*  System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();*/

    }

}
