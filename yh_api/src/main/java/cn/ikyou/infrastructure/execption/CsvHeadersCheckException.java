package cn.ikyou.infrastructure.execption;

public class CsvHeadersCheckException extends RuntimeException {

	private static final long serialVersionUID = 8746916937720444336L;

	public CsvHeadersCheckException() {

	}

	public CsvHeadersCheckException(String msg) {
		super(msg);
	}
	
}
