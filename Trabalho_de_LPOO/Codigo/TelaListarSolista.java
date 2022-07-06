import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaListarSolista implements Janela {

	private JMenuItem botaoDeOrigem;
	private JPanel tela;
	private ArrayList<Solista> solistaAtual;
	
	public TelaListarSolista(JMenuItem botaoDeOrigem, ArrayList<Solista> solistas) {

		this.botaoDeOrigem = botaoDeOrigem;
		this.solistaAtual = solistas;
		definicoesTela();

	}
	
	@Override
	public void definicoesTela() {
		tela = new JPanel();
		tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));
	}

	@Override
	public JPanel getTela() {
		
		tela.removeAll();
		
		for (int i = 0 ; i < this.solistaAtual.size() ; i++) {
            
			tela.add(new JLabel(this.solistaAtual.get(i).toString()));

		}
		
		return tela;
	}

	@Override
	public JMenuItem getSource() {

		return this.botaoDeOrigem;

	}

	@Override
	public String getMensagemStatus() {

		return "Listando solistas...";

	}
}
