package com.example.chrisbraunschweiler1.collapsableheadersample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private Button mSearchButton;
    private RelativeLayout mSearchMask;
    private LinearLayout mSearchMaskExpanded;
    private LinearLayout mSearchMaskCollapsed;
    private View mSearchMaskCollapsedBackground;
    private TextView mCollapsedArtistText;
    private TextView mCollapsedAlbumText;
    private List<MusicPlayerOverviewViewModel> mOverViewModels;
    private MusicPlayerAdapter mAdapter;
    private float mSearchMaskScrollOffset;
    private float mExpandedSearchMaskHeight;
    private float mInitialExpandedSearchMaskY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchButton = (Button) findViewById(R.id.searchButton);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSearchMask = (RelativeLayout) findViewById(R.id.searchMask);
        mSearchMaskExpanded = (LinearLayout) findViewById(R.id.searchMaskExpanded);
        mSearchMaskCollapsedBackground = findViewById(R.id.searchMaskCollapsedBackground);
        mSearchMaskCollapsed = (LinearLayout) findViewById(R.id.searchMaskCollapsed);
        mCollapsedArtistText = (TextView) findViewById(R.id.collapsedArtistText);
        mCollapsedAlbumText = (TextView) findViewById(R.id.collapsedAlbumText);

        mOverViewModels = new ArrayList<>();
        mAdapter = new MusicPlayerAdapter(this, mOverViewModels, new MusicPlayerAdapter.Listener() {
            @Override
            public void onItemClicked(int position) {

            }
        });

        initializeRecyclerView();

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
        mSearchMaskCollapsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snapToExpandedSearchMask((int) mSearchMaskScrollOffset);
            }
        });

        findViewById(R.id.contentView).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mExpandedSearchMaskHeight = mSearchMaskExpanded.getHeight();
                mInitialExpandedSearchMaskY = (int) mSearchMask.getY();
            }
        });
    }

    private void initializeRecyclerView() {
        mRecyclerView.setAdapter(mAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setOnScrollListener(new MyScrollListener());
    }

    private void performSearch() {
        mOverViewModels.clear();
        addSearchMaskPlaceholderItem(mOverViewModels);
        addDummySearchResultItems(mOverViewModels);
        populateCollapsedHeader();
        mAdapter.notifyDataSetChanged();
    }

    private void addSearchMaskPlaceholderItem(List<MusicPlayerOverviewViewModel> overViewModels) {
        MusicPlayerOverviewViewModel maskPlaceholder = new MusicPlayerOverviewViewModel();
        maskPlaceholder.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchMaskPlaceholder);
        overViewModels.add(maskPlaceholder);
    }

    private void addDummySearchResultItems(List<MusicPlayerOverviewViewModel> overViewModels) {
        MusicPlayerOverviewViewModel searchResult1 = new MusicPlayerOverviewViewModel();
        searchResult1.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult1.setAlbum("AM");
        searchResult1.setArtist("Arctic Monkeys");
        searchResult1.setSong("Do I wanna know?");
        searchResult1.setDuration("4:32");
        overViewModels.add(searchResult1);

        MusicPlayerOverviewViewModel searchResult2 = new MusicPlayerOverviewViewModel();
        searchResult2.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult2.setAlbum("Asleep at Heaven's Gate");
        searchResult2.setArtist("Rogue Wave");
        searchResult2.setSong("Used to it");
        searchResult2.setDuration("2:50");
        overViewModels.add(searchResult2);

        MusicPlayerOverviewViewModel searchResult3 = new MusicPlayerOverviewViewModel();
        searchResult3.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult3.setAlbum("Our Own House");
        searchResult3.setArtist("MisterWives");
        searchResult3.setSong("Our Own House");
        searchResult3.setDuration("3:52");
        overViewModels.add(searchResult3);

        MusicPlayerOverviewViewModel searchResult4 = new MusicPlayerOverviewViewModel();
        searchResult4.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult4.setAlbum("AM");
        searchResult4.setArtist("Arctic Monkeys");
        searchResult4.setSong("Do I wanna know?");
        searchResult4.setDuration("4:32");
        overViewModels.add(searchResult4);

        MusicPlayerOverviewViewModel searchResult5 = new MusicPlayerOverviewViewModel();
        searchResult5.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult5.setAlbum("Fool for Love");
        searchResult5.setArtist("Lord Huron");
        searchResult5.setSong("Fool for Love");
        searchResult5.setDuration("4:35");
        overViewModels.add(searchResult5);

        MusicPlayerOverviewViewModel searchResult6 = new MusicPlayerOverviewViewModel();
        searchResult6.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult6.setAlbum("This Is All Yours");
        searchResult6.setArtist("alt-J");
        searchResult6.setSong("Left Hand Free");
        searchResult6.setDuration("2:54");
        overViewModels.add(searchResult6);

        MusicPlayerOverviewViewModel searchResult7 = new MusicPlayerOverviewViewModel();
        searchResult7.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult7.setAlbum("Reflections");
        searchResult7.setArtist("MisterWives");
        searchResult7.setSong("Reflections");
        searchResult7.setDuration("3:09");
        overViewModels.add(searchResult7);

        MusicPlayerOverviewViewModel searchResult8 = new MusicPlayerOverviewViewModel();
        searchResult8.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult8.setAlbum("The Best Of");
        searchResult8.setArtist("Tropico Band");
        searchResult8.setSong("Pusti ritam");
        searchResult8.setDuration("3:38");
        overViewModels.add(searchResult8);

        MusicPlayerOverviewViewModel searchResult9 = new MusicPlayerOverviewViewModel();
        searchResult9.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult9.setAlbum("Friend");
        searchResult9.setArtist("Grizzly Bear");
        searchResult9.setSong("Alligator");
        searchResult9.setDuration("5:15");
        overViewModels.add(searchResult9);

        MusicPlayerOverviewViewModel searchResult10 = new MusicPlayerOverviewViewModel();
        searchResult10.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult10.setAlbum("The Days/Nights");
        searchResult10.setArtist("Avicii");
        searchResult10.setSong("The Nights");
        searchResult10.setDuration("2:57");
        overViewModels.add(searchResult10);

        MusicPlayerOverviewViewModel searchResult11 = new MusicPlayerOverviewViewModel();
        searchResult11.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult11.setAlbum("101");
        searchResult11.setArtist("WALLA");
        searchResult11.setSong("101");
        searchResult11.setDuration("3:24");
        overViewModels.add(searchResult11);

        MusicPlayerOverviewViewModel searchResult12 = new MusicPlayerOverviewViewModel();
        searchResult12.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult12.setAlbum("Bad Blood");
        searchResult12.setArtist("Bastille");
        searchResult12.setSong("Flaws");
        searchResult12.setDuration("3:39");
        overViewModels.add(searchResult12);

        MusicPlayerOverviewViewModel searchResult13 = new MusicPlayerOverviewViewModel();
        searchResult13.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult13.setAlbum("Talking Dreams");
        searchResult13.setArtist("Echosmith");
        searchResult13.setSong("Bright");
        searchResult13.setDuration("3:41");
        overViewModels.add(searchResult13);

        MusicPlayerOverviewViewModel searchResult14 = new MusicPlayerOverviewViewModel();
        searchResult14.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult14.setAlbum("Talking Dreams");
        searchResult14.setArtist("Echosmith");
        searchResult14.setSong("Cool Kids");
        searchResult14.setDuration("3:58");
        overViewModels.add(searchResult14);

        MusicPlayerOverviewViewModel searchResult15 = new MusicPlayerOverviewViewModel();
        searchResult15.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult15.setAlbum("Smallpools");
        searchResult15.setArtist("Smallpools");
        searchResult15.setSong("Mason Jar");
        searchResult15.setDuration("3:40");
        overViewModels.add(searchResult15);

        MusicPlayerOverviewViewModel searchResult16 = new MusicPlayerOverviewViewModel();
        searchResult16.setItemType(MusicPlayerOverviewViewModel.ItemType.SearchResult);
        searchResult16.setAlbum("Holy Fire");
        searchResult16.setArtist("Foals");
        searchResult16.setSong("My Number");
        searchResult16.setDuration("4:01");
        overViewModels.add(searchResult16);
    }

    private void populateCollapsedHeader() {
        mCollapsedArtistText.setText("Incubus");
        mCollapsedAlbumText.setText("Ligh Grenades");
    }

    private void expandOrCollapseSearchMask() {
        int differenceCollapsedAndExpandedMasks = (int) (mExpandedSearchMaskHeight - mSearchMaskCollapsed.getHeight());
        float ratio = mSearchMaskScrollOffset / differenceCollapsedAndExpandedMasks;
        float higherRatio = ratio * 4;
        ratio = 1 - ratio;
        higherRatio = 1 - higherRatio;

        float scrollOffset = mSearchMaskScrollOffset <= differenceCollapsedAndExpandedMasks ? mSearchMaskScrollOffset : differenceCollapsedAndExpandedMasks;

        mSearchMask.setTranslationY(-scrollOffset);
        mSearchButton.setTranslationY(-scrollOffset);

        mSearchButton.setAlpha(ratio);
        mSearchMaskExpanded.setAlpha(higherRatio);
        mSearchMaskCollapsedBackground.setAlpha(1 - ratio);
        mSearchMaskCollapsed.setAlpha(1 - (ratio));

        if (mSearchMaskExpanded.getAlpha() == 0) {
            mSearchMaskExpanded.setVisibility(View.INVISIBLE);
        } else {
            mSearchMaskExpanded.setVisibility(View.VISIBLE);
        }
        if (mSearchMaskCollapsed.getAlpha() == 0) {
            mSearchMaskCollapsed.setVisibility(View.INVISIBLE);
        } else {
            mSearchMaskCollapsed.setVisibility(View.VISIBLE);
        }
    }

    private void snapToCollapsedSearchMask(int distanceToCollapsedState) {
        mRecyclerView.smoothScrollBy(0, distanceToCollapsedState);
    }

    private void snapToExpandedSearchMask(int distanceToExpandedState) {
        mRecyclerView.smoothScrollBy(0, -distanceToExpandedState);
    }

    private class MyScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mSearchMaskScrollOffset += dy;

            expandOrCollapseSearchMask();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    //Snap into place
                    int expandedMaskBottom = (int) (mInitialExpandedSearchMaskY + mSearchMask.getHeight());
                    int collapsedMaskBottom = (int) (mInitialExpandedSearchMaskY + mSearchMaskCollapsed.getHeight());
                    int currentMaskBottom = (int) (mSearchMask.getY() + mSearchMask.getHeight());

                    int distanceToExpandedState = Math.abs(currentMaskBottom - expandedMaskBottom);
                    int distanceToCollapsedState = Math.abs(currentMaskBottom - collapsedMaskBottom);
                    Log.e("", "Distance To Exp: " + distanceToExpandedState + " DistToColl: " + distanceToCollapsedState);
                    if (distanceToExpandedState <= distanceToCollapsedState) {
                        //Snap to expanded state
                        Log.e("", "Snap to expanded state: " + distanceToExpandedState);
                        snapToExpandedSearchMask(distanceToExpandedState);
                    } else {
                        //Snap to collapsed state
                        Log.e("", "Snap to collapsed state: " + distanceToCollapsedState);
                        snapToCollapsedSearchMask(distanceToCollapsedState);
                    }
                    break;
            }
        }
    }
}
