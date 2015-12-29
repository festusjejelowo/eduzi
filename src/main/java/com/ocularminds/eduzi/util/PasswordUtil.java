package com.ocularminds.eduzi.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

	public static String hashPassword(String pwd) {

		String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt());
		return hashed;
	}

	public static boolean verifyPassword(String pwd, String hash) {

		if(hash == null) return false;
		boolean b = BCrypt.checkpw(pwd, hash);
		return b;
	}
}
