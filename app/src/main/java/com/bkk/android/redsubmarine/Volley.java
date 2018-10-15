package com.bkk.android.redsubmarine;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bkk.android.redsubmarine.model.RedditPost;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Volley {

    // class variables
    private static final String LOG_TAG = Volley.class.getSimpleName();
    private List<RedditPost> redditPosts = new ArrayList<>();

    Context mContext;

    public Volley(Context context1) {
        this.mContext = context1;
    } // constructor


    public List<RedditPost> doStuff(String string_url) {

        // Instantiate the RequestQueue.
        RequestQueue queue = com.android.volley.toolbox.Volley.newRequestQueue(mContext);

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, string_url
                ,(String) null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//                Log.d(LOG_TAG, response.toString());


                // parse JSON data
                // data(JSON object) >> children(JSON array) >> data(JSON object)
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray children = data.getJSONArray("children");

                    for(int i=0; i<children.length(); i++) {

                        JSONObject post = children.getJSONObject(i).getJSONObject("data");

                        RedditPost redditPost = new RedditPost(
                                post.getString("title"),
                                post.getString("thumbnail"),
                                post.getString("url"),
                                post.getString("subreddit"),
                                post.getString("author"),
                                post.getString("permalink"),
                                post.getString("id"),
                                post.getString("subreddit_name_prefixed"),
                                post.getInt("score"),
                                post.getInt("num_comments"),
                                post.getLong("created_utc"),
                                post.getBoolean("over_18")

                        );

                        // now add the post to the ArrayList, but if check if the Array is empty
                        if (redditPosts == null) {
                            redditPosts = new ArrayList<>();
                        }

                        redditPosts.add(redditPost);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Data fetched from Reddit, now update the Adapter
//                mAdapter.swapData(redditPosts);

            } // onResponse
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError e1) {
                VolleyLog.d(LOG_TAG, "onErrorResponse " + e1.getMessage());
            } // onErrorResponse()

        });

        // Add the request to the RequestQueue, what is this "Queue" ?
        queue.add(request1);

        return redditPosts;
    } // doStuff()


} // class Volley
