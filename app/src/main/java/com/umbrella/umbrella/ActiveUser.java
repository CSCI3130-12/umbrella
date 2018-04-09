package com.umbrella.umbrella;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by justin on 22/02/18.
 * A class to store an active user
 * Can be passed using Parcels
 */

public class ActiveUser extends User implements Parcelable {
    private String token;

    public ActiveUser(Parcel in) {
        super.setUsername(in.readString());
        super.setPassword(in.readString());
        this.token = in.readString();
    }

    public ActiveUser(String user, String pass, String token) {
        super(user, pass);
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(super.getUsername());
        parcel.writeString(super.getPassword());
        parcel.writeString(token);
    }

    public static final Creator<ActiveUser> CREATOR = new Creator<ActiveUser>() {
        @Override
        public ActiveUser createFromParcel(Parcel parcel) {
            return new ActiveUser(parcel);
        }

        @Override
        public ActiveUser[] newArray(int i) {
            return new ActiveUser[0];
        }
    };

}
