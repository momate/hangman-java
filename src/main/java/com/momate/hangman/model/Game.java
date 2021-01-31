package com.momate.hangman.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class Game {
    
    private String answer;
    private int life;
    private Set<Character> lettersUsed = new HashSet<>();
    private boolean isWin = false;
        
    public void addUsedLetter(char letter) {
        this.lettersUsed.add(letter);
    }
    
    public boolean isUsedLetter(char letter){
      return this.lettersUsed.contains(letter);
    }
    
    
    
    
}
