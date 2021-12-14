package com.airgg.myfirstapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airgg.myfirstapplication.models.Todos;
import com.airgg.myfirstapplication.utils.DatabaseHelper;
import com.airgg.myfirstapplication.utils.DatePickerFragment;
import com.airgg.myfirstapplication.utils.DialogFactory;

public class form_todos extends AppCompatActivity {

    Todos todo;
    EditText txt_judul;
    EditText txt_konten;
    EditText txt_startdate;
    EditText txt_enddate;
    Integer _id = -1;

    DatabaseHelper db;
//    public form_todos(Todos todo) {
//        this.todo = todo;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_todos);

        // -- SETUP -- \\
        TextView lblFormTodos = (TextView) findViewById(R.id.lblFormTodos);
        Button btn_add = (Button) findViewById(R.id.btn_add);
        Button btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_add.setVisibility(View.GONE);
        btn_edit.setVisibility(View.GONE);

        txt_judul = (EditText) findViewById(R.id.txt_judul);
        txt_konten = (EditText) findViewById(R.id.txt_konten);
        txt_startdate = (EditText) findViewById(R.id.txt_startdate);
        txt_enddate = (EditText) findViewById(R.id.txt_enddate);

        final DatePickerFragment datePickStartDate = new DatePickerFragment(this, R.id.txt_startdate);
        final DatePickerFragment datePickEndDate = new DatePickerFragment(this, R.id.txt_enddate);
        Intent i_from_crud_todos = getIntent();
        String act = i_from_crud_todos.getStringExtra("act");
        _id = i_from_crud_todos.getIntExtra("ref_id", -1);
        lblFormTodos.setText("Form " + act);
        todo = (Todos) i_from_crud_todos.getSerializableExtra("m_todo");
        // -- [END] SETUP -- \\

        if(act != null && act.equals("add")) {
            btn_add.setVisibility(View.VISIBLE);
        }
        if(act != null && act.equals("edit")) {
            btn_edit.setVisibility(View.VISIBLE);
        }

        if (todo != null) {
            txt_judul.setText(todo.getJudul());
            txt_konten.setText(todo.getKonten());
            txt_startdate.setText(todo.getTglstart());
            txt_enddate.setText(todo.getTglend());
        }

        txt_startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickStartDate.onClick(view);
            }
        });
        txt_enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickEndDate.onClick(view);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

    }

    private void updateData() {
        String judul = txt_judul.getText().toString();
        String konten = txt_konten.getText().toString();
        String startDate = txt_startdate.getText().toString();
        String endDate = txt_enddate.getText().toString();
        if (judul.equals("") || konten.equals("") || startDate.equals("") || endDate.equals("")) {
            alertWarningNull();
            return;
        }
        Todos mytodo = new Todos(_id, judul, konten, startDate, endDate);
        db = new DatabaseHelper(this);
        long hasil = db.updateTodo(mytodo);
        final AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Pesan");
        myAlert.setMessage("Success Update Data.!");
        myAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent iToList = new Intent(form_todos.this, crud_todos.class);
                iToList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(iToList);
            }
        });
        AlertDialog alert = myAlert.create();
        alert.show();
    }

    private void insertData() {
        String judul = txt_judul.getText().toString();
        String konten = txt_konten.getText().toString();
        String startDate = txt_startdate.getText().toString();
        String endDate = txt_enddate.getText().toString();
        if (judul.equals("") || konten.equals("") || startDate.equals("") || endDate.equals("")) {
            alertWarningNull();
            return;
        }
        Todos mytodo = new Todos(null, judul, konten, startDate, endDate);
        db = new DatabaseHelper(this);
        long hasil = db.insertTodo(mytodo);

        final AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Pesan");
        myAlert.setMessage("Success Insert Data.!");
        myAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent iToList = new Intent(form_todos.this, crud_todos.class);
                iToList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(iToList);
            }
        });

//        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                // Do nothing
//                dialog.dismiss();
//            }
//        });

        AlertDialog alert = myAlert.create();
        alert.show();

    }

    private void alertWarningNull() {
        final AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Pesan");
        myAlert.setMessage("Fill all data.!");
        myAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = myAlert.create();
        alert.show();
    }
}