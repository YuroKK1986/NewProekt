/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Кожевников Юрий Викторович
 * 
 */

public class Game extends JFrame {
    
    private static Game game_game;
    private static long last_frame_time;
    private static Image Ferm;
    private static Image Perepel;
    private static Image DDD;
    private static float drop_left = 200;
    private static float drop_top = -100;
    private static float drop_v = 200;
    private static int score = 0; 
    
    
      public static void main(String[] args) throws IOException {
        Ferm = ImageIO.read(Game.class.getResourceAsStream("Ferm.png"));
        Perepel = ImageIO.read(Game.class.getResourceAsStream("Perepel.png"));
        DDD = ImageIO.read(Game.class.getResourceAsStream("DDD.png"));
        game_game = new Game();
        game_game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_game.setLocation(100, 100);
        game_game.setSize(1500, 700);
        game_game.setResizable(false);
        last_frame_time = System.nanoTime();
        GameField game_field = new GameField();
        
        
        game_field.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + DDD.getWidth(null);
                float drop_bottom = drop_top + DDD.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;
                
                if (is_drop) {
                    drop_top = -100; 
                    drop_left = (int) (Math.random() * (game_field.getWidth() - DDD.getWidth(null))); 
                    drop_v = drop_v + 10; 
                    score++; 
                    game_game.setTitle("Score: " + score); 
                }
            }
        });
        
        game_game.add(game_field);
        game_game.setVisible(true);
      }
    public static void onRepaint(Graphics g){
        long current_time = System.nanoTime();
        float delt_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        drop_top = drop_top + drop_v * delt_time;
        g.drawImage(Ferm, 0, 0, null);
        //g.drawImage(Perepel, 50, 50, null);
        g.drawImage(Perepel, (int) drop_left, (int) drop_top, null);
        if(drop_top > game_game.getHeight()) g.drawImage(DDD, 400, 50, null);
        
    }
     

    private static class GameField extends JPanel{
  
    protected void paintComponent(Graphics g){
        
        super.paintComponent(g);
        onRepaint(g);
        repaint();
        }
    }

}


    
    
