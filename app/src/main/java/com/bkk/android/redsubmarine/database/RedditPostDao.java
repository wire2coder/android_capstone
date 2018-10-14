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
    // only LIST can be use in "DAO"
    @Query("SELECT * FROM redditpost ORDER BY id")
    LiveData< List<RedditPostEntry> >  loadAllSavedRedditPost();
    //    List<RedditPostEntry> loadAllSavedRedditPost();


    @Query("SELECT * FROM redditpost ORDER BY id")
    List<RedditPostEntry> loadAllSavedRedditPostWidget();
//    LiveData< List<RedditPostEntry> > loadAllSavedRedditPost();


    // don't add the same Reddit post to database twice
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    void insertRedditPost(RedditPostEntry redditPostEntry);

    @Delete()
    void deleteRedditPost(RedditPostEntry redditPostEntry);

    // get 1 RedditPost
    @Query("SELECT * FROM redditpost WHERE post_id = :post_id")
//    RedditPostEntry loadRedditPostEntryById(String post_id);
    LiveData< RedditPostEntry >  loadRedditPostEntryById(String post_id);

    // @Update()

} // class RedditPostDao
