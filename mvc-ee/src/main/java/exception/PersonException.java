package exception;

public class PersonException extends RuntimeException{

    private int errorCode;

    public PersonException(int errorCode, String msg) {
        super(msg);
        this.errorCode=errorCode;
    }

}
