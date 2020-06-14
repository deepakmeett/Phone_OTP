package com.example.phone_otp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Main3Activity extends AppCompatActivity {

    TextView textView;
    EditText editText1, editText2;
    Button button;
    Database database;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main3 );
        textView = findViewById( R.id.text2 );
        editText1 = findViewById( R.id.edit1 );
        editText2 = findViewById( R.id.password );
        button = findViewById( R.id.buttonPane2 );
        database = new Database( Main3Activity.this );
        model = new Model();
        Intent intent = getIntent();
        String s2 = intent.getStringExtra( "a" );
        textView.setText( s2 );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText1.getText().toString();
                String password = editText2.getText().toString();
                model.setEmail( email );
                model.setPassword( password );
                
                database.add( model );
                Toast.makeText( Main3Activity.this, "Registration", Toast.LENGTH_SHORT ).show();

            }
        } );
        button.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent1 = new Intent( Main3Activity.this, Main4Activity.class );
                startActivity( intent1 );
                return false;
            }
        } );
    }
}
