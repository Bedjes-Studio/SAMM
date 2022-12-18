package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

public class Signup {
    public static class Request {
        @SerializedName("name")
        public String name;

        @SerializedName("email")
        public String email;

        @SerializedName("password")
        public String password;

        public Request(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }

    public static class Response {
        @SerializedName("message")
        public String message;
    }
}
