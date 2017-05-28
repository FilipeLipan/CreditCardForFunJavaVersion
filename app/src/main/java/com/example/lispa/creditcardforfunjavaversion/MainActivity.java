package com.example.lispa.creditcardforfunjavaversion;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int GET_NEW_CARD = 2;

    public static String KEY_CREDITCARDS = "CREDITCARDS";

    private ArrayList<CreditCard> mCreditCards = new ArrayList<CreditCard>();
    private CreditCardView mCardView;

    private CreditCardAdapter mCreditCardAdapter;
    private RecyclerView mRecyclerView;

    FloatingActionButton mAddFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(KEY_CREDITCARDS)){
                mCreditCards = savedInstanceState.getParcelableArrayList(KEY_CREDITCARDS);
            }
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_rv);
        mAddFab = (FloatingActionButton) findViewById(R.id.add_fab);
        mCreditCardAdapter = new CreditCardAdapter( mCreditCards, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mCreditCardAdapter);


        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardEditActivity.class);
                startActivityForResult(intent, GET_NEW_CARD);
            }
        });
    }

    public void addCreditCard(CreditCard creditCard){
        mCreditCards.add(creditCard);
        mCreditCardAdapter.swapCreditCards(mCreditCards);
    }


    public void onActivityResult(int reqCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && reqCode == GET_NEW_CARD) {

            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);

            addCreditCard(new CreditCard(cardHolderName, cardNumber, expiry, cvv));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_CREDITCARDS, mCreditCards);
    }
}
