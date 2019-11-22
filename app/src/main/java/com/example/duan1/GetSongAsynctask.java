package com.example.duan1;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class GetSongAsynctask extends AsyncTask<Void, Void, ArrayList<File>> {
    private PresenterView presenterView;
    public GetSongAsynctask(PresenterView presenterView) {
        this.presenterView = presenterView;
    }

    @Override
    protected ArrayList<File> doInBackground(Void... voids) {
        try {
            findSongs(Environment.getExternalStorageDirectory());
        }catch (Exception e){
            Log.e("error", "null"+e.getMessage());
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<File> files) {
        super.onPostExecute(files);
        if (files != null) {
            presenterView.data(files);

        } else {
            Log.e("nullll", "null");
        }

    }

    public ArrayList<File> findSongs(File root) {
        ArrayList<File> al = new ArrayList<>();
        File[] files = root.listFiles();

        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                al.addAll(findSongs(singleFile));

            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    al.add(singleFile);
                }
            }
        }
        return al;
    }
}
