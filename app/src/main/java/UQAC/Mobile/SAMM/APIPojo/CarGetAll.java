package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

public class CarGetAll {
    public static class Response {

        @SerializedName("_id")
        public String id;

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("year")
        public int year;

        @SerializedName("ownerId")
        public String ownerId;

        @SerializedName("guestsId")
        public String guestsId[];

        @SerializedName("specsId")
        public String specsId;

        @SerializedName("__v")
        public String version;


        public Response(int mileage, int year) {
            this.mileage = mileage;
            this.year = year;
        }
    }
}
