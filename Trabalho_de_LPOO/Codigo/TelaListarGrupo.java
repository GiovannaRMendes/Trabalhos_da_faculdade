import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaListarGrupo implements Janela {
	private JMenuItem botaoDeOrigem;
	private JPanel tela;
	private ArrayList<Grupo> grupoAtual;
	
	public TelaListarGrupo(JMenuItem botaoDeOrigem, ArrayList<Grupo> grupos) {

		definicoesTela();
		this.botaoDeOrigem = botaoDeOrigem;
		this.grupoAtual = grupos;
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
		
		for (int i = 0 ; i < grupoAtual.size() ; i++) {
            
			tela.add(new JLabel(grupoAtual.get(i).toString()));

		}
		
		return tela;
	}

	@Override
	public JMenuItem getSource() {

		return botaoDeOrigem;

	}

	@Override
	public String getMensagemStatus() {
		return "Listando grupos de Kpop...";
	}
}
