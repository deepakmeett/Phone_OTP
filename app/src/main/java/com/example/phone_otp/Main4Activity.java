package com.example.phone_otp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Main4Activity extends AppCompatActivity {

    TextView textView;
    EditText editText1, editText2;
    Button button;
    Database _database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main4 );
        editText1 = findViewById( R.id.logedit1 );
        editText2 = findViewById( R.id.logpassword );
        button = findViewById( R.id.logbuttonPane2 );
        _database = new Database( this );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText1.getText().toString();
                String password = editText2.getText().toString();
                Boolean res = _database.checkUser( email, password );
                if (res) {
                    Toast.makeText( Main4Activity.this, "Login successfully", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( Main4Activity.this, "Not Successfully", Toast.LENGTH_SHORT ).show();
                }

            }
        } );

    }
}
