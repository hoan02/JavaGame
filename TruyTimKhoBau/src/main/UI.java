package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.OBJ_Key;
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font pressStart2P, robotoMono;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message;
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;
    public int titleScreenState = 0;
    int subState = 0;
    public String currentDialogue;

    double playTime;
    double timeLimited = 100;
    DecimalFormat dFormat = new DecimalFormat("#00.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = new FileInputStream("res/font/PressStart2P-Regular.ttf");
            pressStart2P = Font.createFont(Font.TRUETYPE_FONT, is);
            is = new FileInputStream("res/font/RobotoMono-Medium.ttf");
            robotoMono = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;  
    }
    public void setTimeLimited(int level){
        if(level == 0) {
            timeLimited = 90;
        }
        else if(level == 1) {
            timeLimited = 70;
        }
        else if(level == 2) {
            timeLimited = 50;
        }
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(robotoMono);
        g2.setColor(Color.WHITE);

        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState) {
            drawPlayScreen();
        }
        //OPINIONS STATE
        if(gp.gameState == gp.optionsState) {
            drawOptionsState();
        }
        // LOSE STATE
        if(gp.gameState == gp.loseState) {
            drawLoseScreen();
        }
        // FINISH STATE
        if(gp.gameState == gp.finishedState) {
            drawFinishScreen();
        }
    }
    public void drawTitleScreen() {
        // BACKGROUND COLOR
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(pressStart2P);
        g2.setColor(Color.WHITE);
        // TITLE MENU
        if(titleScreenState == 0) {
            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            String text = "Truy tìm kho báu";
            int x = getXCenteredText(text);
            int y = gp.tileSize*5;

            // SHADOW
            g2.setColor(Color.GRAY);
            g2.drawString(text, x+5, y+5);

            // NAME COLOR
            g2.setColor(Color.PINK);
            g2.drawString(text, x, y);

            //MENU
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

            text = "NEW GAME";
            x = getXCenteredText(text);
            y += gp.tileSize*5;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">" , x-gp.tileSize, y);
            }

            text = "LEVEL";
            x = getXCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">" , x-gp.tileSize, y);
            }

            text = "ABOUT";
            x = getXCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">" , x-gp.tileSize, y);
            }

            text = "QUIT";
            x = getXCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 3) {
                g2.drawString(">" , x-gp.tileSize, y);
            }
        }
        // TITLE LEVEL
        else if(titleScreenState == 1) {
            g2.setFont(g2.getFont().deriveFont(40F));
            g2.setColor(Color.YELLOW);
            String text = "SELECT LEVEL:";
            int x = getXCenteredText(text);
            int y = gp.tileSize*5;
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.setColor(Color.WHITE);
            if(gp.level == 0) {
                g2.setColor(Color.RED);
            }
            text = "EASY";
            x = getXCenteredText(text);
            y += gp.tileSize*4;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">" , x-gp.tileSize, y);
            }

            g2.setColor(Color.WHITE);
            if(gp.level == 1) {
                g2.setColor(Color.RED);
            }
            text = "MEDIUM";
            x = getXCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">" , x-gp.tileSize, y);
            }

            g2.setColor(Color.WHITE);
            if(gp.level == 2) {
                g2.setColor(Color.RED);
            }
            text = "HARD";
            x = getXCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">" , x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 2) {
            g2.setFont(robotoMono);
            g2.setColor(Color.YELLOW);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
            String text = "Hướng dẫn chơi";
            int textX = getXCenteredText(text);
            int textY = gp.tileSize*2;
            g2.drawString(text, textX, textY);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
            text = """
                     * Người chơi sẽ sử dụng các phím A,W,D,S hoặc 4 phím mũi tên
                    (LEFT, UP, RIGHT, DOWN) tương ứng với các hướng: trái, trên,
                    phải, dưới để di chuyển nhân vật.
                     * Để mở được những cánh cửa, người chơi sẽ phải đi tìm những
                    chiếc chìa khóa.
                     * Nếu người chơi tìm được kho báu trước khi thời gian hết
                    thì sẽ thắng.
                     * Game có 3 LEVEL để người chơi chọn:
                         - EASY: 90 giây
                         - MEDIUM: 70 giây
                         - HARD: 50 giây""";
            textX = gp.tileSize;
            textY += gp.tileSize*3;
            for(String line: text.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 40;
            }

        }
    }

    public void drawPlayScreen() {
        // FINISHED
        if(gameFinished) {
            gp.gameState = gp.finishedState;
        }
        else {
            // PLAYING
            if(playTime <= timeLimited) {
                drawPlayState();
            }
            // LOSE
            else {
                gp.gameState = gp.loseState;
                gp.stopMusic();
            }
        }
    }
    public void drawPlayState() {
        g2.setFont(robotoMono);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        g2.setColor(Color.white);
        if(gp.level != 0){
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/10, gp.tileSize, gp.tileSize, null);
            g2.drawString("x" + gp.player.hasKey, gp.tileSize*3/2, gp.tileSize);
        }

        //TIME
        playTime += (double)1/60;
        g2.drawString("Time:" + dFormat.format(timeLimited-playTime), gp.screenWidth - gp.tileSize*6, 30);

        if(messageOn) {
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*10);

            messageCounter++;
            if(messageCounter > 120) { // 120 khung hinh =  2 giay
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    public void drawOptionsState() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize*7;
        int frameY = gp.tileSize*2;
        int frameWidth = gp.tileSize*11;
        int frameHeight = gp.tileSize*13;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0 -> options_top(frameX, frameY);
            case 1 -> options_endGameConfirmation(frameX, frameY);
        }

    }
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXCenteredText(text);
        textY = frameY + gp.tileSize*2;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(20F));
        // MUSIC
        textX = frameX + gp.tileSize*2;
        textY += gp.tileSize*4;
        g2.drawString("Music", textX, textY);
        if(commandNum == 0) {
            gp.keyH.enterPressed = false;
            g2.drawString(">", textX-20, textY);
        }

        // SE
        textX = frameX + gp.tileSize*2;
        textY += gp.tileSize*1.5;
        g2.drawString("SE", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-20, textY);
        }

        // END GAME
        textX = frameX + gp.tileSize*2;
        textY += gp.tileSize*1.5;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX-20, textY);
            if(gp.keyH.enterPressed) {
                subState = 1;
                commandNum = 0;
            }
        }

        // BACK
        textX = frameX + gp.tileSize*2;
        textY += gp.tileSize*3;
        g2.drawString("Back", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX-20, textY);
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                gp.keyH.enterPressed = false;
            }
        }

        // MUSIC VOLUME
        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*5+12;
        g2.drawRect(textX, textY, 120, 24); // 120:5=24
        int volumeWidth = 24*gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE VOLUME
        textY += gp.tileSize*1.5;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24*gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Quit the game and \nreturn to the title \nscreen?";
        g2.setFont(g2.getFont().deriveFont(20F));
        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        g2.setFont(g2.getFont().deriveFont(30F));
        String text = "Yes";
        textX = getXCenteredText(text);
        textY += gp.tileSize*2;
        g2.drawString(text, textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-20, textY);
            if(gp.keyH.subEnterPressed) {
                subState = 0;
                gp.stopMusic();
                gp.keyH.subEnterPressed = false;
                gp.gameState = gp.titleState;
            }
        }

        // NO
        text = "No";
        textX = getXCenteredText(text);
        textY += gp.tileSize*2;
        g2.drawString(text, textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-20, textY);
            if(gp.keyH.subEnterPressed) {
                gp.gameState = gp.optionsState;
                subState = 0;
            }
        }
    }
    public void drawLoseScreen() {
        // SUB WINDOW
        int frameX = gp.tileSize*5;
        int frameY = gp.tileSize*3;
        int frameWidth = gp.tileSize*15;
        int frameHeight = gp.tileSize*11;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.ORANGE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "YOU LOSE";
        int x = getXCenteredText(text);
        int y = gp.tileSize*7;
        g2.drawString(text, x, y);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        text = "Press \"P\" key to go to title screen";
        x = getXCenteredText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        gp.gameThread = null;
        gp.playSE(5);
    }

    public void drawFinishScreen() {
        int frameX = gp.tileSize*3;
        int frameY = gp.tileSize*3;
        int frameWidth = gp.tileSize*19;
        int frameHeight = gp.tileSize*13;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setFont(robotoMono);
        String text;
        int x;
        int y;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        g2.setColor(Color.YELLOW);
        text = "Congratulations!";

        x = getXCenteredText(text);
        y = gp.screenHeight/2 - gp.tileSize*3;

        g2.drawString(text, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
        g2.setColor(Color.WHITE);
        text = "You found the treasure!";
        x = getXCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);

        String s = dFormat.format(playTime);
        text = "Your time is : " + s + "s!";
        x = getXCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);

        // Check Scores
        if(playTime < gp.config.scores[gp.level]) {
            text = "You broke the old record of " + dFormat.format(gp.config.scores[gp.level]) + "s!";
            x = getXCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            gp.config.saveNewScores(s);
        }

        text = "Press \"P\" key to go to title screen";
        x = getXCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);

        gp.gameThread = null;
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(31,62,66,240);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXCenteredText(String  text) {
        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - textLength/2;
    }
}
