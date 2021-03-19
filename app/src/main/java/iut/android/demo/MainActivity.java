package iut.android.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startBtn);

        /**
         * Mise a jour du button lors du clique
         */
        startBtn.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this , GameActivity.class));
           /* switch (startBtn.getText().toString()){
                case "PAUSE":
                    startBtn.setText(getString(R.string.start));
                    break;
                case "JOUER":
                    startBtn.setText(getString(R.string.pause));
                    break;
                default:
                    startBtn.setText("ERREUR");
                    break;
            }*/ });


    }
}