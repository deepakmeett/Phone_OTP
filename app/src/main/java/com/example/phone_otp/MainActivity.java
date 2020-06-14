package com.example.phone_otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1, b2;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    String vid;
    PhoneAuthProvider.ForceResendingToken phon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent( MainActivity.this, Main2Activity.class );
            startActivity( intent );
            finish();
        }
        setContentView( R.layout.activity_main );
        ed1 = findViewById( R.id.phone_number );
        ed2 = findViewById( R.id.otp_edit );
        b1 = findViewById( R.id.otp_button );
        b2 = findViewById( R.id.sign_up );
        
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verification( ed1.getText().toString() );
            }
        } );

        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = ed2.getText().toString();
                PhoneAuthCredential pap = PhoneAuthProvider.getCredential( vid, s1 );
                signInWithPhoneAuthCredential( pap );
            }
        } );
    }
    
    public void verification(String phoneNumber){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        vid = s;
                        phon = forceResendingToken;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential( phoneAuthCredential );
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText( MainActivity.this, "Verification Failed", Toast.LENGTH_SHORT ).show();
                        
                    }
                });

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText( MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent( MainActivity.this, Main2Activity.class );
                            startActivity( intent );
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText( MainActivity.this, "Not Successfully", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    }
                });
    }
}