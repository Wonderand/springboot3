package top.hu.test.spring6_test.util;

import java.util.HashMap;
import java.util.Map;

public class JWTUsers {

    private static final Map<String,String> USERS = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            USERS.put("admin"+i,"password"+1);
        }
    }

    public static boolean isLogin(String username, String password){
        if (null == username||username.trim().length() == 0){
            return false;
        }
        String obj = USERS.get(username);

        if (null == obj ||!obj.equals(password)){
            return false;
        }
        return true;
    }
}
