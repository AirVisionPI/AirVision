/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

/**
 *
 * @author agavazzi
 */
public class Utils {

    public static void clear() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void sleep(Integer second, String message) {
        try {
            for (int i = second; i > 0; i--) {
                clear();
                if (message.length() >= 1) {
                    System.out.println("\n-----------------------> " + message);
                }
                System.out.println("\n======================= Aguarde: " + i + "... ========================");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }

    public static void sleepSecond(Integer second) {
        try {
            for (int i = second; i > 0; i--) {
                System.out.println("\n======================= Aguarde: " + i + "... ========================");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }

    public static Double converterByteToGigabyte(Double valorByte) {
        Double convertido1 = valorByte / 1024;
        Double convertido2 = convertido1 / 1024;
        Double convertido3 = convertido2 / 1024;

        return convertido3;
    }

    public static Double converterMillisecondsToSeconds(Double milliseconds) {
        Double seconds = (milliseconds / 1000) % 60;

        return seconds;
    }
}
