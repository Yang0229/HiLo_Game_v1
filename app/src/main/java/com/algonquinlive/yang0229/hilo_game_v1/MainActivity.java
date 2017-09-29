
/**
 *     App Name : HiLo Game
 *  Description : This id an Android Application about number guessing game.
 *                It will random a number between 1 and 1000, user get 10 chance to guess the random number.
 *                The guess times and remain times will be displayed on the screen.
 *                User can get 2 kind of according to how many times that you used.
 *     @author  : BO YANG ( yang0229@algonquinlive.com )
 */



package com.algonquinlive.yang0229.hilo_game_v1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static final String ABOUT_DIALOG_TAG = "About Dialog";

    EditText guess_number;
    TextView times_used;
    TextView times_remain;
    int int_guess_number;
    int int_times_used = 0;
    int int_times_remain = 10;
    int int_correct_number = 20;
    Button guess_button;
    Button reset_button;


//  onCreate Menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guess_number = (EditText) findViewById(R.id.id_guess_number);
        guess_button = (Button) findViewById(R.id.id_guess_button);
        reset_button = (Button) findViewById(R.id.id_reset_button);
        times_used = (TextView) findViewById(R.id.id_times_used_number);
        times_remain = (TextView) findViewById(R.id.id_times_remain_number);
    }


    public void reset_click(View view) {
        int_times_used = 0;
        int_times_remain = 10;
        times_used.setText(new Integer(int_times_used).toString());
        times_remain.setText(new Integer(int_times_remain).toString());
        Random rand = new Random();
        int_correct_number = rand.nextInt(1000) + 1;

    }


    public void guess_click(View view) {

        if (int_times_used < 10) {
            if (!validation()) {
                if (int_times_used <= 5) {
                    Toast.makeText(MainActivity.this, "Superior win!", Toast.LENGTH_LONG).show();
                    int_times_used += 1;
                    int_times_remain -= 1;
                }

                if (int_times_used > 5) {
                    Toast.makeText(MainActivity.this, "Excellent win!", Toast.LENGTH_LONG).show();
                    int_times_used += 1;
                    int_times_remain -= 1;
                }

            }
            times_used.setText(new Integer(int_times_used).toString());
            times_remain.setText(new Integer(int_times_remain).toString());
        } else {
            Toast.makeText(MainActivity.this, "Sorry! Your have already run out all chances, please reset a new game", Toast.LENGTH_LONG).show();
        }
    }



    private boolean validation() {

        try {
            int_guess_number = Integer.parseInt(guess_number.getText().toString());
            if (int_guess_number < int_correct_number && int_guess_number >= 1) {
                Toast.makeText(MainActivity.this, "Your number too low", Toast.LENGTH_SHORT).show();
                int_times_used += 1;
                int_times_remain -=1;
                return true;
            }

            if (int_guess_number > int_correct_number && int_guess_number <= 1000) {
                Toast.makeText(MainActivity.this, "Your number too high", Toast.LENGTH_SHORT).show();
                int_times_used += 1;
                int_times_remain -=1;
                return true;
            }

            if (int_guess_number < 1) {
                Toast.makeText(MainActivity.this, "Opps! Your number must be (1-1000)", Toast.LENGTH_SHORT).show();
                int_times_used += 1;
                int_times_remain -=1;
                return true;
            }

            if (int_guess_number > 1000) {
                Toast.makeText(MainActivity.this, "Opps! Your number must be (1-1000)", Toast.LENGTH_SHORT).show();
                int_times_used += 1;
                int_times_remain -=1;
                return true;
            }



        }catch (Exception e ){
            guess_number.setError("Opps! Please enter number (1-1000)");
            guess_number.requestFocus();
            return true;
        }
        return false;
    }
}


