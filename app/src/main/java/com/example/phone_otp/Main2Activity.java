package com.example.phone_otp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
public class Main2Activity extends AppCompatActivity {

    TextInputEditText textInputEditText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        button = findViewById( R.id.buttonPanel );
        textInputEditText = findViewById( R.id.tezt );
        button.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent( Main2Activity.this, MainActivity.class );
                startActivity( intent );
                return false;
            }

        } );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = textInputEditText.getText().toString();
                Intent intent = new Intent( Main2Activity.this, Main3Activity.class);
                intent.putExtra( "a", s1 );
                startActivity( intent );
                

            }
        } );
    }

}
