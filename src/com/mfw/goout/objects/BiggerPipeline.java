package com.mfw.goout.objects;

import com.mfw.goout.framework.GameObject;
import com.mfw.goout.framework.ObjectId;
import com.mfw.goout.framework.Texture;
import com.mfw.goout.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class BiggerPipeline extends GameObject {
    Texture tex = Game.getInstance();
    private int type;

    public BiggerPipeline(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> object) { }

    @Override
    public void render(Graphics g) {
        if (type == 9) { //pipe besar atas
            g.drawImage(tex.rectangle[9], (int) x, (int) y, 64, 32, null);
        }
        if (type == 11) { //pipe besar bawah
            g.drawImage(tex.rectangle[11], (int) x, (int) y, 64, 32, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,64,32);
    }
}
