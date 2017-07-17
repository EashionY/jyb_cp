package cn.jyb.exception;

public class ImgpathException extends RuntimeException {

	private static final long serialVersionUID = 3254177359812242465L;

	public ImgpathException() {
		super();
	}

	public ImgpathException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ImgpathException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ImgpathException(String arg0) {
		super(arg0);
	}

	public ImgpathException(Throwable arg0) {
		super(arg0);
	}

	
}
