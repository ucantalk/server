package com.ucan.server.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.glassfish.jersey.internal.util.Base64;

public class DigestUtils {
	private static MessageDigest md = null;

	public static String md5(final String c) {
		if (md == null) {
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		if (md != null) {
			md.update(c.getBytes());
			return byte2hex(md.digest());
		}
		return "";
	}

	public static String byte2hex(byte b[]) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = (new StringBuffer(String.valueOf(hs))).toString();
		}

		return hs.toUpperCase();
	}

	public static byte[] hex2byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static Key randomKey() {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom());
			SecretKey secretKey = kgen.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			Key key = new SecretKeySpec(keyBytes, "AES");
			return key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String content, Key key) {
		try {
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return Base64.encodeAsString(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String content, Key key) {
		byte[] decryptFrom = hex2byte(content);
		try {
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(decryptFrom);
			return new String(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String composeUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uid = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return uid.toUpperCase();
	}

	public static void main(String[] args) {
	 
		System.out.println(randomKey());
		Key key = randomKey();
		String keyStr = byte2hex((key.getEncoded()));
		System.out.println("toString: " + keyStr);
		String content = encrypt("aaa-aaa", key);
		byte[] key2 = hex2byte(keyStr);
		System.out.println(content);

		System.out.println(decrypt(content, new SecretKeySpec(key2, "AES")));
		;
	}
}
