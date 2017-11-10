package base;

import java.security.*;
import java.math.*;

public class SHA512 {	
	
	   public String hashString(String meegegevenString) throws Exception{
       String s= meegegevenString;
       MessageDigest m=MessageDigest.getInstance("SHA-512");
       m.update(s.getBytes(),0,s.length());
       return new BigInteger(1,m.digest()).toString(16);
       
    }
	
	
}