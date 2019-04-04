package util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
//import java.util.Arrays;

public class Aes {
    /**
     *
     * @author ngh
     * AES128 �㷨
     *
     * CBC ģʽ
     *
     * PKCS7Padding ���ģʽ
     *
     * CBCģʽ��Ҫ���ƫ��������iv������16λ
     * ��Կ sessionKey������16λ
     *
     * ����java ��֧��PKCS7Padding��ֻ֧��PKCS5Padding ����PKCS7Padding �� PKCS5Padding û��ʲô����
     * Ҫʵ����java����PKCS7Padding��䣬��Ҫ�õ�bouncycastle�����ʵ��
     */
    private final String sessionKey = "xxxxxxxxxxxxxxxx";
    // ƫ���� 16λ
    private final String iv = "xxxxxxxxxxxxxxxx";

    // �㷨����
    final String KEY_ALGORITHM = "AES";
    // �ӽ����㷨/ģʽ/��䷽ʽ
    final String algorithmStr = "AES/CBC/PKCS7Padding";
    // �ӽ��� ��Կ 16λ

    byte[] ivByte;
    byte[] keybytes;
    private Key key;
    private Cipher cipher;
    boolean isInited = false;

    public void init() {
        // �����Կ����16λ����ô�Ͳ���.  ���if �е����ݺ���Ҫ
        keybytes = sessionKey.getBytes();
        ivByte = iv.getBytes();
//        int base = 16;
//        if (keybytes.length % base != 0) {
//            int groups = keybytes.length / base + (keybytes.length % base != 0 ? 1 : 0);
//            byte[] temp = new byte[groups * base];
//            Arrays.fill(temp, (byte) 0);
//            System.arraycopy(keybytes, 0, temp, 0, keybytes.length);
//            keybytes = temp;
//        }
        // ��ʼ��
        Security.addProvider(new BouncyCastleProvider());
        // ת����JAVA����Կ��ʽ
        key = new SecretKeySpec(keybytes, KEY_ALGORITHM);
        try {
            // ��ʼ��cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * ���ܷ���
     *
     * @param content
     *            Ҫ���ܵ��ַ���
     * @param keyBytes
     *            ������Կ
     * @return
     */
    public String encrypt(String content) {
        byte[] encryptedText = null;
        byte[] contentByte = content.getBytes();
        init();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(contentByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(Hex.encode(encryptedText));
    }
    /**
     * ���ܷ���
     *
     * @param encryptedData
     *            Ҫ���ܵ��ַ���
     * @param keyBytes
     *            ������Կ
     * @return
     */
    public String decrypt(String encryptedData) {
        byte[] encryptedText = null;
        byte[] encryptedDataByte = Hex.decode(encryptedData);
        init();
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(encryptedDataByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encryptedText);
    }

    /*public static void main(String[] args) {
        Aes aes = new Aes();

        //�����ַ���
        String content = "������������������";
        System.out.println("����ǰ�ģ�" + content);
//        System.out.println("������Կ��" + new String(keybytes));
        // ���ܷ���
        String enc = aes.encrypt(content);
        System.out.println("���ܺ�����ݣ�" + enc);
        System.out.println(enc.length());
        // ���ܷ���
        String dec = aes.decrypt(enc);
        System.out.println("���ܺ�����ݣ�" + dec);
    }*/
}