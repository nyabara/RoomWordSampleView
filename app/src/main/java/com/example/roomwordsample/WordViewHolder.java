package com.example.roomwordsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;
    private final TextView wordNumber;
    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        wordNumber=itemView.findViewById(R.id.textNumber);
        wordItemView=itemView.findViewById(R.id.textWord);
    }
    public void bind(String text, String number){
        wordItemView.setText(text);
        wordNumber.setText(number);
    }
    static WordViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }
}
