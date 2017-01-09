package ss.week6;

public class TooFewArgumentsException extends WrongArgumentException {
	public TooFewArgumentsException () {
		super ("Too few arguments");
    }

    public TooFewArgumentsException (String message) {
        super (message);
    }

    public TooFewArgumentsException (Throwable cause) {
        super (cause);
    }

    public TooFewArgumentsException (String message, Throwable cause) {
        super (message, cause);
    }
}
