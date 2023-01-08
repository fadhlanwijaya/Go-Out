package com.mfw.goout.framework;

import com.mfw.goout.window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet bs, ps, rs;
    private BufferedImage background_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage rectangle_sheet = null;

    public BufferedImage[] background = new BufferedImage[4];
    public BufferedImage[] player = new BufferedImage[9];
    public BufferedImage[] rectangle = new BufferedImage[19];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            background_sheet = loader.loadImage("/background.png");
            player_sheet = loader.loadImage("/player.png");
            rectangle_sheet = loader.loadImage("/rectangle.png");
        } catch (Exception e){
            e.printStackTrace();
        }

        bs = new SpriteSheet(background_sheet);
        ps = new SpriteSheet(player_sheet);
        rs = new SpriteSheet(rectangle_sheet);

        getTextures();
    }

    private void getTextures(){
        background[0] = bs.grabImage(0,0,960,640); //menu bg
        background[1] = bs.grabImage(0,640,960,640); //level bg
        background[2] = bs.grabImage(0,1280,480,320); //button bg
        background[3] = bs.grabImage(0,1600,200,96); //button

        player[0] = ps.grabImage(1688,0,422,208); //idle player
        player[1] = ps.grabImage(0,208,422,208); //falling anim player
        player[2] = ps.grabImage(422,208,422,208); //falling anim player
        player[3] = ps.grabImage(844,208,422,208); //falling anim player
        player[4] = ps.grabImage(1266,208,422,208); //falling anim player
        player[5] = ps.grabImage(0,0,422,208); //jumping anim player
        player[6] = ps.grabImage(422,0,422,208); //jumping anim player
        player[7] = ps.grabImage(844,0,422,208); //jumping anim player
        player[8] = ps.grabImage(1266,0,422,208); //jumping anim player

        rectangle[0] = rs.grabImage(0,0,32,32); //block atas kiri
        rectangle[1] = rs.grabImage(32,0,32,32); //block atas tengah
        rectangle[2] = rs.grabImage(64,0,32,32); //block atas kanan

        rectangle[3] = rs.grabImage(0,32,32,32); //block tengah kiri
        rectangle[4] = rs.grabImage(64,32,32,32); //block tengah kanan

        rectangle[5] = rs.grabImage(0,64,32,32); //block bawah kiri
        rectangle[6] = rs.grabImage(32,64,32,32); //block bawah tengah
        rectangle[7] = rs.grabImage(64,64,32,32); //block bawah kanan

        rectangle[8] = rs.grabImage(0,96,32,32); //pipe kecil atas
        rectangle[9] = rs.grabImage(32,96,64,32); //pipe besar atas
        rectangle[10] = rs.grabImage(0,128,32,32); //pipe kecil bawah
        rectangle[11] = rs.grabImage(32,128,64,32); //pipe kecil bawah

        rectangle[12] = rs.grabImage(0,160,32,32); //gate tutup atas
        rectangle[13] = rs.grabImage(0,192,32,32); //gate tutup atas tengah
        rectangle[14] = rs.grabImage(0,224,32,32); //gate tutup bawah tengah
        rectangle[15] = rs.grabImage(0,256,32,32); //gate tutup bawah
        rectangle[16] = rs.grabImage(32,160,32,32); //gate buka atas
        rectangle[17] = rs.grabImage(32,192,32,32); //gate transparan
        rectangle[18] = rs.grabImage(32,224,32,32); //gate buka bawah
    }

}
