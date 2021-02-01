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
        prepare();

        while (!game.isWin() && game.getLife() > 0) {
            emptyWordPrinter(emptyWord);
            inputValidator();

            if (game.isUsedLetter(guess)) {
                System.out.println("This letter is already used. Try another");
            } else {
                checkGuess();
            }
            System.out.println("Guessed letters: " + game.getLettersUsed().toString());
        }

        finish();
    }
    
    private void emptyWordPrinter(String emptyWord){
        String formattedWord = emptyWord.replaceAll(".(?!$)", "$0 ");
        System.out.println(formattedWord);
    }

    private void inputValidator() {
        String input = SC.nextLine();
        while (input.length() != 1 || input.contains(" ")) {
            System.out.println("You can only guess letters");
            input = SC.nextLine();
        }

        guess = input.charAt(0);
    }

    private void checkGuess() {
        if (!gameService.isCorrectGuess(guess)) {
            noteIncorrectGuess();
        } else {
            int indexOfChar = game.getAnswer().indexOf(guess);
            emptyWord = gameService.correctLetter(emptyWord, guess, indexOfChar);
            checkIsWin(emptyWord);
        }
    }

    private void checkIsWin(String currentWord) {
        emptyWord = currentWord;
        if (currentWord.indexOf("_") == -1) {
            game.setWin(true);
        }
    }

    private void prepare() {
        System.out.println(WELCOME_MSG);
        emptyWord = game.getAnswer().replaceAll(".", "_");

    }

    private void finish() {
        if (game.isWin()) {
            System.out.println("You were correct, the answer is: " + game.getAnswer());
            System.out.println("WIN");
        } else {
            System.out.println("LOSE");
            System.out.println("The answer is : " + game.getAnswer());
        }

        SC.close();
    }

    private void noteIncorrectGuess() {
        boolean isLast = game.getLife() == 1;
        if (isLast) {
            System.out.println("This is your last chance");
        } else {
            System.out.printf("Wrong Answer! %d attempt left", game.getLife());
            System.out.println();
        }

    }
}
