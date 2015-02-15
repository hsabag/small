package org.sb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	private static ThreadLocal<MessageDigest> md = new ThreadLocal<MessageDigest>();

	public static String cryptWithMD5(String pass) throws NoSuchAlgorithmException
	{
		getMessageDigest();
		byte[] passBytes = pass.getBytes();
		MessageDigest messageDigest = md.get();
		messageDigest.reset();
		byte[] digested = messageDigest.digest(passBytes);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<digested.length;i++){
			sb.append(Integer.toHexString(0xff & digested[i]));
		}
		return sb.toString();
	}

	private static void getMessageDigest() throws NoSuchAlgorithmException {
		
		if (md.get() == null)
		{
			md.set(MessageDigest.getInstance("MD5"));
		}
		
	}
}