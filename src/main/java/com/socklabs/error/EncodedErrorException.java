package com.socklabs.error;

/**
 * Created by ngerakines on 1/30/14.
 */
public class EncodedErrorException extends Exception {

	private final EncodedError encodedError;

	public EncodedErrorException(final EncodedError encodedError) {
		super(encodedError.code());
		this.encodedError = encodedError;
	}

	public EncodedErrorException(final EncodedError encodedError, final Throwable cause) {
		super(encodedError.code(), cause);
		this.encodedError = encodedError;
	}

	public EncodedError getEncodedError() {
		return encodedError;
	}

}
