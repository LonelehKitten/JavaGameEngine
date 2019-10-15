import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderStrategy extends BufferedImage {

    private Graphics2D strategy;

    public RenderStrategy(int width, int height, int imageType){
        super(width, height, imageType);
        strategy = createGraphics();
    }

    public void add(BufferedImage image, int x, int y, int xoffset, int yoffset){
        strategy.drawImage(image, x, y, xoffset, yoffset, null);
    }

    public RenderStrategy setDimension(int width, int height){
        RenderStrategy rs = new RenderStrategy(width, height, this.getType());
        rs.add(this, 0, 0, this.getWidth(), this.getWidth());
        return rs;
    }
}
