import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaListarArtista implements Janela {
	private JMenuItem botaoDeOrigem;
	private JPanel tela;
	private ArrayList<Artista> artistasAtual;
	
	public TelaListarArtista(JMenuItem botaoDeOrigem, ArrayList<Artista> artistas) {

		this.botaoDeOrigem = botaoDeOrigem;
		this.artistasAtual = artistas;
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
		
		for (int i = 0 ; i < artistasAtual.size() ; i++) {
            
			tela.add(new JLabel(artistasAtual.get(i).toString()));

		}
		
		return tela;
	}

	@Override
	public JMenuItem getSource() {

		return this.botaoDeOrigem;

	}

	@Override
	public String getMensagemStatus() {
		return "Listando artistas...";
	}
}
