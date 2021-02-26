package com.example.roomwordsample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private final LiveData<PagedList<Word>> mAllWords;
    private WorkManager mWorkManager;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
        mWorkManager = WorkManager.getInstance(application);
    }
    public LiveData<PagedList<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepository.insert(word); }
    void appyWork(){
        mWorkManager.enqueue(OneTimeWorkRequest.from(NotificationWorkManager.class));
        //OneTimeWorkRequest.Builder builder=new OneTimeWorkRequest.Builder(NotificationWorkManager.class).build()
    }
}
