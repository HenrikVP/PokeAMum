package com.example.pokeamum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    List<Player> players;
    int turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        players = (List<Player>) getIntent().getSerializableExtra("Players");
        //first turn
        Collections.shuffle(players);
        for (Player player: players) doTurn(player);
    }

    void doTurn(Player player) {
        ((LinearLayout)findViewById(R.id.ll_start_turn)).setVisibility(View.VISIBLE);
        Button startButton = findViewById(R.id.btn_start_turn);
        startButton.setText(player.name);
        startButton.setOnClickListener(view -> {
            ((LinearLayout) findViewById(R.id.ll_start_turn)).setVisibility(View.GONE);
            //Player select card
            showPlayedCards(player);
            showPlayableCards(player);
        });


        //Show played cards
        //Show up to 3 playable cards
        //Playable cards is clickable
        //After all players have played card, the best wins
        //The winner collects all the cards and start next turn
    }

    void showPlayableCards(Player player) {
        for (int i = 0; i < 3; i++) {
            LinearLayout parent = (LinearLayout) findViewById(R.id.ll_playable_cards);
            ImageView cardImage = (ImageView) getLayoutInflater().inflate(R.layout.card, null);
            parent.addView(cardImage);
            Picasso.get().load(player.cards.get(i).image + "/high.jpg").into(cardImage);

            final int nr = i;
            cardImage.setOnClickListener(view -> {
                player.playedCard = player.cards.get(nr);
                Toast.makeText(this,"Playing " + player.cards.get(nr).name,
                        Toast.LENGTH_LONG).show();
                cardImage.setVisibility(View.GONE);
                showPlayedCards(player);

            });
        }
    }

    void showPlayedCards(Player player) {
        for (Player p : players) {
            //Show played card
            if (p.playedCard != null) {
                LinearLayout item = (LinearLayout) findViewById(R.id.ll_played_cards);
                ImageView cardImage = (ImageView) getLayoutInflater().inflate(R.layout.card, null);
                item.addView(cardImage);
                Picasso.get().load(p.playedCard.image + "/high.jpg").into(cardImage);

                if (p == player) {
                    cardImage.setOnClickListener(view -> showPlayableCards(player));
                }
            }
        }
    }
}
//
//    View[] tiles = new ImageView[9];
//    // get reference to LayoutInflater
//    LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//for(int i = 0; i<tiles.length; i++) {
//        //Creating copy of imageview by inflating it
//        ImageView image = (ImageView) inflater.inflate(R.layout.singleimage, null);
//        tiles[i] = image;
//        tiles[i].setId(i);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, 345);
//        params.leftMargin = 32*2*3;
//        params.topMargin = 34*2*3;
//        layout.addView(tiles[i]);
//        }