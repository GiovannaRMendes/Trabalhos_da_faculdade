import javax.swing.JMenuItem;
import javax.swing.JPanel;

public interface Janela {
	public JPanel getTela();
	public JMenuItem getSource();
	public String getMensagemStatus();
	public void definicoesTela();
}
