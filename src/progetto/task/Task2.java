package progetto.task;

import java.util.*;

//Importing the task2Utils class in order to use the methods for the task
import progetto.methods.task2Utils;

//Importing the objects classes directly into the task
import progetto.model.Game;
import progetto.model.Team;
import progetto.model.GameTeamAssociations;

public class Task2 {

    //creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Game> game_List, ArrayList<Team> team_List, int p, int q,
    		GameTeamAssociations teamsToGames) {

        //Initializing the 4 points with the information of the track, every point of task 2 is a boolean
        boolean taskPoint1 = task2Utils.findTeamsInGames(teamsToGames, p, q);
        boolean taskPoint2 = task2Utils.findTitlesWithSharedTeams(teamsToGames, q);
        boolean taskPoint3 = true;
        boolean taskPoint4 = true;

        //This is our output , if all of these conditions are valid we are printing YES
        if (taskPoint1 && taskPoint2 && taskPoint3 && taskPoint4) System.out.println("YES");
        //If only one of them is not valid we are printing NO
        else System.out.println("NO");

        //DEBUGGING
        if(taskPoint1) System.out.println("The first condition is True");
        if(taskPoint2) System.out.println("The second condition is True");
        if(taskPoint3) System.out.println("The third condition is True");
        if(taskPoint4) System.out.println("The fourth condition is True");
    }

}
