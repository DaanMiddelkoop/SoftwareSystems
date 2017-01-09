package ss.week6;

public class ArgumentLengthsDifferException extends WrongArgumentException {
	public ArgumentLengthsDifferException () {
		super ("Argument Lengths differ");
    }

    public ArgumentLengthsDifferException (String message) {
        super (message);
    }

    public ArgumentLengthsDifferException (Throwable cause) {
        super (cause);
    }

    public ArgumentLengthsDifferException (String message, Throwable cause) {
        super (message, cause);
    }
    
    public ArgumentLengthsDifferException (int i1, int i2) {
    	super ("length of first: " + Integer.toString(i1) + ". length of second: " + Integer.toString(i2));
    }
}
