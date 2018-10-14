package com.bkk.android.redsubmarine.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bkk.android.redsubmarine.MainActivity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    // class variables
    private static final String LOG_TAG = MainViewModel.class.getSimpleName();

    private AppDatabase database1;
    private LiveData< List<RedditPostEntry> > asdf1; // << // make it private, and use a public GETTER
    private LiveData< RedditPostEntry > redditpost1;


    public MainViewModel(@NonNull Application application) {
        super(application);

        // get a copy of the database
        database1 = AppDatabase.getsInstance(this.getApplication());

        // get all "saved posts" from database
//        Log.d(LOG_TAG, "Actively retrieving the tasks from the Database");
//        asdf1 = database1.redditPostDao().loadAllSavedRedditPost();

    } // constructor




    // getter, get all "saved posts" from database
    public LiveData< List<RedditPostEntry> > getAllSavedPostsFromViewModel() {
        Log.d(LOG_TAG, "Actively retrieving the tasks from the Database");
        asdf1 = database1.redditPostDao().loadAllSavedRedditPost();

        return asdf1;
    } // getAllSavedPostsFromViewModel()


    // getter
    public LiveData< RedditPostEntry > getRedditPostById(String id) {
        Log.d(LOG_TAG, "Actively retrieving ONE task from the Database");
        redditpost1 = database1.redditPostDao().loadRedditPostEntryById( id );

        return redditpost1;
    } // getRedditPostById()

} // class MainViewModel extends AndroidViewModel
