package com.example.jsfdemo.domain;

/**
 * Created by kamil on 23.11.14.
 */

public enum Pogoda {
    SLONECZNA, DESZCZOWA;
    private static Pogoda[] pogody = values();
    public static Pogoda fromInteger(Integer i) {
        return pogody[i - 1];
    }
}
