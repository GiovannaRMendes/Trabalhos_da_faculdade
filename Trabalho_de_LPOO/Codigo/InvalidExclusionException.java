public class InvalidExclusionException extends RuntimeException {
    InvalidExclusionException() {
        super("Essa exclusão não pode ser efetivada, pois esse grupo ou solista possui algum(ns) show(s) para fazer.");
    }
}