package net.zhoubian.app.util;

public class ParameterUtils {

    public static String getBaseUrl() {
        String port = System.getProperty("http.port");
        if (port == null) {
            port = "8080";
        }
        return "http://localhost:"+port+"/zhoubian";
    }
}
