package com.jenson.service.common.system;

public class ShareChar {

    private static String uploadPath;

    public static void setUploadPath(String value){
        uploadPath = value;
    }

    public static String getUploadPath(){
        return uploadPath;
    }

}
