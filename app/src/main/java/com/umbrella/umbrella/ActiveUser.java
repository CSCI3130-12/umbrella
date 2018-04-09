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

    /**
     * A constructor that takes a Parcel and converts it into the ActiveUser object
     * @param in A parcel version of ActiveUser
     */
    public ActiveUser(Parcel in) {
        super.setUsername(in.readString());
        super.setPassword(in.readString());
        this.token = in.readString();
    }

    /**
     * A constructor to create the user with specific data
     * @param user The User's username
     * @param pass The User's password
     * @param token A secure auth token
     */
    public ActiveUser(String user, String pass, String token) {
        super(user, pass);
        this.token = token;
    }

    /**
     * Describes the contents of the parcel
     * @return Zero.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write the object to Parcel form
     * @param parcel The Parcel to write to
     * @param i An arbitrary integer that does nothing
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(super.getUsername());
        parcel.writeString(super.getPassword());
        parcel.writeString(token);
    }

    /**
     * The Parcel creator
     */
    public static final Creator<ActiveUser> CREATOR = new Creator<ActiveUser>() {
        /**
         * Creates a new ActiveUser from a parcel
         * @param parcel The received Parcel
         * @return A new ActiveUser object with the Parcel information
         */
        @Override
        public ActiveUser createFromParcel(Parcel parcel) {
            return new ActiveUser(parcel);
        }

        /**
         * Creates an empty array of ActiveUsers
         * @param i The size of the empty array
         * @return The empty array
         */
        @Override
        public ActiveUser[] newArray(int i) {
            return new ActiveUser[0];
        }
    };

}
