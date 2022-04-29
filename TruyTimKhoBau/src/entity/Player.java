package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;
    public boolean checkSwap = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize/3;
        solidArea.y = gp.tileSize/3;
        solidAreaDefaultX = gp.tileSize/3;
        solidAreaDefaultY = gp.tileSize/3;
        solidArea.width = gp.tileSize/3;
        solidArea.height = gp.tileSize/3;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 2;
        direction = "down";
    }

    public void getPlayerImage() { // Lấy hình nhân vật chủ đề
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }
    public BufferedImage setup(String imageName) {
        UtilityTool uToll = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("res/player/" + imageName + ".png"));
            image = uToll.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if(checkSwap == true) {
                if(keyH.upPressed) {
                    direction = "down";
                }
                else if(keyH.downPressed) {
                    direction = "up";
                }
                else if(keyH.leftPressed) {
                    direction = "right";
                }
                else {
                    direction = "left";
                }
            }
            else {
                if(keyH.upPressed) {
                    direction = "up";
                }
                else if(keyH.downPressed) {
                    direction = "down";
                }
                else if(keyH.leftPressed) {
                    direction = "left";
                }
                else {
                    direction = "right";
                }
            }
    
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i) { // Nhặt vật phẩm
        if(i != 999) {
            String objectName = gp.obj[i].name;
            switch(objectName) {
            case "Key":
                gp.playSE(1);
                hasKey++;
                gp.obj[i] = null;
                gp.ui.showMessage("You got a key!");
                break;
            case "Door":
                if(hasKey > 0) {
                    gp.playSE(2);
                    gp.obj[i] = null;
                    gp.ui.showMessage("You opened the door!");
                    hasKey--;
                }
                else {
                    gp.ui.showMessage("You need a key!");
                }
                break;
            case "Boots":
                gp.playSE(3);
                speed += 1;
                gp.obj[i] = null;
                gp.ui.showMessage("Speed up!");
                break;
            case "Swap":
                gp.playSE(3);
                gp.obj[i] = null;
                checkSwap = true;
                gp.ui.showMessage("Reverse!");
                break;
            case "Chest":
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(4);
                break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
            }
            case "down" -> {
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
            }
            case "left" -> {
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
            }
            case "right" -> {
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
            }
        }

        g2.drawImage(image, x, y,null);
    }


}
