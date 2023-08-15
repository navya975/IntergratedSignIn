package com.example.loginscreen;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class MainActivity2 extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleBtn = findViewById(R.id.google_btn);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
        //googleBtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                        //correct
                        Toast.makeText(MainActivity2.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    }else
                        //incorrect
                        Toast.makeText(MainActivity2.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
                    signIn();
                }
            });



        }


        protected void onActivityResult(int requestCode, int resultCode,Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1000){
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                try {
                    task.getResult(ApiException.class);
                    navigateToSecondActivity();
                } catch (ApiException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

        }



    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    private void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(MainActivity2.this,SecondActivity.class);
        startActivity(intent);
    }
    }



