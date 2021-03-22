package hr.fer.zemris.avsp.lab01.simhash;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Public static methods necessary in this task.
 * 
 * @author Ivan Skorupan
 */
public class Util {
	
	public static String simhash(String text) {
		int[] sh = new int[128];
		String[] textTokens = text.split("\\s+");
		
		for (String token : textTokens) {
			byte[] hash = DigestUtils.md5(token);
			
			for (int i = 0; i < hash.length; i++) {
				byte ithByte = hash[i];
				String binary = String.format("%8s", Integer.toBinaryString(ithByte & 0xff));
				
				for (int j = 0; j < binary.length(); j++) {
					char currentByte = binary.charAt(j);
					int currentIndex = i * 8 + j;
					
					sh[currentIndex] += currentByte == '1' ? 1 : -1;
				}
			}
		}
		
		for (int i = 0; i < sh.length; i++)
			sh[i] = (sh[i] >= 0) ? 1 : 0;
		
		BigInteger shToBigInt = new BigInteger(intArrToString(sh), 2);
		return shToBigInt.toString(16);
	}
	
	private static String intArrToString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++)
			sb.append(arr[i]);
		
		return sb.toString();
	}
	
}
