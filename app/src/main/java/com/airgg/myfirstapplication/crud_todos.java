package com.airgg.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.airgg.myfirstapplication.adapters.CobaListViewAdapter;
import com.airgg.myfirstapplication.adapters.TodoListViewAdapter;
import com.airgg.myfirstapplication.models.Hero;
import com.airgg.myfirstapplication.models.Todos;
import com.airgg.myfirstapplication.utils.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class crud_todos extends AppCompatActivity {

    private DatabaseHelper db;
    Todos todo;
    List<Todos> todoList;
    ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        // -- SETUP -- \\
        db = new DatabaseHelper(this);
        FloatingActionButton btn_add_todo = (FloatingActionButton) findViewById(R.id.btn_add_todo);
        Button btn_search_todo = findViewById(R.id.btn_seach_todo);
        final EditText txt_search_todo = findViewById(R.id.txt_search) ;
        todoList = new ArrayList<>();
        listView = findViewById(R.id.list_todos);
        context = this;

        todoList = db.getAllTodo();

        TodoListViewAdapter adapter = new TodoListViewAdapter(this, R.layout.item_listview_custom, todoList);

        listView.setAdapter(adapter);
        // -- [END] SETUP -- \\

        btn_add_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_form_todos = new Intent(crud_todos.this, form_todos.class);
//                Todos addTodo = new Todos(-1, "Lari Pagi", "bareng temen temen", "211010", "211010");
                Todos addTodo = new Todos();
                i_form_todos.putExtra("act", "add");
                i_form_todos.putExtra("m_todo", addTodo);
                startActivity(i_form_todos);
            }
        });

        btn_search_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterData(txt_search_todo.getText().toString());
            }
        });
    }

    private void filterData(String cari){
        todoList = db.getAllTodo();
        List<Todos> tl = new ArrayList<>();
        for(int i = 0; i < todoList.size(); i++){
            Todos todo = todoList.get(i);
            if(todo.getJudul().toString().toLowerCase().contains(cari) || todo.getJudul().toLowerCase().contains(cari)){
                tl.add(todo);
            }
        }

        TodoListViewAdapter adapter = new TodoListViewAdapter(this, R.layout.item_listview_custom, tl);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}