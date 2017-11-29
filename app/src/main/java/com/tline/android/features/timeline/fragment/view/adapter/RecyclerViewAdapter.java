package com.tline.android.features.timeline.fragment.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tline.android.R;
import com.tline.android.features.timeline.fragment.view.holder.RecyclerViewItemHolder;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewItemHolder> {

    private List<Tweet> mList;
    private Context mContext;

    public RecyclerViewAdapter(Context context) {
        this.mList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    @Override
    public RecyclerViewItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_list, viewGroup, false);
        return new RecyclerViewItemHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewItemHolder viewHolder, int position) {

        viewHolder.setTweet(mList.get(position));
    }

    public void clear() {

        mList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> list) {

        mList.addAll(list);
        notifyDataSetChanged();
    }
}