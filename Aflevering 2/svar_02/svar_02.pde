// Johan Svendsen og Kenneth Johansen
// Active PDE program version.
// 8-bit Binary to Unsigned integer number


Bit[] bits = new Bit[8];    // array with room for 8 on/off Bit instances
int decimal = 0;
int binary = 0;
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
    text(bitValue, width/9 * bits[i].position - 10, 50);
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
  }  // for
}  // keyReleased

void mouseReleased() {
  decimal = 0;
  binary = 0;
  for (int i = 0; i < bits.length; i++) {
    bits[i].updateMouse();
    decimal += bits[i].value;  
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
    rectMode(CENTER); //siger at rektangelet skal starte fra midten i stedet for øverste venstre hjørne
    fill(colour);
    rect(width / 9 * position, 80, 50, 50); //rect i stedet for ellipse for at lave en firkant
  }
  
  void updateKey() {
    if (key == position + 48) {
      switch(colour) {
        case (55):
          colour = (255);
          value = int (pow(2, 8 - position));
          digit = int (pow(10, 8 - position));
          break;
        case (255):
          colour = (55);
          value = 0;
          digit = 0;
          break;
      }  // switch
    }  // if      
  }  // updateKey
  
  void updateMouse() {
    if (onSquare(width/9 * position, 80, 50)) { //giver værdierne for firkanternes centrum og dens sidelængde
      switch(colour) {
        case (55):
          colour = (255);
          value = int (pow(2, 8 - position));    // slow calculations
          digit = int (pow(10, 8 - position));   
          break;
        case (255):
          colour = (55);
          value = 0;
          digit = 0;
          break;
      }  // switch
    }  // onCircle
  }  // updateMouse
}  // class

boolean onSquare(int x, int y, int side) {
  int halvSide = side / 2; //finder halvdelen af sidelængden
  int rectX1 = x - halvSide;  //trækker en halvside fra centrum
  int rectX2 = rectX1 + side;  // addere den ene side med sidelængden for at finde den anden side
  int rectY1 = y - halvSide; 
  int rectY2 = rectY1 + side; //finder 2 koordinatsæt for firkanten, 1 er for øverste højre hjørne 2 er for nederste venstre hjørne
  
  if ( mouseX >= rectX1 && mouseX <= rectX2 && mouseY >= rectY1 && mouseY <= rectY2 ) { //tester om musen er mellem en af firkanternes øverstehøjre hjørne og nederste venstre hjørne
    return true;
  } else {
    return false;
  }  // if
}  // end onSquare

// end
