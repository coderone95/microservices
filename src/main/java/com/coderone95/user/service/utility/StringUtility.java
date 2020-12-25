package com.coderone95.user.service.utility;

public class StringUtility {

    public static Boolean isEmptyOrNull(String val){
        if(val == null || val == ""){
            return true;
        }
        return false;
    }
    public static Boolean isNotNullAndBlank(String val){
        if(val != null && val != ""){
            return true;
        }
        return false;
    }
}
