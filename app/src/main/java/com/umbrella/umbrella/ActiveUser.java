package com.umbrella.umbrella;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by justin on 22/02/18.
 */

public class ActiveUser extends User implements Parcelable{
    private String token;

    public ActiveUser(Parcel in){

    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){

    }

    public static final Creator<ActiveUser> CREATOR = new Creator<ActiveUser>() {
        @Override
        public ActiveUser createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public ActiveUser[] newArray(int i) {
            return new ActiveUser[0];
        }
    };

}
