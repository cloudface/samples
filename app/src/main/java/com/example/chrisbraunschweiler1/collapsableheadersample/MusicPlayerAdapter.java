package com.example.chrisbraunschweiler1.collapsableheadersample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MusicPlayerAdapter extends RecyclerView.Adapter<MusicPlayerAdapter.ViewHolder> {

    private Context mContext;
    private List<MusicPlayerOverviewViewModel> mOverviewItems;
    private Listener mListener;

    public MusicPlayerAdapter(Context context, List<MusicPlayerOverviewViewModel> overviewItems, Listener listener) {
        mContext = context;
        mOverviewItems = overviewItems;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ViewHolder(v, new ViewHolder.Listener() {
            @Override
            public void onItemClicked(int position) {
                if (mListener != null) {
                    mListener.onItemClicked(position);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mSearchMaskPlaceholder.setVisibility(View.GONE);
        holder.mSearchResultItem.setVisibility(View.GONE);
        if (mOverviewItems.get(position).getItemType() == MusicPlayerOverviewViewModel.ItemType.SearchMaskPlaceholder) {
            holder.mSearchMaskPlaceholder.setVisibility(View.VISIBLE);
        } else {
            holder.mSearchResultItem.setVisibility(View.VISIBLE);
            setRowLabels(holder, position);
        }
    }

    private void setRowLabels(ViewHolder holder, int position) {
        holder.mArtistText.setText(mOverviewItems.get(position).getArtist());
        holder.mAlbumText.setText(mOverviewItems.get(position).getAlbum());
        holder.mSongText.setText(mOverviewItems.get(position).getSong());
        holder.mDurationText.setText(mOverviewItems.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return mOverviewItems.size();
    }

    public interface Listener {
        void onItemClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout mSearchMaskPlaceholder;
        public RelativeLayout mSearchResultItem;
        public TextView mArtistText;
        public TextView mAlbumText;
        public TextView mSongText;
        public TextView mDurationText;
        private Listener mListener;

        public ViewHolder(View v, Listener listener) {
            super(v);
            mListener = listener;
            mSearchMaskPlaceholder = (RelativeLayout) v.findViewById(R.id.searchMaskPlaceholder);
            mSearchResultItem = (RelativeLayout) v.findViewById(R.id.searchResultItem);
            mArtistText = (TextView) v.findViewById(R.id.artistText);
            mAlbumText = (TextView) v.findViewById(R.id.albumText);
            mSongText = (TextView) v.findViewById(R.id.songText);
            mDurationText = (TextView) v.findViewById(R.id.durationText);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClicked(getPosition());
                    }
                }
            });
        }

        public interface Listener {
            void onItemClicked(int position);
        }
    }
}
