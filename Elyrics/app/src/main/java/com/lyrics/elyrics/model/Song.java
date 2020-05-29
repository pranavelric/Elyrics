package com.lyrics.elyrics.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Song {

    @SerializedName("status")
private  status  stat;
@SerializedName("content")
private List<content> content;

    public status getStat() {
        return stat;
    }

    public void setStat(status stat) {
        this.stat = stat;
    }

    public List<Song.content> getContent() {
        return content;
    }

    public void setContent(List<Song.content> content) {
        this.content = content;
    }

    public class status{
        @SerializedName("code")
        private int code;

        @SerializedName("message")
        private String mesg;

        @SerializedName("failed")
        private boolean failed;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMesg() {
            return mesg;
        }

        public void setMesg(String mesg) {
            this.mesg = mesg;
        }

        public boolean isFailed() {
            return failed;
        }

        public void setFailed(boolean failed) {
            this.failed = failed;
        }
    }


    public class content {
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("lyrics")
        @Expose
        private String lyrics;
        @SerializedName("artist")
        @Expose
        private String artist;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLyrics() {
            return lyrics;
        }

        public void setLyrics(String lyrics) {
            this.lyrics = lyrics;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }
    }


}