package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyHandler implements KeyListener{ // Nhận đầu vào bàn phím // The listener interface để nhận các sự kiện bàn phím (tổ hợp phím)
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, subEnterPressed;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {   
    }

    @Override
    public void keyPressed(KeyEvent e) { // Phím được nhấn
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            if(gp.ui.titleScreenState == 0) {
                titleState(code);
            }
            if(gp.ui.titleScreenState == 1) {
                titleScreenStateLevel(code);
            }
            if(gp.ui.titleScreenState == 2) {
                titleScreenStateAbout(code);
            }
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState) {
            playState(code);
        }
        // OPTIONS STATE
        else if(gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        // LOSE STATE
        else if(gp.gameState == gp.loseState) {
            loseState(code);
        }
        else if(gp.gameState == gp.finishedState) {
            finishState(code);
        }
    }

    public void setDefaultNewGame(){
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        enterPressed = false;
        subEnterPressed = false;
        gp.ui.playTime = 0;
        gp.ui.commandNum = 0;
        gp.ui.subState = 0;
        gp.ui.gameFinished = false;
        gp.tileM.setMap(gp.level);
        gp.aSetter.setObject(gp.level);
        gp.ui.setTimeLimited(gp.level);
        gp.player.setDefaultValues();
    }

    public void titleState(int code) {
        if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
            gp.playSE(6);
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }
        if(code == KeyEvent.VK_S | code == KeyEvent.VK_DOWN) {
            gp.playSE(6);
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }

        }
        if(code == KeyEvent.VK_ENTER) {
            gp.playSE(6);
            // NEW GAME
            if(gp.ui.commandNum == 0) {
                setDefaultNewGame();
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            // LEVEL
            if(gp.ui.commandNum == 1) {
                gp.ui.commandNum = 0;
                gp.ui.titleScreenState = 1;
            }
            // ABOUT
            if(gp.ui.commandNum == 2) {
                gp.playSE(6);
                gp.ui.titleScreenState = 2;
            }
            // QUIT
            if(gp.ui.commandNum == 3) {
                gp.playSE(6);
                System.exit(0);
            }
        }
    }

    public void titleScreenStateLevel(int code) {
        if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
            gp.playSE(6);
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_S | code == KeyEvent.VK_DOWN) {
            gp.playSE(6);
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            gp.playSE(6);
            // EASY
            if(gp.ui.commandNum == 0) {
                gp.level = 0;
                gp.playSE(6);
            }
            // MEDIUM
            if(gp.ui.commandNum == 1) {
                gp.level = 1;
                gp.playSE(6);
            }
            // HARD
            if(gp.ui.commandNum == 2) {
                gp.level = 2;
                gp.playSE(6);
            }
        }
        // BACK
        if(code == KeyEvent.VK_ESCAPE) {
            gp.playSE(6);
            gp.ui.commandNum = 0;
            gp.ui.titleScreenState = 0;
        }
    }

    public void titleScreenStateAbout(int code){
        if(code == KeyEvent.VK_ESCAPE) {
            gp.playSE(6);
            gp.ui.titleScreenState = 0;
        }
    }

    public void playState(int code) {
        if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S | code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }
    }

    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
            if(gp.ui.subState == 1){
                if(gp.ui.commandNum == 0){
                    subEnterPressed = true;
                    gp.gameState = gp.loseState;
                    gp.stopMusic();
                }
                else if(gp.ui.commandNum == 1) {
                    gp.ui.subState = 0;
                    gp.ui.commandNum = 2;
                    enterPressed = false;
                    gp.gameState = gp.optionsState;
                }
            }
        }
        int maxCommandNum = switch (gp.ui.subState) {
            case 0 -> 3;
            case 1 -> 1;
            default -> 0;
        };
        if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(6);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S | code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(6);
            if(gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT) {
            if(gp.ui.subState == 0) {
                if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(6);
                }
                if(gp.ui.commandNum == 1 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(6);
                }
            }
        }
        if(code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT) {
            if(gp.ui.subState == 0) {
                if(gp.ui.commandNum == 0 && gp.music.volumeScale <5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(6);
                }
                if(gp.ui.commandNum == 1 && gp.se.volumeScale <5) {
                    gp.se.volumeScale++;
                    gp.playSE(6);
                }
            }
        }
    }

    public void loseState(int code) {
        if(code == KeyEvent.VK_P) {
            gp.playSE(6);
            gp.setupGame();
            gp.startGameThread();
            gp.ui.playTime = 0;
            Arrays.fill(gp.obj, null);
        }
    }

    public void finishState(int code) {
        if(code == KeyEvent.VK_P) {
            gp.playSE(6);
            gp.setupGame();
            gp.startGameThread();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // Phím được dùng
        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if(code == KeyEvent.VK_S | code == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            if(code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if(code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
        }
        if(gp.gameState == gp.optionsState) {
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = false;
                subEnterPressed = false;
            }
        }
    }
    
}
