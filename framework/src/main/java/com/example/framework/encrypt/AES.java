package com.example.framework.encrypt;

/** Created by chaunce on 16/1/5. */
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/** AES 算法 对称加密，密码学中的高级加密标准 2005年成为有效标准 */
public class AES {

  public static final String AESKEY = "1qwer2tyuiopas3dfghjklz4xcv5bnm6";

  //static Cipher cipher;
  //static final String KEY_ALGORITHM = "AES";
  //static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";
  //static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
  ///*
  // * AES/CBC/NoPadding 要求
  // * 密钥必须是16位的；Initialization vector (IV) 必须是16位
  // * 待加密内容的长度必须是16的倍数，如果不是16的倍数，就会出如下异常：
  // * javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes
  // *
  // *  由于固定了位数，所以对于被加密数据有中文的, 加、解密不完整
  // *
  // *  可以看到，在原始数据长度为16的整数n倍时，假如原始数据长度等于16*n，则使用NoPadding时加密后数据长度等于16*n，
  // *  其它情况下加密数据长 度等于16*(n+1)。在不足16的整数倍的情况下，假如原始数据长度等于16*n+m[其中m小于16]，
  // *  除了NoPadding填充之外的任何方 式，加密数据长度都等于16*(n+1).
  // */
  //static final String CIPHER_ALGORITHM_CBC_NoPadding = "AES/CBC/NoPadding";
  //
  //static SecretKey secretKey;
  //
  //public static void main(String[] args) throws Exception {
  //  method1("a*jal)k32J8czx囙国为国宽");
  //  method2("a*jal)k32J8czx囙国为国宽");
  //  method3("a*jal)k32J8czx囙国为国宽");
  //
  //  method4("123456781234囙为国宽");// length = 16
  //  method4("12345678abcdefgh");// length = 16
  //
  //  String content = "test";
  //  String password = "12345678";
  //  //加密
  //  System.out.println("加密前：" + content);
  //  byte[] encryptResult = encrypt(content, password);
  //  String encryptResultStr = parseByte2HexStr(encryptResult);
  //  System.out.println("加密后：" + encryptResultStr);
  //  //解密
  //  byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
  //  byte[] decryptResult = decrypt(decryptFrom, password);
  //  System.out.println("解密后：" + new String(decryptResult));
  //}
  //
  ///**
  // * 使用AES 算法 加密，默认模式  AES/ECB
  // */
  //static void method1(String str) throws Exception {
  //  cipher = Cipher.getInstance(KEY_ALGORITHM);
  //  //KeyGenerator 生成aes算法密钥
  //  secretKey = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
  //
  //  cipher.init(Cipher.ENCRYPT_MODE, secretKey);//使用加密模式初始化 密钥
  //  byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
  //
  //  System.out.println("method1-加密：" + Arrays.toString(encrypt));
  //  cipher.init(Cipher.DECRYPT_MODE, secretKey);//使用解密模式初始化 密钥
  //  byte[] decrypt = cipher.doFinal(encrypt);
  //  System.out.println("method1-解密后：" + new String(decrypt));
  //}
  //
  ///**
  // * 使用AES 算法 加密，默认模式 AES/ECB/PKCS5Padding
  // */
  //static void method2(String str) throws Exception {
  //  cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
  //  //KeyGenerator 生成aes算法密钥
  //  secretKey = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
  //
  //  cipher.init(Cipher.ENCRYPT_MODE, secretKey);//使用加密模式初始化 密钥
  //  byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
  //
  //  System.out.println("method2-加密：" + Arrays.toString(encrypt));
  //  cipher.init(Cipher.DECRYPT_MODE, secretKey);//使用解密模式初始化 密钥
  //  byte[] decrypt = cipher.doFinal(encrypt);
  //  System.out.println("method2-解密后：" + new String(decrypt));
  //}
  //
  //static byte[] getIV() {
  //  String iv = "1234567812345678"; //IV length: must be 16 bytes long
  //  return iv.getBytes("utf-8");
  //}
  //
  ///**
  // * 使用AES 算法 加密，默认模式 AES/CBC/PKCS5Padding
  // */
  //static void method3(String str) throws Exception {
  //  cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
  //  //KeyGenerator 生成aes算法密钥
  //  secretKey = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
  //  System.out.println("密钥的长度为：" + secretKey.getEncoded().length);
  //
  //  cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用加密模式初始化 密钥
  //  byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
  //
  //  System.out.println("method3-加密：" + Arrays.toString(encrypt));
  //  cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用解密模式初始化 密钥
  //  byte[] decrypt = cipher.doFinal(encrypt);
  //  System.out.println("method3-解密后：" + new String(decrypt));
  //}
  //
  ///**
  // * 使用AES 算法 加密，默认模式 AES/CBC/NoPadding  参见上面对于这种mode的数据限制
  // */
  //static void method4(String str) throws Exception {
  //  cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC_NoPadding);
  //  //KeyGenerator 生成aes算法密钥
  //  secretKey = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
  //  System.out.println("密钥的长度为：" + secretKey.getEncoded().length);
  //
  //  cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用加密模式初始化 密钥
  //  byte[] encrypt = cipher.doFinal(str.getBytes(), 0, str.length()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
  //
  //  System.out.println("method4-加密：" + Arrays.toString(encrypt));
  //  cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用解密模式初始化 密钥
  //  byte[] decrypt = cipher.doFinal(encrypt);
  //
  //  System.out.println("method4-解密后：" + new String(decrypt));
  //}

  /**
   * 加密
   *
   * @param content 需要加密的内容
   * @param password 加密密码
   */
  public static byte[] encrypt(String content, String password) {
    try {
      //KeyGenerator kgen = KeyGenerator.getInstance("AES");
      //kgen.init(128, new SecureRandom(password.getBytes()));
      //SecretKey secretKey = kgen.generateKey();
      //byte[] enCodeFormat = secretKey.getEncoded();
      byte[] pwd = password.getBytes("utf-8");
      SecretKeySpec key = new SecretKeySpec(pwd, "AES");
      Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
      byte[] byteContent = content.getBytes("utf-8");
      cipher.init(Cipher.ENCRYPT_MODE, key); // 初始化
      byte[] result = cipher.doFinal(byteContent);
      return result; // 加密
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static byte[] encrypt(String content) {
    return encrypt(content, AESKEY);
  }

  public static byte[] decrypt(byte[] content) {
    return decrypt(content, AESKEY);
  }

  /**
   * 解密
   *
   * @param content 待解密内容
   * @param password 解密密钥
   */
  public static byte[] decrypt(byte[] content, String password) {
    try {
      //KeyGenerator kgen = KeyGenerator.getInstance("AES");
      //kgen.init(128, new SecureRandom(password.getBytes()));
      //SecretKey secretKey = kgen.generateKey();
      //byte[] enCodeFormat = secretKey.getEncoded();

      try {
        byte[] byteContent = password.getBytes("utf-8");
        SecretKeySpec key = new SecretKeySpec(byteContent, "AES");
        Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key); // 初始化
        byte[] result = cipher.doFinal(content);
        return result; // 解密
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String bytesToHexStr(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    for (int i = 0; i < bytes.length; i++) {
      String hv = Integer.toHexString(bytes[i] & 0xFF);
      if (hv.length() == 1) {
        sb.append(0);
      }
      sb.append(hv);
    }
    return sb.toString();
  }

  public static byte[] hexStrToBytes(String hexString) {
    String hex = "0123456789ABCDEF";
    if (hexString == null || "".equals(hexString)) {
      return null;
    }
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toUpperCase().toCharArray();
    byte[] bytes = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      bytes[i] = (byte) (hex.indexOf(hexChars[pos]) << 4 | hex.indexOf(hexChars[pos + 1]));
    }
    return bytes;
  }

  /**
   * Gets encrypt str. 获取加密结果字串
   *
   * @param content the content
   * @param password the password
   * @return the encrypt str
   */
  public static String getEncryptStr(String content, String password) {
    //加密
    byte[] encryptResult = encrypt(content, password);
    String encryptResultStr = bytesToHexStr(encryptResult);
    return encryptResultStr;
  }

  /**
   * Gets encrypt str. 获取加密结果字串
   *
   * @param content the content 用固定的password
   * @return the encrypt str
   */
  public static String getEncryptStr(String content) {
    return getEncryptStr(content, AESKEY);
  }

  /**
   * Gets decrypt str. 获取解密字串
   *
   * @param content the encrypt result str 用固定的password
   * @return the decrypt str
   */
  public static String getDecryptStr(String content) {

    return getDecryptStr(content, AESKEY);
  }

  /**
   * Gets decrypt str. 获取解密字串
   *
   * @param encryptResultStr the encrypt result str
   * @param password the password
   * @return the decrypt str
   */
  public static String getDecryptStr(String encryptResultStr, String password) {
    //解密
    byte[] decryptFrom = hexStrToBytes(encryptResultStr);
    byte[] decryptResult = decrypt(decryptFrom, password);
    String decryptStr = null;
    try {
      decryptStr = new String(decryptResult, "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return decryptStr;
  }
}
