package com.example.hardsofttestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    final String fileName = "jsonFilms.txt";
    int preLast = -1;
    CustomAdapter ca;
    Films obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obj = new Films(fileName);
        obj.readDataFromFile(getApplicationContext());

        ListView listView = (ListView)findViewById(R.id.listOfFilms);

        obj.loadFilms(ca);

        ca = new CustomAdapter(getBaseContext(),obj);

        listView.setAdapter(ca);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean scrolled = false;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                scrolled = true;
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrolled) {
                    int lastItem = visibleItemCount + firstVisibleItem;

                    if ((lastItem) >= (totalItemCount)) {
                        if (preLast != lastItem) {
                            obj.loadFilms(ca);

                            scrolled = false;
                            preLast = lastItem;
                        }
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"Film number " + (i + 1), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
