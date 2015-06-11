package com.example.chrisbraunschweiler1.collapsableheadersample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MusicPlayerListAdapter extends ArrayAdapter<MusicPlayerOverviewViewModel>{
    public MusicPlayerListAdapter(Context context, int resource, List<MusicPlayerOverviewViewModel> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_result, parent, false);
        }

        MusicPlayerOverviewViewModel item = getItem(position);
        RelativeLayout searchMaskPlaceholder = (RelativeLayout) convertView.findViewById(R.id.searchMaskPlaceholder);
        RelativeLayout searchResultItem = (RelativeLayout) convertView.findViewById(R.id.searchResultItem);
        TextView artistText = (TextView) convertView.findViewById(R.id.artistText);
        TextView albumText = (TextView) convertView.findViewById(R.id.albumText);
        TextView songText = (TextView) convertView.findViewById(R.id.songText);
        TextView durationText = (TextView) convertView.findViewById(R.id.durationText);

        searchMaskPlaceholder.setVisibility(View.GONE);
        searchResultItem.setVisibility(View.GONE);
        if (item.getItemType() == MusicPlayerOverviewViewModel.ItemType.SearchMaskPlaceholder) {
            searchMaskPlaceholder.setVisibility(View.VISIBLE);
        } else {
            searchResultItem.setVisibility(View.VISIBLE);
            artistText.setText(item.getArtist());
            albumText.setText(item.getAlbum());
            songText.setText(item.getSong());
            durationText.setText(item.getDuration());
        }

        return convertView;
    }
}
