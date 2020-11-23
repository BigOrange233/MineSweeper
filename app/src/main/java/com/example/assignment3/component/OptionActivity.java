//this is the option activity class
package com.example.assignment3.component;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment3.R;
import com.example.assignment3.model.UserPref;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class OptionActivity extends AppCompatActivity {

    private String[] boardOption = {"4 x 6","5 x 10","6 x 15"};
    private String[] submarineOption = {"6","10","15","20","24","30"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Options");
        }

        setSizeChoose();
        setSubmarineChoose();
    }

    private void setSizeChoose() {
        Spinner spinner = (Spinner) findViewById(R.id.sizeChoose);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, boardOption);
        spinner.setAdapter(adapter);

        int start_position = 0;

        UserPref pref = UserPref.getInstance();
        String current = pref.getRow() + " x " + pref.getColum();
        for (String string : boardOption) {
            if (current.equals(string)) {
                break;
            }
            start_position++;
        }
        spinner.setSelection(start_position);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String map = parent.getItemAtPosition(position).toString();
                String[] splited = map.split("x");
                UserPref pref = UserPref.getInstance();
                int row = Integer.parseInt(splited[0].replaceAll(" ", ""));
                int colum = Integer.parseInt(splited[1].replaceAll(" ", ""));

                if (row * colum < pref.getSubmarine()) {
                    Toast.makeText(OptionActivity.this, "Table is too small", Toast.LENGTH_SHORT).show();
                } else {
                    pref.reset(row, colum);
                }
                Toast.makeText(OptionActivity.this, "row=" + row + " colum=" + colum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void setSubmarineChoose(){
        Spinner spinner = (Spinner)findViewById(R.id.submarineChoose);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,submarineOption);
        spinner.setAdapter(adapter);

        int start_position = 0;
        UserPref pref = UserPref.getInstance();
        String current = String.valueOf(pref.getSubmarine());

        for (String temp :submarineOption){
            if(current.equals(temp)){
                break;
            }
            start_position++;
        }

        spinner.setSelection(start_position);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String map = parent.getItemAtPosition(position).toString();

                UserPref pref = UserPref.getInstance();
                int numberOfSub = Integer.parseInt(map);




//    private void setSizeChoose() {
//        Spinner spinner = (Spinner) findViewById(R.id.sizeChoose);
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, boardOption);
//        spinner.setAdapter(adapter);
//
//        int start_position = 0;
//
//        UserPref pref = UserPref.getInstance();
//        String current = pref.getRow() + " x " + pref.getColum();
//        for (String string : boardOption) {
//            if (current.equals(string)) {
//                break;
//            }
//            start_position++;
//        }
//        spinner.setSelection(start_position);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override

                if(pref.getRow() * pref.getColum() < numberOfSub){
                    Toast.makeText(OptionActivity.this,"Too much submarines", Toast.LENGTH_SHORT).show();
                }else{
                    pref.resetSubmarine(numberOfSub);
                }
                Toast.makeText(OptionActivity.this, "Number of Submarine=" + map, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String map = parent.getItemAtPosition(position).toString();
//                String[] splited = map.split("x");
//                UserPref pref = UserPref.getInstance();
//                int row = Integer.parseInt(splited[0].replaceAll(" ", ""));
//                int colum = Integer.parseInt(splited[1].replaceAll(" ", ""));
//
//                if (row * colum < pref.getSubmarine()) {
//                    Toast.makeText(OptionActivity.this, "Table is too small", Toast.LENGTH_SHORT).show();
//                } else {
//                    pref.reset(row, colum);
//                }
//                Toast.makeText(OptionActivity.this, "row=" + row + " colum=" + colum, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }


}

