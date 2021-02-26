package com.example.roomwordsample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    private static Context activity;
    public abstract WordDao mWordDao();
    private static volatile WordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static WordRoomDatabase getDatabase(final Context context){
        activity=context;
        if (INSTANCE==null){
            synchronized (WordRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class,"word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = INSTANCE.mWordDao();
                dao.deleteAll();

                Word word = new Word("Hello","43456");
                dao.insert(word);
                word = new Word("World", "12345");
                dao.insert(word);
                fillWithJsonData(activity);
            });
        }
    };
    private static void fillWithJsonData(Context context){
        WordDao dao=INSTANCE.mWordDao();
        JSONArray jsonArray=loadJOSONArray(context);
        try {
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                String word=object.getString("name");
                String number=object.getString("phone");
                dao.insert(new Word(word,number));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private static JSONArray loadJOSONArray(Context context){
        StringBuilder builder=new StringBuilder();
        InputStream in=context.getResources().openRawResource(R.raw.contacts_list);
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line=reader.readLine())!=null){
                builder.append(line);
            }
            JSONObject jsonObject=new JSONObject(builder.toString());
            return jsonObject.getJSONArray("contacts");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
