package com.example.roomwordsample;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private  LiveData<PagedList<Word>> mAllWords;
    public WordRepository(Application application){
        WordRoomDatabase db=WordRoomDatabase.getDatabase(application);
        mWordDao=db.mWordDao();
        mAllWords=new LivePagedListBuilder<Integer,Word>( mWordDao.getAlphabetizedWords(),3)
        .build();
    }
    LiveData<PagedList<Word>> getAllWords(){
        return mAllWords;
    }
    void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(()->{
            mWordDao.insert(word);
        });
    }
}
