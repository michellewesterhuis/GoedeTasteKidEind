package nl.tschout.tastekid;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultItem implements Parcelable {
    private final String mName, mType;

    public ResultItem(String name, String type) {
        mName = name;
        mType = type;
    }

    public ResultItem(Parcel in) {
        mName = in.readString();
        mType = in.readString();
    }

    public static final Parcelable.Creator<ResultItem> CREATOR =
            new Parcelable.Creator<ResultItem>() {
        @Override
        public ResultItem createFromParcel(Parcel in) {
            return new ResultItem(in);
        }

        @Override
        public ResultItem[] newArray(int size) {
            return new ResultItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mName);
        out.writeString(mType);
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }
}