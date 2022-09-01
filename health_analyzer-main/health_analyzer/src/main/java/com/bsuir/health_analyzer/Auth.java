package com.bsuir.health_analyzer;

import org.json.JSONObject;

public class Auth {
    static public boolean Verify(JSONObject body) {
        String login = body.getString("login");
        String password = body.getString("password");
        return login == "admin" && password == "admin";
    }
}
