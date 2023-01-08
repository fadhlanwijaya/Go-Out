package com.mfw.goout.window;

import com.mfw.goout.framework.ObjectId;

import java.awt.*;

public class HUD {
    private Handler handler;
    private Camera cam;

    public HUD(Handler handler, Camera cam){
        this.handler = handler;
        this.cam = cam;
    }

    public void tick(){
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
            }
        }
    }

    public void showNum(int times, Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 120));
        if(times > 100 && times <150){
            g.drawString("1", -(int)cam.getX()+445, (Game.HEIGHT/2)+32);
        } else if (times > 150 && times < 200){
            g.drawString("2", -(int)cam.getX()+445, (Game.HEIGHT/2)+32);
        } else if (times > 200 && times < 250){
            g.drawString("3", -(int)cam.getX()+445, (Game.HEIGHT/2)+32);
        }
    }
}
