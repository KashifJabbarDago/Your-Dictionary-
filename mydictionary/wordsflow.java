package com.learning.mydictionary;

public class wordsflow {

    String word_name;
    String word_meaning;
    int id;
    public wordsflow(int id, String word_name, String word_meaning) {
        this.word_name = word_name;
        this.word_meaning = word_meaning;
        this.id = id;
    }

    public wordsflow(String word_name, String word_meaning) {
        this.word_name = word_name;
        this.word_meaning = word_meaning;
    }

}
