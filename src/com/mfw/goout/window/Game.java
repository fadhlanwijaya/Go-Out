package com.mfw.goout.window;

import com.mfw.goout.framework.KeyInput;
import com.mfw.goout.framework.ObjectId;
import com.mfw.goout.framework.Texture;
import com.mfw.goout.objects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -6261436164361361187L;
    private int times = 0;
    private int endtime = 0;
    private boolean runningGame = false;
    private Thread thread;

    public int[] obstacle = new int[10];
    public static int WIDTH, HEIGHT;

    //Object
    Handler handler;
    Camera cam;
    Menu menu;
    HUD hud;
    static Texture tex;

    public enum STATE {
        MENU,
        GAME,
        OVER,
        END,
    }

    public STATE gameState = STATE.MENU;

    private void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();
        handler = new Handler();
        cam = new Camera(0,0);
        menu = new Menu(this, handler, cam);
        hud = new HUD(handler,cam);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
    }

    public synchronized void startThread(){
        if(runningGame){
            return;
        }

        runningGame = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lasttime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(runningGame){
            long now = System.nanoTime();
            delta += (now - lasttime) /ns;
            lasttime = now;

            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS : " + frames + " TICKS : " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick(){
        handler.tick();

        if (gameState == STATE.GAME) {
            hud.tick();
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getId() == ObjectId.Player) {
                    cam.tick(handler.object.get(i));

                    if (times > 250 && times < 1390){
                        handler.object.get(i).setStart(true);
                    }
                    if (times > 1390 && times < 1450){ //1390 - 1450
                        handler.object.get(i).setFinish(true);
                    }

                    if (handler.object.get(i).isGameover()) {
                        endtime += 1;
                        if (endtime > 50) {
                            gameState = Game.STATE.OVER;
                            endtime += 0;
                        }
                    } else if(handler.object.get(i).isCrashing()) {
                        times += 0;
                    } else if(handler.object.get(i).isStart() && handler.object.get(i).isFinish()) {
                        handler.object.get(i).setFalling(true); //tambahan
                        endtime += 1;
                        if (endtime > 110) {
                            gameState = STATE.END;
                            endtime += 0;
                        }
                    } else {
                        times += 1;
                        gameState = STATE.GAME;
                    }
                }
            }
        } else if (gameState == STATE.MENU || gameState == STATE.OVER || gameState == STATE.END) {
            deleteLevel();
            endtime = 0;
            times = 0;
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(cam.getX(), cam.getY());

        if(gameState == STATE.MENU || gameState == STATE.OVER || gameState == STATE.END) {
            menu.render(g);
        } else if(gameState == STATE.GAME){
            for(int bg=0; bg<960*6; bg+=960){
                g.drawImage(tex.background[1], bg, 0, getWidth(), getHeight(), null);
            }
            handler.render(g);
            hud.showNum(times, g);
        }
        g2d.translate(-cam.getX(), -cam.getY());

        g.dispose();
        bs.show();
    }

    private void level(){
        for(int i=0; i<3200; i+=(32*10)) {
            int rand = (int) Math.floor(Math.random() * (13 - 5 + 1) + 5) * 32;
            obstacle[i/320] = rand;
        }

        //Player
        handler.addObject(new Player(240, 300, handler, ObjectId.Player));

        //Top
        for(int xx=0; xx<960*6; xx+=32){
            if(xx == 0){
                handler.addObject(new Block(xx, 0, 0, ObjectId.Block));
            } else if(xx == (960*6)-32){
                handler.addObject(new Block(xx, 0, 2, ObjectId.Block));
            } else {
                handler.addObject(new Block(xx, 0, 1, ObjectId.Block));
            }
        }

        //Bottom
        for(int xx=0; xx<960*6; xx+=32){
            if(xx == 0){
                handler.addObject(new Block(xx, 640-32, 5, ObjectId.Block));
            } else if(xx == (960*6)-32){
                handler.addObject(new Block(xx, 640-32, 7, ObjectId.Block));
            } else {
                handler.addObject(new Block(xx, 640-32, 6, ObjectId.Block));
            }
        }

        //Right
        for(int yy=32; yy<640; yy+=32){
            if(yy == 256){
                handler.addObject(new Block(960*5, yy, 16, ObjectId.Block));
            } else if(yy == 288 || yy == 320){
                handler.addObject(new Block(960*5, yy, 17, ObjectId.Block));
            } else if(yy == 352){
                handler.addObject(new Block(960*5, yy, 18, ObjectId.Block));
            } else {
                handler.addObject(new Block(960*5, yy, 4, ObjectId.Block));
            }
        }

        //Left
        for(int yy=32; yy<640; yy+=32){
            if(yy == 256){
                handler.addObject(new Block(0, yy, 12, ObjectId.Block));
            } else if(yy == 288){
                handler.addObject(new Block(0, yy, 13, ObjectId.Block));
            } else if(yy == 320){
                handler.addObject(new Block(0, yy, 14, ObjectId.Block));
            } else if(yy == 352){
                handler.addObject(new Block(0, yy, 15, ObjectId.Block));
            } else {
                handler.addObject(new Block(0, yy, 3, ObjectId.Block));
            }
        }

        //Obstacles
        for(int j=0; j<3200; j+=(32*10)){
            for(int yy=32; yy<640-32; yy+=32){
                if(yy <= obstacle[j/320]-(32*4)){
                    handler.addObject(new Pipeline(j+(960+480), yy, 8, ObjectId.Pipeline));
                } else if(yy >= obstacle[j/320]+(32*4)){
                    handler.addObject(new Pipeline(j+(960+480), yy, 10, ObjectId.Pipeline));
                }
            }
        }
        for(int j=0; j<3200; j+=(32*10)){
            for(int yy=32; yy<640-32; yy+=32){
                if(yy == obstacle[j/320]-(32*3)){
                    handler.addObject(new BiggerPipeline((j+(960+480))-16, yy, 9, ObjectId.BiggerPipeline));
                } else if(yy == obstacle[j/320]+(32*3)){
                    handler.addObject(new BiggerPipeline((j+(960+480))-16, yy, 11, ObjectId.BiggerPipeline));
                }
            }
        }
    }

    public void createLevel(){
        level();
    }

    public void deleteLevel(){
        for (int i = 0; i < handler.object.size(); i++) {
            handler.removeObject(handler.object.get(i));
        }
    }

    public static Texture getInstance(){ return tex; }

    public static void main(String[] args){
        new Window(960,640,"Go Out", new Game());
    }
}
