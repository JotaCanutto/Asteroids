package asteroids;

public class Missil extends Sprite{
    
    private final int BOARD_WIDTH = 800;
    private final int MISSILE_SPEED = 10;
    
    public Missil(int x, int y) {
        super(x, y);
        
        initMissil();
    }

    private void initMissil() {
        loadImagem("src/resources/missil.png");  
        getDimensoesImg();
    }
    
    public void moverM() {
        
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH) {
            visivel = false;
        }
    }
}
