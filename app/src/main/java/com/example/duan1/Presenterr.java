package com.example.duan1;

import android.content.Context;

public class Presenterr {
    private Context context;
    private PresenterView presenterView;

    public Presenterr(Context context, PresenterView presenterView) {
        this.context = context;
        this.presenterView = presenterView;

    }
    public void GetSong() {
        new GetSongAsynctask(presenterView).execute();

    }
}
