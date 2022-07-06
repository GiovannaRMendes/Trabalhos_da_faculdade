public class InvalidDancerNumberException extends RuntimeException {
    InvalidDancerNumberException() {
        super("Você inseriu um valor inválido para dançarinos.\nPor favor, insira de 6 até 10 para dançarinos.\n");
    }
}