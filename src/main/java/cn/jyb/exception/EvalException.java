package cn.jyb.exception;

public class EvalException extends RuntimeException {

	private static final long serialVersionUID = -6265094663906731336L;

	public EvalException() {
		super();
	}

	public EvalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EvalException(String message, Throwable cause) {
		super(message, cause);
	}

	public EvalException(String message) {
		super(message);
	}

	public EvalException(Throwable cause) {
		super(cause);
	}

	
}
