package per.crystal.dormmanage.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

    public static String getEncrypt(String content) {
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(md!=null){
            md.update(content.getBytes());
            byte[] encodeBytes = md.digest();
            char encodeStr[] = new char [encodeBytes.length * 2];
            int k = 0;

            for( int i = 0 ; i < encodeBytes.length ; i++ ){
                byte encodeByte = encodeBytes[i];
                encodeStr[k++] = hexDigits[encodeByte >> 4 & 0xf];
                encodeStr[k++] = hexDigits[encodeByte & 0xf];
            }
            return new String(encodeStr);
        }
        return null;
    }

}
