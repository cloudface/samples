package com.example.chrisbraunschweiler1.collapsableheadersample;

public class MusicPlayerOverviewViewModel {
    private String mAlbum;
    private String mSong;
    private String mArtist;
    private ItemType mItemType;
    private String mDuration;

    public String getArtist() {
        return mArtist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public String getSong() {
        return mSong;
    }

    public void setArtist(String artist) {
        this.mArtist = artist;
    }

    public void setAlbum(String album) {
        this.mAlbum = album;
    }

    public void setSong(String song) {
        this.mSong = song;
    }

    public ItemType getItemType() {
        return mItemType;
    }

    public void setItemType(ItemType itemType) {
        this.mItemType = itemType;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        this.mDuration = duration;
    }

    public enum ItemType{
        SearchMaskPlaceholder,
        SearchResult
    }
}
