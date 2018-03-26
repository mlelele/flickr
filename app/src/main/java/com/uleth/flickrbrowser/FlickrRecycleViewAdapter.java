package com.uleth.flickrbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shah Jabeen Sajid on 2018-03-24.
 */

class FlickrRecycleViewAdapter extends RecyclerView.Adapter<FlickrRecycleViewAdapter.FlickrImageViewHolder>{

    private static final String TAG = "FlickrRecycleViewAdapt";
    private List<Photo> mPhotoList;
    private Context mContext;

    public FlickrRecycleViewAdapter(Context context, List<Photo> photoList)
    {
        //this.mContext = mContext;
        //this.mPhotoList = mPhotoList;
        mContext = context;
        mPhotoList = photoList;
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //Called by layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new FlickrImageViewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position)
    { // called by the layout manager when it wants new data in an existing row
        Photo photoItem = mPhotoList.get(position);
        Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + "-->" + position);
        Picasso.with(mContext).load(photoItem.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.title.setText(photoItem.getTitle());
    }

    @Override
    public int getItemCount()
    {
        Log.d(TAG, "getItemCount: called");
        return ((mPhotoList != null) && (mPhotoList.size() != 0 ) ? mPhotoList.size() :0);
        //return 0;
    }
    void loadNewData(List<Photo> newPhotos)
    {
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }
    public Photo getPhoto(int position)
    {
        return ((mPhotoList != null) && (mPhotoList.size()!= 0) ? mPhotoList.get(position) :null);

    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder
    {
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public FlickrImageViewHolder(View itemView)
        {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail= (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);


        }
    }
}
