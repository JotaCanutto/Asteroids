package asteroids;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Sprite {
    
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visivel;
    protected Image imagem;
    
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visivel = true;
    }
    
    protected void loadImagem(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        imagem = ii.getImage();
    }
    
    protected void getDimensoesImg() {

        width = imagem.getWidth(null);
        height = imagem.getHeight(null);
    }    
    
    public Image getImagem() {
        return imagem;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisivel() {
        return visivel;
    }
    
    public void setVisivel(Boolean visivel) {
        this.visivel = visivel;
    }
    
    public Rectangle getLimite() {
        return new Rectangle(x, y, width, height);
    }
}
