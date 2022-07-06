public class InvalidDurationException extends RuntimeException {
    InvalidDurationException() {
        super("Você inseriu um valor inválido para duração de um show.\nPor favor, insira de 1 até 6 para duração de um show.\n");
    }
}