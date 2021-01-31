package com.momate.hangman.service;

import com.momate.hangman.repository.WordRepository;
import com.momate.hangman.model.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameService {

    private final WordRepository wordRepository;
    private Game game;

    public GameService(WordRepository wordRepository,int life) {
        this.wordRepository = wordRepository;
        initGame(life);
    }

    public boolean isCorrectGuess(char letter) {
        game.addUsedLetter(letter);
        String answer = game.getAnswer();
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter) {
                return true;
            }
        }
        game.setLife(game.getLife() - 1);
        return false;
    }


    
    private void initGame(int life){
        game = new Game();
        game.setAnswer(getRandomWord());
        game.setLife(life);
               
    }

    private String getRandomWord() {
        int max = wordRepository.getWordListSize();
        return this.wordRepository.getWord(getRandomInt(max));
    }

    private int getRandomInt(int max) {
        return (int) Math.floor(Math.random() * Math.floor(max));
    }

}
