import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaListarEmpresaDeKpop implements Janela {
	private JMenuItem botaoDeOrigem;
	private JPanel tela;
	private ArrayList<EmpresaDeKpop> empresaDeKpopAtual;
	
	public TelaListarEmpresaDeKpop(JMenuItem botaoDeOrigem, ArrayList<EmpresaDeKpop> empresaDeKpopAtual) {

		this.botaoDeOrigem = botaoDeOrigem;
		this.empresaDeKpopAtual = empresaDeKpopAtual;
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
		
		for (int i = 0 ; i < this.empresaDeKpopAtual.size() ; i++) {
            
			tela.add(new JLabel(this.empresaDeKpopAtual.get(i).toString()));

		}
		
		return tela;
	}

	@Override
	public JMenuItem getSource() {

		return this.botaoDeOrigem;

	}

	@Override
	public String getMensagemStatus() {

		return "Listando empresas de Kpop...";
	}

}
