package com.example.hackermode;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {

    int win=0;
    int loss=0;
    String inter;
    int winner;
    int loser;
    int mwin;
    int mloss;
    int Oage, Gage;
    float x;
    int counter = 0;
    SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display();
        TextView tvl = (TextView) findViewById(R.id.statwin);
        mwin= Integer.parseInt(tvl.getText().toString());
        TextView tvr = (TextView) findViewById(R.id.statloss);
        mloss= Integer.parseInt(tvr.getText().toString());



    }

    public void colorchanger()
    {   ratiofinder();
        int result;
        int greenColorValue = Color.parseColor("#00ff00");
        int redColorValue = Color.parseColor("#ff0000");
        result = ColorUtils.blendARGB(greenColorValue, redColorValue, x );
        String hexColor = String.format("#%06X", (0xFFFFFF & result));
        RelativeLayout currentLayout =
                (RelativeLayout) findViewById(R.id.container);

        currentLayout.setBackgroundColor(Color.parseColor(hexColor));

    }

    public void submitfn(View view) {
        EditText originalage = (EditText) findViewById(R.id.originalAge);
        inter = originalage.getText().toString();

        if (inter.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter an age!", Toast.LENGTH_SHORT).show();
            return;
        }

        Oage = Integer.parseInt(inter);

        originalage.setVisibility(View.INVISIBLE);
        Button sub = (Button) findViewById(R.id.submitButton);
        sub.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Age has been set!", Toast.LENGTH_SHORT).show();

        RelativeLayout currentLayout =
                (RelativeLayout) findViewById(R.id.container);

        currentLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));

        EditText guessedage = (EditText) findViewById(R.id.guessedAge);
        guessedage.getText().clear();
        TextView res = (TextView) findViewById(R.id.result);
        res.setText(" Predicting the unpredictable? ");
        res.setTextColor(Color.parseColor("#FF8F00"));

        if (Oage > 100 || Oage < 1) {
            Toast.makeText(this, "Enter age within 1-100 limit!", Toast.LENGTH_SHORT).show();
            originalage.getText().clear();
            originalage.setVisibility(View.VISIBLE);
            sub.setVisibility(View.VISIBLE);

        }

        counter = 0;
    }

    public void guessfn(View view) {
        EditText guessedage = (EditText) findViewById(R.id.guessedAge);

        String interme= guessedage.getText().toString();
        if (interme.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a Guess!", Toast.LENGTH_SHORT).show();
            return;
        }


        Gage = Integer.parseInt(interme);
        if(inter.equals(" "))
        { Toast.makeText(getApplicationContext(), "Please enter the original DEATH-AGE before guessing!", Toast.LENGTH_SHORT).show();
            return;}

        compare();

        return;

    }


    public void compare() {
        TextView res = (TextView) findViewById(R.id.result);


        if (Gage == Oage && (counter < 3) && (Oage > 0) && (Oage < 101)) {
            counter += 1;
            res.setText("WOW. Whatte Pro. You guesed it at your " + (counter) + " try" + "\n\"NEW GAME?, submit a new age \"");
            res.setTextColor(Color.parseColor("#ffffff"));
            colorchanger();
            counter = 0;

            win+=1;
            save();
            display();
//            TextView wincount = (TextView) findViewById(R.id.statwin);
//            wincount.setText(String.valueOf(win));



            EditText editTextone = (EditText) findViewById(R.id.originalAge);
            editTextone.getText().clear();
            editTextone.setVisibility(View.VISIBLE);

            Button sub = (Button) findViewById(R.id.submitButton);
            sub.setVisibility(View.VISIBLE);

            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();
            Oage = Gage = 0;
            return;



        } else if (Gage < Oage && (counter < 3) && (Oage > 0) && (Oage < 101)) {

            counter += 1;
            colorchanger();
            res.setText("Your guess is LESSER than the actual age. Guess again " + "\n Chance remaining: " + (3 - counter) );
            res.setTextColor(Color.parseColor("#ffffff"));
            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();


        } else if (Gage > Oage && (counter < 3) && (Oage > 0) && (Oage < 101)) {

            counter += 1;
            res.setText("Your guess is GREATER than the actual age. Guess again" + "\n Chance remaining: " + (3 - counter));
            res.setTextColor(Color.parseColor("#ffffff"));
            colorchanger();
            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();


        }

        if (counter == 3) {
            counter = 0;

            res.setText("You have no tries left, NEW GAME? ");
            res.setTextColor(Color.parseColor("#ffffff"));
            colorchanger();
            EditText editTextone = (EditText) findViewById(R.id.originalAge);
            editTextone.getText().clear();
            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();
            Oage = Gage = 0;

            loss=loss+1;
            save();
            display();
//            TextView losscount = (TextView) findViewById(R.id.statloss);
//            losscount.setText(String.valueOf(loss));


            EditText Origage = (EditText) findViewById(R.id.originalAge);
            Origage.setVisibility(View.VISIBLE);
            Button sub = (Button) findViewById(R.id.submitButton);
            sub.setVisibility(View.VISIBLE);

        }

        // save();
        //   display();
    }


    public void ratiofinder() {
        float diff;
        float key;

        diff = abs((Oage - Gage));

        if (Oage < 50) {
            key = 100 - Oage;
            x = (diff / key);
        } else if (Oage > 50) {
            key = Oage;
            x = (diff / key);
        } else if (Oage == 50) {
            key = Oage;
            x = (diff / key);
        }
    }

    public void save(){
        winner=win+mwin;
        loser=loss+mloss;

        myPrefs = getSharedPreferences("PrefID", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putInt("losskey",loser);
        editor.putInt("winkey",winner);
        editor.apply();

    }

    public void display()
    {
        myPrefs =  getSharedPreferences("PrefID", Context.MODE_PRIVATE);
        int LOSS = myPrefs.getInt("losskey", 0);
        int WIN=myPrefs.getInt("winkey", 0);

        TextView wincount = (TextView) findViewById(R.id.statwin);
        wincount.setText(String.valueOf(WIN));

        TextView losscount = (TextView) findViewById(R.id.statloss);
        losscount.setText(String.valueOf(LOSS));


    }



}

