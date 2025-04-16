package checkpoint1;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Represents the cube geometry and color data. Handles drawing itself in 3D.
 */
public class Cube {
    public static void drawCube() {
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
}