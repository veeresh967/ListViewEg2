package com.veeresh.b37_listvieweg2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //declare all variables
    EditText et; //visible
    Button b, b2;
    ListView lv;
    ArrayList<String> al; //invisible
    ArrayAdapter<String> aa;
    int longPressPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize all variables
        //initialize all visible components with findview by id
        et = (EditText) findViewById(R.id.editText1);
        b = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        lv = (ListView) findViewById(R.id.listView1);
        //intiaizlie all inivisble with new operator
        al = new ArrayList<String>();
        //establish link between arraylist and array adapter
        aa = new ArrayAdapter<String>(this, R.layout.row, al);
        //establish link between array adapter to listview
        lv.setAdapter(aa);
        //if user clicks on the listview, i want to display a toast message.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "CLICKEDD.."+al.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        //if user long press an element of list view, remove that element
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //display this element in the edit text
                et.setText(al.get(i));
                longPressPosition = i; //capture the position of old element where user long presd
                //show a toast please edit
                Toast.makeText(MainActivity.this, "please edit now", Toast.LENGTH_SHORT).show();
                et.requestFocus();
                return true;
            }
        });
        //button 2 (replace) logic
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al.set(longPressPosition, et.getText().toString().trim());
                aa.notifyDataSetChanged();
            }
        });
        //button click listener
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //a. read element from edit text
                String city = et.getText().toString().trim();
                if(city.equals("")){
                    Toast.makeText(MainActivity.this, "plz enter valid city", Toast.LENGTH_SHORT).show();
                    et.requestFocus();
                    return;
                }
                //b. add that city element to array list
                al.add(city); //this always adds new element in 0th location
                //al.add("");//immediately add an empty element
                //let us sort the array list
                //Collections.sort(al);
                //c. tell to adapter
                aa.notifyDataSetChanged(); //predefined method
                //d. clean edit text so that user can enter nxt city
                et.setText("");
                //e. put cursor on edit text
                et.requestFocus(); //predefined method
            }
        });
    }
}