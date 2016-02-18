package com.ucan.server.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucan.server.model.UserCore;

public class TokenUtil {
	private Key key;
	private String key16;
	private static TokenUtil mInstance;

	public static TokenUtil gInstance() {
		if (mInstance == null) {
			mInstance = new TokenUtil();
		}
		return mInstance;
	}

	private TokenUtil() {
		this.key = DigestUtils.randomKey();
		this.key16 = DigestUtils.byte2hex(key.getEncoded());

	}

	public <T> String composeToken(T t) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Class<?> cls = t.getClass();
		Method methods[] = cls.getDeclaredMethods();
		Field fields[] = cls.getDeclaredFields();
		for (Field field : fields) {
			String fldtype = field.getType().getSimpleName();
			String getMetName = pareGetName(field.getName());
			String result = "";
			if (!checkMethod(methods, getMetName)) {
				continue;
			}
			Method method = null;
			Object object = null;
			try {
				method = cls.getMethod(getMetName, null);

				object = method.invoke(t, new Object[] {});
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != object) {
				result = String.valueOf(object);
				System.out.println(result);
			}
			map.put(field.getName(), result);
		}

		return map.toString();
	}

	public String reToken(String content) {
		return null;
	}

	public String getKey() {
		return key16;
	}

	public String decomposeToken(String token, String key) {
		return DigestUtils.decrypt(token, new SecretKeySpec(DigestUtils.hex2byte(key), "AES"));
	}

	/**
	 * 拼接某属性get 方法
	 * 
	 * @param fldname
	 * @return
	 */
	public static String pareGetName(String fldname) {
		if (null == fldname || "".equals(fldname)) {
			return null;
		}
		String pro = "get" + fldname.substring(0, 1).toUpperCase() + fldname.substring(1);
		return pro;
	}

	/**
	 * 拼接某属性set 方法
	 * 
	 * @param fldname
	 * @return
	 */
	public static String pareSetName(String fldname) {
		if (null == fldname || "".equals(fldname)) {
			return null;
		}
		String pro = "set" + fldname.substring(0, 1).toUpperCase() + fldname.substring(1);
		return pro;
	}

	/**
	 * 判断该方法是否存在
	 * 
	 * @param methods
	 * @param met
	 * @return
	 */
	public static boolean checkMethod(Method methods[], String met) {
		if (null != methods) {
			for (Method method : methods) {
				if (met.equals(method.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ObjectMapper omMapper = new ObjectMapper();
		UserCore userCore = new UserCore();
		userCore.setAccount("1");
		userCore.setPassword("2");
		userCore.setRole(1);
		userCore.setTimeStamp(DateUtil.getTimeStamp());
		try {
			String content = omMapper.writeValueAsString(userCore);
			String key = gInstance().getKey();
			String token = gInstance().composeToken(userCore);

			System.out.println(content);
			System.out.println(key);
			System.out.println(token);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
