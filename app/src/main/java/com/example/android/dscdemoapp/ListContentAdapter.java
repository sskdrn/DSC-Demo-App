package com.example.android.dscdemoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListContentAdapter extends RecyclerView.Adapter<ListContentAdapter.ContentViewHolder> {
    private ArrayList<ListContent> arrayList;

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.card_text);
        }
    }

    public ListContentAdapter(ArrayList<ListContent> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content, parent, false);
        ContentViewHolder contentViewHolder = new ContentViewHolder(view);
        return contentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        ListContent listContent= arrayList.get(position);
        holder.textView.setText(listContent.getText());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
