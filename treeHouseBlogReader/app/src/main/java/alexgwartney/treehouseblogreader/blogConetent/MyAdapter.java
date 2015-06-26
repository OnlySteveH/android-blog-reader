package alexgwartney.treehouseblogreader.blogConetent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import alexgwartney.treehouseblogreader.R;

/**
 * Created by alex on 6/26/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HourViewHolder> {
    public  content[] mDataset;


    public MyAdapter(content[] data) {
        mDataset = data;

    }


//  the lay out is not the correct lay out but its just there for a example.
    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test, parent, false);
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mId;
        public TextView mUrl;
        public TextView mTitle;
        public TextView mDate;
        public TextView mAuthor;
        public TextView mThumbnail;

        public HourViewHolder(View itemView) {
            super(itemView);

            mId = (TextView) itemView.findViewById(R.id.blogid);
            mUrl= (TextView) itemView.findViewById(R.id.url);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mAuthor = (TextView) itemView.findViewById(R.id.author);
            mThumbnail= (TextView) itemView.findViewById(R.id.thumbnail);
        }

        public void bindHour(content hour) {
            mId.setText(hour.getId());
            mUrl.setText(hour.getUrl());
            mTitle.setText(hour.getTitle());
            mDate.setText(hour.getDate());
            mAuthor.setText(hour.getAuthor());
            mThumbnail.setText(hour.getThumbnail());
        }

        @Override
        public void onClick(View view) {

        }
    }
}