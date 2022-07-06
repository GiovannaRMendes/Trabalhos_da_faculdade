import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaListarShow implements Janela {
	private JMenuItem botaoDeOrigem;
	private JPanel tela;
	private ArrayList<Show> showAtual;
	
	public TelaListarShow(JMenuItem botaoDeOrigem, ArrayList<Show> shows) {

        definicoesTela();
		this.botaoDeOrigem = botaoDeOrigem;
		this.showAtual = shows;
		tela = new JPanel();
		tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

	}

    @Override
	public void definicoesTela() {
		tela = new JPanel();
		tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));
	}
	
	@Override
	public JPanel getTela() {

		
		tela.removeAll();
		
		for (int i = 0 ; i < this.showAtual.size() ; i++) {
            
			tela.add(new JLabel(this.showAtual.get(i).toString()));

		}
		
		return tela;
	}

	@Override
	public JMenuItem getSource() {

		return this.botaoDeOrigem;

	}

	@Override
	public String getMensagemStatus() {
		return "Listando shows...";
	}
}
