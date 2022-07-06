public class SameNameException extends RuntimeException {
	public SameNameException() {
		super("O nome dos grupos/K-idols devem ser únicos, ou seja, você está criando um grupo/k-idol com um nome já existente.\nPor favor, insira um nome diferente.");
	}
}
