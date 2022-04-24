package NadavOren_YanivBenDavid.Exceptions;

@SuppressWarnings("serial")
public class OurException extends Exception {
	String msg;

	public OurException(String str) {
		msg = str;
	}

	public String getMessage() {
		return msg;
	}
}
