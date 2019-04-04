package util;



import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Encode_decode {
	
	
	public String decode_data(String data) throws UnsupportedEncodingException {   //处理密文
		Aes aes = new Aes();
		String aes_de_data = aes.decrypt(data);
		byte[] asBytes = Base64.getDecoder().decode(aes_de_data);
		String new_data = new String(asBytes, "utf-8");
		return new_data;
	}
	public String encode_data(String data) throws UnsupportedEncodingException {//处理明文
		String base_en_data = Base64.getEncoder().encodeToString(data.getBytes("utf-8"));
		Aes aes = new Aes();
		String new_data = aes.encrypt(base_en_data);
		return new_data;
	}
	/*public static void main(String[] args) throws UnsupportedEncodingException{
		

	        //加密字符串
	        String msg = "msg_1ss?!@!$!@#^#。.";
	        Encode_decode a = new Encode_decode();
	        String aaa = a.encode_data(msg);
	        System.out.println(aaa);
	        String bbb = a.decode_data(aaa);
	        System.out.println(bbb);
	}*/

}
