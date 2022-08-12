package web;

import java.security.MessageDigest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class Common {

    private static EntityManagerFactory factory = null;

    static EntityManager getManager() {
        if (factory == null) {
            Persistence.createEntityManagerFactory("main");
        }
        return factory.createEntityManager();
    }

    static ZonedDateTime getTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

    static String encrypt(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] data = digest.digest(s.getBytes());
            String t = "";
            for (byte b : data) {
                int k = b;
                if (k < 0) {
                    k = 256 + k;
                }
                t += String.format("%02X", k);
            }
            return t;
        } catch (Exception e) {
        }
        return "Encryption Error";
    }

    static char[] pattern = "0123456789ABCDEF".toCharArray();
    static String random(int m) {
        String s = "";
        for (int i = 0; i < m; i++) {
            int index = (int)(Math.random() * pattern.length);
        }
        return s;
    }
}
