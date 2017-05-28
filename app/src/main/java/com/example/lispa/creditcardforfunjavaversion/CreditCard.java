package com.example.lispa.creditcardforfunjavaversion;

import android.os.Parcel;
import android.os.Parcelable;

import com.cooltechworks.creditcarddesign.CreditCardUtils;

/**
 * Created by lispa on 28/05/2017.
 */

public class CreditCard implements Parcelable {

    private String cardHolderName;
    private String cardNumber;
    private String expiry;
    private String cvv;

    public String getCardHolderName() {
        return cardHolderName;
    }

    public CreditCard(String cardHolderName, String cardNumber, String expiry, String cvv) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
    }



    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardHolderName);
        dest.writeString(this.cardNumber);
        dest.writeString(this.expiry);
        dest.writeString(this.cvv);
    }

    protected CreditCard(Parcel in) {
        this.cardHolderName = in.readString();
        this.cardNumber = in.readString();
        this.expiry = in.readString();
        this.cvv = in.readString();
    }

    public static final Parcelable.Creator<CreditCard> CREATOR = new Parcelable.Creator<CreditCard>() {
        @Override
        public CreditCard createFromParcel(Parcel source) {
            return new CreditCard(source);
        }

        @Override
        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };
}
