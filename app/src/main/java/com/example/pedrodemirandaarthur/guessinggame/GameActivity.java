package com.example.pedrodemirandaarthur.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by pedrodemirandaarthur on 6/17/17.
 */

public class GameActivity extends AppCompatActivity {

    TextView clue;

    String clueWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Intent i = getIntent();
        clue = (TextView) findViewById(R.id.clue);

        clueWord =  (String) i.getSerializableExtra("clueWord");

        clue.setText(clueWord);
    }

    public String HideWord(String word) {

        String hideWord = "";
        for (int i = 0; i < word.length(); i++) {
            hideWord = "_" + " ";
        }
        return hideWord;
    }

}
