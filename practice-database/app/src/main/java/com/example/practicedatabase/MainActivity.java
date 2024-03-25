package com.example.practicedatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNRP, editTextNama;
    private Button buttonSimpan, buttonAmbildata, buttonUpdate, buttonDelete;
    private SQLiteDatabase dbku;
    private SQLiteOpenHelper opendb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.opendb = new SQLiteOpenHelper(this, "db.sql", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {}

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
        };

        this.dbku = this.opendb.getWritableDatabase();
        this.dbku.execSQL("create table if not exists mhs(nrp TEXT, nama TEXT);");

        this.editTextNRP = (EditText)findViewById(R.id.editTextNRP);
        this.editTextNama = (EditText) findViewById(R.id.editTextName);
        this.buttonSimpan = (Button) findViewById(R.id.buttonSave);
        this.buttonAmbildata = (Button) findViewById(R.id.buttonGetAllData);
        this.buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        this.buttonDelete = (Button) findViewById(R.id.buttonDelete);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id = v.getId();
                    if (id == R.id.buttonSave) {
                        MainActivity.this.simpan();
                    }
                    else if (id == R.id.buttonGetAllData) {
                        MainActivity.this.getData();
                    }
                    else if (id == R.id.buttonUpdate) {
                        MainActivity.this.update();
                    }
                    else if (id == R.id.buttonDelete) {
                        MainActivity.this.delete();
                    }
                }
                catch (Exception ignored) {}
            }
        };

        this.buttonSimpan.setOnClickListener(listener);
        this.buttonAmbildata.setOnClickListener(listener);
        this.buttonUpdate.setOnClickListener(listener);
        this.buttonDelete.setOnClickListener(listener);
    }

    @Override
    protected void onStop() {
        this.dbku.close();
        this.opendb.close();
        super.onStop();
    }

    private void simpan() {
        ContentValues dataBaru = new ContentValues();
        dataBaru.put("nrp", this.editTextNRP.getText().toString());
        dataBaru.put("nama", this.editTextNama.getText().toString());

        dbku.insert("mhs", null, dataBaru);
        Toast.makeText(this,"Data Tersimpan",Toast.LENGTH_LONG).show();
    }

    private void getData() {
        Cursor cur = dbku.rawQuery("select * from mhs where nrp = ?",new String[] {this.editTextNRP.getText().toString()});

        try {
            if (cur.getCount() > 0) {
                Toast.makeText(this,"Data Ditemukan Sejumlah " + cur.getCount(),Toast.LENGTH_LONG).show();
                cur.moveToFirst();
                this.editTextNama.setText(cur.getString(cur.getColumnIndexOrThrow("nama")));
            }
            else {
                Toast.makeText(this,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ignored) {}
    }

    private void update() {
        ContentValues dataBaru = new ContentValues();
        String nrp = this.editTextNRP.getText().toString();
        dataBaru.put("nrp", nrp);
        dataBaru.put("nama", this.editTextNama.getText().toString());

        dbku.update("mhs", dataBaru, "nrp = ?", new String[] {nrp});
        Toast.makeText(this,"Data Terupdate",Toast.LENGTH_LONG).show();
    }

    private void delete() {
        dbku.delete("mhs","nrp = ?", new String[] {this.editTextNRP.getText().toString()});
        Toast.makeText(this,"Data Terhapus",Toast.LENGTH_LONG).show();
    }
}