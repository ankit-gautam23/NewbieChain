import java.security.MessageDigest;

public class StringUtil {
    public static String applySha256(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // Hexadecimal Hash
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }


    }
}
