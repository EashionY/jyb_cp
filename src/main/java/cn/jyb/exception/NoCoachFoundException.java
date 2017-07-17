package cn.jyb.exception;

public class NoCoachFoundException extends RuntimeException {

	private static final long serialVersionUID = 8720456440123397040L;

	public NoCoachFoundException() {
		super();
	}

	public NoCoachFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoCoachFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoCoachFoundException(String message) {
		super(message);
	}

	public NoCoachFoundException(Throwable cause) {
		super(cause);
	}

	
}
