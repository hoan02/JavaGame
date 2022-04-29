 package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ // 
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 * 16 tile
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 32*32 tile
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 19;
    public final int screenWidth = tileSize * maxScreenCol;  // xxx pixels
    public final int screenHeight = tileSize * maxScreenRow; // xxx pixels

    // FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Config config = new Config(this);
    Thread gameThread; // implements Runnable để sử dụng : giữ cho chương trình chạy tới lúc dừng

    //ENTITY AND OBJECTS
    Player player;
    public SuperObject[] obj;


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int loseState = 2;
    public final int optionsState = 3;
    public final int finishedState = 4;

    // LEVEL
    public int level = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // // Đặt kích thước của lớp này
        this.setBackground(Color.black); // Đặt hình nền cho trò chơi
        this.setDoubleBuffered(true); // Nếu được đặt thành true, tất cả bản vẽ từ thành phần này sẽ được thực hiện trong bộ đệm vẽ ngoài màn hình
        this.addKeyListener(keyH);
        this.setFocusable(true); // Với this, this GamePanel  có thể được "tập trung" để nhận đầu vào chính
    }

    public void setupGame() {
        gameState = titleState;
        player = new Player(this, keyH);
        obj = new SuperObject[10];
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // Tự động gọi hàm run
    }

    @Override
    public void run() {
        double drawInterval = (double)1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0; 

        while(gameThread != null) { 
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if(gameState == playState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) { // Vẽ một vật thể lên màn hình
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; // Graphics2d là lớp mở rộng của Graphics


        //TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        //OTHER
        else{
            //TILE
            tileM.draw(g2);
            //OBJECT
            for (SuperObject superObject : obj) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            //PLAYER
            player.draw(g2);
            //UI
            ui.draw(g2);
            g2.dispose(); // Loại bỏ bối cảnh đồ họa này và giải phóng bất kỳ tài nguyên hệ thống nào mà nó đang sử dụng
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
