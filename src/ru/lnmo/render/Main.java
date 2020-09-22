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

public class Main extends JFrame {

    static final int w = 1366;
    static final int h = 768;

    public static void draw(Graphics2D g) {
        //Создаем буффер в который рисуем кадр.
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        //Рисуем кадр.
        //Render.render(img);
        Render.renderLine1(img, 683, 384, 783, 384);
        Render.renderLine1(img, 683, 384, 683, 484);
        Render.renderLine1(img, 683, 384, 683, 284);
        Render.renderLine1(img, 583, 384, 683, 384);
        Render.renderLine1(img, 683, 384, 613, 454);
        Render.renderLine1(img, 683, 384, 613, 314);
        Render.renderLine1(img, 683, 384, 753, 314);
        Render.renderLine1(img, 683, 384, 753, 454);
        Render.renderLine1(img, 683, 384, 775, 422);
        Render.renderLine1(img, 683, 384, 644, 476);
        Render.renderLine1(img, 683, 384, 590, 345);
        Render.renderLine1(img, 721, 291, 683, 384);
        Render.renderLine1(img, 683, 384, 721, 476);
        Render.renderLine1(img, 683, 384, 590, 422);
        Render.renderLine1(img, 683, 384, 644, 291);
        Render.renderLine1(img, 683, 384, 775, 345);

        Render.renderTriangle(img, 100, 200, 200, 300, 300, 100);

        Render.renderTriangle1(img, 833, 534, 833, 634, 871, 626, new Color(0, 0, 0));
        Render.renderTriangle1(img, 833, 534, 871, 626, 903, 604, new Color(16, 16, 16));
        Render.renderTriangle1(img, 833, 534, 903, 604, 925, 572, new Color(32, 32, 32));
        Render.renderTriangle1(img, 833, 534, 925, 572, 933, 534, new Color(48, 48, 48));
        Render.renderTriangle1(img, 833, 534, 933, 534, 925, 495, new Color(64, 64, 64));
        Render.renderTriangle1(img, 833, 534, 925, 495, 903, 463, new Color(80, 80, 80));
        Render.renderTriangle1(img, 833, 534, 903, 463, 871, 441, new Color(96, 96, 96));
        Render.renderTriangle1(img, 833, 534, 871, 441, 833, 434, new Color(112, 112, 112));
        Render.renderTriangle1(img, 833, 534, 833, 434, 794, 441, new Color(128, 128, 128));
        Render.renderTriangle1(img, 833, 534, 794, 441, 762, 463, new Color(144, 144, 144));
        Render.renderTriangle1(img, 833, 534, 762, 463, 740, 495, new Color(160, 160, 160));
        Render.renderTriangle1(img, 833, 534, 740, 495, 733, 534, new Color(176, 176, 176));
        Render.renderTriangle1(img, 833, 534, 733, 534, 740, 572, new Color(192, 192, 192));
        Render.renderTriangle1(img, 833, 534, 740, 572, 762, 604, new Color(208, 208, 208));
        Render.renderTriangle1(img, 833, 534, 762, 604, 794, 626, new Color(224, 224, 224));
        Render.renderTriangle1(img, 833, 534, 794, 626, 833, 634, new Color(240, 240, 240));

        g.drawImage(img, 0, 0, null);
    }



    //магический код позволяющий всему работать, лучше не трогать
    public static void main(String[] args) throws InterruptedException {
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
