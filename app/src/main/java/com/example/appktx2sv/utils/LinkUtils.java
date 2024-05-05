package com.example.appktx2sv.utils;

import com.example.appktx2sv.net.RetrofitClient;

public class LinkUtils {
    public static String GetResLink(Integer idResource){
        String url = RetrofitClient.GI().baseUrl + "/api/v1/res/" + idResource.toString();
        return  url;
    }
}
