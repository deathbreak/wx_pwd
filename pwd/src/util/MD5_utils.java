package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5_utils {
	public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//确定计算方法
				MessageDigest md5=MessageDigest.getInstance("MD5");
	 			//BASE64Encoder base64en = new BASE64Encoder();
	 			//加密后的字符串
	 			String newstr=Base64.getEncoder().encodeToString(md5.digest(str.getBytes("utf-8")));
	 			return newstr;
	}
}
