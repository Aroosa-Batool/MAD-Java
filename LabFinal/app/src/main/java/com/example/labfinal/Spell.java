package com.example.labfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class Spell extends Activity {
    private EditText editText;
    private TextView errorsTextView;
    private Button saveButton;
    private TextView enter;
    private TextView error;

    private boolean isCheckingSpelling = false;
    private ArrayList<String> dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        editText = findViewById(R.id.editText);
        errorsTextView = findViewById(R.id.textView);
        saveButton = findViewById(R.id.btn);
        error=findViewById(R.id.tvErr);
        enter=findViewById(R.id.tvEnter);

        // Load the dictionary.txt file from the resources
        dictionary = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.my_dictionary);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Listen for changes in the EditText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isCheckingSpelling) {
                    startSpellingCheck();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Listen for clicks on the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                String fileName = "Text-" + System.currentTimeMillis() + ".txt";
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE));
                    outputStreamWriter.write(text);
                    outputStreamWriter.close();
                    Toast.makeText(getApplicationContext(), "Text saved to " + fileName, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void startSpellingCheck() {
        isCheckingSpelling = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String text = editText.getText().toString().toLowerCase();
                String[] words = text.split("\\s+");
                StringBuilder errors = new StringBuilder();
                for (String word : words) {
                    if (!dictionary.contains(word)) {
                        errors.append(word).append(" ");
                    }
                }
                if (errors.length() > 0) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            errorsTextView.setText(errors.toString());
                        }
                    });
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            errorsTextView.setText("");
                        }
                    });
                }
                isCheckingSpelling = false;
            }
        }).start();
    }
}