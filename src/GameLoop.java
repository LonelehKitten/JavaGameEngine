

public class GameLoop {

    private Game game;
    private Thread gameloop;

    public GameLoop(Game game){
        this.game = game;
        gameloop = new Thread(loop);
    }

    public void start(){
       gameloop.start();
    }

    private Runnable loop = () -> {/*
        int MAX_FPS = 30;
        long start_time;
        long time_millis;
        long wait_time;

        long total_time = 0;
        long target_time = 1000 / MAX_FPS;
        */
        long excess = 0;
        long noDelays = 0;
        final long DESIRED_UPDATE_TIME = 40;
        final long NO_DELAYS_PER_YIELD = 16;

        long overSleepTime = 0;
        try{
            Thread.sleep(100);
            while(true) {/*
                start_time = System.nanoTime();

                game.update();
                game.render();

                time_millis = (System.nanoTime() - start_time) / 1000000;
                wait_time = target_time - time_millis;

                if(wait_time > 0) Thread.sleep(wait_time);

                total_time += System.nanoTime() - start_time;


                */


                long beforeTime = System.currentTimeMillis();

                //Pula os quadros enquanto o tempo for em excesso.

                while (excess > DESIRED_UPDATE_TIME) {
                    game.update();
                    excess -= DESIRED_UPDATE_TIME;
                }

                game.update();
                game.render();

                long afterTime = System.currentTimeMillis();
                long totalTime = afterTime - beforeTime;

                long sleepTime = DESIRED_UPDATE_TIME - totalTime - overSleepTime;
                if (sleepTime >= 0) {
                    Thread.sleep(sleepTime);
                    long afterSleepTime = System.currentTimeMillis();
                    overSleepTime = afterSleepTime - afterTime;
                    noDelays = 0;
                }
                else {
                    overSleepTime = 0;
                    excess += totalTime - DESIRED_UPDATE_TIME;
                    if (++noDelays == NO_DELAYS_PER_YIELD)
                        Thread.yield();
                }





            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    };
}
