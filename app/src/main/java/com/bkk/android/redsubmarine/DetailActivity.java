package com.bkk.android.redsubmarine;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.bkk.android.redsubmarine.model.RedditPost;

import butterknife.BindView;
import butterknife.ButterKnife;

// This class is for showing 1 Reddit post
public class DetailActivity extends AppCompatActivity {

    // class variables
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    String id;
    @BindView(R.id.toolbar2) Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set up ButterKnife
        ButterKnife.bind(this);

        Bundle bundle1 = getIntent().getExtras();
        RedditPost redditPost1 = bundle1.getParcelable(getString(R.string.redditPost1) );
        Log.d(LOG_TAG, redditPost1.getTitle() );


        setSupportActionBar(toolbar2); // set up the top Action Bar, i can see 'RedSubmarine' title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( redditPost1.getTitle() );

        // ain't no "State data" saved
//        if (savedInstanceState == null) {


        DetailFragment detailFragment = new DetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        // make a new bundle
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable( getString(R.string.redditPost1) , redditPost1); // << using Parcelable

        // fragment setArgument
        detailFragment.setArguments(bundle2);

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_detail1, detailFragment)
                .commit();
//        }

    } // onCreate()



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) { // << built in Android variable

            // https://developer.android.com/reference/androidx/core/app/NavUtils
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
        }

        return true; // returned true the click event will be consumed by the onOptionsItemSelect()
    } // onOptionsItemSelected()


} // class DetailActivity
