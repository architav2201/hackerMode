package com.example.hackermode;

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

import com.example.hackermode.R;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {

    int Oage, Gage;
    float x;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        String inter = originalage.getText().toString();
        Oage = Integer.parseInt(inter);

        RelativeLayout currentLayout =
                (RelativeLayout) findViewById(R.id.container);

        currentLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));



        EditText guessedage = (EditText) findViewById(R.id.guessedAge);
        // guessedage.setSelection(3); // Custom point Cursor
        guessedage.getText().clear();
        TextView res = (TextView) findViewById(R.id.result);
        res.setText("Predicting the unpredictable?");
        res.setTextColor(Color.BLUE);
        res.setTextSize(50);

        if (Oage > 100 || Oage < 1) {
            Toast.makeText(this, "Enter age within 1-100 limit!", Toast.LENGTH_SHORT).show();
            originalage.getText().clear();


//            Button btn=(Button) findViewById(R.id.submitButton);
//            btn.setOnClickListener(new View.OnClickListener(){
//                public void onClick(View v)
//                {
//                    EditText et=(EditText) findViewById(R.id.originalAge);
//                    et.setText("");
        }


        counter = 0;
        //  TextView res = (TextView) findViewById(R.id.result);
        //   res.setText(" ");

    }

    //TextView forchecking = (TextView) findViewById(R.id.checking);
    // forchecking.setText(String.valueOf(Oage));


    public void guessfn(View view) {
        EditText guessedage = (EditText) findViewById(R.id.guessedAge);
        Gage = Integer.parseInt(guessedage.getText().toString());
        // TextView forchecking = (TextView) findViewById(R.id.checking);
        // forchecking.setText(String.valueOf(Gage));

        compare();

        return;

    }


    public void compare() {
        TextView res = (TextView) findViewById(R.id.result);


        if (Gage == Oage && (counter < 3) && (Oage > 0) && (Oage < 101)) {
            counter += 1;
            res.setText("WOW. Whatte Pro. You guesed it at your " + (counter) + " try" + "\n\"NEW GAME?, submit a new age \"");
            res.setTextColor(Color.WHITE);
            res.setTextSize(25);
            colorchanger();
            counter = 0;
            EditText editTextone = (EditText) findViewById(R.id.originalAge);
            editTextone.getText().clear();
            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();
            Oage = Gage = 0;
            return;

        } else if (Gage < Oage && (counter < 3) && (Oage > 0) && (Oage < 101)) {

            counter += 1;
            colorchanger();
            res.setText("Your guess is LESSER than the actual age. Guess again " + "\n Chance remaining: " + (3 - counter) );
            res.setTextColor(Color.WHITE);
            res.setTextSize(25);
            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();


        } else if (Gage > Oage && (counter < 3) && (Oage > 0) && (Oage < 101)) {

            counter += 1;
            res.setText("Your guess is GREATER than the actual age. Guess again" + "\n Chance remaining: " + (3 - counter));
            res.setTextColor(Color.WHITE);
            res.setTextSize(25);
            colorchanger();
            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();


        }

        if (counter == 3) {
            counter = 0;
            res.setText("You have no tries left, NEW GAME? ");
            res.setTextColor(Color.WHITE);
            res.setTextSize(25);
            colorchanger();
            EditText editTextone = (EditText) findViewById(R.id.originalAge);
            editTextone.getText().clear();
            EditText editTexttwo = (EditText) findViewById(R.id.guessedAge);
            editTexttwo.getText().clear();
            Oage = Gage = 0;

        }
    }


    public void ratiofinder() {
        float diff;
        float key;

        diff = abs((Oage - Gage));

        if (Oage < 50) {
            key = 100 - Oage;
            x =  (diff / key);
        } else if (Oage > 50) {
            key = Oage;
            x = (diff / key);
        }
        else if(Oage==50)
        {
            key=Oage;
            x=(diff/key);
        }


    }}

