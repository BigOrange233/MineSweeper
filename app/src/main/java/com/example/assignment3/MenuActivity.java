//menu activity class
package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.assignment3.component.HelpActivity;
import com.example.assignment3.component.OptionActivity;
import com.example.assignment3.component.PlayActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);

        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.hide();
        }


        setButtons();

    }

    private void setButtons() {
        //
//
//    Button helpbutton = (Button)findViewById(R.id.help_button);
//        helpbutton.setOnClickListener(new View.OnClickListener() {
///**
// * Called when a view has been clicked.
// *
// * @param v The view that was clicked.
// */
//@Override
//public void onClick(View v) {
//        Intent help = new Intent(MenuActivity.this , HelpActivity.class);
//        startActivity(help);
//        }
//        });
        Button optionbutton = (Button)findViewById(R.id.option_button);
        optionbutton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent option = new Intent (MenuActivity.this, OptionActivity.class);
                startActivity(option);
            }
        });

        Button playbutton = (Button)findViewById(R.id.play_button);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent play = new Intent (MenuActivity.this, PlayActivity.class);
                startActivity(play);
            }
        });
//
//
//
//        Button optionbutton = (Button)findViewById(R.id.option_button);
//        optionbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View view) {
//                Intent option = new Intent (MenuActivity.this, OptionActivity.class);
//                startActivity(option);
//            }
//        });
//
//        }
        Button helpbutton = (Button)findViewById(R.id.help_button);
        helpbutton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent help = new Intent(MenuActivity.this , HelpActivity.class);
                startActivity(help);
            }
        });

//    Button playbutton = (Button)findViewById(R.id.play_button);
//        playbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View view) {
//                Intent play = new Intent (MenuActivity.this, PlayActivity.class);
//                startActivity(play);
//            }
//        });
    }
}



