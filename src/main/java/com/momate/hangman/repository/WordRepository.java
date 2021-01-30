package com.momate.hangman.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {

    private List<String> wordList = new ArrayList<>();
    
    public WordRepository(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String word;
            while((word = br.readLine()) != null){
                wordList.add(word.toLowerCase());
            }     
        }catch(IOException ex){
            
        }      
    }
    
    public String getWord(int index){
        if(index <= getWordListSize()){
            return wordList.get(index);
        }else{
            throw new IndexOutOfBoundsException("Word is not found");
        }
    }
    
    public int getWordListSize(){
        return wordList.size();
    }
}
