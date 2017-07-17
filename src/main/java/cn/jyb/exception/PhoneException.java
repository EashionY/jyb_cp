package cn.jyb.exception;

public class PhoneException extends RuntimeException {

	private static final long serialVersionUID = 5495559528239635241L;

	public PhoneException() {
		super();
	}

	public PhoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PhoneException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhoneException(String message) {
		super(message);
	}

	public PhoneException(Throwable cause) {
		super(cause);
	}

}
