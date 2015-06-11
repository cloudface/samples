package com.example.chrisbraunschweiler1.collapsableheadersample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<MusicPlayerOverviewViewModel> mModels;

    public TestAdapter(List<MusicPlayerOverviewViewModel> models){
        mModels = models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mArtistText;

        public ViewHolder(View v) {
            super(v);
            mArtistText = (TextView) v.findViewById(R.id.artistText);
        }
    }
}
