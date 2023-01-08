package com.mfw.goout.window;

import com.mfw.goout.framework.ObjectId;
import com.mfw.goout.framework.Texture;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Camera cam;
    Texture tex = Game.getInstance();

    public Menu(Game game, Handler handler, Camera cam){
        this.game = game;
        this.handler = handler;
        this.cam = cam;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.MENU){
            //play
            if(mouseOver(mx, my, (Game.WIDTH/2)-100, 288, 200, 64)){
                game.gameState = Game.STATE.GAME;
                game.createLevel();
            }
            //quit
            if(mouseOver(mx, my, (Game.WIDTH/2)-100, 394, 200, 64)){
                System.exit(1);
            }
        }
        if(game.gameState == Game.STATE.OVER){
            //play again
            if(mouseOver(mx, my, (Game.WIDTH/2)-100, 280, 200, 64)){
                game.gameState = Game.STATE.GAME;
                game.createLevel();
            }
            //main menu
            if(mouseOver(mx, my, (Game.WIDTH/2)-100, 364, 200, 64)) {
                game.gameState = Game.STATE.MENU;
            }
        }
        if(game.gameState == Game.STATE.END){
            //play again
            if(mouseOver(mx, my, (Game.WIDTH/2)-100, 280, 200, 64)){
                game.gameState = Game.STATE.GAME;
                game.createLevel();
            }
            //main menu
            if(mouseOver(mx, my, (Game.WIDTH/2)-100, 364, 200, 64)) {
                game.gameState = Game.STATE.MENU;
            }
        }
    }

    public void mouseReleased(MouseEvent e){ }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    public void tick(){
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
            }
        }
    }

    public void render(Graphics g) {
        if (game.gameState == Game.STATE.MENU) {
            Font gameFont1 = new Font(Font.SANS_SERIF, Font.BOLD, 65);
            Font gameFont2 = new Font(Font.SANS_SERIF, Font.BOLD, 25);

            //menu bg
            g.drawImage(tex.background[0], -(int) cam.getX(), 0, 960, 640, null);

            //go out
            g.setFont(gameFont1);
            g.setColor(Color.black);
            g.drawString("GO OUT", -(int) cam.getX() + (Game.WIDTH / 2) - 128, 170);

            //start game
            g.drawImage(tex.background[3], -(int) cam.getX() + (Game.WIDTH / 2) - 100, 288, 200, 64, null);
            g.setFont(gameFont2);
            g.setColor(Color.white);
            g.drawString("PLAY", -(int) cam.getX() + (Game.WIDTH / 2) - 32, 330);

            //quit
            g.drawImage(tex.background[3], -(int) cam.getX() + (Game.WIDTH / 2) - 100, 394, 200, 64, null);
            g.setFont(gameFont2);
            g.setColor(Color.white);
            g.drawString("QUIT", -(int) cam.getX() + (Game.WIDTH / 2) - 28, 436);

        } else if (game.gameState == Game.STATE.OVER) {
            Font gameFont1 = new Font(Font.SANS_SERIF, Font.BOLD, 50);
            Font gameFont2 = new Font(Font.SANS_SERIF, Font.BOLD, 25);

            //game over bg
            g.drawImage(tex.background[0], -(int) cam.getX(), 0, 960, 640, null);
            g.drawImage(tex.background[2], -(int) cam.getX() + 240, 160, 480, 320, null);

            //game over
            g.setFont(gameFont1);
            g.setColor(Color.white);
            g.drawString("GAME OVER", -(int) cam.getX() + 330, 240);

            //play again
            g.drawImage(tex.background[3], -(int) cam.getX() + 380, 280, 200, 64, null);
            g.setFont(gameFont2);
            g.setColor(Color.white);
            g.drawString("PLAY AGAIN", -(int) cam.getX() + 405, 322);

            //main menu
            g.drawImage(tex.background[3], -(int) cam.getX() + 380, 364, 200, 64, null);
            g.setFont(gameFont2);
            g.setColor(Color.white);
            g.drawString("MAIN MENU", -(int) cam.getX() + 410, 406);

        } else if (game.gameState == Game.STATE.END) {
            Font gameFont1 = new Font(Font.SANS_SERIF, Font.BOLD, 42);
            Font gameFont2 = new Font(Font.SANS_SERIF, Font.BOLD, 25);

            //end bg
            g.drawImage(tex.background[0], -(int) cam.getX(), 0, 960, 640, null);
            g.drawImage(tex.background[2], -(int) cam.getX() + 240, 160, 480, 320, null);

            //end
            g.setFont(gameFont1);
            g.setColor(Color.white);
            g.drawString("YOU'VE SURVIVED", -(int) cam.getX() + 290, 240);

            //play again
            g.drawImage(tex.background[3], -(int) cam.getX() + 380, 280, 200, 64, null);
            g.setFont(gameFont2);
            g.setColor(Color.white);
            g.drawString("PLAY AGAIN", -(int) cam.getX() + 405, 322);

            //main menu
            g.drawImage(tex.background[3], -(int) cam.getX() + 380, 364, 200, 64, null);
            g.setFont(gameFont2);
            g.setColor(Color.white);
            g.drawString("MAIN MENU", -(int) cam.getX() + 410, 406);
        }
    }
}
