/*
 * Copyright 2022 - Hugo LANGLAIS & Alexia LACOMBE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package UQAC.Mobile.SAMM.API.APIPojo;

import com.google.gson.annotations.SerializedName;

/**
 * POJO class for signup requests
 */
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
