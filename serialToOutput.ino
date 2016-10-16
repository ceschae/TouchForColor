void setup() {
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  
  while(1) {
    int symbols[] = {0, 0, 0, 0, 0, 0};

    for(int i = 0; i < 6; i++) {
      String num = Serial.readString();
      symbols[i] = num.charAt(i);
      Serial.print(num);
    }

      for (int i = 0; i < 5; i++) {
        if(symbols[i] == '1') {
          analogWrite(100, i + 2);
        } else {
          analogWrite(0, i + 2);
        }
    }

    delay(500);
    for(int i = 0; i < 5; i++) {
      digitalWrite(0, i + 2);
    }
    delay(250);
  }

}
