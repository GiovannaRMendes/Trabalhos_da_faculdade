public class NotANameException extends RuntimeException {
    NotANameException() { 
        super("Campo textual recebeu números.");
    }
}