package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button reset,b1,b2,b3,b4,b5,b6,b7,b8,b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Boolean[] turn = {false};

        reset = findViewById(R.id.btnreset);
        b1 = findViewById(R.id.button11);
        b2 = findViewById(R.id.button12);
        b3 = findViewById(R.id.button13);
        b4 = findViewById(R.id.button14);
        b5 = findViewById(R.id.button15);
        b6 = findViewById(R.id.button16);
        b7 = findViewById(R.id.button17);
        b8 = findViewById(R.id.button18);
        b9 = findViewById(R.id.button19);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFunc();
            }
        });

        View.OnClickListener object = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button click = (Button) view;
                String buttonText = click.getText().toString();
                if (!turn[0]) {
                    if (buttonText.equals("-")) {
                        click.setText("X");
                    }
                } else {
                    if (buttonText.equals("-")) {
                        click.setText("O");
                    }
                }
                turn[0] = !turn[0];

                boolean check = checkWinCondition();
                if (check == false) {
                    checkDrawCondition();
                }
            }
        };

        b1.setOnClickListener(object);
        b2.setOnClickListener(object);
        b3.setOnClickListener(object);
        b4.setOnClickListener(object);
        b5.setOnClickListener(object);
        b6.setOnClickListener(object);
        b7.setOnClickListener(object);
        b8.setOnClickListener(object);
        b9.setOnClickListener(object);

    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void resetFunc() {
        b1.setText("-");
        b2.setText("-");
        b3.setText("-");
        b4.setText("-");
        b5.setText("-");
        b6.setText("-");
        b7.setText("-");
        b8.setText("-");
        b9.setText("-");
    }
    public boolean checkWinCondition() {

        boolean check = false;
        if(b1.getText().equals("X") && b2.getText().equals("X") && b3.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b4.getText().equals("X") && b5.getText().equals("X") && b6.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b7.getText().equals("X") && b8.getText().equals("X") && b9.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b1.getText().equals("X") && b4.getText().equals("X") && b7.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b2.getText().equals("X") && b5.getText().equals("X") && b8.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b3.getText().equals("X") && b6.getText().equals("X") && b9.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b1.getText().equals("X") && b5.getText().equals("X") && b9.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b3.getText().equals("X") && b5.getText().equals("X") && b7.getText().equals("X")) {
            showToast("X Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(b1.getText().equals("O") && b2.getText().equals("O") && b3.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b4.getText().equals("O") && b5.getText().equals("O") && b6.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b7.getText().equals("O") && b8.getText().equals("O") && b9.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b1.getText().equals("O") && b4.getText().equals("O") && b7.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b2.getText().equals("O") && b5.getText().equals("O") && b8.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b3.getText().equals("O") && b6.getText().equals("O") && b9.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b1.getText().equals("O") && b5.getText().equals("O") && b9.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        if(b3.getText().equals("O") && b5.getText().equals("O") && b7.getText().equals("O")) {
            showToast("O Wins");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
            check = true;
        }
        return check;
    }

    public void checkDrawCondition() {
        if (!b1.getText().equals("-") && !b2.getText().equals("-") && !b3.getText().equals("-") && !b4.getText().equals("-") && !b5.getText().equals("-") && !b6.getText().equals("-") && !b7.getText().equals("-") && !b8.getText().equals("-") && !b9.getText().equals("-")){
            showToast("It's a draw");
            new Handler().postDelayed(() -> { resetFunc(); }, 1000);
        }
    }
}