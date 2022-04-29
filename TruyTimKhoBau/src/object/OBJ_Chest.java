package object;

import main.GamePanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{
    GamePanel gp;
    public OBJ_Chest (GamePanel gp) {
        this.gp = gp;
        name = "Chest";
        try {
            image = ImageIO.read(new File("res/objects/chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
