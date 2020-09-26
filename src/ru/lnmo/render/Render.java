package ru.lnmo.render;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

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

    public static void renderLine1(BufferedImage img, int x1, int y1, int x2, int y2){
        if(x1!=x2){
            double k=((double)(y1-y2))/((double)(x1-x2));
            if((x1<x2)&(y1>=y2)){
                if(y1!=y2){
                    int y=0;
                    for (int i = min(x1, x2); i < max(x1, x2); i++) {
                        for (int j = min(y1, y2); j < max(y1, y2); j++) {
                            if(j==(((i-x1)*(y2-y1))/(x2-x1))+y1){
                                y=j;
                                img.setRGB(i, j, new Color(0, 0, 0).getRGB() );
                            }else {
                                img.setRGB(i, y, new Color(0, 0, 0).getRGB());
                            }
                        }
                    }
                }else{
                    int y=y1;
                    for (int i = min(x1, x2); i < max(x1, x2); i++) {
                        img.setRGB(i, y, new Color(0, 0, 0).getRGB());
                    }
                }
            }else{
                int y = 768;
                for (int i = min(y1, y2); i <max(y1, y2); i++) {
                    for (int j = min(x1, x2); j <max(x1, x2); j++) {
                        if (i == (((j - x1) * (y2 - y1)) / (x2 - x1)) + y1) {
                            y = j;
                            img.setRGB(j, i, new Color(0, 0, 0).getRGB());
                        } else {
                            img.setRGB(y, i, new Color(0, 0, 0).getRGB());
                        }
                    }
                }
            }
        }else{
            for (int i = min(y1, y2); i < max(y1, y2); i++) {
                img.setRGB(x1, i, new Color(0, 0, 0).getRGB());
            }
        }
    }

    public static void renderTriangle(BufferedImage img, int x1, int y1, int x2, int y2, int x3, int y3){
        int max_y=max(y1, max(y2, y3));
        int max_x=max(x1, max(x2, x3));
        int min_x=min(x1, min(x2, x3));
        int min_y=min(y1, min(y2, y3));
        int r=0;
        double a= (double)((x2-x1)*(y3-y1)-(y2-y1)*(x3-x1));
        for (int x = min_x; x <= max_x; x++) {
            r++;
            for (int y = min_y; y <= max_y; y++) {
                Color c= new Color(r, 0, 0);
                double u=((double)((x-x1)*(y3-y1)-(x3-x1)*(y-y1)))/(a);
                double v=((double)((x2-x1)*(y-y1)-(x-x1)*(y2-y1)))/(a);
                if(((u+v)<=1)&(u>=0)&(v>=0)){
                    img.setRGB(x, y, c.getRGB());
                }
            }
        }
    }
    public static void renderTriangle1(BufferedImage img, int x1, int y1, int x2, int y2, int x3, int y3, Color c){
        int max_y=max(y1, max(y2, y3));
        int max_x=max(x1, max(x2, x3));
        int min_x=min(x1, min(x2, x3));
        int min_y=min(y1, min(y2, y3));
        double a= (double)((x2-x1)*(y3-y1)-(y2-y1)*(x3-x1));
        for (int x = min_x; x <= max_x; x++) {
            for (int y = min_y; y <= max_y; y++) {
                double u=((double)((x-x1)*(y3-y1)-(x3-x1)*(y-y1)))/(a);
                double v=((double)((x2-x1)*(y-y1)-(x-x1)*(y2-y1)))/(a);
                if(((u+v)<=1)&(u>=0)&(v>=0)){
                    img.setRGB(x, y, c.getRGB());
                }
            }
        }
    }
    //вектора
    //сумма, она же разность, если v2 брать с отрицательными координатами
    public static int[] addition(int[] v1, int[] v2){
        int[] sum=new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            sum[i]=v1[i]+v2[i];
        }
        return sum;
    }

    //умножение на скаляр
    public static int[] multiplication_scal( int[] v, int a){
        int[]res=new int[v.length];
        for (int i = 0; i < v.length; i++) {
            res[i]=v[i]*a;
        }
        return res;
    }


    public static int multiplication(int[] v1, int[] v2){
        int res=0;
        for (int i = 0; i < v1.length; i++) {
            res+=v1[i]*v2[i];
        }
        return res;
    }

    public static int[] normal(int[] v){
        int[]n=new int[v.length];
        int d2=0;
        for (int i = 0; i < v.length/2; i++) {
            d2+=((v[v.length-i-1]-v[i])*(v[v.length-i-1]-v[i]));
        }
        int v_l=(int)Math.sqrt(d2);
        for (int i = 0; i < v.length/2; i++) {
            n[i]=v[i];
        }
        for (int i = v.length/2; i < v.length; i++) {
            n[i]=v[i]-v[v.length-i-1];
        }
        return n;
    }

    //матрицы
    //умножение
    public static int[][] matrixmultiplication(int[][] m1, int[][]m2){
        int[][]res=new int[m1.length][m2[0].length];
        if(m1[0].length==m2.length){
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[0].length; j++) {
                    for (int k = 0; k < m1[0].length; k++) {
                        res[i][j] += (m1[i][k] * m2[k][j]);
                    }
                }
            }
            return res;
        }else{
            return res;
        }
    }

    //сложение
    public static int[][] matrixaddition(int[][] m1, int[][]m2) {
        int[][] res = new int[m1.length][m1[0].length];
        if ((m1.length==m2.length)&(m1[0].length==m2[0].length)){
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m1[0].length; j++) {
                    res[i][j] = m1[i][j] + m2[i][j];
                }
            }
            return res;
        }else {
            return res;
        }
    }
}
