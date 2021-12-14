package com.airgg.myfirstapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.airgg.myfirstapplication.R;
import com.airgg.myfirstapplication.crud_todos;
import com.airgg.myfirstapplication.detail_todos;
import com.airgg.myfirstapplication.form_todos;
import com.airgg.myfirstapplication.models.Todos;
import com.airgg.myfirstapplication.utils.DatabaseHelper;

import java.util.List;

public class TodoListViewAdapter extends ArrayAdapter<Todos> {
    List<Todos> listTodo;
    Context context;
    int resource;

    public TodoListViewAdapter(Context context, int resource, List<Todos> listTodo) {
        super(context, resource, listTodo);
        this.context = context;
        this.listTodo = listTodo;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(resource, null, false);

//        -- Element in ItemCustom
//        TextView txtJudul = view.findViewById((R.id.lbl_judul);
        TextView txtJudul = view.findViewById(R.id.lbl_judul);
        TextView txtKonten = view.findViewById(R.id.lbl_konten);
        TextView txtTanggal = view.findViewById(R.id.lbl_date);

        Button btn_edit = view.findViewById(R.id.btn_list_edit);
        Button btn_del = view.findViewById(R.id.btn_list_delete);
        Button btn_detail = view.findViewById(R.id.btn_list_detail);

        final Todos todo = listTodo.get(position);

        txtJudul.setText(todo.getJudul());
        txtKonten.setText(todo.getKonten());
        txtTanggal.setText(todo.getTglstart());

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_form_todos = new Intent(context, detail_todos.class);
                Todos editTodo = new Todos(todo.getId(), todo.getJudul(), todo.getKonten(), todo.getTglstart(), todo.getTglend());
//                Todos editTodo = new Todos();
                i_form_todos.putExtra("act", "detail");
                i_form_todos.putExtra("ref_id", todo.getId());
                i_form_todos.putExtra("m_todo", editTodo);
                context.startActivity(i_form_todos);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_form_todos = new Intent(context, form_todos.class);
                Todos editTodo = new Todos(todo.getId(), todo.getJudul(), todo.getKonten(), todo.getTglstart(), todo.getTglend());
//                Todos editTodo = new Todos();
                i_form_todos.putExtra("act", "edit");
                i_form_todos.putExtra("ref_id", todo.getId());
                i_form_todos.putExtra("m_todo", editTodo);
                context.startActivity(i_form_todos);
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
                myAlert.setTitle("Pesan");
                myAlert.setMessage("Yakin ingin Delete?");
                myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.deleteTodo(todo);
                        dialog.dismiss();
                        alertOke();
                        listTodo.remove(position);
                        notifyDataSetChanged();
//                        Intent iToList = new Intent(context, crud_todos.class);
//                        iToList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        notifyDataSetChanged();
                    }
                });

                myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = myAlert.create();
                alert.show();
            }
        });

        return view;
    }

    private void alertOke() {
        final AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
        myAlert.setTitle("Pesan");
        myAlert.setMessage("Success Delete");
        myAlert.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = myAlert.create();
        alert.show();
    }
}
