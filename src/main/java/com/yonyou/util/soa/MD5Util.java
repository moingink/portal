/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yonyou.util.soa;

import java.security.MessageDigest;

/**
 * MD5加密工具
 * 
 * @author lixi
 */
public final class MD5Util {

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 对一段字节数组进行128为MD5加密
	 * 
	 * @param b
	 *            目标数组
	 * @return 如果出错则返回当前系统时间
	 */
	public static String getBytesMD5(byte[] b) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(b);

			return messageDigestMD5(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 返回字符串utf-8编码的字节数组的128位md5值
	 * 
	 * @param src
	 *            源字符串
	 * @return 加密后的字符串
	 */
	public static String getStringMD5(String src) {

		try {
			byte[] b = src.getBytes("gb2312");
			return getBytesMD5(b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 返回一个bytes的md5校验码
	 * 
	 * @param data
	 * @return
	 */
	private static String messageDigestMD5(byte[] data) {

		char[] temp = new char[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			byte b = data[i];
			temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
			temp[i * 2 + 1] = hexDigits[b & 0x0f];
		}
		return new String(temp);
	}

	public static void main(String[] args) {
		String[] src = {
				"663741EEA47CFEE5ADB1906A401CC69F",
				"660042438 122101纪洪亮14.1221010210110220100004010",
				"CC17CA2CC53DE9143E05F86E15C3A2EA",
				"68华为终端有限公司48021010210110210300008010",
				"A2D74CC76FB35552274DEECABCF8EC29",
				"870042438 122101纪洪亮111121010210110200200001000",
				"3F1228D3F7B2FF00132EC4CEF42058F7",
				"114华为终端有限公司100.1121010210110310200011010",
				"65F54F7D4C3D447AFDCD3BB0FA9FF374",
				"880015374 121201高珊2312010810110200200005062220203020306859110",
				"EE6BC3DCE8EE05B3A64F08EEC18915BD",
				"890015374 121201高珊5512010810110200200006062220203020306859110",
				"84AF59751E6E4C04E905E34D4A4BC21A",
				"61华为终端有限公司12.1221010210110210200008010",
				"D3A1811D754BD85823595EB676131DD0",
				"62华为终端有限公司20.1221010210110210200009010",
				"6FEA6EAA7F666BD9909F3C28F52FB9FB",
				"90支付宝专用测试户2011201081011020020001605719054017107120", };

		for (int i = 0; i < src.length; i += 2) {
			// System.out.println(src[i]);
			// System.out.println(src[i + 1]);
			System.out.print(src[i] + "   ");
			System.out.println(getStringMD5(src[i + 1].trim()));
		}
		System.out
				.println(getStringMD5("660042438 122101纪洪亮14.1221010210110220100004010"));
		System.out
				.println(getStringMD5("660042438 122101纪洪亮14.1221010210110220100004010"));
	}

}
