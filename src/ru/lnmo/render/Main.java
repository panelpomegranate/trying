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
        String fileName = "C:/Users/leoso/Downloads/uaz.txt";
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

            if (l.length() != 0) {
                if (l.charAt(0) == 'v' && l.charAt(1) == ' ') {
                    kk = kk + 1;
                    String s = "";
                    int qq = 0;
                    for (int i = 2; i < l.length(); i++) {
                        if (l.charAt(i) != ' ') {
                            s = s + l.charAt(i);
                        } else {
                            u[kk][qq] = Double.parseDouble(s);
                            s = "";
                            qq = qq + 1;
                        }
                    }
                    u[kk][qq] = Double.parseDouble(s);
                    s = "";
                    qq = qq + 1;
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
                    kk = kk + 1;
                    String s = "";
                    int qq = 0;
                    int ss = 0;
                    for (int i = 2; i < l.length(); i++) {

                        if (l.charAt(i) != ' ' && l.charAt(i) != '/') {
                            s = s + l.charAt(i);
                        }
                        if (l.charAt(i) == '/') {
                            if (qq == 0) {
                                ff[ss] = Integer.parseInt(s);
                            }
                            qq = qq + 1;
                        }
                        if (l.charAt(i) == ' ') {
                            ss = ss + 1;
                            s = "";
                            qq = 0;
                        }
                    }
                    int hh = ff[0];
                    double x4 = u[hh][0] + 300;
                    double y4 = u[hh][1] + 300;
                    double z4 = u[hh][2] + 300;
                    hh = ff[1];
                    double x5 = u[hh][0] + 300;
                    double y5 = u[hh][1] + 300;
                    double z5 = u[hh][2] + 300;
                    hh = ff[2];
                    double x6 = u[hh][0] + 300;
                    double y6 = u[hh][1] + 300;
                    double z6 = u[hh][2] + 300;
                    Render.renderTriangle(img, x4, y4, x5, y5, x6, y6, z4, z5, z6);
                }
            }
        }
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
            long frameLength = 1000 / 60; //пытаемся работать из рассчета  60 кадров в секунду
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
