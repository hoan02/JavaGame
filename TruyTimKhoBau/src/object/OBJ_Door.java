package object;

import main.GamePanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
    GamePanel gp;
    public OBJ_Door (GamePanel gp) {
        this.gp = gp;
        name = "Door";
        try {
            image = ImageIO.read(new File("res/objects/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
