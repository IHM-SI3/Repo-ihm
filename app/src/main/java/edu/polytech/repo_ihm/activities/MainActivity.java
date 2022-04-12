package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.polytech.repo_ihm.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }




    public void goToMesInventaires(View view) {
        Intent intent = new Intent(MainActivity.this, MesInventairesActivity.class);
        startActivity(intent);
    }

    public void goToMarketPlace(View view) {
        Intent intent = new Intent(MainActivity.this, MarketPlaceActivity.class);
        startActivity(intent);
    }

    public void goToAideDosage(View view) {
        Intent intent = new Intent(MainActivity.this, AideDosageActivity.class);
        startActivity(intent);
    }

    public void goToIdeeRecettes(View view) {
        Intent intent = new Intent(MainActivity.this, IdeeRecettesActivity.class);
        startActivity(intent);
    }
    public void goToAlertePeremption(View view) {
        Intent intent = new Intent(MainActivity.this, IdeeRecettesActivity.class);
        startActivity(intent);
    }
}