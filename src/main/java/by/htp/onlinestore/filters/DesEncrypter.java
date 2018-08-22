package by.htp.onlinestore.filters;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class DesEncrypter {
//    Cipher ecipher;
//    Cipher dcipher;
//    private SecretKey key = KeyGenerator.getInstance("DES").generateKey();
//    private static volatile DesEncrypter instance;

//    public static DesEncrypter getInstance() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
//        DesEncrypter localInstance = instance;
//        if (localInstance == null) {
//            synchronized (DesEncrypter.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new DesEncrypter();
//                }
//            }
//        }
//        return localInstance;
//    }
//
//    public DesEncrypter() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
//        ecipher = Cipher.getInstance("DES");
//        dcipher = Cipher.getInstance("DES");
//        ecipher.init(Cipher.ENCRYPT_MODE, key);
//        dcipher.init(Cipher.DECRYPT_MODE, key);
//    }
//
//    public String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
//        byte[] utf8 = str.getBytes("UTF8");
//        byte[] enc = ecipher.doFinal(utf8);
//        return new sun.misc.BASE64Encoder().encode(enc);
//    }
//
//    public String decrypt(String str) throws IOException, IllegalBlockSizeException, BadPaddingException {
//        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
//        byte[] utf8 = dcipher.doFinal(dec);
//        return new String(utf8, "UTF8");
//    }
//
}