package com.bkk.android.redsubmarine.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bkk.android.redsubmarine.model.RedditPost;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface RedditPostDao {

    // DONE: submission1, adding LiveData
    @Query("SELECT * FROM redditpost ORDER BY id")
    // only LIST can be use in "DAO"
    List<RedditPostEntry> loadAllSavedRedditPost();
//    LiveData< List<RedditPostEntry> > loadAllSavedRedditPost();

    // don't addeding the same Reddit post twice
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    void insertRedditPost(RedditPostEntry redditPostEntry);

//    @Update()

    @Delete()
    void deleteRedditPost(RedditPostEntry redditPostEntry);

    // get 1 RedditPost
    @Query("SELECT * FROM redditpost WHERE post_id = :post_id")
    RedditPostEntry loadRedditPostEntryById(String post_id);

} // class RedditPostDao
