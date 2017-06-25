package com.example.pedrodemirandaarthur.guessinggame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pedrodemirandaarthur on 6/17/17.
 */

public class GameActivity extends AppCompatActivity {

    TextView clue, hideWord, guessedLetters;
    Button btnTry;
    EditText inputLetter;
    String clueWord, word, guessingLetter;
    char[] arrayWord, arrayHideWord;
    ArrayList<String> arrayGuessedLetters = new ArrayList<String>();

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
        guessedLetters = (TextView) findViewById(R.id.guessedLetters);

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
        Boolean thereIs = false;
        for(int i = 0; i < arrayWord.length; i++){
            if(arrayWord[i] == charLetter){
                arrayHideWord[i] = charLetter;
                thereIs = true;
            }
        }
        for(int j = 0; j < arrayHideWord.length; j++){
            newHideWord += arrayHideWord[j];
        }
        if(!thereIs){
            if(!hasLetter(letter)){
                arrayGuessedLetters.add(letter);
            }
            guessedLetters.setText(ReturnGuessedLetters());
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

    public String ReturnGuessedLetters(){
        String guessedLetters = "";
        for(int i = 0; i < arrayGuessedLetters.size(); i++){
            guessedLetters = guessedLetters + arrayGuessedLetters.get(i) + " ";
        }
        if(arrayGuessedLetters.size() == 5){
            new AlertDialog.Builder(this)
                    .setTitle("Game Over!")
                    .setMessage("Do you want to play again?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            MainActivity activity = new MainActivity();
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                        }})
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            GameActivity.this.finish();
                            System.exit(0);
                        }});
        }
        return guessedLetters;
    }

    public Boolean hasLetter(String letter){
        Boolean value = false;
        for(int i=0; i < arrayGuessedLetters.size(); i++){
            if(arrayGuessedLetters.get(i).equals(letter)){
                value = true;
            }
        }
        return value;
    }

}
