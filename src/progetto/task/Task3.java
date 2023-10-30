package progetto.task;

import java.util.*;

//Importing the task3Utils class in order to use the methods for the task
import progetto.methods.task3Utils;

//Importing the Objects Classes directly into the task
import progetto.model.Game;
import progetto.model.GameTeamAssociations;
import progetto.model.Team;

//This is the class for the Third Task ,
//It has a run method that calls every method of its task3Util class
public class Task3 {

    //Creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Game> game_List, ArrayList<Team> team_List, 
    		 ArrayList<Game> newGame_List, GameTeamAssociations teamsToGames) {
    	
    	//Initializing boolean variables with the methods
        boolean taskPoint1 = task3Utils.findUnassignedTeams(team_List, newGame_List, teamsToGames);
        boolean taskPoint2 = task3Utils.findNewGamesTimeOverlap(game_List, newGame_List);
        boolean taskPoint3 = task3Utils.findContainsUnderfundedTitles(newGame_List, teamsToGames);
    	
    	//If all points are true then we print "VALID" as requested
        if(taskPoint1 && taskPoint2 && taskPoint3) System.out.println("VALID");
        //If just one condition is false then we print "NOT VALID"
        else System.out.println("NOT VALID");
        
        /*DEBUGGING
        if(taskPoint1) System.out.println("The First condition is True");
        if(taskPoint2) System.out.println("The Second condition is True"); 
        if(taskPoint3) System.out.println("The Third condition is True");*/
    }

}
