package com.example.assignment3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.hide();
        }

        final CountDownTimer timer = new CountDownTimer(TIMEOUT,1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                TextView text = (TextView)findViewById(R.id.skip_button);
                text.setText("Click to Skip in " + millisUntilFinished/1000 + " second.");
//                TextView text = (TextView)findViewById(R.id.skip_button);
//                text.setText("Click to Skip in " + millisUntilFinished/1000 + " second.");
            }

            @Override
            public void onFinish() {
                Intent menu = new Intent(MainActivity.this , MenuActivity.class);
                startActivity(menu);
                finish();
                }
            }.start();

//        @Override
//        public void onFinish() {
//            Intent menu = new Intent(MainActivity.this , MenuActivity.class);
//            startActivity(menu);
//            finish();
//        }
//    }.start();
//

        TextView skip = (TextView)findViewById(R.id.skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tap = new Intent(MainActivity.this , MenuActivity.class);
                timer.cancel();
                startActivity(tap);
                finish();
            }
        });



        }
    }