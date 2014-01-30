package com.socklabs.error;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Arrays;

/**
 * Created by ngerakines on 1/22/14.
 */
public final class ErrorUtil {

	public static final int MIN_RANGE = 0;
	public static final int MAX_RANGE = 33554431;
	public static final int ERROR_CODE_LENGTH = 5;

	private static final char[] ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789".toCharArray();
	private static final int[] DEFAULT_ARRAY = new int[ERROR_CODE_LENGTH];

	static {
		Arrays.fill(DEFAULT_ARRAY, -1);
	}

	protected ErrorUtil() {}

	public static String padErrorCode(final String errorCode) {
		if (errorCode.length() < ERROR_CODE_LENGTH) {
			return Strings.padStart(errorCode, ERROR_CODE_LENGTH, 'A');
		}
		return errorCode;
	}

	public static String hashId(final int id) {
		Preconditions.checkArgument(
				id > MIN_RANGE && id <= MAX_RANGE,
				"CodedError codes must be in the range of 1 through 33,554,431");

		if (id < ALPHABET.length) {
			return padErrorCode(String.valueOf(ALPHABET[id]));
		}

		final int[] digits = DEFAULT_ARRAY.clone();
		int num = id;
		int place = 0;

		while (num > 0) {
			final int remainder = num % ALPHABET.length;
			digits[place] = remainder;
			num = num / ALPHABET.length;
			place++;
		}

		final StringBuilder stringBuilder = new StringBuilder(place);
		for (int i = place - 1; i > -1; i--) {
			final int digit = digits[i];
			stringBuilder.append(ALPHABET[digit]);
		}

		return padErrorCode(stringBuilder.toString());
	}

}
