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
    private LiveData< List<RedditPostEntry> > asdf1; // << // make it private, and use a public GETTER


    public MainViewModel(@NonNull Application application) {
        super(application);

        // get a copy of the database
        AppDatabase database1 = AppDatabase.getsInstance(this.getApplication());

        // get all "saved posts" from database
        Log.d(LOG_TAG, "Actively retrieving the tasks from the Database");
        asdf1 = database1.redditPostDao().loadAllSavedRedditPost();

    } // constructor


    // getter
    public LiveData< List<RedditPostEntry> > getAllSavedPostsFromViewModel() {
        return asdf1;
    } // getAllSavedPostsFromViewModel()

} // class MainViewModel extends AndroidViewModel
