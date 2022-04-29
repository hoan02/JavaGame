package tile;

import java.io.*;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager { // Quản lý maps và nền
    
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    public String[] map = { "res/maps/map01.txt",
                            "res/maps/map02.txt",
                            "res/maps/map03.txt"};
    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];

        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
    }

    public void setMap(int level) {
        loadMap(map[level]);
    }

    public void getTileImage() {
        setup(0, "grass", false);
        setup(1, "tree", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new File("res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadMap(String filePath) { // Nhập dữ liệu bản đồ
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split("");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) { // Vẽ nền và map lên mình hình
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, null);
            col++;
            x += gp.tileSize;

            if(col ==  gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
