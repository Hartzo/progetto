package progetto.methods;

import java.util.ArrayList;

import progetto.model.Game;
import progetto.model.Team;

//This Class contains all the methods that are needed in the Task1 Class
//In this way the program is well written , more modular and easy to maintain
public class task1Utils {

    //Creating a method to calculate the sum of all the devs working in the team_List
    public static int getDevs_Sum(ArrayList<Team> team_List) {
        int result = 0;
        for(Team t : team_List) result += t.getNum_devs();
        return result;
    }

    //Creating a method to check if a Game is underfunded, we need it in the Task1 Class
    public static int isUnderfunded(ArrayList<Game> game_List) {
        int numUnderfunded = 0;

        return numUnderfunded;
    }

}
