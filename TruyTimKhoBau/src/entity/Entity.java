package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {  // class q
    public int x, y;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // BufferedImage: Nó mô tả một hình ảnh với một bộ đệm dữ liệu hình ảnh có thể truy cập được.
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea; 
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
