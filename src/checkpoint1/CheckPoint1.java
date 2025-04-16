/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkpoint1;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse; // for using Mouse
import java.io.*;
import java.util.*;


//testing commits with this comment
public class CheckPoint1 {

    private float camX = 0, camY = 0, camZ = 5;
    private float pitch = 0, yaw = 0;
    private final float speed = 0.1f;

    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Checkpoint 1");
            Display.create();
            initGL();
            renderLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glFrustum(-1, 1, -0.75, 0.75, 1, 100);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);
        glClearColor(0f, 0f, 0f, 1f);
        Mouse.setGrabbed(true);
    }

    private void renderLoop() {
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            updateCamera();
            glLoadIdentity();
            applyCamera();

            drawCube();

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    private void updateCamera() {
        yaw += Mouse.getDX() * 0.1f;
        pitch -= Mouse.getDY() * 0.1f;

        float dx = (float) Math.sin(Math.toRadians(yaw)) * speed;
        float dz = (float) Math.cos(Math.toRadians(yaw)) * speed;
        
        // Moving In
        if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            camX -= dx;
            camZ += dz;
        }
        // Moving Out
        if (Keyboard.isKeyDown(Keyboard.KEY_W)|| Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            camX += dx;
            camZ -= dz;
        }
        // Moving Up
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) camY += speed;
        
        // Moving Down
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) camY -= speed;
        
        // Moving Right
        if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            camX += dz;
            camZ += dx;
        }
        // Moving Left
        if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            camX -= dz;
            camZ -= dx;
        }

    }

    private void applyCamera() {
        glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw, 0, 1, 0);
        glTranslatef(-camX, -camY, -camZ);
    }

    private void drawCube() {
        float size = 1f;
        glBegin(GL_QUADS);

        // Front (red)
        glColor3f(1, 0, 0);
        glVertex3f(-size, -size, -size);
        glVertex3f(size, -size, -size);
        glVertex3f(size, size, -size);
        glVertex3f(-size, size, -size);

        // Back (green)
        glColor3f(0, 1, 0);
        glVertex3f(-size, -size, size);
        glVertex3f(-size, size, size);
        glVertex3f(size, size, size);
        glVertex3f(size, -size, size);

        // Left (blue)
        glColor3f(0, 0, 1);
        glVertex3f(-size, -size, size);
        glVertex3f(-size, -size, -size);
        glVertex3f(-size, size, -size);
        glVertex3f(-size, size, size);

        // Right (yellow)
        glColor3f(1, 1, 0);
        glVertex3f(size, -size, -size);
        glVertex3f(size, -size, size);
        glVertex3f(size, size, size);
        glVertex3f(size, size, -size);

        // Top (cyan)
        glColor3f(0, 1, 1);
        glVertex3f(-size, size, -size);
        glVertex3f(size, size, -size);
        glVertex3f(size, size, size);
        glVertex3f(-size, size, size);

        // Bottom (magenta)
        glColor3f(1, 0, 1);
        glVertex3f(-size, -size, -size);
        glVertex3f(-size, -size, size);
        glVertex3f(size, -size, size);
        glVertex3f(size, -size, -size);

        glEnd();
    }

    public static void main(String[] args) {
        new CheckPoint1().start();
    }
}

