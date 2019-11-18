import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class RenderStrategy extends BufferedImage {

    private static Graphics2D strategy;

    private static int width, height, margin;

    public RenderStrategy(Game game, int margin){
        super(IConfig.MONITOR_WIDTH+2*margin, IConfig.MONITOR_HEIGHT+2*margin, BufferedImage.TYPE_INT_ARGB);

       strategy = (Graphics2D) getGraphics();

        this.width = getWidth();
        this.height = getHeight();
        this.margin = margin;
    }

    public static void add(BufferedImage image, int x, int y, int xoffset, int yoffset, ImageObserver imageObserver){

        if(x < width-margin && xoffset > -margin && y < height-margin && yoffset > -margin){
            strategy.drawImage(image, x, y, xoffset, yoffset, imageObserver);
        }

    }

    public static void add(BufferedImage image, int x, int y, int xoffset, int yoffset, int srcx1, int srcy1, int srcx2, int srcy2, ImageObserver imageObserver){

        if(x < width-margin && xoffset > -margin && y < height-margin && yoffset > -margin){
            strategy.drawImage(image, x, y, xoffset, yoffset, srcx1, srcy1, srcx2, srcy2, imageObserver);
        }

    }

    public void render(Game game, Graphics2D g2d){
        strategy.setColor(new Color(255, 255, 255));
        strategy.fillRect(0, 0, IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT);



        game.getPlace().render(g2d);
        game.getPlayer().render(g2d);

        g2d.drawImage(this, -margin, -margin, width-margin, height-margin, null);

        this.flush();

    }

    public static Graphics2D getStrategy(){
        return strategy;
    }

}
