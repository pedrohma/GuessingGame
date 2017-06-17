package com.example.pedrodemirandaarthur.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by pedrodemirandaarthur on 6/17/17.
 */

public class GameActivity extends AppCompatActivity {

    TextView clue, hideWord;
    Button btnTry;
    EditText inputLetter;
    String clueWord, word, guessingLetter;
    char[] arrayWord;
    String[] arrayGuessedLetters;

    boolean isOk = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        Intent i = getIntent();

        clue = (TextView) findViewById(R.id.clue);
        hideWord = (TextView) findViewById(R.id.hideWord);
        btnTry = (Button) findViewById(R.id.btnTry);
        inputLetter = (EditText) findViewById(R.id.inputLetter);

        clueWord =  (String) i.getSerializableExtra("clueWord");
        word =  (String) i.getSerializableExtra("word");

        arrayWord = word.toCharArray();
        Log.d("array", "array " + Arrays.toString(arrayWord));

        clue.setText(clueWord);
        hideWord.setText(HideWord(word));

        btnTry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                guessingLetter = inputLetter.getText().toString();

                if(guessingLetter.length() > 1){
                    inputLetter.setError("Just one letter per time.");
                    isOk = false;
                }
                if(guessingLetter.isEmpty()){
                    inputLetter.setError("Can't be empty.");
                    isOk = false;
                }
                if(isOk){

                }
            }
        });
    }

    public String HideWord(String word) {

        String hideWord = "";
        for (int i = 0; i < word.length(); i++) {
            hideWord = hideWord + "_" + " ";
        }
        return hideWord;
    }

}
