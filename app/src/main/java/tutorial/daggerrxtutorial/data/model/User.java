package tutorial.daggerrxtutorial.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lorence on 29/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class User implements Parcelable {

    public String login;
    public long id;
    public String url;
    public String email;

    public User() {}

    protected User(Parcel in) {
        login = in.readString();
        id = in.readLong();
        url = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeLong(id);
        dest.writeString(url);
        dest.writeString(email);
    }
}
