package progetto.task;

import java.util.*;

//Importing the task2Utils class in order to use the methods for the task
import progetto.methods.task2Utils;

//Importing the objects classes directly into the task
import progetto.model.Game;
import progetto.model.Team;
import progetto.model.GameTeamAssociations;

/* This is the Class for the Second Task , there are four conditions and each one of them
 * calls a specific method to check the condition if it's true or false */
public class Task2 {

    //Creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Game> game_List, ArrayList<Team> team_List, int p, int q,
                           GameTeamAssociations teamsToGames) {

        //Initializing the 4 points as booleans with the track's information
        boolean taskPoint1 = task2Utils.findTeamsInGames(teamsToGames, p, q);
        boolean taskPoint2 = task2Utils.findGamesWithSharedTeams(teamsToGames, q, game_List);
        boolean taskPoint3 = task2Utils.findUnderfundedGames(game_List, teamsToGames, p, q);
        boolean taskPoint4 = task2Utils.findGamesWithSharedTSkill(teamsToGames, p, team_List);

        //This is our output , if all of these conditions are valid we are printing YES
        if (taskPoint1 && taskPoint2 && taskPoint3 && taskPoint4) System.out.println("YES");
            //If only one of them is not valid we are printing NO
        else System.out.println("NO");
    }

}
