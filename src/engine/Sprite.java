package engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Sprite {

    private BufferedImage image;

    private int ticks, step, interval, frameWidth, frameHeight, horizontal, vertical;

    private Map<Integer, int[][]> animation_pattern = new HashMap<>();

    public Sprite(String path){
        try {
            this.image = ImageIO.read(new File(path));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Sprite(String path, int frameWidth, int frameHeight, int horizontal, int vertical, int interval){
        this(path);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.interval = interval;
        this.step = 0;
    }

    public void render(int x, int y, int xoffset, int yoffset){
        RenderStrategy.add(image, x, y, xoffset, yoffset, null);
    }

    public void render(int x, int y, int xoffset, int yoffset,int width,int height, int framex, int framey){
        RenderStrategy.add(
                image,
                x,
                y,
                xoffset,
                yoffset,
                framex,
                framey,
                framex+width,
                framey+height,
             null);
    }


    public void render(int x, int y, int xoffset, int yoffset, int srcx, int srcy, int srcxoff, int srcyoff, ImageObserver io){
        RenderStrategy.add(
                image,
                x,
                y,
                xoffset,
                yoffset,
                srcx,
                srcy,
                srcxoff,
                srcyoff,
                io);
    }

    public void render(int x, int y, int xoffset, int yoffset, int framex, int framey){
        RenderStrategy.add(
                image,
                x,
                y,
                xoffset,
                yoffset,
                (framex*frameWidth)-frameWidth,
                (framey*frameHeight)-frameHeight,
                (framex*frameWidth),
                (framey*frameHeight),
                null);
    }

    public void animate(int x, int y, int xoffset, int yoffset, int animation){
        RenderStrategy.add(
                image,
                x,
                y,
                xoffset,
                yoffset,
                animation_pattern.get(animation)[step][0],
                animation_pattern.get(animation)[step][1],
                animation_pattern.get(animation)[step][0]+frameWidth,
                animation_pattern.get(animation)[step][1]+frameHeight,
                null
        );

        if(ticks == interval){
            ticks = 0;
            step++;
            if(step == animation_pattern.get(animation).length) step = 0;
        }
        else if(ticks < interval) ticks++;
    }

    public void addAnimation(int id, int[][] frames){

        for(int i = 0; i < frames.length; i++){
            frames[i][0] = (frames[i][0] * frameWidth) - frameWidth;
            frames[i][1] = (frames[i][1] * frameHeight) - frameHeight;
        }

        animation_pattern.put(id, frames);
    }


    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight(){
        return frameHeight;
    }


}
