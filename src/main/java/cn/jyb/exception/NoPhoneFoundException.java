package cn.jyb.exception;

public class NoPhoneFoundException extends RuntimeException {

	private static final long serialVersionUID = 505854289177774928L;

	public NoPhoneFoundException() {
		super();
	}

	public NoPhoneFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoPhoneFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoPhoneFoundException(String message) {
		super(message);
	}

	public NoPhoneFoundException(Throwable cause) {
		super(cause);
	}

}
