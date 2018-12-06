package org.lendingtree.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EncryptionTools {
    private static final String ENCRYPTION_FILEPATH = ".\\encryption_data.txt";
    private static final int HASH = 0;
    private static final int SALT = 1;

    private static String encryptionData[] = getHashFromFile();

    private static byte[] computeHash(String passwordCandidate) throws Exception {
        java.security.MessageDigest messageDigest;
        messageDigest = java.security.MessageDigest.getInstance(encryptionData[HASH]);
        messageDigest.reset();
        messageDigest.update(passwordCandidate.getBytes());
        return messageDigest.digest();
    }

    private static String byteArrayToHexString(byte[] plainPassword) {
        StringBuffer hash = new StringBuffer(plainPassword.length * 2);
        for (int i = 0; i < plainPassword.length; i++) {
            int v = plainPassword[i] & 0xff;
            if (v < 16) {
                hash.append(encryptionData[SALT]);
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

    private static String[] getHashFromFile(){
        BufferedReader bufferedReader;
        List<String> lines = new ArrayList<String>();
        String line;
        String linesFromFile[] = new String[2];
        try{
            int stringArrayPosition = 0;
            bufferedReader = new BufferedReader(new FileReader(ENCRYPTION_FILEPATH));
            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                linesFromFile[stringArrayPosition] = line;
                stringArrayPosition++;
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return linesFromFile;
    }
}