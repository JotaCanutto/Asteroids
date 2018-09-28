package asteroids;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Nave extends Sprite{
    
    private int dx;
    private int dy;
    private List<Missil> misseis;
    
    public Nave( int x, int y){
        super(x, y);
        
        initNave();
    }
    
    private void initNave() {

        misseis = new ArrayList<>();
        
        loadImagem("src/resources/nave.png");
        getDimensoesImg();
    }
    
    public void mover(){
        
        x+= dx;
        y+= dy;
        
        if(x == 0){
            x = 0;
        }
        if(y==0){
            y = 0;
        }
        if (x < 1){
            x = 1;
        }
        if (y < 1){
            y = 1;
        }
    }
    
    public List<Missil> getMisseis() {
        return misseis;
    }
    
    public void keyPressed (KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            atirar();
        }
        if (key == KeyEvent.VK_LEFT){
            dx = -4;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 4;
        }
        if(key == KeyEvent.VK_UP){
            dy = -4;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = 4;
        }
    }
    
    public void atirar() {
        misseis.add(new Missil(x + width, y + height / 2));
    }
    
    public void keyReleased (KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
        if(key == KeyEvent.VK_UP){
            dy = 0;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = 0;
        }
    }
    
}
