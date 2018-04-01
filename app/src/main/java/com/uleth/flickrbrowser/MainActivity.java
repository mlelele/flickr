package com.uleth.flickrbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class MainActivity extends BaseActivity implements GetFlickrJsonData.OnDataAvailable, RecyclerItemClickListener.OnRecyclerClickListener {

    private static final String TAG = "MainActivity";
    private FlickrRecycleViewAdapter mFlickrRecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateToolbar(false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));


        mFlickrRecycleViewAdapter = new FlickrRecycleViewAdapter(this, new ArrayList<Photo>());
        recyclerView.setAdapter(mFlickrRecycleViewAdapter);



        Log.d(TAG, "onCreate: ends");
    }

    @Override
    protected void onResume(){
        Log.d(TAG,"onResume starts");
        super.onResume();
        GetFlickrJsonData getFlickrJsonData  = new GetFlickrJsonData(this,"https://api.flickr.com/services/feeds/photos_public.gne","en-us", true);
        getFlickrJsonData.executeOnSameThread("android, nougat");
        //getFlickrJsonData.execute("android,nougat");
        Log.d(TAG,"onResume ends ");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);

            //true means you dealt with the action, so this a test
            return true;
        }

        if (id == R.id.action_search) {
            return true;
        }

        Log.d(TAG, "onOptionsItemSelected() returned: returned ");
        return super.onOptionsItemSelected(item);
    }

    //@Override
    public void OnDataAvailable(List<Photo> data, DownloadStatus status) {
        Log.d(TAG, "onDataAvailable: starts");
        if (status == DownloadStatus.OK) {
            //Log.d(TAG, "onDataAvailable: data is:" + data);
            mFlickrRecycleViewAdapter.loadNewData(data);
        } else {
            //download or processing failed
            Log.e(TAG, "onDataAvailable: failed with status" + status);
        }
        Log.d(TAG, "onDataAvailable: ends");

    }
    @Override
    public void onItemClick(View view, int position){
        Log.d(TAG, "onItemClick: starts");
        Toast.makeText(MainActivity.this, "Normal top at position" + position, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemLongClick(View view, int position){
        Log.d(TAG, "onItemLongClick: starts");
      //  Toast.makeText(MainActivity.this, "long top at position" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PhotoDetailActivity.class);
        intent.putExtra(PHOTOTRANSFER,mFlickrRecycleViewAdapter.getPhoto(position));
        startActivity(intent);


    }
}
