// Kenneth Ahlmann Moothedath Johansen
// Johan Maack Svendsen


Bit[] bits = new Bit[8];    // array with room for 8 on/off Bit instances
int decimal = 0;
int binary = 0;
boolean sign = false; //ny boolean til at bestemme fortegn
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
    } // if else for at sørge for den ikke skriver 128 ved mest betydende bit men fortegn i stedet

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
    if (decimal < 0) { // tester om det nuværende decimal er negativt eller postivt
      decimal -= bits[i].value; // trækker fra hvis det er negativt
    } else {
      decimal += bits[i].value;  // lægger til hvis det er positivt
    }
    
    if (sign == true) {
      if (decimal > 0 ){
        decimal = decimal * -1;
      } // tester om sign er true og ændre fortegnet til negativt hvis det nuværende decimal er positivt
    } else {
      if (decimal < 0) {
        decimal = decimal * -1;
      }
    } // ændre fortegnet til positivt hvis det nuværende var negativt 
    binary += bits[i].digit;
  }  // for
}  // keyReleased

void mouseReleased() {
  decimal = 0;
  binary = 0;
  for (int i = 0; i < bits.length; i++) {
    bits[i].updateMouse();
    if (decimal < 0) { // tester om det nuværende decimal er negativt eller postivt
      decimal -= bits[i].value; // trækker fra hvis det er negativt
    } else {
      decimal += bits[i].value;  // lægger til hvis det er positivt
    }
    
    if (sign == true) {
      if (decimal > 0 ){
        decimal = decimal * -1;
      } // tester om sign er true og ændre fortegnet til negativt hvis det nuværende decimal er positivt
    } else {
      if (decimal < 0) {
        decimal = decimal * -1;
      }
    } // ændre fortegnet til positivt hvis det nuværende var negativt
    
    binary += bits[i].digit;
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
          if (8 - position != 7) { //tester om det ikke er sign-bitten og opløfter 2 i den placeringen af bitten
            value = int (pow(2, 8 - position));
           } else { // ædnre sign til true hvis det var sign-bitten der var ændret
             value = 0;
             sign = true;
           }  
          break;
        case (255):
          colour = (55);
          value = 0;
          digit = 0;
          if (8 - position == 7) {
            sign = false;
          } // tester om det er sign-bitten og ændre sign til false hvis det er den der er slået fra
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
          if (8 - position != 7) { //tester om det ikke er sign-bitten og opløfter 2 i den placeringen af bitten
            value = int (pow(2, 8 - position));
           } else { // ædnre sign til true hvis det var sign-bitten der var ændret
             value = 0;
             sign = true;
           }  
          break;
        case (255):
          colour = (55);
          value = 0;
          digit = 0;
          if (8 - position == 7) {
            sign = false;
          } // tester om det er sign-bitten og ændre sign til false hvis det er den der er slået fra

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
