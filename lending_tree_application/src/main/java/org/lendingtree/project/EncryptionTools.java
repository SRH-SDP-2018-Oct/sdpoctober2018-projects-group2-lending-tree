package org.lendingtree.project;

public class EncryptionTools {

    private static byte[] computeHash(String passwordCandidate) throws Exception {
        java.security.MessageDigest messageDigest = null;
        messageDigest = java.security.MessageDigest.getInstance("SHA-1");
        messageDigest.reset();
        messageDigest.update(passwordCandidate.getBytes());
        return messageDigest.digest();
    }

    private static String byteArrayToHexString(byte[] plainPassword) {
        StringBuffer hash = new StringBuffer(plainPassword.length * 2);
        for (int i = 0; i < plainPassword.length; i++) {
            int v = plainPassword[i] & 0xff;
            if (v < 16) {
                hash.append('0');
            }
            hash.append(Integer.toHexString(v));
        }
        return hash.toString().toUpperCase();
    }

    public static String encryptPassword(String plainPassword) {
        String encryptedPassword = "";
        try {
            encryptedPassword = byteArrayToHexString(computeHash(plainPassword));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while encrypting password");
        }
        return encryptedPassword;
    }
}