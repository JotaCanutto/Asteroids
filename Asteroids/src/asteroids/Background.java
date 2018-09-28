package asteroids;

public class Background extends Sprite{
    
    public Background(int x, int y) {
        super(x, y);
        
        initBackground();
    }

    private void initBackground() {
        loadImagem("src/resources/background.jpg");
        getDimensoesImg();
    }
}
