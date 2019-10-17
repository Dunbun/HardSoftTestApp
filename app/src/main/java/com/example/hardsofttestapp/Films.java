package com.example.hardsofttestapp;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;



public class Films {
    private final String TAG = "MyApp";

    private int filmsCount;
    private String dataAboutFilms;
    private JSONArray arrayOfFilms;
    private String fileName;
    private int counter;

    Films(String fileName){
        this.fileName = fileName;
        dataAboutFilms = "";
        filmsCount = 0;
        arrayOfFilms = null;
        counter = 0;
    }

    public int loadFilms(CustomAdapter ca){
        if(counter <= filmsCount - 9 || counter <= 9){
            counter += 10;

            if(ca != null){
                ca.setCount(counter);
                ca.refresh();
            }

        }else{
            counter = filmsCount;
        }
        return counter;
    }

    public String readDataFromFile(Context myContext) {
        try {
            InputStream stream = myContext.getAssets().open(fileName);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            dataAboutFilms = new String(buffer);

            try {
                arrayOfFilms = new JSONArray(dataAboutFilms);
                filmsCount = arrayOfFilms.length();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i(TAG, "ReadDataFromFile: " + e.getMessage());
            }

        } catch (IOException e) {
            Log.i(TAG, "LoadDataFromFile: " + e.getMessage());
        }

        return dataAboutFilms;
    }

    public JSONObject getJSONObject(int index){
        try {
            return arrayOfFilms.getJSONObject(index);
        } catch (JSONException e) {
            Log.i(TAG, "getJSONObject: " + e.getMessage());
        }
        return null;
    }

    public String getJSONAttribute(JSONObject currentFilm, String attribute){
        if(currentFilm != null){
            try {
                return currentFilm.getString(attribute);
            } catch (JSONException e) {
                Log.i(TAG, "getJSONAttribute: " + e.getMessage());
            }
        }

        return "Failed";
    }

    public int getCounter(){
        return counter;
    }

}
