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
import java.util.Random;

public class CheckPoint1 {

    Camera cam = new Camera();
    
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

    Random rand = new Random();
    SimplexNoise noise = new SimplexNoise(64, 0.4, rand.nextInt());
    private void renderLoop() {
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            cam.updateCamera();
            glLoadIdentity();
            cam.applyCamera();

            int gridSize = 30;
            float spacing = 1.0f;

            int maxHeight = 10;

            for (int x = 0; x < gridSize; x++) {
                for (int z = 0; z < gridSize; z++) {
                    // Use simplex noise to get height
                    double noiseVal = noise.getNoise(x, z);
                    int height = (int) ((noiseVal + 1) / 2 * maxHeight); // Normalize from [-1,1] to [0,1] then scale

                    for (int y = 0; y < height; y++) {
                        glPushMatrix();
                        glTranslatef(x * spacing, y * spacing, z * spacing);
                        Cube.drawCube();
                        glPopMatrix();
                    }
                }
            }

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    public static void main(String[] args) {
        new CheckPoint1().start();
    }
}

