// ------------------------------------------------------------ 
// Author : Maxime Benoit
// Name   : Lerp Progression
// Date   : 23/03/2022
// Review : 04/06/2024
// ------------------------------------------------------------ 

PGraphics sc;

color c1 = #e3d0a6;
color c2 = #ff2c00;
color bg = #ece1c5;

float border = 50;
boolean isVertical = false;
boolean isReverse = true;

void setup() {
    size(1440, 1018);
    pixelDensity(2);
    sc = createGraphics(width, height);
    noStroke();
    
    background(bg);
    
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

void drawStripe(float amount, int row, color from, color to, boolean vertical) {
    float w = vertical ? width / 4 : width / amount; // Width of each stripe for horizontal, height for vertical
    float h = vertical ? height / amount : height / 4; // Height of each stripe for horizontal, width for vertical

    sc.beginDraw();

    for (int i = 0; i < amount; ++i) {
        float index = map(i, 0, amount, 0, 1); // Interpolated index for color
        color lerp = lerpColor(from, to, index); // Gradient color

        float x = vertical ? w * row : w * i; // X position for vertical or horizontal
        float y = vertical ? h * i : h * row; // Y position for vertical or horizontal
        
        sc.fill(lerp);
        sc.stroke(lerp);
        sc.rect(x, y, w, h);
    }
    
    sc.endDraw();
}

