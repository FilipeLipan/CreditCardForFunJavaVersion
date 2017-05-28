package com.example.lispa.creditcardforfunjavaversion;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.creditcarddesign.CreditCardView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by lispaCre on 28/05/2017.
 */

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.ViewHolder> {

    private List<CreditCard> mCreditCards;
    private Context context;

    public CreditCardAdapter(List<CreditCard> mCreditCards, Context context) {
        this.mCreditCards = mCreditCards;
        this.context = context;
    }

    public void swapCreditCards(List<CreditCard> creditCards){
        mCreditCards = creditCards;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(mCreditCards.get(position));
    }

    @Override
    public int getItemCount() {
        if(mCreditCards != null){
            return mCreditCards.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CreditCardView creditCardView;
        FloatingActionButton removeFab;
        public ViewHolder(View itemView) {
            super(itemView);

            creditCardView = (CreditCardView) itemView.findViewById(R.id.cardView_cv);
            removeFab = (FloatingActionButton) itemView.findViewById(R.id.remove_fab);

        }

        public void onBind(final CreditCard creditCard){
            creditCardView.setCVV(creditCard.getCvv());
            creditCardView.setCardHolderName(creditCard.getCardHolderName());
            creditCardView.setCardExpiry(creditCard.getExpiry());
            creditCardView.setCardNumber(creditCard.getCardNumber());

            removeFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCreditCards.remove(mCreditCards.remove(getLayoutPosition()));
                    notifyItemRemoved(getLayoutPosition());
                }
            });
        }
    }
}
