// Kenneth Johansen og Johan Svendsen

size(500, 500);

square(100, 100, 50); //kvadrat defineret af hjørne og en sidelængde

rect(100, 200, 50, 50); // rektangel defineret af et hjørne og 2 sidelængder

quad(200, 100, 250, 100, 250, 150, 200, 150); // firkant difineret af  4 hjørner

//forskellige rectModes
rectMode(CORNER); //default metoden at lave en rect på
rectMode(CENTER); //bruger midten til at definere placering
rect(325, 225, 50, 50);
rectMode(RADIUS); // bruger også midten til at definere placering men bruger "radius" i stedet for en fuld sidelængde
rect(425, 225, 25, 25);


// ekstra firkanter af 2 trekanter og af 4 linjer
line(200, 200, 250, 200);
line(250, 200, 250, 250);
line(250, 250, 200, 250);
line(200, 250, 200, 200);

triangle(300, 100, 350, 150, 300, 150);
triangle(350, 100, 350, 150, 300, 100);
