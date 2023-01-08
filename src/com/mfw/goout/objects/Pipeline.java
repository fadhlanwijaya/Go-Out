package com.mfw.goout.objects;

import com.mfw.goout.framework.GameObject;
import com.mfw.goout.framework.ObjectId;
import com.mfw.goout.framework.Texture;
import com.mfw.goout.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Pipeline extends GameObject {
    Texture tex = Game.getInstance();
    private int type;

    public Pipeline(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> object) { }

    @Override
    public void render(Graphics g) {
        if (type == 8) { //pipe kecil atas
            g.drawImage(tex.rectangle[8], (int) x, (int) y, 32, 32, null);
        }
        if (type == 10) { //pipe kecil bawah
            g.drawImage(tex.rectangle[10], (int) x, (int) y, 32, 32, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
