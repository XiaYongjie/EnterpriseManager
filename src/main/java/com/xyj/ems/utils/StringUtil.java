package com.xyj.ems.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;


/**
 * 字符串处理集合类 主要功能：判断是否为null或者空,字符串是否在数组中,截取，字符在字符串中出现的次数等方法
 */
public class StringUtil {

	/**
	 * 生成随机数
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length,String type) { //length表示生成字符串的长度
		String base = "";
		if(type.equals("1")){
			base = "123456789";
		}else{
			base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";   
		}
		
		Random random = new Random();   
		StringBuffer sb = new StringBuffer();
		int number = 0;
		for (int i = 0; i < length; i++) {   
			number = random.nextInt(base.length());   
			sb.append(base.charAt(number));   
		}   
		return sb.toString();   
	} 
	
	
	/**
	 * 截取指定字节长度的字符串
	 * 
	 * @param s
	 *            原来的字符
	 * @param m
	 *            字节数
	 * @return
	 */
	public static String substring(String s, int m) {
		if (isBlank(s)) {
			return "";
		}
		long l = 0;
		String r = s;
		for (int i = 0; i < s.length() && i < m; i++) {
			int c = s.charAt(i);
			if (c > 127) {
				l = l + 2;
			} else {
				l = l + 1;
			}
			if (l >= m) {
				r = s.substring(0, i + 1);
				break;
			}
		}

		return r;
	}

	/**
	 * 截取英文字符串方法，超出部分用...表示，单词不截断
	 * 
	 * @param s
	 *            字符串
	 * @param len
	 *            长度
	 * @return
	 */
	public static String substring4e(String s, int len) {
		if (s == null) {
			return "";
		}
		if (s.getBytes().length <= len) {
			return s;
		}
		while (!" ".equals(s.substring(len, len + 1)) && len > 0) {
			len--;
		}
		return substring(s, len) + "...";
	}

	public static String substringSimple(String s, int len) {
		if (s.length() > len) {
			s = s.substring(0, len);
		}
		return s;
	}
	
	
	/**
	 * 亚洲语系(中文，日文，朝鲜语)截取字符串
	 * @param s
	 * @param len
	 *            字节长度
	 * @return
	 */
	public static String multiSubstring(String s, int len) {
		if (s == null) {
			return "";
		}
		try {
			if (s.getBytes("Unicode").length <= len) {
				return s;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return multiSubstring2(s, len)+"...";
	}
	
	
	
	
	public static String multiSubstring2(String s, int length) {
		String s1 = s;
		try{
		byte[] bytes = s.getBytes("Unicode");
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始

		for (; i < bytes.length && n < length; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}
		}

		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0) {
				i = i - 1;
			}
			// 该UCS2字符是字母或数字，则保留该字符
			else {
				i = i + 1;
			}
		}
		
		s1 =  new String(bytes, 0, i, "Unicode");
		}catch(UnsupportedEncodingException u){
			u.printStackTrace();
		}
		return s1;
	}

	/**
	 * 去除字符串空格
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static String trim(String s) {
		if (s == null) {
			return "";
		}

		s = s.replaceAll("　", " ");
		s = s.replaceAll("\\s+", " ");

		return s.trim();
	}


	/**
	 * 字符在字符串中出现的次数
	 * 
	 * @param string
	 * @param a
	 * @return
	 */
	public static int occurTimes(String string, String a) {
		int pos = -2;
		int n = 0;

		while (pos != -1) {
			if (pos == -2) {
				pos = -1;
			}
			pos = string.indexOf(a, pos + 1);
			if (pos != -1) {
				n++;
			}
		}
		return n;
	}

	/**
	 * 是否为空 null 或 ""
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean nullOrSpace(String s) {
		if (s == null) {
			return true;
		}
		if (s.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 是否为空
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean isNull(String s) {
		return isBlank(s);
	}

	/**
	 * 返回字符串如果为null 则返回""
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static String nullValue(String s) {
		return s == null ? "" : s.trim();
	}

	/**
	 * 返回字符串 如果为null 返回defaultValue
	 * 
	 * @param s
	 *            字符串
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static String nullValue(String s, String defaultValue) {
		return s == null ? defaultValue : s;
	}

	/**
	 * 重载方法
	 * 
	 * @param s
	 *            对象
	 * @return
	 */
	public static String nullValue(Object s) {
		return s == null ? "" : s.toString();
	}

	public static String LongValue(Long s) {
		return s == null || s.intValue() <= 0 ? "" : s.toString();
	}

	public static String LongValueZero(Long s) {
		return s == null || s.intValue() <= 0 ? "0" : s.toString();
	}
	
	public static String LongValueOne(Long s){
		return s==null || s.intValue()<=0 ? "1" : s.toString();
	}

	/**
	 * 重载方法
	 * 
	 * @param s
	 * @return
	 */
	public static String nullValue(long s) {
		return s < 0 ? "" : String.valueOf(s);
	}

	/**
	 * 重载方法
	 * 
	 * @param s
	 * @return
	 */
	public static String nullValue(int s) {
		return s < 0 ? "" : "" + s;
	}


	/**
	 * 是否selected
	 * 
	 * @param arg
	 *            值
	 * @param selectedValue
	 *            值
	 * @return
	 */
	public static String isSelected(String arg, String selectedValue) {
		return (arg != null && arg.equals(selectedValue)) ? "selected" : "";
	}

	/**
	 * 是否checked
	 * 
	 * @param arg
	 *            值
	 *            值
	 * @return
	 */
	public static String isChecked(String arg, String checkedValue) {
		return (arg != null && arg.equals(checkedValue)) ? "checked" : "";
	}

	/**
	 * 返回字符串如为null返回空
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static String noNull(String s) {
		return replaceTNR(s);
	}
	
	public static String noNull2CSVString(String s) {
		return s == null ? "\"\"" :"\""+replaceTNR(s)+"\"";
	}
	
	public static String noNull2CSVNumber(String s) {
		return s == null ? "\"\"" :"\"	"+ replaceTNR(s) +"\"";
	}
	/**
	 * 转化为json适合的格式，如去掉空格，将双引号转化为转义格式
	 * @return
	 */
	public static String replaceToJsonType(String str){
		return replaceTNR(str).replace("'","\\'").replace("\"", "\\\"");
	}
	
	/**
	 * 去掉\n \t \r
	 * @return
	 */
	public static String replaceTNR(String str){
		
		return str == null ? "" : str.replace("\t"," ").replace("\n", "").replace("\r", "") ;
	
	}
	
	/**
	 * 判断字符串是否为空 
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean isBlank(String s) {
		return s == null || s.trim().length() == 0;
	}
	
	public static boolean isNullObject(Object object) {
		return object == null;
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static String upperCaseFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 字符串是否在字符串数组中 忽略大小写
	 * 
	 * @param url
	 *            字符串
	 * @param allUrl
	 *            字符串数组
	 * @return
	 */
	public static boolean in(String url, String[] allUrl) {
		for (int i = 0; i < allUrl.length; i++) {
			if (allUrl[i].equalsIgnoreCase(url)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串是否在字符串数组中 不忽略大小写
	 * 
	 * @param url
	 *            字符串
	 * @param allUrl
	 *            字符串数组
	 * @return
	 */
	public static boolean inWithCase(String url, String[] allUrl) {
		for (int i = 0; i < allUrl.length; i++) {
			if (allUrl[i].equals(url)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回字符
	 * 
	 * @param n
	 *            第几个
	 * @return
	 */
	public static String getChar(int n) {
		return String.valueOf((char) n);
	}

	/**
	 * 返回字符
	 * 
	 * @param n
	 *            第几个
	 * @return
	 */
	public static String getCol(int n) {
		return String.valueOf((char) (n + 65));
	}

	/**
	 * 字符串中是否含有中文
	 * 
	 * @param s
	 *            字符串
	 * @return
	 */
	public static boolean includeChinese(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c > 100) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 格式化sql
	 * 
	 * @param sql
	 *            字符串
	 * @return
	 */
	public static String escapeSql(String sql) {
		if (sql == null) {
			return null;
		}
		sql = sql.replaceAll("'", "''");
		sql = sql.replaceAll("_", "\\_");
		sql = sql.replaceAll("%", "\\%");
		sql = sql.replaceAll("\\(", "\\\\(");
		sql = sql.replaceAll("\\)", "\\\\)");
		return sql;
	}

	/**
	 * 格式化字符串并在前台加上空格
	 * 
	 * @param text
	 *            字符串
	 * @return
	 */
	public static String firstIndent(String text) {
		text = text.trim();
		text = text.replaceAll("&nbsp;", "");
		text = text.replaceAll("　", "");
		text = text.replaceAll("　", "");
		text = text.replaceAll("\\s+", "");
		return "&nbsp;&nbsp;&nbsp;&nbsp;" + text;
	}

	/**
	 * 
	 * @param password
	 * @param intensity
	 * @param pwdLength
	 * @return 密码强度 1 为低等强度 2为中等强度 3为高等强度
	 */
	public static int validPassword(String password, String intensity,
			int pwdLength) {
		String charGroup[][] = {
				{ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
						"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
						"x", "y", "z" },
				{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
						"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
						"X", "Y", "Z" },
				{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" } };
		int level = 0;
		boolean flagGroup[] = { false, false, false };
		for (int i = 0; i < charGroup.length; i++) {
			String charSmallGroup[] = charGroup[i];

			for (int j = 0; j < password.length(); j++) {
				if (flagGroup[i] == true) {
					continue;
				}

				String str = password.substring(j, j + 1);
				if (inWithCase(str, charSmallGroup)) {
					level++;
					flagGroup[i] = true;
				}
			}
		}

		return level;
	}

	/**
	 * 左边补零以满足长度要求
	 * 
	 * resultLength 最终长度
	 */
	public static String addZeroLeft(String arg, int resultLength) {
		if (arg == null)
			return "";
		String result = arg;
		if (result.length() < resultLength) {
			for (int i = result.length(); i < resultLength; i++) {
				result = "0" + result;
				if (result.length() == resultLength)
					break;
			}
		}
		return result;
	}

	
	public static String formatDecimal(BigDecimal big){
		String pattern="###,##0.00";
	    DecimalFormat df = new DecimalFormat(pattern);
	    return df.format(big); 

	}
	
	public static String encode(String str){
		
		try {
			
			str = URLEncoder.encode(str, "utf-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	public static String decode(String str){
		
		if(str == null || "".equals(str)) return "";
		
		try {
			
			str = URLDecoder.decode(str, "utf-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	/**
	 * sql语句正则表达式转义字符转换
	 * @param sql
	 * @return
	 */
	public static String regexp_sql_replace(String sql){
		
		return sql.replace("[", "\\[")
				  .replace("]", "\\]")
				  .replace("(", "\\(")
				  .replace(")", "\\)")
				  .replace("*", "\\*")
				  .replace("+", "\\+")
				  .replace("?", "\\?")
				  .replace(".", "\\.")
				  .replace("^", "\\^");
	}

	/**
	 * 重复生成N次组成新的字符串。
	 */
	public static String repeat(String src, Long num) {

        StringBuffer s = new StringBuffer();
        for (int i = 0; i < num; i++)
        	s.append(src);
        return s.toString();
        
    }
	
	public static int containNum(String str,String temp) {
		   if(str == null || temp == null || "".equals(str) || "".equals(temp))
			   return 0;
		   int i = 0;
		   int count = 0;
		   while(true){
			   i = str.indexOf(temp);
			   if(i > -1){
				   
				   count++;
				   str = str.substring(i+temp.length(),str.length());
				   
			   }else{
				   break;
			   }
		   }
		   return count;
	} 
	
	public static String concatArray(String[] array,String separator){
		
		if(array == null || array.length == 0)
			return "";
		
		
		if(separator == null)
			separator = "";
		
		StringBuffer buffer = new StringBuffer();
		
		for(String str :array){
			buffer.append(str).append(separator);
		}
		
		String temp = buffer.toString();
		
		if(temp.endsWith(separator)){
			
			temp = temp.substring(0,temp.length() - separator.length());
		}
		return temp;
	};
	 public static String getExtention(String fileName)  {   
	         int pos = fileName.lastIndexOf( "." );   
	     return fileName.substring(pos); 
	 }
	 
	 
 /**
	 * 替换字符串
	 * @param str
	 * @param newChar
	 * @param oldChar
	 * @return
	 */
	public static String replace(String str, String newChar, String... oldChar){
		for (String oc : oldChar) {
			str = str.replace(oc, newChar);
		}
		return str;
	}
	
	/**
	 * 判断字符串是否不为null且不为""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断字符串是否为null或为""
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str) || " ".equals(str) || str.equals("[]") || str.equals("null")|| str.equals("NULL");
	}
	
	/**
	 * 判断数据中是否有无效的值
	 * @param param
	 * @return
	 */
	public static String checkString(String param){
		if(param == null || "null".equals(param) || "NULL".equals(param)|| "[]".equals(param) || "无数据".equals(param)|| "-".equals(param)|| "未知".equals(param) || "\"null\"".equals(param)){
			return "";
		}else{
			return param;
		}
	}
	/**
	 * 判断数据中是否有无效的值
	 * @param param
	 * @return
	 */
	public static boolean isCheckString(String param){
		return param == null || "null".equals(param) || "NULL".equals(param)|| "[]".equals(param) || "无数据".equals(param)|| "-".equals(param)|| "未知".equals(param);
	}
	
	/**
	 * 截取时间字段
	 * @param date
	 * @return
	 */
	public static String getDate(String date){
		if(StringUtil.isNotEmpty(date) && date.length() > 10){
			date = date.substring(0, 10);
		}
		return date;
	}
	
	/**
	 * @param value 要处理的值
	 * @param oldChar 要被替换的值
	 * @param newChar 替换的值
	 * @param size 替换的次数
	 * @return
	 */
	public static String replace(String value,String oldChar,String newChar,int size){
		int index = 0 , count = 0;
		StringBuffer sb =new StringBuffer(value);
		while((index = sb.indexOf(oldChar)) != -1 && (count < size)){
			sb.replace(index, index + oldChar.length(), newChar);
			count ++ ;
		}
		return sb.toString();
	}
	
	public static String toString(Object obj){
		if(obj != null){
			return obj.toString();
		}
		return null;
	}


}
