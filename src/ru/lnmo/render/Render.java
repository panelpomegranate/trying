package ru.lnmo.render;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {

    public static void render(BufferedImage img){
//        img.setRGB(500, 300, new Color(255, 0, 200).getRGB());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                img.setRGB(i, j, new Color(i * j % 256, (i + j) % 256, (i * i + j * j) % 256).getRGB() );
            }
        }
    }

    //Стоит начать с этого
    public static void renderLine(BufferedImage img, int x1, int y1, int x2, int y2){
        for (int i = 0; i < 1366; i++) {
            for (int j = 0; j < 768; j++) {
                if(j==(((i-x1)*(y2-y1))/(x2-x1))+y1){
                    img.setRGB(i, j, new Color(0, 0, 0).getRGB() );
                }
            }
        }
    }

    public static void renderTriangle(BufferedImage img, int x1, int y1, int x2, int y2, int x3, int y3){
        for (int i = 0; i < 1366; i++) {
            for (int j = 0; j < 768; j++) {
                if((j<=(((i-x1)*(y2-y1))/(x2-x1))+y1)&(j>=(((i-x2)*(y3-y2))/(x3-x2))+y2)&(j<=(((i-x3)*(y1-y3))/(x1-x3))+y3)){
                    img.setRGB(i, j, new Color(0, 0, 0).getRGB() );
                }
            }
        }
    }
}
