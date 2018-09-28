package asteroids;

public class Meteoro extends Sprite{
    
    private final int INICIAL_X = 800;
    
    public Meteoro(int x, int y) {
        super(x, y);
        
        initMeteoro();
    }

    private void initMeteoro() {
        loadImagem("src/resources/meteoro.png");
        getDimensoesImg();
    }
    
    public void moverMe() {

        if (x < 0) {
            x = INICIAL_X;
        }

        x -= 5;
    }
}
