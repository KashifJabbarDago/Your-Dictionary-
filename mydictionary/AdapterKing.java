package com.learning.mydictionary;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdapterKing extends RecyclerView.Adapter<AdapterKing.ViewHolderMe> {
    Context context;
    AppDatabase db;



    //UserDao userDao = db.userDao();
//    ArrayList<User> arrayList = (ArrayList<User>) db.userDao().getAll();
    ArrayList<wordsflow> words_flows = new ArrayList<>();
    AdapterKing(Context context,ArrayList<wordsflow> arrayList) {
        this.context = context;
        this.words_flows = arrayList;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolderMe holder, int position) {
        wordsflow my_words_are = words_flows.get(position);
        // set words in recycler textview note: you stuck for a week in just this following mistake that's why noted:
        holder.header_text_realtime.setText(my_words_are.word_name);
        holder.header_text_realtime2.setText(my_words_are.word_meaning);


    }

    @Override
    public int getItemCount() {
        return words_flows.size();
    }

    public static class ViewHolderMe extends RecyclerView.ViewHolder {
        TextView header_text_realtime,header_text_realtime2;

        public ViewHolderMe(@NonNull View itemView) {
            super(itemView);

            header_text_realtime = itemView.findViewById(R.id.header_text_realtime);
            header_text_realtime2 = itemView.findViewById(R.id.header_text_realtime2);

        }
    }
    @NonNull
    @Override
    public ViewHolderMe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false);
        return new ViewHolderMe(view);
    }
}

