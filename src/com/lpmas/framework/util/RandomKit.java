package com.lpmas.framework.util;

import java.util.Random;

public class RandomKit {
	public static final String CS_NUMBER = "0123456789";
	public static final String CS_LOWERCASE_LETTER = "abcdefghijklmnopqrstuvwxyz";
	public static final String CS_UPPERCASE_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String CS_LETTER = CS_LOWERCASE_LETTER + CS_UPPERCASE_LETTER;
	public static final String CS_ALPHANUM = CS_NUMBER + CS_LETTER;

	public static String getRandomString(String candidateString, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int candidateStringLength = candidateString.length();
		for (int i = 0; i < length; i++) {
			sb.append(candidateString.charAt(random.nextInt(candidateStringLength)));
		}
		return sb.toString();
	}
}
