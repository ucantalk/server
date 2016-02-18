package com.ucan.server.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	/**
	 * 返回当前日期 YYYY-MM-DD
	 * 
	 * @return
	 */
	public static String getNowDate() {
		return LocalDate.now().toString();
	}

	public static String getNowDateTime() {
		return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().trim();
	}

	public static Long getTimeStamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 比较时间戳，返回秒
	 * 
	 * @param t
	 * @return
	 */
	public static Long compareToTimeMillisAsSec(Long t) {
		return (System.currentTimeMillis() - t) / (1000);
	}

	/**
	 * 比较时间戳，返回分钟
	 * 
	 * @param t
	 * @return
	 */
	public static Long compareToTimeMillisAsMin(Long t) {
		return (System.currentTimeMillis() - t) / (1000 * 60);
	}

	public static void main(String[] args) {
		System.out.println(compareToTimeMillisAsMin(1452323349851L));

	}
}
