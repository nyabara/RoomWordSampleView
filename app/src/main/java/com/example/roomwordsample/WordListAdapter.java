package com.example.roomwordsample;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class WordListAdapter extends PagedListAdapter<Word,WordViewHolder> {
    protected WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word current=getItem(position);
        holder.bind(current.getWord(),current.getNumber());

    }
    static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.equals(newItem);
        }
    }
}
