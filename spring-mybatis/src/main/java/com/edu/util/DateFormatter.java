package com.edu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


public class DateFormatter extends org.springframework.format.datetime.DateFormatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatter.class);
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if (isNumber(text)){
            try {
                return new Date(Long.valueOf(text));
            }catch (Exception e){
                LOGGER.error("日期转换格式出错",e.getMessage());
            }
        }
        return super.parse(text, locale);
    }
    private boolean isNumber(String text){
        if (text==null||text.length()<=0){
            return false;
        }
        for (int i=0;i<text.length();i++){
            if (text.charAt(i)>'9'||text.charAt(i)<'0'){
                return false;
            }
        }
        return true;
    }
}
