package asteroids;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Asteroids extends JFrame{
    
    public Asteroids() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Board());

        setTitle("Asteroids");
        setSize(800, 650);
        
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    

    public static void main(String[] args) {
       EventQueue.invokeLater(() -> {
            Asteroids as = new Asteroids();
            as.setVisible(true);
        });
    }
    
}
