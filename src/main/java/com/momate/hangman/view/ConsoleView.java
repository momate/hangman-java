package com.momate.hangman.view;

import java.util.Scanner;
import com.momate.hangman.service.GameService;
import com.momate.hangman.model.Game;

public class ConsoleView implements View {

    private static final String WELCOME_MSG = "Welcome to my Hangman game. Let's START!";
    private static final Scanner SC = new Scanner(System.in);
    private String emptyWord;

    private char guess;

    private GameService gameService;
    private Game game;

    public ConsoleView(GameService gameService) {
        this.gameService = gameService;
        this.game = gameService.getGame();
    }

    @Override
    public void start() {

       
    }



}
