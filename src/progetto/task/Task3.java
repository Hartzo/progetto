package progetto.task;

import java.util.*;

//Importing the task2Utils class in order to use the methods for the task

//Importing the objects classes directly into the task
import progetto.model.Game;
import progetto.model.Team;
import progetto.model.GameTeamAssociations;

public class Task3 {

    //creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Game> game_List, ArrayList<Team> team_List, 
    		 ArrayList<Game> newGame_List, GameTeamAssociations teamsToGames) {




        //Initializing the 3 points with the information of the track, every point of task 3 is a boolean
        boolean taskPoint1 = true;
        boolean taskPoint2 = true;
        boolean taskPoint3 = true;

        //This is our output , if all of these conditions are valid we are printing "VALID"
        if (taskPoint1 && taskPoint2 && taskPoint3) System.out.println("VALID");
            //If only one of them is not valid we are printing "NOT VALID"
        else System.out.println("NOT VALID");

        //DEBUGGING
        if(taskPoint1) System.out.println("The first condition is True");
        if(taskPoint2) System.out.println("The second condition is True");
        if(taskPoint3) System.out.println("The third condition is True");
    }

}
