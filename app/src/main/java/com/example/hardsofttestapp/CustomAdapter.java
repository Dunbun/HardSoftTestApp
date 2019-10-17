package com.example.hardsofttestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {
    private int count;
    private Context context;
    private Films obj;

    public CustomAdapter(Context context, Films obj){
        count = obj.getCounter();
        this.context = context;
        this.obj = obj;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.listitem, null);

        JSONObject film = obj.getJSONObject(i);

        TextView nameOfFilm = (TextView) view.findViewById(R.id.nameOfFilmTV);
        nameOfFilm.setText("name: " + obj.getJSONAttribute(film,"name"));

        TextView countries = (TextView) view.findViewById(R.id.countriesTV);
        countries.setText("countries: " + obj.getJSONAttribute(film,"countries"));

        TextView duration = (TextView) view.findViewById(R.id.durationTV);

        duration.setText("duration: " + obj.getJSONAttribute(film,"duration"));

        TextView actors = (TextView) view.findViewById(R.id.actorsTV);
        actors.setText("actors: " + obj.getJSONAttribute(film,"actors"));

        TextView description = (TextView) view.findViewById(R.id.descriptionTV);
        description.setText("description: " + obj.getJSONAttribute(film,"description"));

        return view;
    }

    public void refresh(){
        getCount();
        notifyDataSetChanged();
    }

    public void setCount(int counter){
        count = counter;
    }
}