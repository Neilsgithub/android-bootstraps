package info.xiequan.androidbootstraps.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by spark on 31/1/15.
 * www.blueowls.net
 * i@xiequan.info
 */
public class Data {
    @SerializedName("RecipeName")
    private String RecipeName;

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }
}
