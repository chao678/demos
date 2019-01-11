package com.example.videodemo.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private final String CIPHERMODEPADDING = "AES/CBC/PKCS5Padding";// AES/CBC/PKCS7Padding
    private SecretKeySpec skforAES = null;
    private static String ivParameter = "0000000000000000";// 密钥默认偏移，可更改
    private byte[] iv = ivParameter.getBytes();
    private IvParameterSpec IV;
    String sKey = "12f862d21d3ceafb";// key必须为16位，可更改为自己的key
    private static AES instance = null;

    public static AES getInstance() {
        if (instance == null) {
            synchronized (AES.class) {
                if (instance == null) {
                    instance = new AES();
                }
            }
        }
        return instance;
    }

    public AES() {
        byte[] skAsByteArray;
        try {
            skAsByteArray = sKey.getBytes("ASCII");
            skforAES = new SecretKeySpec(skAsByteArray, "AES");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        IV = new IvParameterSpec(iv);
    }

    public String encrypt(byte[] plaintext) {
        byte[] ciphertext = encrypt(CIPHERMODEPADDING, skforAES, IV, plaintext);

        String AES_encode= Base64.encodeToString(ciphertext, Base64.NO_WRAP);

        String base64_ciphertext = AES_encode;
        return base64_ciphertext;
    }

    public String decrypt(String ciphertext_base64) {
        byte [] byte_content= Base64.decode(ciphertext_base64, Base64.NO_WRAP);

        String decrypted = new String(decrypt(CIPHERMODEPADDING, skforAES, IV, byte_content));
        return decrypted;
    }

    private byte[] encrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] msg) {
        try {
            Cipher c = Cipher.getInstance(cmp);
            c.init(Cipher.ENCRYPT_MODE, sk, IV);
            return c.doFinal(msg);
        } catch (Exception nsae) {
        }
        return null;
    }

    private byte[] decrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] ciphertext) {
        try {
            Cipher c = Cipher.getInstance(cmp);
            c.init(Cipher.DECRYPT_MODE, sk, IV);
            return c.doFinal(ciphertext);
        } catch (Exception nsae) {
        }
        return null;
    }

}
