package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

public class Login {


    public static class Request {
        @SerializedName("email")
        public String email;

        @SerializedName("password")
        public String password;

        public Request(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static class Response {
        @SerializedName("userId")
        public String userId;

        @SerializedName("token")
        public String token;
    }
}
