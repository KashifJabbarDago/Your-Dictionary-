package com.learning.mydictionary;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Struct;
import java.util.ArrayList;

public class HomePage extends Fragment {

    RecyclerView recyclerview_Id;
    FloatingActionButton floatbutton;
   EditText word_name,word_meaning;
   Button btn_save,btn_cancel;
   TextView header_text_realtime;


    public HomePage() {
        // Required empty public constructor
    }

    //@SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View vw = inflater.inflate(R.layout.fragment_home_page, container, false);
         floatbutton = vw.findViewById(R.id.floatBtn);
         //wordsarrList.clone();
       AppDatabase db = AppDatabase.getDB(getContext());
       //UserDao userDao = db.userDao();

        ArrayList<wordsflow> wordsarrList = new ArrayList<>();
        AdapterKing adapter = new AdapterKing(getActivity(),wordsarrList);


        recyclerview_Id = vw.findViewById(R.id.recyclerview_Id);
        recyclerview_Id.setLayoutManager(new LinearLayoutManager(getActivity()));
        wordsarrList.add(new wordsflow("dynamic","no allow"));
        wordsarrList.add(new wordsflow("ok","no allow"));
        db.userDao().getAll().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                floatbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Dialog dialog = new Dialog(getContext());
                 dialog.setContentView(R.layout.float_btn_design);
                 word_name = dialog.findViewById(R.id.word_name);
                 word_meaning = dialog.findViewById(R.id.word_meaning);
                 btn_save = dialog.findViewById(R.id.btn_save);
                 btn_cancel = dialog.findViewById(R.id.btn_cancel);

                 btn_cancel.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         dialog.dismiss();
                     }
                 });

                 btn_save.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         // Database manage
                         String word_str = word_name.getText().toString();
                         String word_mean_str = word_meaning.getText().toString();
                         Toast.makeText(getActivity(), "saved", Toast.LENGTH_LONG).show();
                         db.userDao().insertAll(new User(word_str, word_mean_str));
                         
                        // ArrayList<User> arrayList = (ArrayList<User>) db.userDao().getAll();
                          //   Log.i("debug", arrayList.get(i).getFirstName() + " " + arrayList.get(i).getLastName());
                             // Database closes
                         String name = "", mean= "";
                         if (!word_name.getText().toString().equals(" ")) {
                           //  for (int i = 0; i < arrayList.size(); i++) {
                              //   name = arrayList.get(i).getFirstName();
                            // }
                             name = user.getFirstName();

                         } else {
                             Toast.makeText(getActivity(), "Please enter correct phone detail", Toast.LENGTH_LONG).show();
                         }
                         if (!word_meaning.getText().toString().equals(" ")) {
                          //   for (int i = 0; i < arrayList.size(); i++) {
                              //   mean = arrayList.get(i).getLastName();
                          //   }
                             mean = user.getLastName();
                         } else {
                             Toast.makeText(getActivity(), "Please enter correct number detail", Toast.LENGTH_LONG).show();
                         }

                         wordsarrList.add(new wordsflow(name, mean));
                             adapter.notifyItemInserted(wordsarrList.size() -1 );
                             recyclerview_Id.scrollToPosition(wordsarrList.size());
                             recyclerview_Id.isSaveEnabled();
                             dialog.dismiss();

                         }
                 });

                 dialog.show();
                     }
                 });
             }
        });

        recyclerview_Id.setAdapter(adapter);
        return vw;
    }

}
