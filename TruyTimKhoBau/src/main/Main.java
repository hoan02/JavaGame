package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Nhấn nút x để thoát
        window.setResizable(false); // Không cho thay đổi kích cỡ
        window.setTitle("Game Maze"); // Tên game

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.config.loadConfig();

        window.pack(); // Khiến cửa sổ này có kích thước vừa với kích thước và bố cục ưa thích của thành phần con = (GamePanel) =
        window.setLocationRelativeTo(null); // Cho cửa sổ ở trung tâm màn hình
        window.setVisible(true); // Để có thể nhìn thấy cửa sổ

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
