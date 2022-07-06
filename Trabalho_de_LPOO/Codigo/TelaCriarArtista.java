import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCriarArtista implements Janela {

	private JPanel tela;
	private JMenuItem botaoDeOrigem;

	private JPanel cadastrarNomeKidol;
	private JTextField nomeKidol;

	private JPanel cadastrarPapel;
	private JComboBox<String> papel;

	private JButton salvar;
	
	public TelaCriarArtista(JMenuItem botaoDeOrigem, ArrayList<Artista> artistas) {
		this.botaoDeOrigem = botaoDeOrigem;

		this.tela = new JPanel(); // Essa tela inteira
		this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));
		
		definicoesTela();
		
		this.salvar = new JButton("Salvar");
		this.salvar.addActionListener((f) -> {

			try {
				if (nomeKidol.getText().strip().equals("") || papel.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}

				for (int cont = 0; cont < artistas.size(); cont++) {
					if(artistas.get(cont).getNomeKidol().equals(nomeKidol.getText())){
						throw new SameNameException();
					}
				}
				
				Artista artista = new Artista(nomeKidol.getText(), papel.getSelectedItem().toString());

				artistas.add(artista);
				
				nomeKidol.setText("");
				papel.setSelectedIndex(-1);

				JOptionPane.showMessageDialog(null, "Artista cadastrado", "Success", 1);
			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (InvalidNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
			}
			catch (SameNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}

		});
		tela.add(salvar);
		
	}

	@Override
	public void definicoesTela() {
		this.nomeKidol = new JTextField(40);
		this.cadastrarNomeKidol = new JPanel(); 
		this.cadastrarNomeKidol.setLayout(new FlowLayout());
		this.cadastrarNomeKidol.add(new JLabel("Nome do K-idol"));
		this.cadastrarNomeKidol.add(nomeKidol);
		this.tela.add(cadastrarNomeKidol);

		String[] vetorPapeis = {"Rapper", "Singer", "Dancer"};
		this.papel = new JComboBox<String>(vetorPapeis);
		this.papel.setSelectedIndex(-1);
		this.cadastrarPapel = new JPanel();
		this.cadastrarPapel.setLayout(new FlowLayout());
		this.cadastrarPapel.add(new JLabel("Papel do K-idol"));
		this.cadastrarPapel.add(papel);
		this.tela.add(cadastrarPapel);
	}
	
	@Override
	public JPanel getTela() {
		nomeKidol.setText("");
		papel.setSelectedIndex(-1);

		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoDeOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Criando um novo artista...";
	}

}
