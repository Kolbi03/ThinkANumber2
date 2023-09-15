package com.example.thinkanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewHeart1;
    private ImageView imageViewHeart2;
    private ImageView imageViewHeart3;
    private ImageView imageViewHeart4;
    private ImageView imageViewHeart5;
    private Button buttonSubmit;
    private Button buttonPlus;
    private Button buttonMinus;
    private TextView textViewNumber;
    private int life;
    private int randomNumber;
    private int number;
    private AlertDialog.Builder alertGameOver;
    private Button buttonEasy;
    private Button buttonHard;
    private boolean hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (number <= 0) {
                    //Felugró ablak
                    Toast.makeText(MainActivity.this, "0-nál kissebb nem lehet", Toast.LENGTH_SHORT).show();
                } else {
                    number--;
                    textViewNumber.setText(String.valueOf(number));

                }
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number >= 10 && hard == false) {
                    //Felugró ablak
                    Toast.makeText(MainActivity.this, "10-nél nagyobb nem lehet", Toast.LENGTH_SHORT).show();
                } else if (number >= 20 && hard == true)
                {
                    Toast.makeText(MainActivity.this, "20-nál nagyobb nem lehet", Toast.LENGTH_SHORT).show();
                }
                else {
                    number++;
                    textViewNumber.setText(String.valueOf(number));

                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number > randomNumber) {
                    Toast.makeText(MainActivity.this, "Lejjebb kell tippelni", Toast.LENGTH_SHORT).show();
                    life--;
                    imageViewModify();
                } else if (number < randomNumber) {
                    Toast.makeText(MainActivity.this, "Feljebb kell tippelni", Toast.LENGTH_SHORT).show();
                    life--;
                    imageViewModify();
                } else {
                    Toast.makeText(MainActivity.this, "Ez a jó megoldás", Toast.LENGTH_SHORT).show();
                    alertGameOver.setTitle("Gratulálok, nyertél!").create();
                    alertGameOver.show();
                }

            }
        });
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    hard();
                }
        });
        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easy();
            }
        });
    }

    private void init() {
        hard = false;
        buttonHard = findViewById(R.id.hard);
        buttonEasy = findViewById(R.id.easy);
        imageViewHeart1 = findViewById(R.id.Viewheart1);
        imageViewHeart2 = findViewById(R.id.Viewheart2);
        imageViewHeart3 = findViewById(R.id.Viewheart3);
        imageViewHeart4 = findViewById(R.id.Viewheart4);
        imageViewHeart5 = findViewById(R.id.Viewheart5);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        textViewNumber = findViewById(R.id.textViewNumber);
        life = 3;
        Random random = new Random();
        randomNumber = random.nextInt(11);
        number = 0;
        alertGameOver = new AlertDialog.Builder(MainActivity.this);
        alertGameOver.setTitle("Játék Vége")
                .setMessage("Újra akarod kezdeni?")
                .setNegativeButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //imageView Reset, number és random number reset
                        newGame();
                    }
                })
                .setPositiveButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setCancelable(false)
                .create();
    }

    public void hard()

    {
        imageViewHeart5.setVisibility(View.VISIBLE);
        imageViewHeart4.setVisibility(View.VISIBLE);
        life = 5;
        Random random = new Random();
        randomNumber = random.nextInt(21);
        number = 0;
        hard = true;
    }

    public void easy()
    {
        imageViewHeart5.setVisibility(View.INVISIBLE);
        imageViewHeart4.setVisibility(View.INVISIBLE);
        life = 3;
        Random random = new Random();
        randomNumber = random.nextInt(11);
        number = 0;
        hard = false;
    }



    //Imageviewheart1.setinvisibility
    public void imageViewModify()
    {
        switch (life)
        {
            case 4:
                imageViewHeart5.setImageResource(R.drawable.heart1);
                break;

            case 3:
                imageViewHeart4.setImageResource(R.drawable.heart1);
                break;

            case 2:
                imageViewHeart3.setImageResource(R.drawable.heart1);
                break;

            case 1:
                imageViewHeart2.setImageResource(R.drawable.heart1);
                break;

            case 0:
                imageViewHeart1.setImageResource(R.drawable.heart1);
                alertGameOver.show();
                break;

        }

    }
    private void newGame()
    {
        imageViewHeart5.setImageResource(R.drawable.heart2);
        imageViewHeart4.setImageResource(R.drawable.heart2);
        imageViewHeart3.setImageResource(R.drawable.heart2);
        imageViewHeart2.setImageResource(R.drawable.heart2);
        imageViewHeart1.setImageResource(R.drawable.heart2);
        textViewNumber.setText("0");
        life = 3;
        Random random = new Random();
        randomNumber = random.nextInt(11);
        number = 0;
        alertGameOver.setTitle("Játék Vége") .create();
        easy();
    }
}