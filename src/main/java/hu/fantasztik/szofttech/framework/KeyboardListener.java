package hu.fantasztik.szofttech.framework;

import hu.fantasztik.szofttech.player.Player;
import hu.fantasztik.szofttech.state.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {

        if (Game.turnFirstKeyPress) {
            Game.turnFirstKeyPress = false;

            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    Window.pause();
                    Game.turnFirstKeyPress = true;
                    break;
                case KeyEvent.VK_X:
                    Game.turnFirstKeyPress = true;
                    Window.pause();
                    Window.gameEnd();
                    break;
                case KeyEvent.VK_H:
                        Player.playerGold += 1000;
                        Window.updatePlayerGold();
                        break;
                default:
                    break;
            }
        }
    }

}