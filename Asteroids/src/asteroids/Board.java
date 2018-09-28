package asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
    
    private Timer timer;
    private Background background;
    private Nave nave;
    private List<Meteoro> meteoros;
    private boolean ingame;
    private final int DELAY = 15;
    private final int INAVE_X = 40;
    private final int INAVE_Y = 60;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 650;
    //CREATED SOME VARIABLES HERE
    private int vida = 3;
    private int score = 0;
    //private int wave = 1;
    
    private final int[][] pos = {
        {2790, 29}, {1685, 59}, {550, 89},
        {780, 109}, {2580, 139}, {2680, 239},
        {790, 259}, {760, 450}, {790, 150},
        {980, 209}, {860, 45}, {510, 370},
        {1930, 159}, {1590, 480}, {530, 560},
        {940, 359}, {990, 30}, {920, 200},
        {2300, 259}, {660, 350}, {2540, 90},
        {810, 220}, {860, 420}, {740, 180},
        {820, 128}, {2490, 170}, {1700, 430},
        {2790, 329}, {8685, 59}, {6550, 89},
        {7780, 109}, {9580, 139}, {5680, 239},
        {4790, 259}, {760, 250}, {1790, 150},
        {980, 209}, {6860, 45}, {7510, 370},
        {1930, 159}, {1590, 480}, {530, 560},
        {940, 359}, {990, 30}, {6920, 200},
        {2300, 259}, {660, 350}, {2540, 90},
        {6810, 220}, {860, 420}, {7740, 180},
        {820, 128}, {2490, 170}, {6700, 430}
    };
    
    public Board() {
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        ingame = true;
        
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        nave = new Nave(INAVE_X, INAVE_Y);
        initMeteoros();

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void initMeteoros() {
        
        meteoros = new ArrayList<>();

        for (int[] p : pos) {
            meteoros.add(new Meteoro(p[0], p[1]));
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (ingame) {
            desenharObjetos(g);

        }
        else {

            desenharGameOver(g);
        }
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void desenharObjetos(Graphics g) {
              
        //g.drawImage(background.getImagem(), 0, 0, null);
        
        if (nave.isVisivel()) {
            g.drawImage(nave.getImagem(), nave.getX(), nave.getY(),this);
        }

        List<Missil> ms = nave.getMisseis();

        for (Missil missil : ms) {
            if (missil.isVisivel()) {
                g.drawImage(missil.getImagem(), missil.getX(), 
                        missil.getY(), this);
            }
        }

        
            for (Meteoro meteoro : meteoros) {
                if (meteoro.isVisivel()) {
                    g.drawImage(meteoro.getImagem(), meteoro.getX(), meteoro.getY(), this);
                }
            }
        
        g.setColor(Color.WHITE);
        g.drawString("VIDAS: " + vida, 5, 15);
        g.drawString("SCORE: " + score, 5, 35);
        //g.drawString("WAVE: " + wave, 5, 55);
        
    }
    
    private void desenharGameOver(Graphics g) {

        String msg = "Game Over";
        String scr = "Score Final: " + score*vida;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.drawString(scr, ((B_WIDTH - fm.stringWidth(msg))-45) / 2, (B_HEIGHT / 2)+45);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        inGame();

        updateMisseis();
        updateNave();
        
        updateMeteoros();
        
        checarColisao();
        
        repaint();
        
    }
    
    private void updateMisseis() {

        List<Missil> misseis = nave.getMisseis();

        for (int i = 0; i < misseis.size(); i++) {

            Missil missil = misseis.get(i);

            if (missil.isVisivel()) {

                missil.moverM();
            } else {

                misseis.remove(i);
            }
        }
    }
    
    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }
    
    private void updateNave() {

        if (nave.isVisivel()) {
            
            nave.mover();
        }
    }
    
    private void updateMeteoros() {

        if (meteoros.isEmpty()) {
            ingame = false;
            return;
        }

        for (int i = 0; i < meteoros.size(); i++) {

            Meteoro m = meteoros.get(i);
            
            if (m.isVisivel()) {
                m.moverMe();
            } else {
                meteoros.remove(i);
            }
        }
    }
    
    public void checarColisao() {

        Rectangle r3 = nave.getLimite();

        for (Meteoro meteoro : meteoros) {
            
            Rectangle r2 = meteoro.getLimite();

            if (r3.intersects(r2) && vida > 1) {

                meteoro.setVisivel(false);
                vida--;
            }
            else if (r3.intersects(r2) && vida == 1){
                nave.setVisivel(false);
                meteoro.setVisivel(false);
                ingame = false;
            }
        }
        
        

        List<Missil> ms = nave.getMisseis();

        ms.forEach((mi) -> {
            
            Rectangle r1 = mi.getLimite();

            meteoros.forEach((meteoro) -> {
                
                Rectangle r2 = meteoro.getLimite();
                if (r1.intersects(r2)) {
                    mi.setVisivel(false);
                    meteoro.setVisivel(false);
                    score += 75;
                }
            });
        });
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            nave.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            nave.keyPressed(e);
        }
    }
}
