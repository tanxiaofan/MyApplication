package com.example.framework.encrypt;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA {
  public static final String publicKey =
      "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApvmAVZKELE9aHWnx4C4fyJ4Izv0FeMqCVh+JfraQw6oPWkbkiHU8pTzwCC977MmltcBeWfrYA8cEQpl9iRBv1rV/719jhRcstFs9B8hbgLNogADdJeoM6JnaBn2v0DWhHJ1UNNMkJ6tcddcxscYlPsMb5tj3ECOwQKYsomKM9zeX9DWOPeFmw7V+ilSdsWRTjwtPbWOa1Sg1BEBBx079eBdzqlF2P/iZDMhnDFnX6nuyco7I7S60q5jzorDH6ZVu3yhFbzwgI7QD+CPMBNJQhw03+udcUJ52VLuehu3AwaQyKJT0hCtTkpoeINNnzhWj33ufcWLQoRsMhv76UeI6mQIDAQAB";

  static int type = 64; // 64: base64，16：16进制
  static KeyPairGenerator keyPairGenerator = null;
  static KeyFactory keyFactory = null;
  static Cipher cipher = null;

  static {
    try {
      keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyFactory = KeyFactory.getInstance("RSA");
      cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //public static class Key {
  //	public String publicKey;
  //	public String privateKey;
  //}
  //
  //public static Key createKey() {
  //	return createKey(null);
  //}
  //
  //public static Key createKey(String dir) {
  //	SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes());
  //	keyPairGenerator.initialize(512, secureRandom);
  //	KeyPair keyPair = keyPairGenerator.generateKeyPair();
  //	PublicKey publicKey = keyPair.getPublic();
  //	PrivateKey privateKey = keyPair.getPrivate();
  //
  //	if (StringUtils.isNotEmpty(dir)) {
  //		File file = new File(StringUtils.contact(dir, "/publicKey"));
  //		FileUtils.writeByteArrayToFileUncheck(file, publicKey.getEncoded(), false);
  //		file = new File(StringUtils.contact(dir, "/privateKey"));
  //		FileUtils.writeByteArrayToFileUncheck(file, privateKey.getEncoded(), false);
  //	}
  //
  //	Key key = new Key();
  //	key.publicKey = bytesToHexStr(publicKey.getEncoded());
  //	key.privateKey = bytesToHexStr(privateKey.getEncoded());
  //	return key;
  //}

  public static String encode(String msg) {
    return encode(msg, publicKey);
  }

  public static String encode(String msg, String pubKey) {
    try {
      byte[] pubKeyBytes = hexStrToBytes(pubKey);

      X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKeyBytes);
      PublicKey publicKey = keyFactory.generatePublic(spec);

      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] bytes = cipher.doFinal(msg.getBytes("utf-8"));
      return bytesToHexStr(bytes);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String decode(String hexMsg, String priKey) {
    try {
      byte[] priKeyBytes = hexStrToBytes(priKey);

      PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(priKeyBytes);
      PrivateKey privateKey = keyFactory.generatePrivate(spec);

      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] bytes = cipher.doFinal(hexStrToBytes(hexMsg));
      return new String(bytes, "utf-8");
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String bytesToHexStr(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    if (bytes == null || bytes.length == 0) {
      return null;
    }

    if (type == 64) {
      byte[] byteContent = Base64.encode(bytes, Base64.DEFAULT);
      try {
        return new String(byteContent, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }

    String hv = null;
    for (int i = 0; i < bytes.length; i++) {
      hv = Integer.toHexString(bytes[i] & 0xFF);
      if (hv.length() == 1) {
        sb.append(0);
      }
      sb.append(hv);
    }
    return sb.toString();
  }

  public static byte[] hexStrToBytes(String hexString) {
    if (hexString == null || "".equals(hexString)) {
      return null;
    }
    if (type == 64) {
      return Base64.decode(hexString, Base64.DEFAULT);
    }

    String hex = "0123456789ABCDEF";
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toUpperCase().toCharArray();
    byte[] bytes = new byte[length];
    int pos = -1;
    for (int i = 0; i < length; i++) {
      pos = i * 2;
      bytes[i] = (byte) (hex.indexOf(hexChars[pos]) << 4 | hex.indexOf(hexChars[pos + 1]));
    }
    return bytes;
  }
}
