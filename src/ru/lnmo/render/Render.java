package ru.lnmo.render;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

public class Render {

    public static void render(BufferedImage img) {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                img.setRGB(i, j, new Color(i * j % 256, (i + j) % 256, (i * i + j * j) % 256).getRGB());
            }
        }
    }

    public static void renderLine(BufferedImage img, int x1, int y1, int x2, int y2) {
        if (x1 != x2) {
            double k = ((double) (y1 - y2)) / ((double) (x1 - x2));
            if ((x1 < x2) & (y1 >= y2)) {
                if (y1 != y2) {
                    int y = 0;
                    for (int i = min(x1, x2); i < max(x1, x2); i++) {
                        for (int j = min(y1, y2); j < max(y1, y2); j++) {
                            if (j == (((i - x1) * (y2 - y1)) / (x2 - x1)) + y1) {
                                y = j;
                                img.setRGB(i, j, new Color(0, 0, 0).getRGB());
                            } else {
                                img.setRGB(i, y, new Color(0, 0, 0).getRGB());
                            }
                        }
                    }
                } else {
                    int y = y1;
                    for (int i = min(x1, x2); i < max(x1, x2); i++) {
                        img.setRGB(i, y, new Color(0, 0, 0).getRGB());
                    }
                }
            } else {
                int y = 768;
                for (int i = min(y1, y2); i < max(y1, y2); i++) {
                    for (int j = min(x1, x2); j < max(x1, x2); j++) {
                        if (i == (((j - x1) * (y2 - y1)) / (x2 - x1)) + y1) {
                            y = j;
                            img.setRGB(j, i, new Color(0, 0, 0).getRGB());
                        } else {
                            img.setRGB(y, i, new Color(0, 0, 0).getRGB());
                        }
                    }
                }
            }
        } else {
            for (int i = min(y1, y2); i < max(y1, y2); i++) {
                img.setRGB(x1, i, new Color(0, 0, 0).getRGB());
            }
        }
    }

    public static void renderTriangle(BufferedImage img, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3) {
        {
            for (int x = (int) (Math.min(x1, Math.min(x2, x3))); x <= (int) (Math.max(x1, Math.max(x2, x3))); x++) {
                for (int y = (int) (Math.min(y1, Math.min(y2, y3))); y < (int) (Math.max(y1, Math.max(y2, y3))); y++) {
                    double AB[] = {x2 - x1, y2 - y1, z2 - z1};
                    double AC[] = {x3 - x1, y3 - y1, z3 - z1};
                    double PA[] = {x1 - x, y1 - y};
                    double u1[] = {AB[0], AC[0], PA[0]};
                    double u2[] = {AB[1], AC[1], PA[1]};
                    double z[] = new double[3];
                    z[0] = u1[2]* u2[1] - u1[1] * u2[2];
                    z[1] = u1[0] * u2[2] - u1[2] * u2[0];
                    z[2] = u1[1] * u2[0] - u1[0] * u2[1];
                    double u = z[0] / z[2];
                    double v = z[1] / z[2];
                    if (u + v <= 1 && u >= 0 && v >= 0) {
                        if (x >= 0 & y >= 0) {
                            img.setRGB(x, y, new Color(0, 0, 0).getRGB());
                        }
                    }
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

    //произведение двух трехмерных векторов
    public static double[] multiplication(double[] v1, double[] v2){
        double[] res=new double[3];

        res[0]=v1[1]*v2[2]-v1[2]*v2[1];
        res[1]=v1[2]*v2[0]-v1[0]*v2[2];
        res[2]=v1[0]*v2[1]-v1[1]*v2[0];

        return res;
    }

    //нормализация
    public static double[] normalize(double[] v){
        double res[]=new double[v.length];
        double z=0;
        for (int i = 0; i < v.length; i++) {
            z+=v[i]*v[i];
        }
        for (int i = 0; i < v.length; i++) {
            res[i]=v[i]/(Math.sqrt(z));
        }
        return res;
    }

    //скалярное произведение векторов размерности 3
    public static double scal_multiplication(double[] v1, double[] v2){
        double res=0;

        for (int i = 0; i < v1.length; i++) {
            res+=v1[i]*v2[i];
        }

        return res;
    }

    //умножение матрицы 3x3 на вектор длины 3
    public static double[] matrixandvector(double[][]m, double[] v){
        double[]res=new double[3];

        for (int i = 0; i < 3; i++) {
            res[i]+=m[i][0]*v[0]+m[i][1]*v[1]+m[i][2]*v[2];
        }

        return res;
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