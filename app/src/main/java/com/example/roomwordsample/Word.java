package com.example.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "word")
    private String mWord;
    @ColumnInfo(name = "number")
    private String mNumber;

    public Word(String word, String number)
    {
        this.mWord = word;
        this.mNumber=number;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {

        return mWord;
    }
    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }
}
