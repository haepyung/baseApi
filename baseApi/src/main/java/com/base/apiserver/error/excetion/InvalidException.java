package com.base.apiserver.error.excetion;

/**
 * <p> API 통신시 PARAMETER ERROR 처리
 * @author PARK
 *
 */
public class InvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidException(String message) {
        super(message);
    }

    public InvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}