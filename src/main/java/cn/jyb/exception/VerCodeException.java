package cn.jyb.exception;

public class VerCodeException extends RuntimeException {

	private static final long serialVersionUID = 5467687867086490833L;

	public VerCodeException() {
		super();
	}

	public VerCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VerCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public VerCodeException(String message) {
		super(message);
	}

	public VerCodeException(Throwable cause) {
		super(cause);
	}

	
}
