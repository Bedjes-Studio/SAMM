package UQAC.Mobile.SAMM.API.APIPojo;

import com.google.gson.annotations.SerializedName;

public class DeleteSelector {

    public static class Request {

        @SerializedName("id")
        public String id;

        public Request(String id) {
            this.id = id;
        }
    }

    // TODO : add response to read deletion results
}
