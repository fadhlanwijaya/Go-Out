package com.mfw.goout.objects;

import com.mfw.goout.framework.GameObject;
import com.mfw.goout.framework.ObjectId;
import com.mfw.goout.framework.Texture;
import com.mfw.goout.window.Animation;
import com.mfw.goout.window.Game;
import com.mfw.goout.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {
    private float width = 120, height = 59;

    private float gravity = 0.9f;
    private final float MAX_SPEED = 7;

    private Handler handler;

    Texture tex = Game.getInstance();

    private Animation playerJump;
    private Animation playerFall;

    public Player(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;

        playerJump = new Animation(1, tex.player[5], tex.player[6], tex.player[7], tex.player[8]);
        playerFall = new Animation(1, tex.player[1], tex.player[2], tex.player[3], tex.player[4]);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if(!start && !finish){
            x += velX + 3;
            y += 0;
        } else if (start && !finish){
            x += velX + 3;
            y += velY;
        } else if (start && finish){
            if (y < 300){
                x += 2;
                y += 1;
            } else if(y > 300){
                x += 2;
                y -= 1;
            } else if (y == 300 ){
                x += velX*0;
                y += velY*0;
            }
        }

        if(falling || jumping){
            velY += gravity;

            if(velY > MAX_SPEED){
                velY = MAX_SPEED;
            }
        }

        Collision(object);
        playerJump.runAnimation();
        playerFall.runAnimation();
    }

    private void Collision(LinkedList<GameObject> object){
        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //Collision Player ke Block
            if(tempObject.getId() == ObjectId.Block){
                //TopCollision
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    crashing = true;
                    y = tempObject.getY() + 32;
                    for(int j=0; j<5; j++){
                        velX += 0.05;
                    }
                    velY = 0;
                }

                //BottomCollision
                if(getBounds().intersects(tempObject.getBounds())){
                    crashing = true;
                    gameover = true;
                    y = tempObject.getY() - 38;
                    velX = -3;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
                else{
                    falling = true;
                }

                //RightCollision
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - 119;
                    for(int j=0; j<4; j++){
                        velX += -1.2;
                    }
                }

                //LeftCollision
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    x = tempObject.getX() + 31;
                    crashing = true;
                    for(int j=0; j<5; j++){
                        velX += 0.05;
                    }
                }
            }

            //Collision Player ke Pipeline
            if(tempObject.getId() == ObjectId.Pipeline){
                //RightCollision
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    crashing = true;
                    x = tempObject.getX() - 119;
                    for(int j=0; j<4; j++){
                        velX += -1.2;
                    }
                }
                //LeftCollision
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    crashing = true;
                    x = tempObject.getX() + 30;
                    for(int j=0; j<4; j++){
                        velX += 0.05;
                    }
                }
            }

            //Collision Player ke Bigger Pipeline
            if(tempObject.getId() == ObjectId.BiggerPipeline){
                //TopCollision
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    crashing = true;
                    y = tempObject.getY() + 32;
                    for(int j=0; j<5; j++){
                        velX += 0.05;
                    }
                    velY = 0;
                }

                //BottomCollision
                if(getBounds().intersects(tempObject.getBounds())){
                    crashing = true;
                    y = tempObject.getY() - 44;
                    for(int j=0; j<5; j++){
                        velX += 0.05;
                    }
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
                else{
                    falling = true;
                }

                //RightCollision
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    crashing = true;
                    x = tempObject.getX() - 117.5f;
                    for(int j=0; j<4; j++){
                        velX += -1.2;
                    }
                }

                //LeftCollision
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    crashing = true;
                    x = tempObject.getX() + 63;
                    for(int j=0; j<4; j++){
                        velX += 1.2;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(jumping){
            playerJump.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
        } else if(falling){
            playerFall.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
        }

        //Player Collision
        getBounds();
        getBoundsTop();
        getBoundsRight();
        getBoundsLeft();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x+20,(int)y+27,49,10);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int)x+29,(int)y+1,(int)(width/2)-16,9);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int)x+59,(int)y+13,(int)(width/2)-1,(int)height-50);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x+1,(int)y+13,(int)(width/2)-2,(int)height-50);
    }

}
