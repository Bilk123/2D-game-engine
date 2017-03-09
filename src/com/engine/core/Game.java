package com.engine.core;

import com.engine.core.entity.mob.Player;
import com.engine.core.graphics.Screen;
import com.engine.core.input.Keyboard;
import com.engine.core.input.Mouse;
import com.engine.core.level.DefaultLevel;
import com.engine.core.util.Time;
import com.engine.core.util.math.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Blake on 3/5/2017.
 */
public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 300;
    public static final int HEIGHT = (int) (WIDTH * 9.0 / 16.0);
    public static final int SCALE = 3;
    public static final String TITLE = "2D Game Engine";

    private JFrame frame;
    private boolean running = false;
    private Thread gameThread;
    private BufferedImage toRender = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) toRender.getRaster().getDataBuffer()).getData();
    private Screen screen;
    private DefaultLevel level;
    private Player player;
    private Keyboard key;

    public Game() {
        init();
    }

    private void init() {
        Dimension size = new Dimension(SCALE * WIDTH, SCALE * HEIGHT);
        setPreferredSize(size);
        key = new Keyboard();
        Mouse mouse = new Mouse();
        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        screen = new Screen(WIDTH, HEIGHT);
        level = new DefaultLevel("/levels/default level.png");
        player = new Player(new Vector2f(10, 8), "/sprite sheets/playerMain.png", key, level);
        initFrame();

    }

    private void initFrame() {
        frame = new JFrame(TITLE);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Window loaded: " + TITLE);
        System.out.println("Buffer size: " + WIDTH + " : " + HEIGHT);
        System.out.println("Screen size: " + getWidth() + " : " + getHeight());
    }

    public synchronized void start() {
        running = true;
        gameThread = new Thread(this, TITLE);
        gameThread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = Time.getTime();
        final double ns = Time.SECOND / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        long timer = System.currentTimeMillis();
        requestFocus();
        while (running) {
            long now = Time.getTime();
            delta += (now - lastTime) / ns;
            Time.setDeltaTime(delta / 60.0);
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
                updates++;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(TITLE + " | " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void update() {
        key.update();
        player.update();
        level.update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        int xScroll = (int) (player.loc.getX() - screen.getWidth() / 2);
        int yScroll = (int) (player.loc.getY() - screen.getHeight() / 2);
        level.render(xScroll, yScroll, screen);
        player.render(screen);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(toRender, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
