package com.sdsmdg.bouncyball;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private float x, y;
    private float vx = 0, vy = 0;
    private float ax, ay = 9.8f;
    int side = 20;
    int increasedSize = 100;
    static int h;
    String TAG = "harshit";
    boolean grow = false;
    boolean shrink = false;
    boolean firstBlock = false;
    boolean lastBlock = false;
    static List<Block> stack = new ArrayList<>();

    public Block(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getAy() {
        return ay;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        Block.h = h;
    }

    public void update(int time) {

        if (y <= h - side / 2) {
            vy = vy + (ay * 0.01f * time);
            y = y + (vy * 0.01f * time);
            x = x + (vx * 0.01f * time);
        } else {
            vy = -3 * vy / 10;
            vx = 0;
            y = h - side / 2 + vy * time * 0.01f;
            if (firstBlock) {
                grow = true;
                stack.add(this);
            }
            else {
                shrink = true;
            }
        }
        grow();
        shrink();

    }

    public void checkCollission(Block block) {

        if (x - block.x >= 0 && x - block.x < (side + block.side) / 2 && y - block.y >= 0 && y - block.y < (side + block.side) / 2) {//top left
            if (x - block.x > y - block.y) {
                block.x = x - (side + block.side) / 2;
                block.vx = -0.3f * block.vx;
                block.shrink = true;
            } else {
                block.y = y - (side + block.side) / 2;
                block.vy = - 0.0f * block.vy;
                block.vx = 0;
                if(lastBlock) {
                    block.grow = true;
                    stack.add(block);
                }
                else if(!block.grow)//This is to prevent the ball on top to shrink again
                    block.shrink = true;
            }
        } else if (x - block.x >= 0 && x - block.x < (side + block.side) / 2 && block.y - y >= 0 && block.y - y < (side + block.side) / 2) {//bottom left
            if (x - block.x > block.y - y) {
                block.x = x - (side + block.side) / 2;
                block.vx = -0.3f * block.vx;
                block.shrink = true;
            } else {
                block.y = y + (side + block.side) / 2;
                block.vy = - 0.0f * block.vy;
                block.shrink = true;
            }
        } else if (block.x - x >= 0 && block.x - x < (side + block.side) / 2 && block.y - y >= 0 && block.y - y < (side + block.side) / 2) {//bottom right
            if (block.x - x > block.y - y) {
                block.x = x + (side + block.side) / 2;
                block.vx = -0.3f * block.vx;
                block.shrink = true;
            } else {
                block.y = y + (side + block.side) / 2;
                block.vy = - 0.0f * block.vy;
                block.shrink = true;
            }
        } else if (block.x - x >= 0 && block.x - x < (side + block.side) / 2 && y - block.y >= 0 && y - block.y < (side + block.side) / 2) {//top right
            if (block.x - x > y - block.y) {
                block.x = x + (side + block.side) / 2;
                block.vx = -0.3f * block.vx;
                block.shrink = true;
            } else {
                block.y = y - (side + block.side) / 2;
                block.vy = - 0.0f * block.vy;
                block.vx = 0;
                if(lastBlock) {
                    block.grow = true;
                    stack.add(block);
                }
                else if(!block.grow)
                    block.shrink = true;
            }
        }
    }

    public void grow() {
        if (grow && !shrink) {
            if (side < increasedSize) {
                side++;
            }
            if (y == increasedSize) {
                grow = false;
            }
        }
    }

    public void shrink() {
        if (shrink) {
            if (side > 0) {
                side--;
            }
        }
    }

}

