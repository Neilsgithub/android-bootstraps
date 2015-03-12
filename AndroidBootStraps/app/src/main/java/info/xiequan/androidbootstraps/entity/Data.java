package info.xiequan.androidbootstraps.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by spark on 31/1/15.
 * www.blueowls.net
 * i@xiequan.info
 */
public class Data implements Parcelable {
    @SerializedName("RecipeName")
    private String RecipeName;

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.RecipeName);
    }

    public Data() {
    }

    private Data(Parcel in) {
        this.RecipeName = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
