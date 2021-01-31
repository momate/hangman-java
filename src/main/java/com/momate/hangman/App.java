package com.momate.hangman;

import com.momate.hangman.repository.WordRepository;
import com.momate.hangman.service.GameService;
import com.momate.hangman.view.ConsoleView;
import com.momate.hangman.view.View;
import java.io.File;

public class App {
    
    public static void main(String[] args) {
        int gameLife = 6;
        File file = new File("wordlist.txt");
        
        WordRepository wordRepository = 
                new WordRepository(file.getAbsolutePath());
        GameService service = 
                new GameService(wordRepository,gameLife);
        View view = new ConsoleView(service);
        
        view.start();
        
    }
    
}
