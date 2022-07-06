public class SameGroupOrSoloException extends RuntimeException {
	public SameGroupOrSoloException() {
		super("O mesmo grupo/solista foi selecionado mais de uma vez para o mesmo show.");
	}
}
