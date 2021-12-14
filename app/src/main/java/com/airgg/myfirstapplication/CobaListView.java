package com.airgg.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.airgg.myfirstapplication.adapters.CobaListViewAdapter;
import com.airgg.myfirstapplication.models.Hero;

import java.util.ArrayList;
import java.util.List;

public class CobaListView extends Activity {
    //a List of type hero for holding list items
    List<Hero> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coba_listview);

        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lst_view);


        heroList.add(new Hero( "Spiderman", "Avengers"));
        heroList.add(new Hero( "Joker", "Injustice Gang"));
        heroList.add(new Hero( "Iron Man", "Avengers"));

        //creating the adapter
        CobaListViewAdapter adapter = new CobaListViewAdapter(this, R.layout.row_list2, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);

        Button btn_find = (Button) findViewById(R.id.btn_find);
        final EditText txt_find = (EditText) findViewById(R.id.txt_find);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterList(txt_find.getText());
            }
        });
    }

    private void filterList(Editable cari) {
        List<Hero> hl = new ArrayList<>();
        for(int i = 0; i < heroList.size(); i++){
            if(heroList.get(i).getName().toString().toLowerCase().contains(cari.toString())){
                hl.add(heroList.get(i));
            }
        }

        //creating the adapter
        CobaListViewAdapter adapter = new CobaListViewAdapter(this, R.layout.row_list2, hl);
        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }
}
