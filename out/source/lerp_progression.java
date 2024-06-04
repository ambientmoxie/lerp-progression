/* autogenerated by Processing revision 1293 on 2024-06-04 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class lerp_progression extends PApplet {

// ------------------------------------------------------------ 
// Author : Maxime Benoit
// Name   : Lerp Progression
// Date   : 23/03/2022
// ------------------------------------------------------------ 

PGraphics sc;

int c1 = 0xFFE3D0A6;
int c2 = 0xFFFF2C00;
int bg = 0xFFECE1C5;

float border = 50;
boolean isVertical = false; // Set this to true for vertical distribution
boolean isReverse = true;

public void setup() {
    /* size commented out by preprocessor */; // Simplified size calculation
    /* pixelDensity commented out by preprocessor */; // High-DPI displays support
    sc = createGraphics(width, height);
    noStroke();
    
    background(bg); // Background color setup
    
    // Drawing stripes
    for (int i = 0; i < 4; i++) {
        if(isReverse){
            if (i % 2 == 0) {
                drawStripe(pow(2, 3 - i) * 10, i, c1, c2, isVertical);
            } else {
                drawStripe(pow(2, 3 - i) * 10, i, c2, c1, isVertical);
            }
        } else {
            drawStripe(pow(2, 3 - i) * 10, i, c1, c2, isVertical);
        }
    }

    imageMode(CENTER);
    image(sc, width / 2, height / 2, width - border, height - border);

    save("output.tif"); // Save the output image
}

// ----------------------------
// Draws horizontal or vertical stripes
// ----------------------------
public void drawStripe(float amount, int row, int from, int to, boolean vertical) {
    float w = vertical ? width / 4 : width / amount; // Width of each stripe for horizontal, height for vertical
    float h = vertical ? height / amount : height / 4; // Height of each stripe for horizontal, width for vertical

    sc.beginDraw();

    for (int i = 0; i < amount; ++i) {
        float index = map(i, 0, amount, 0, 1); // Interpolated index for color
        int lerp = lerpColor(from, to, index); // Gradient color

        float x = vertical ? w * row : w * i; // X position for vertical or horizontal
        float y = vertical ? h * i : h * row; // Y position for vertical or horizontal

        sc.fill(lerp);
        sc.stroke(lerp);
        sc.rect(x, y, w, h);
    }
    
    sc.endDraw();
}


  public void settings() { size(1440, 1018);
pixelDensity(2); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lerp_progression" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}