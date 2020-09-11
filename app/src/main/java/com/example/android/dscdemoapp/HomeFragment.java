package com.example.android.dscdemoapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Iterator;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue requestQueue;
    private static ArrayList<String> stringArrayList;

    public View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        String jsonString = "{\"event_titles\":[\"Adobe XD Scratchclass - Introduction\",\"Latest Innovation and Trends in Flutter\",\"Discuss with DSC - Blockchain and Pi cryptocurrency\",\"Discuss with DSC - Data Engineering with Spark using Databricks\",\"Free Practical Cloud course\",\"DeveLup Series - Machine Learning Novice to Jarvis\",\"DeveLup Series - Problem Solving with Design Thinking\",\"DeveLup Series - Touring google Cloud\",\"DeveLup Series - Unboxing Mixed Reality\",\"DeveLup Series - Flutter Zero to Hero\",\"DeveLup Series - Kickstart with Firebase\",\"DeveLup Series - Graphic Designing - Intermediate\",\"DeveLup Series - Introduction to JavaScript\",\"DeveLup Series - Getting started with LaTeX\",\"DeveLup Series Launch\"]}";
        String[] event_titles = {"Adobe XD Scratchclass - Introduction", "Latest Innovation and Trends in Flutter", "Discuss with DSC - Blockchain and Pi cryptocurrency", "Discuss with DSC - Data Engineering with Spark using Databricks", "Free Practical Cloud course", "DeveLup Series - Machine Learning Novice to Jarvis", "DeveLup Series - Problem Solving with Design Thinking", "DeveLup Series - Touring google Cloud", "DeveLup Series - Unboxing Mixed Reality", "DeveLup Series - Flutter Zero to Hero", "DeveLup Series - Kickstart with Firebase", "DeveLup Series - Graphic Designing - Intermediate", "DeveLup Series - Introduction to JavaScript", "DeveLup Series - Getting started with LaTeX", "DeveLup Series Launch"};

        ArrayList<ListContent> arrayList=new ArrayList<ListContent>();
        stringArrayList=new ArrayList<String>();
        getJsonFromUrl();
        return rootView;
    }

    private void getJsonFromUrl() {
        final ArrayList<ListContent> arrayList = new ArrayList<ListContent>();
        String url = "https://wayhike.com/dsc/demo_app_api.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response); //
                            JSONArray jsonArray = jsonObject.getJSONArray("event_titles");

                            for (int i = 0; i < jsonArray.length(); i++)
                                arrayList.add(new ListContent(jsonArray.getString(i)));
                            recyclerView = rootView.findViewById(R.id.recycler_view);
                            layoutManager = new LinearLayoutManager(getActivity());
                            adapter = new ListContentAdapter(arrayList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            recyclerView.setLayoutManager(layoutManager);
                            Snackbar snackBar = Snackbar.make(rootView,
                                    "Welcome to the app!!", Snackbar.LENGTH_SHORT);
                            snackBar.show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            CharSequence text = "JSON Error: Something went wrong!!";
                            Snackbar snackBar = Snackbar.make(rootView,
                                    text, Snackbar.LENGTH_SHORT);
                            snackBar.show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Snackbar snackBar = Snackbar.make(rootView,
                        "Error: Check your internet connection", Snackbar.LENGTH_SHORT);
                snackBar.show();
            }
        });
        queue.add(stringRequest);


    }


}
