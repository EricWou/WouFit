package com.example.woufit.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHashing {

    //to hash the password
    public static String hash_SHA_512_Password(String passwordToHash, String salt) {

        String generatedPassword = null;

        try {
            //sets the hashing algorithm for MessageDigest object
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            //update() - adds the bytes of the salt to MessageDigest object
            md.update(salt.getBytes(StandardCharsets.UTF_8));

            //digest() - adds the bytes of the password to MessageDigest and processes
            //the entire object into a final hash (which is held in an array of bytes)
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));

            //converts the bytes object into a hexadecimal String
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found", e);
        }

        return generatedPassword;
    }

    //to compute a random salt to ensure greater password protection
    public static String computeSalt() throws NoSuchAlgorithmException {

        //using a SecureRandom instance to generate random numbers  using SHA1PRNG algorithm
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

        //create an array of bytes and populates it with random bytes
        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        //converts the byte array to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : salt) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    /*
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String passwordToHash = "password";
        String salt = getSalt();

        String securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
        System.out.println(securePassword);
        Log.d("HashPassword", securePassword);
    }
    */
}
