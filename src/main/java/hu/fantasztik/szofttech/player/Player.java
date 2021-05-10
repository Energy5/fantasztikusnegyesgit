package hu.fantasztik.szofttech.player;

import hu.fantasztik.szofttech.state.Difficulty;

public class Player {
    public static int playerGold;
    public Player(Difficulty difficulty) {
        switch(difficulty)
        {
            case EASY:
                playerGold = 500;
                break;
            case MEDIUM:
                playerGold = 300;
                break;
            case HARD:
                playerGold = 100;
                break;
        }
    }

    public static void gainGold(int gold){
        playerGold += gold;
    }
}
