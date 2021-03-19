package iut.android.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

public class GameActivity extends AppCompatActivity {


    TextView tvop, tvt, tvverdict;
    EditText etrep;
    Random alea;
    int operande1 = 0, operande2 = 0 , nb_reponse = 0 , nb_erreur = 0;
    Chronometer chrono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        if( savedInstanceState == null) findViewById(R.id.fadeLayout).startAnimation(anim);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        chrono.start();
    }

    private void init() {
        chrono = findViewById(R.id.chrono);
        tvop = findViewById(R.id.tvoperation);
        tvt = findViewById(R.id.tvtitre);
        tvverdict = findViewById(R.id.tvrep);
        etrep = findViewById(R.id.reponse);
        alea = new Random(System.currentTimeMillis());
        initialiserOperation();
    }

    public void initialiserOperation() {
        operande1 = 1+alea.nextInt(9);
        operande2 = 1+alea.nextInt(9);
        tvop.setText(operande1+ " x "+operande2+" = ");
    }
    /**
     * le prototype de la méthode pour
     * un bouton déclaré dans le fichier xml
     * est
     * public void nomMethode(View v) {}
     * @param v
     */
    public void verification(View v) {
        int reponse = 0;
        if ( ! etrep.getText().toString().equals(""))
            reponse = Integer.parseInt(etrep.getText().toString());

        if(operande1*operande2 == reponse) {
            tvverdict.setText("bonne réponse");
            tvverdict.setBackgroundColor(Color.GREEN);
            initialiserOperation();
        } else {
            tvverdict.setBackgroundColor(Color.RED);
            tvverdict.setText("mauvaise réponse");
            nb_erreur++;
        }
        etrep.setText("");
        etrep.setHint(""+reponse);

        if (++nb_reponse == 10){
            startActivity(new Intent(GameActivity.this , MainActivity.class));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong("timer" , chrono.getBase());
        outState.putInt("nb_rep" , nb_reponse);
        outState.putInt("op1" , operande1);
        outState.putInt("op2" , operande2);
        outState.putInt("nb_erreur",nb_erreur);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nb_reponse = savedInstanceState.getInt("nb_rep" , 0);
        chrono.setBase(savedInstanceState.getLong("timer"));
        operande1 = savedInstanceState.getInt("op1" , 0);
        operande2 = savedInstanceState.getInt("op2" , 0);
        nb_erreur = savedInstanceState.getInt("nb_erreur",0);
        tvop.setText(operande1+ " x "+operande2+" = ");
    }
}