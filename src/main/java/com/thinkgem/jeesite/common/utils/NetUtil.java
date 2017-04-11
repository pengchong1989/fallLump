package com.thinkgem.jeesite.common.utils;

public class NetUtil {
	
	public static String print16String(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("00000000  ");
		int count = 1;
		int index = 1;
		for (int i = 0; i < bytes.length; i++) {
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = bytes[i];
			String value16 = intTo16String(bytesToInt(temp));
			if (value16.length() == 1) {
				value16 = "0" + value16;
			}
			buffer.append(value16 + " ");
			if (count != 0 && count % 8 == 0) {
				buffer.append(" ");
			}
			if (count != 0 && count % 16 == 0) {
				buffer.append("\r\n");
				String value16Index = intTo16String(index);
				value16Index += "0";
				int bq = 8 - value16Index.length();
				String tempIndexStr = "";
				for (int j = 0; j < bq; j++) {
					tempIndexStr += "0";
				}
				tempIndexStr += value16Index;
				buffer.append(tempIndexStr + "  ");
				index++;
			}
			count++;
		}
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
	public static String byteToString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		String[] strs = null;
		StringBuffer str = new StringBuffer();
		int count = 1;
		for (int i = 0; i < bytes.length; i++) {
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = bytes[i];
			String value16 = intTo16String(bytesToInt(temp));
			if (value16.length() == 1) {
				value16 = "0" + value16;
			}
			buffer.append(value16 + " ");
			count++;
		}
		strs = buffer.toString().split(" ");
		for(String string : strs){
			str.append((char)Integer.parseInt(string, 16));
		}
		return str.toString();
	}
	
	public static String intTo16String(int count) {
		String bivalue = Integer.toBinaryString(count);
		int valuebiint = Integer.parseInt(bivalue, 2);
		String value16 = Integer.toHexString(valuebiint).toUpperCase();
		return value16;
	}
	
	public static int bytesToInt(byte[] in) {
		int result = 0;
		for (int i = 3, j = 0; j < 4; j++, i--) {
			result += ((in[j] & 0xff) << (i * 8));
		}
		return result;
	}
	
	/**
	 * byte转换Int
	 * 
	 * @param in
	 * @return
	 */
	public static int bytesToInt(byte in) {
		byte[] temp = new byte[4];
		temp[0] = 0x00;
		temp[1] = 0x00;
		temp[2] = 0x00;
		temp[3] = in;

		int result = 0;
		for (int i = 3, j = 0; j < 4; j++, i--) {
			result += ((temp[j] & 0xff) << (i * 8));
		}
		return result;
	}
	
	/**
	 * Int转换byte[]
	 */
	public static byte[] intToBytes(int in) {
		byte[] result = new byte[4];
		for (int i = 3, j = 0; j < 4; i--, j++) {
			result[j] = (byte) ((in >>> (i * 8)) & 0xff);
		}
		return result;
	}
	
	/**
	 * 校验和
	 * @return
	 */
	public static byte checkNum(byte[] bs,int start,int end){
		int num = 0;
		for (int i = start-1; i < end; i++) {
			num += bytesToInt(bs[i]);
		}
		byte[] b = intToBytes(num);
		return b[3];
	}
}
