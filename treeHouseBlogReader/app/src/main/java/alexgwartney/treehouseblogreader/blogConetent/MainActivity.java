package alexgwartney.treehouseblogreader.blogConetent;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import alexgwartney.treehouseblogreader.R;




public class MainActivity extends ActionBarActivity {



    public String url = "http://blog.teamtreehouse.com/api/get_recent_summary/?count=20";
    public static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    content[] mDataset;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);



        if(isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            getCurrentDetails(jsonData);
                            mAdapter = new MyAdapter(mDataset);
                            mRecyclerView.setAdapter(mAdapter);

                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }


    }






    private content[] getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);

        JSONArray data = forecast.getJSONArray("posts");

         mDataset = new content[data.length()];

        for (int i = 0; i < data.length(); i++) {

            JSONObject jsonDay = data.getJSONObject(i);
            content day = new content();

            day.setId(jsonDay.getInt("id"));
            day.setUrl(jsonDay.getString("url"));
            day.setTitle(jsonDay.getString("title"));
            day.setDate(jsonDay.getString("date"));
            day.setAuthor(jsonDay.getString("author"));
            day.setThumbnail(jsonDay.getString("thumbnail"));
            mDataset[i] = day;
        }


        return mDataset;

    }


    // This will check if there is a net work conection
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }







}
