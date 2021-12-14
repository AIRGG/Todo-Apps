package com.airgg.myfirstapplication;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AplikasikuActivity extends Activity {
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X", "Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X", "Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X", "Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aplikasiku_layout);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(MainActivity.NOTIF_ID);

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_list, mobileArray);
        final ListView listView = (ListView) findViewById(R.id.lstData);
//        listView.setAdapter(adapter);
//        listView.setAdapter(new MyAdapter(this, mobileArray));

//        ---- BATAS ----
        // Create the item mapping
        String[] from = new String[] { "title", "description" };
        int[] to = new int[] { R.id.title, R.id.description };
        // Add some rows
        List<HashMap<String, Object>> fillMaps = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("title", "First title"); // This will be shown in R.id.title
        map.put("description", "description 1"); // And this in R.id.description
        fillMaps.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "Second title");
        map.put("description", "description 2");
        fillMaps.add(map);

        SimpleAdapter adapterr = new SimpleAdapter(this, fillMaps, R.layout.row_list, from, to);
//        setListAdapter(adapter);
        listView.setAdapter(adapterr);
//        --- BATAS ---

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {
//                AplikasikuActivity item = (AplikasikuActivity) adapter.getItem(i);
                Button btn_list = view.findViewById(R.id.btn_list);

                btn_list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View viewbtn) {
//                        viewbtn.get
                        showToast(i);
                    }
                });


//                String s = listView.getItemAtPosition(i).toString();
//
//                Toast.makeText(, s, Toast.LENGTH_LONG).show();
//                adapter.dismiss(); // If you want to close the adapter
            }
        });
    }

    private void showToast(Integer i){
        Toast.makeText(this, mobileArray[i].toString(), Toast.LENGTH_LONG).show();
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, String[] strings) {
            super(context, -1, -1, strings);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LinearLayout listLayout = new LinearLayout(AplikasikuActivity.this);
            listLayout.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.WRAP_CONTENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));
            listLayout.setId(View.generateViewId());

            TextView listText = new TextView(AplikasikuActivity.this);
            listText.setId(View.generateViewId());

            listLayout.addView(listText);

            listText.setText(super.getItem(position));

            return listLayout;
        }
    }
}
