package com.socklabs.error;

/**
 * Created by ngerakines on 1/30/14.
 */
public interface EncodedError {

	int id();

	String code();

	String message();

	String namespace();

	String[] namespaceParts();

}
