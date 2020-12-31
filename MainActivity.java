package com.example.sistemarsip;

import androidx.appcompat.app.AppCompatActivity;

import android.media.audiofx.DynamicsProcessing;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String tag="mainactivity";
    private Button Login1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(tag,"ini di OnCreate");

            Toast.makeText(MainActivity.this, "OnCreate", Toast.LENGTH_SHORT).show();

            Login1 = (Button) findViewById(R.id.btnLogin);
            Login1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();

                }
            }); {

            }

        }

    protected void OnStart() {
        super.onResume();
        Log.v(tag,"ini di Onstart");

        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();

    }

         @Override
        protected void onResume() {
        super.onResume();
        Log.v(tag,"ini di onResume");

             Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();

        }

         @Override
         protected void onPause() {
         super.onPause();
         Log.v(tag,"ini di onpause");

             Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
         }

         @Override
         protected void onStop() {
        super.onStop();
        Log.v(tag,"ini di onStop");

             Toast.makeText(this, "Stop" , Toast.LENGTH_SHORT).show();
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(tag,"ini di ondestroy");
        }

    public void register(View view) {
    }

    public void Login(View view) {
    }
}



