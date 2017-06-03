package com.example.pedrodemirandaarthur.guessinggame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private EditText clueWordEditText;
    private EditText wordEditText;
    String clueWord, word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clueWordEditText = (EditText) findViewById(R.id.clueWord);
        wordEditText = (EditText) findViewById(R.id.word);
        btnStart = (Button) findViewById(R.id.start);

    }


    public String HideWord(String word) {

        String hideWord = "";
        for (int i = 0; i < word.length(); i++) {
            hideWord = "_" + " ";
        }
        return hideWord;
    }

    
    public void onClick(View view) {

        boolean isFulfill = true;
        clueWord = clueWordEditText.getText().toString();
        word = wordEditText.getText().toString();

        if(clueWord.isEmpty() || clueWord.equals("")){
            clueWordEditText.setError("Clue Word can't be blank.");
            isFulfill = false;
        }
        if(word.isEmpty() || word.equals("")) {
            clueWordEditText.setError("Clue Word can't be blank.");
            isFulfill = false;
        }

    }
}
