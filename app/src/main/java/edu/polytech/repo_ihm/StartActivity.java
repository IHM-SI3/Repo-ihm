package edu.polytech.repo_ihm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.repo_ihm.account.AuthenticatorSingleton;
import edu.polytech.repo_ihm.account.LoginActivity;
import edu.polytech.repo_ihm.account.RegisterActivity;
import edu.polytech.repo_ihm.activities.MainActivity;

public class StartActivity extends AppCompatActivity {
    public static String API_HOST = "http://47a0-109-210-108-136.eu.ngrok.io";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        AuthenticatorSingleton.getInstance();


        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        Log.d("pref", sharedPreferences.toString());
        if (sharedPreferences.contains("session_token")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Restauration de la session").setTitle("Connexion").setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            @SuppressLint("ApplySharedPref") Thread login = new Thread(() -> {
                AuthenticatorSingleton.getInstance().setCurrentUser(sharedPreferences.getString("session_token", null));
                try {
                    AuthenticatorSingleton.getInstance().setCurrentUserThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                dialog.cancel();
                finish();
            });
            login.start();
        }


        Button login = findViewById(R.id.gotoLoginButton);
        Button register = findViewById(R.id.gotoRegisterButton);

        login.setOnClickListener((View v) -> {
            startActivity(new Intent(StartActivity.this, LoginActivity.class));
            finish();
        });
        register.setOnClickListener((View v) -> {
            startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            finish();
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        });

    }
}