/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkpoint1;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 * Handles camera position, orientation, and movement using keyboard & mouse.
 */
public class Camera {
    
    private float camX = 0, camY = 0, camZ = 5;
    private float pitch = 0, yaw = 0;
    private final float speed = 0.1f;
    
    public void updateCamera() {
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

    public void applyCamera() {
        glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw, 0, 1, 0);
        glTranslatef(-camX, -camY, -camZ);
    }
}
