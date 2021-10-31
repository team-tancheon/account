package com.tancheon.account.utils;

import java.util.UUID;

public class KeyProvider {

    public static String createKey() {

        return UUID.randomUUID().toString().replace("-", "");

    }

}
