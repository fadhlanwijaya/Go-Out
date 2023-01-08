package com.mfw.goout.objects;

import com.mfw.goout.framework.GameObject;
import com.mfw.goout.framework.ObjectId;
import com.mfw.goout.framework.Texture;
import com.mfw.goout.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {
    Texture tex = Game.getInstance();
    private int type;

    public Block(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> object) { }

    @Override
    public void render(Graphics g) {
        if (type == 0) { //block atas kiri
            g.drawImage(tex.rectangle[0], (int) x, (int) y, 32, 32, null);
        }
        if (type == 1) { //block atas tengah
            g.drawImage(tex.rectangle[1], (int) x, (int) y, 32, 32, null);
        }
        if (type == 2) { //block atas kanan
            g.drawImage(tex.rectangle[2], (int) x, (int) y, 32, 32, null);
        }
        if (type == 3) { //block tengah kiri
            g.drawImage(tex.rectangle[3], (int) x, (int) y, 32, 32, null);
        }
        if (type == 4) { //block tengah kanan
            g.drawImage(tex.rectangle[4], (int) x, (int) y, 32, 32, null);
        }
        if (type == 5) { //block bawah kiri
            g.drawImage(tex.rectangle[5], (int) x, (int) y, 32, 32, null);
        }
        if (type == 6) { //block bawah tengah
            g.drawImage(tex.rectangle[6], (int) x, (int) y, 32, 32, null);
        }
        if (type == 7) { //block bawah kanan
            g.drawImage(tex.rectangle[7], (int) x, (int) y, 32, 32, null);
        }
        if (type == 12) { //gate tutup atas
            g.drawImage(tex.rectangle[12], (int) x, (int) y, 32, 32, null);
        }
        if (type == 13) { //gate tutup atas tengah
            g.drawImage(tex.rectangle[13], (int) x, (int) y, 32, 32, null);
        }
        if (type == 14) { //gate tutup bawah tengah
            g.drawImage(tex.rectangle[14], (int) x, (int) y, 32, 32, null);
        }
        if (type == 15) { //gate tutup bawah
            g.drawImage(tex.rectangle[15], (int) x, (int) y, 32, 32, null);
        }
        if (type == 16) { //gate buka atas
            g.drawImage(tex.rectangle[16], (int) x, (int) y, 32, 32, null);
        }
        if (type == 17) { //gate transparan
            g.drawImage(tex.rectangle[17], (int) x, (int) y, 32, 32, null);
        }
        if (type == 18) { //gate buka bawah
            g.drawImage(tex.rectangle[18], (int) x, (int) y, 32, 32, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
