package com.tancheon.account.utils;

import java.util.Locale;

public class LocaleUtils {

    public static Locale getLocale(String language) {
        if (language.equals(Locale.US.getLanguage()))
            return Locale.US;

        return Locale.KOREA;
    }
}
