package main;

import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(int level) { // Đặt vị trí cho vật thể trên map ở từng level
        if(gp.gameState == gp.titleState) {
            if(level==0) {
                gp.obj[0] = new OBJ_Boots(gp);
                gp.obj[0].x = gp.tileSize*15;
                gp.obj[0].y = gp.tileSize;

                gp.obj[1] = new OBJ_Chest(gp);
                gp.obj[1].x = gp.tileSize*17;
                gp.obj[1].y = gp.tileSize*15;

                gp.obj[2] = new OBJ_Swap(gp);
                gp.obj[2].x = gp.tileSize*7;
                gp.obj[2].y = gp.tileSize;
            }
            else if(level == 1) {
                gp.obj[0] = new OBJ_Key(gp);
                gp.obj[0].x = gp.tileSize*5;
                gp.obj[0].y = gp.tileSize*7;

                gp.obj[1] = new OBJ_Door(gp);
                gp.obj[1].x = gp.tileSize*7;
                gp.obj[1].y = gp.tileSize*7;

                gp.obj[2] = new OBJ_Boots(gp);
                gp.obj[2].x = gp.tileSize*9;
                gp.obj[2].y = gp.tileSize*3;

                gp.obj[3] = new OBJ_Chest(gp);
                gp.obj[3].x = gp.tileSize*23;
                gp.obj[3].y = gp.tileSize;
            }
            else if(level == 2) {
                gp.obj[0] = new OBJ_Key(gp);
                gp.obj[0].x = gp.tileSize*13;
                gp.obj[0].y = gp.tileSize*5;
                gp.obj[1] = new OBJ_Key(gp);
                gp.obj[1].x = gp.tileSize*23;
                gp.obj[1].y = gp.tileSize*15;

                gp.obj[2] = new OBJ_Door(gp);
                gp.obj[2].x = gp.tileSize*11;
                gp.obj[2].y = gp.tileSize*11;
                gp.obj[3] = new OBJ_Door(gp);
                gp.obj[3].x = gp.tileSize*19;
                gp.obj[3].y = gp.tileSize*6;

                gp.obj[4] = new OBJ_Boots(gp);
                gp.obj[4].x = 7 * gp.tileSize;
                gp.obj[4].y = 3 * gp.tileSize;

                gp.obj[5] = new OBJ_Chest(gp);
                gp.obj[5].x = gp.tileSize*23;
                gp.obj[5].y = gp.tileSize*5;
            }
        }
    }

}
