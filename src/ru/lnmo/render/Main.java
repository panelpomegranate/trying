package ru.lnmo.render;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main extends JFrame {
    private static List<String> readUsingScanner(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    static final int w = 1366;
    static final int h = 786;

    public static void draw(Graphics2D g) throws IOException {
        String fileName = "uaz_1.txt";
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// String s="0";
// s= readUsingScanner(fileName);
        int k = 0;
        double u[][] = new double[1000][3];
        double vn[][] = new double[1000][3];
        double vt[][] = new double[1000][3];
        double f[][] = new double[1000][3];
        int ff[] = new int[3];
        int kk = 0;
        for (String l : readUsingScanner(fileName)) {
            k++;
            l.split(" ");
            if(l.isEmpty())continue;
            if (l.length() != 0) {
                if (l.startsWith("v ")) {
                    kk = kk + 1;
                    String s = "";

                    String parts[] = l.split(" ");
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double z = Double.parseDouble(parts[3]);

                    u[kk][0] = x;
                    u[kk][1] = y;
                    u[kk][2] = z;
                }
            }

            if (l.charAt(0) == 'v' && l.charAt(1) == 't') {
                kk = 0;
                String s = "";
                int qq = 0;
                for (int i = 3; i < l.length(); i++) {
                    if (l.charAt(i) != ' ') {
                        s = s + l.charAt(i);
                    } else {
                        vt[i][qq] = Double.parseDouble(s);
                        s = "";

                        qq = qq + 1;
                    }
                }

            }
            if (l.charAt(0) == 'v' && l.charAt(1) == 'n') {

                String s = "";
                int qq = 0;
                for (int i = 3; i < l.length(); i++) {
                    if (l.charAt(i) != ' ') {
                        s = s + l.charAt(i);
                    } else {
                        vn[i][qq] = Double.parseDouble(s);
                        s = "";

                        qq = qq + 1;
                    }
                }

            }
            if (l.charAt(0) == 'f' && l.charAt(1) == ' ') {
                String tris [] = l.split(" ");
                int v1Id = Integer.parseInt(tris[1].split("/")[0]);
                int v2Id = Integer.parseInt(tris[2].split("/")[0]);
                int v3Id = Integer.parseInt(tris[3].split("/")[0]);
/* kk = kk + 1;
String s = "";
int qq = 0;
int ss = 0;
for (int i = 2; i < l.length(); i++) {

if (l.charAt(i) != ' ' && l.charAt(i) != '/') {
s = s + l.charAt(i);
}
if (l.charAt(i) == '/') {
if (qq == 0) {
ff[ss] = Integer.parseInt(s)-1;
}
qq = qq + 1;
}
if (l.charAt(i) == ' ') {
ss = ss + 1;
s = "";
qq = 0;
}
}
*/ int hh = v1Id;
                double x4 = u[hh][0] + 300;
                double y4 = u[hh][1] + 300;
                double z4 = u[hh][2] + 300;
                hh = v2Id;
                double x5 = u[hh][0] + 300;
                double y5 = u[hh][1] + 300;
                double z5 = u[hh][2] + 300;
                hh = v3Id;
                double x6 = u[hh][0] + 300;
                double y6 = u[hh][1] + 300;
                double z6 = u[hh][2] + 300;
                Render.renderTriangle(img, x4, y4, z4, x5, y5, z5, x6, y6, z6);
            }
        }
        g.drawImage(img, 0, 0, null);
    }

    //магический код позволяющий всему работать, лучше не трогать
    public static void main(String[] args) throws InterruptedException, IOException {
        Main jf = new Main();
        jf.setSize(w, h);//размер экрана
        jf.setUndecorated(false);//показать заголовок окна
        jf.setTitle("Моя супер программа");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.createBufferStrategy(2);
//в бесконечном цикле рисуем новый кадр
        while (true) {
            long frameLength = 1000 / 60; //пытаемся работать из рассчета 60 кадров в секунду
            long start = System.currentTimeMillis();
            BufferStrategy bs = jf.getBufferStrategy();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, jf.getWidth(), jf.getHeight());
            draw(g);

            bs.show();
            g.dispose();

            long end = System.currentTimeMillis();
            long len = end - start;
            if (len < frameLength) {
                Thread.sleep(frameLength - len);
            }
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    //Вызывается когда клавиша отпущена пользователем, обработка события аналогична keyPressed
    public void keyReleased(KeyEvent e) {

    }
}