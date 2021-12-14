package com.airgg.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.airgg.myfirstapplication.models.Todos;

public class detail_todos extends AppCompatActivity {

    Todos todo;
    TextView txt_judul;
    TextView txt_konten;
    TextView txt_startdate;
    TextView txt_enddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_todos);

        // -- SETUP -- \\
        Intent i_from_crud_todos = getIntent();
        String act = i_from_crud_todos.getStringExtra("act");
        todo = (Todos) i_from_crud_todos.getSerializableExtra("m_todo");
        txt_judul = (TextView) findViewById(R.id.lblJudul);
        txt_konten = (TextView) findViewById(R.id.lblKonten);
        txt_startdate = (TextView) findViewById(R.id.lblTglStart);
        txt_enddate = (TextView) findViewById(R.id.lblTglEnd);
        // -- [END] SETUP -- \\

        if (todo != null) {
            txt_judul.setText(todo.getJudul().toString());
            txt_konten.setText(todo.getKonten().toString());
            txt_startdate.setText(todo.getTglstart().toString());
            txt_enddate.setText(todo.getTglend().toString());
        }
    }
}