package com.example.rensposi.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.rensposi.Database.DatabaseHelper;
import com.example.rensposi.R;
import com.example.rensposi.Session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class market extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Spinner spinID, spinstokbrg, spinbarang ;
    SessionManager session;
    String email;
    int id_book;
    public String sID, sbarang, sTanggal, sstokbrg;
    int jmlstok;
    int bykstok;
    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

        dbHelper = new DatabaseHelper(market.this);
        db = dbHelper.getReadableDatabase();

        final String[] ID = {"Z78yui1", "5sgh9De", "901utyo"};
        final String[] barang = {"Sabun Mandi", "Sikat Gigi", "Air Mineral"};
        final String[] dewasa = {"0", "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};


        spinID = findViewById(R.id.ID);
        spinbarang = findViewById(R.id.barang);
        spinstokbrg = findViewById(R.id.stokbrg);


        ArrayAdapter<CharSequence> adapterID = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, ID);
        adapterID.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinID.setAdapter(adapterID);

        ArrayAdapter<CharSequence> adapterbarang = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, barang);
        adapterbarang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinbarang.setAdapter(adapterbarang);

        ArrayAdapter<CharSequence> adapterstok = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, dewasa);
        adapterstok.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinstokbrg.setAdapter(adapterstok);

        spinID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sID = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        spinstokbrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sstokbrg = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


            Button btnBook = findViewById(R.id.book);

        etTanggal = findViewById(R.id.tanggalcek);
        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.requestFocus();
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);
        setDateTimeField();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perhitunganstok();
                if (sID != null && sbarang != null && sTanggal != null && sstokbrg != null) {
                    if ((sID.equalsIgnoreCase("Z78yui1") && sbarang.equalsIgnoreCase("Sabun Mandi"))
                            || (sID.equalsIgnoreCase("sgh9De") && sbarang.equalsIgnoreCase("Sikat Gigi"))
                            || (sID.equalsIgnoreCase("901utyo") && sbarang.equalsIgnoreCase("Air Mineral")))
                        ;
                    else {
                        AlertDialog dialog = new AlertDialog.Builder(market.this)
                                .setTitle("apakah stok cocok?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            db.execSQL("INSERT INTO TB_BOOK (ID, Barang, tanggal, Stok) VALUES ('" +
                                                    sID + "','" +
                                                    sbarang + "','" +
                                                    sTanggal + "','" +
                                                    sstokbrg + "','" );
                                            cursor = db.rawQuery("SELECT id_book FROM TB_BOOK ORDER BY id_book DESC", null);
                                            cursor.moveToLast();
                                            if (cursor.getCount() > 0) {
                                                cursor.moveToPosition(0);
                                                id_book = cursor.getInt(0);
                                            }

                                        } catch (Exception e) {
                                            Toast.makeText(market.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Tidak", null)
                                .create();
                        dialog.show();
                    }
                }
            }

            private void perhitunganstok() {
            }
        });

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbmarket);
        toolbar.setTitle("Form Stok");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDateTimeField() {
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal.show();
            }
        });

        dpTanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                sTanggal = dayOfMonth + " " + bulan[monthOfYear] + " " + year;
                etTanggal.setText(sTanggal);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}
