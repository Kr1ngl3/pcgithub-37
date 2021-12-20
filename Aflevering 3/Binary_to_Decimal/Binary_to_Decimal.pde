// Kenneth Johansen
// Johan Svendsen


Bit[] bits = new Bit[8];    // array with room for 8 on/off Bit instances
int decimal = 0;
int binary = 0;
int sign = 1; //ny int til at bestemme fortegn
PFont font;

void setup() {
  size(600, 300);
  noStroke();
  font = createFont("Arial", 48, true);    // Windows 10 have a wrong font path
  for (int i = 0; i < bits.length; i++) {
    bits[i] = new Bit(i);
  }  // for
}  // setup

void draw() {
  background(0);
  for (int i = 0; i < bits.length; i++) {
    bits[i].display();
    fill(255);
    int bitValue = 1 << (bits.length - i - 1);    // very fast calculation of 2^i
    if (bits.length - i - 1 != 7) {
          text(bitValue, width/9 * bits[i].position - 10, 50);
    } else {
      text("fortegn", width/9 * bits[i].position - 10, 50);
    } // if else for at sørge for den ikke skriver 128 ved den sidte men fortegn i stedet

  }  // for
  fill(255);
  textFont(font, 48);
  textAlign(RIGHT);
  text(nf(binary, 8), width/9*8, 180);
  text(decimal, width/9*8, 230);
  textAlign(LEFT);
  text("Binært:", width/9, 180);
  text("Decimalt:", width/9, 230);
  textFont(font, 18);
  fill(0, 255, 255);  // Cyan text
  text("Klik en bit for at tænde (og addere værdien 2^n) eller sluk for en bit.", width/22, 25);
}  // draw

void keyReleased() {
  decimal = 0;
  binary = 0;
  for (int i = 0; i < bits.length; i++) {
    bits[i].updateKey();
    decimal += bits[i].value;  
    binary += bits[i].digit;
    sign = 1; // sørger for der ikke skiftes fortegn når ikke der skal
  }  // for
}  // keyReleased

void mouseReleased() {
  decimal = 0;
  binary = 0;
  for (int i = 0; i < bits.length; i++) {
    bits[i].updateMouse();
    decimal += bits[i].value;  
    decimal = decimal * sign; //ændre fortegnet hvis det skal ændres
    binary += bits[i].digit;
    sign = 1; // sørger for der ikke skiftes fortegn når ikke der skal
  }  // for
}  // mouseReleased

class Bit {             // Bit object class
  int position;
  color colour = (55);  // Grey
  int value = 0;
  int digit = 0;
  
  Bit(int pos) {
    position = pos + 1;
  }
  
  void display() {
    fill(colour);
    ellipse(width/9 * position, 80, 50, 50);
  }
  
  void updateKey() {
    if (key == position + 48) {
      switch(colour) {
        case (55):
          colour = (255);
          digit = int (pow(10, 8 - position));
          if (8 - position != 7) {
            value = int (pow(2, 8 - position));
           } else {
             value = 0;
             sign = -1;
           }
          break;
        case (255):
          colour = (55);
          value = 0;
          digit = 0;
          sign = 1; // sørger for der ikke skiftes fortegn når ikke der skal
          break;
      }  // switch
    }  // if      
  }  // updateKey
  
  void updateMouse() {
    if (onCircle(width/9 * position, 80, 50)) {
      switch(colour) {
        case (55):
          colour = (255);
          digit = int (pow(10, 8 - position));
          if (8 - position != 7) {
            value = int (pow(2, 8 - position));
           } else {
             value = 0;
             sign = -1;
           }  
          break;
        case (255):
          colour = (55);
          value = 0;
          digit = 0;
          sign = 1; // sørger for der ikke skiftes fortegn når ikke der skal
          break;
      }  // switch
    }  // onCircle
  }  // updateMouse
}  // class

boolean onCircle(int x, int y, int diameter) {
  float distX = x - mouseX;
  float distY = y - mouseY;
  int radius = diameter / 2;
  if ( sqrt(sq(distX) + sq(distY)) < radius ) {
    return true;
  } else {
    return false;
  }  // if
}  // end onCircle

// end
