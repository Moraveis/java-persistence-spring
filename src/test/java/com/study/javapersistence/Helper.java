package com.study.javapersistence;

import java.util.Date;

public class Helper {

    static Date tomorrow() {
        return new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
    }
}
