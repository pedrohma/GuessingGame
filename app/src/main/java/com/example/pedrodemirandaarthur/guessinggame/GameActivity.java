package com.example.pedrodemirandaarthur.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by pedrodemirandaarthur on 6/17/17.
 */

public class GameActivity extends AppCompatActivity {

    TextView clue, hideWord;
    Button btnTry;
    EditText inputLetter;
    String clueWord, word, guessingLetter;
    char[] arrayWord, arrayHideWord;
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
        arrayHideWord = HideWord(word).toCharArray();

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
                    hideWord.setText(checkLetter(guessingLetter));
                    inputLetter.setText("");
                }
            }
        });
    }

    public String checkLetter(String letter){
        char charLetter = letter.charAt(0);
        String newHideWord = "";
        for(int i = 0; i < arrayWord.length; i++){
            if(arrayWord[i] == charLetter){
                arrayHideWord[i] = charLetter;
            }
        }
        for(int j = 0; j < arrayHideWord.length; j++){
            newHideWord = newHideWord + arrayHideWord[j];
        }
        return newHideWord;
    }

    public String HideWord(String word) {

        String hideWord = "";
        for (int i = 0; i < word.length(); i++) {
            hideWord = hideWord + "_" + " ";
        }
        return hideWord;
    }

}
