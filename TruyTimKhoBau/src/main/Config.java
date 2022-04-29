package main;

import java.io.*;

public class Config {
    public double[] scores = new double[3];
    GamePanel gp;
     public Config(GamePanel gp) {
         this.gp = gp;
     }

     public void saveConfig()  {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"))){
             // Music volume
             bw.write(String.valueOf(gp.music.volumeScale));
             bw.newLine();

             // SE volume
             bw.write(String.valueOf(gp.se.volumeScale));
             bw.newLine();

             // Scores
             for(int i=0;i<=2;i++) {
                 bw.write(String.valueOf(scores[i]));
                 bw.newLine();
             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

     public void loadConfig() {
         try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))){
             // Music volume
             String s = br.readLine();
             gp.music.volumeScale = Integer.parseInt(s);

             // SE volume
             s = br.readLine();
             gp.se.volumeScale = Integer.parseInt(s);

             // Scores
             s = br.readLine();
             scores[0] = Double.parseDouble(s);
             s = br.readLine();
             scores[1] = Double.parseDouble(s);
             s = br.readLine();
             scores[2] = Double.parseDouble(s);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
     public void saveNewScores(String s) {
         char[] tmp = s.toCharArray();
         tmp[2] = '.';
         s = String.valueOf(tmp);
         scores[gp.level] = Double.parseDouble(s);
         saveConfig();
     }
}
