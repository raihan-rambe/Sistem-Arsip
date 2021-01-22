package com.example.rensposi.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.rensposi.Adapter.AlertDialogManager;
import com.example.rensposi.R;
import com.example.rensposi.Session.SessionManager;

public class MainActivity extends AppCompatActivity {

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        btnLogout = findViewById(R.id.out);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
            }
        });
    }

    public void adminMenu(View v) {
        Intent i = new Intent(this, Loginadmin.class);
        startActivity(i);
    }

    public void karyawanMenu(View v) {
        Intent i = new Intent(this, loginkaryawan.class);
        startActivity(i);
    }

    public void marketMenu(View v) {
        Intent i = new Intent(this, market.class);
        startActivity(i);
    }

    public void historyMenu(View v) {
        Intent i = new Intent(this, history.class);
        startActivity(i);
    }
}