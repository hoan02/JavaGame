package object;

import main.GamePanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Swap extends SuperObject{
    GamePanel gp;
    public OBJ_Swap (GamePanel gp) {
        this.gp = gp;
        name = "Swap";
        try {
            image = ImageIO.read(new File("res/objects/swap.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
