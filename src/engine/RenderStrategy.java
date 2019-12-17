package engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class RenderStrategy extends BufferedImage {

    private static Graphics2D strategy;
    private static RenderSpecialAddition renderSpecialAddition;

    private static int width, height, margin;

    public RenderStrategy(Game game, int margin){
        super(IConfig.MONITOR_WIDTH+2*margin, IConfig.MONITOR_HEIGHT+2*margin, BufferedImage.TYPE_INT_ARGB);

       strategy = (Graphics2D) getGraphics();

        RenderStrategy.width = getWidth();
        RenderStrategy.height = getHeight();
        RenderStrategy.margin = margin;
    }

    public static void add(BufferedImage image, int x, int y, int xoffset, int yoffset, ImageObserver imageObserver){

        if(getXOf(x) < width-margin && xoffset > -margin && getYOf(y) < height-margin && yoffset > -margin){
            strategy.drawImage(
                    image,
                    getXOf(x),
                    getYOf(y),
                    xoffset,
                    yoffset,
                    imageObserver
            );
        }

    }

    public static void add(BufferedImage image, int x, int y, int xoffset, int yoffset, int srcx1, int srcy1, int srcx2, int srcy2, ImageObserver imageObserver){

        if(getXOf(x) < width-margin && getXOf(xoffset) > -margin && getYOf(y) < height-margin && getYOf(yoffset) > -margin){
            strategy.drawImage(
                    image,
                    getXOf(x),
                    getYOf(y),
                    getXOf(xoffset),
                    getYOf(yoffset),
                    srcx1, srcy1, srcx2, srcy2, imageObserver);
        }

    }

    public void render(Game game, Graphics2D g2d){
        strategy.setColor(new Color(0, 0, 0));
        strategy.fillRect(0, 0, IConfig.MONITOR_WIDTH, IConfig.MONITOR_HEIGHT);

        if(renderSpecialAddition != null) renderSpecialAddition.render();

        if(game.getPlace() != null) game.getPlace().render();

        g2d.drawImage(this, 0, 0, width, height, null);

        this.flush();

    }

    public static void resetConfigurations(){
        strategy.setColor(Color.WHITE);
        strategy.setStroke(new BasicStroke(1));
    }

    public static Graphics2D getStrategy(){
        return strategy;
    }

    public static int getXOf(int a){
        return a - (Camera.getCameraCollisionFactor().getX() - Camera.getX());
    }

    public static int getYOf(int a){
        return a - (Camera.getCameraCollisionFactor().getY() - Camera.getY());
    }

    public static void setRenderSpecialAddition(RenderSpecialAddition renderSpecialAddition) {
        RenderStrategy.renderSpecialAddition = renderSpecialAddition;
    }
}
