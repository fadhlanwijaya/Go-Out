package com.mfw.goout.framework;

import com.mfw.goout.window.Game;
import com.mfw.goout.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Handler handler;
    Game game;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //Player
            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_SPACE){
                    if(!tempObject.isCrashing() && tempObject.isStart() && !tempObject.isFinish()) {
                        tempObject.setJumping(true);
                        tempObject.setVelY(-10);
                    }
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //Player
            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_SPACE){
                    if(!tempObject.isCrashing() && tempObject.isStart() && !tempObject.isFinish()) {
                        tempObject.setJumping(false);
                    }
                }
            }
        }
    }

}
