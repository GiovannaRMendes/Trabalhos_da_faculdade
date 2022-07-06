public class InvalidNameException extends RuntimeException {
    InvalidNameException() {
        super("A quantidade de letras é imprópria para ser considerada um nome.");
    }
}