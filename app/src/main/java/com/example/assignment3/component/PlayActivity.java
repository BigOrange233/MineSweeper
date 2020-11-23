//this is the option play activity class
package com.example.assignment3.component;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.model.location;
import com.example.assignment3.model.UserPref;

import java.util.HashSet;
import java.util.Set;

public class PlayActivity extends AppCompatActivity {

    private int totalrow;
    private int totalcolum;
    private int totalsubmarine;
    private  Button[][]buttonmade;
    Set<location> submarine_Position;
    Set<location> submarine_Found;
    Set<location> number_button;

    TextView found;
    TextView scan_used;

    int NumbersOfScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.hide();
        }

        UserPref pref = UserPref.getInstance();
        totalrow = pref.getRow();
        totalcolum = pref.getColum();
        totalsubmarine = pref.getSubmarine();

        submarine_Position = new HashSet<location>();
        submarine_Found = new HashSet<location>();
        number_button = new HashSet<location>();

        NumbersOfScan = 0;
        SetUp();
        TextUpdate();
    }

    private void SetUp() {
        int putSubmarine = 0;

        while(putSubmarine <totalsubmarine){
            int newrow = (int)(Math.random() * totalrow);
            int newcolum = (int)(Math.random() * totalcolum);

            int previous = submarine_Position.size();

            submarine_Position.add(new location(newrow,newcolum));
            if(submarine_Position.size() == previous +1){
                putSubmarine ++;
            }
        }

        buttonmade  = new Button[totalrow][totalcolum];
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

        for (int row = 0; row < totalrow; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for(int colum = 0;colum < totalcolum;colum ++) {
                final int FINAL_COLUM = colum;
                final int FINAL_ROW = row;

//                for(int colum = 0;colum < totalcolum;colum ++) {
//                    f colum;
//                    final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ClickgridButton(FINAL_COLUM, FINAL_ROW);

//                            ClickgridButton(FINAL_COLUM, FINAL_ROW);
                    }
                });
                tableRow.addView(button);
                buttonmade[row][colum] = button;
            }
        }
    }

    private void ClickgridButton(int final_colum, int final_row) {
        System.out.println("Printing All Mines");
        System.out.println("These are discovered mines");

        Button button = buttonmade[final_row][final_colum];
        location tapped = new location(final_row, final_colum);

        if (submarine_Position.contains(tapped)) {
            if (!(submarine_Found.contains(tapped))) {
                lockButtonSize();
                int newWidth = button.getWidth();
                int newHeight = button.getHeight();

                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.submarine);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));

                submarine_Found.add(tapped);
                updateAppearedText(final_row, final_colum);

                TextUpdate();

                if (submarine_Found.size() == totalsubmarine) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(PlayActivity.this);
                    LayoutInflater factory = LayoutInflater.from(PlayActivity.this);
                    View v = factory.inflate(R.layout.wonpage, null);
                    alert.setView(v);
                    alert.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alert.show();
                }
            } else {
                int nearSubmarine = searchSubmarine(final_row, final_colum);
                button.setText(String.valueOf(nearSubmarine));

                if (!number_button.contains(tapped)) {
                    NumbersOfScan++;
                    number_button.add(tapped);
                }

                TextUpdate();
            }
        } else {
            int nearSubmarine = searchSubmarine(final_row, final_colum);
            button.setText(String.valueOf(nearSubmarine));

            if (!number_button.contains(tapped)) {
                NumbersOfScan++;
                number_button.add(tapped);
            }
            TextUpdate();
        }
    }

    private void updateAppearedText(int final_row, int final_colum) {
        for(location location :number_button){
            if(location.getX() == final_row || location.getY() == final_colum) {
                Button button = buttonmade[location.getX()][location.getY()];
                button.setText(String.valueOf(Integer.parseInt(String.valueOf(button.getText())) - 1));
            }
        }
    }

    private void lockButtonSize() {
        for(int rows = 0; rows < totalrow; rows++){
            for(int colums = 0; colums < totalcolum;colums++){
                Button button = buttonmade[rows][colums];

                int w = button.getWidth();
                button.setWidth(w);
                button.setMaxWidth(w);

                int h = button.getHeight();
                button.setHeight(h);
                button.setMaxHeight(h);
            }
        }
    }

    private int searchSubmarine(int final_row, int final_colum) {
        Set<location> SubmarineNotFound = submarine_Position;
        for(location x : submarine_Found) {
            if(SubmarineNotFound.contains(x)) {
                SubmarineNotFound.remove(x);
            }
        }
        int hidden = 0;
        for(location x : SubmarineNotFound) {
            if(x.getX() == final_row || x.getY() == final_colum) {
                hidden  ++;
            }
        }
        return hidden;
    }
    private void TextUpdate(){
        found = (TextView) findViewById(R.id.submaineCount);
        found.setText("Found " + submarine_Found.size() + " of " + totalsubmarine + " mines.");
        scan_used = (TextView) findViewById(R.id.scanCount);
        scan_used.setText("# Scans used: " + number_button.size());
    }


}