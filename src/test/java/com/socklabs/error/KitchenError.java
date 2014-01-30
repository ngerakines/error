package com.socklabs.error;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by ngerakines on 1/30/14.
 */
public enum KitchenError implements EncodedError {
	OVEN_TOO_HOT(1, "Oven is too hot.");

	public static final String[] NAMESPACE_PARTS = new String[] { "KHN" };
	public static final String NAMESPACE = Joiner.on("").join(NAMESPACE_PARTS);

	private static final Map<String, EncodedError> CODE_MAP = Maps.newHashMap();

	static {
		for (final KitchenError errorError : KitchenError.values()) {
			CODE_MAP.put(errorError.code(), errorError);
		}
	}

	private final int number;
	private final String code;
	private final String message;

	KitchenError(final int number, final String message) {
		this.number = number;
		this.message = message;
		this.code = ErrorUtil.hashId(number);
	}

	@Override
	public int id() {
		return number;
	}

	@Override
	public String code() {
		return NAMESPACE + code;
	}

	@Override
	public String message() {
		return message;
	}

	@Override
	public String namespace() {
		return NAMESPACE;
	}

	@Override
	public String[] namespaceParts() {
		return NAMESPACE_PARTS;
	}

	public static Optional<EncodedError> fromCode(final String errorCode) {
		return Optional.fromNullable(CODE_MAP.get(errorCode));
	}

}
