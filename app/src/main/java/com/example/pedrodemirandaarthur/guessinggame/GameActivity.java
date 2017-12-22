package com.example.pedrodemirandaarthur.guessinggame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
    boolean youWin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        Intent i = getIntent();

        populateFields(i);

        arrayWord = word.toCharArray();
        arrayHideWord = HideWord(word).toCharArray();

        clue.setText("Clue: " + clueWord);
        hideWord.setText(HideWord(word));

        btnTry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                guessingLetter = inputLetter.getText().toString();

                isOk = validateGuessingLetter(guessingLetter);

                if(isOk){
                    hideWord.setText(checkLetter(guessingLetter));
                    hideWord.setTextColor(Color.parseColor("#007ba7"));
                    inputLetter.setText("");
                    if(youWin()){
                        populateYouWin();
                        endGame();
                    }
                    else{
                        youLose();
                    }
                }
            }
        });
    }

    private void populateYouWin() {
        hideWord.setText("You win the game!");
        inputLetter.setFocusable(false);
        inputLetter.setEnabled(false);
        inputLetter.setCursorVisible(false);
        inputLetter.setKeyListener(null);
        inputLetter.setBackgroundColor(Color.TRANSPARENT);
        btnTry.setClickable(false);
    }

    private boolean validateGuessingLetter(String guessingLetter) {
        boolean isOk = true;
        try{
            if(guessingLetter.length() > 1){
                inputLetter.setError("Just one letter per time.");
                isOk = false;
            }
            if(guessingLetter.isEmpty()){
                inputLetter.setError("Can't be empty.");
                isOk = false;
            }
        }
        catch (Exception ex){
            String error = ex.getMessage();
        }
        return isOk;
    }

    private void populateFields(Intent i) {
        clue = (TextView) findViewById(R.id.clue);
        hideWord = (TextView) findViewById(R.id.hideWord);
        btnTry = (Button) findViewById(R.id.btnTry);
        inputLetter = (EditText) findViewById(R.id.inputLetter);
        guessedLetters = (TextView) findViewById(R.id.guessedLetters);

        clueWord =  (String) i.getSerializableExtra("clueWord");
        word =  (String) i.getSerializableExtra("word");
    }

    public void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You win!")
                .setMessage("Do you want to play again?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity mainActivity = new MainActivity();
                        Intent intent = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        returnHomeView();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void youLose(){
        if(arrayGuessedLetters.size() >= 5){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game Over!")
                    .setMessage("Do you want to play again?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity mainActivity = new MainActivity();
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    returnHomeView();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public String checkLetter(String letter){
        char charLetter = letter.charAt(0);
        String newHideWord = "";
        Boolean thereIs = false;
        for(int i = 0; i < arrayWord.length; i++){
            if(arrayWord[i] == charLetter){
                if(i == 0){
                    arrayHideWord[i] = charLetter;
                }
                else{
                    arrayHideWord[i * 2] = charLetter;
                }
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
        for(int i = 0; i < arrayGuessedLetters.size(); i++) {
            guessedLetters = guessedLetters + arrayGuessedLetters.get(i) + " ";
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

    public Boolean youWin(){
        int win = 0;
        for(int i = 0; i < arrayHideWord.length; i++){
            if(arrayHideWord[i] == new String("_").charAt(0)){
                win += 1;
            }
        }
        if(win < 1){
            youWin = true;
        }
        return youWin;
    }

    public void returnHomeView(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
