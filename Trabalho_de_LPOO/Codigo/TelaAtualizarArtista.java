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
import javax.swing.DefaultComboBoxModel;


public class TelaAtualizarArtista implements Janela {
	private JPanel tela;
	private JPanel selecionarArtista;
	private JPanel cadastrarNomeKidol;
	private JPanel cadastrarPapel;

	private JMenuItem botaoOrigem;

	private DefaultComboBoxModel<Artista> atualizarArtista;
	private JComboBox<Artista> artistaSelecionado;

	private JTextField nomeKidol;
	private JComboBox<String> papel;

	private ArrayList<Artista> arrayArtistaAtualizaTela;
	private Artista[] vetorArtistas;

	private Artista artistaAserAlterado;

	private JButton atualizar;

	private String[] informacoesAntigas;
	
	public TelaAtualizarArtista(JMenuItem botaoOrigem, ArrayList<Artista> vetorArtistas) {
		this.botaoOrigem = botaoOrigem;
		this.arrayArtistaAtualizaTela = vetorArtistas;
		tela = new JPanel();
		tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

		atualizarTela();

		definicoesTela();

		artistaSelecionado.addActionListener((f) -> {
			if(artistaSelecionado.getSelectedIndex() != -1) {
				Artista artista = (Artista)artistaSelecionado.getSelectedItem();
				nomeKidol.setText(artista.getNomeKidol());
				papel.setSelectedItem(artista.getPapel());

			}
		});

		atualizar = new JButton("Atualizar");
		atualizar.addActionListener((f) -> {
			try {

				if (artistaSelecionado.getSelectedIndex() == -1 ||
					nomeKidol.getText().strip().equals("") ||
					papel.getSelectedIndex() == -1 ||
					artistaSelecionado.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}

				for (int cont = 0; cont < vetorArtistas.size(); cont++) {
					if (vetorArtistas.get(cont).getNomeKidol().equals(nomeKidol.getText()) && vetorArtistas.get(cont).equals(artistaSelecionado.getSelectedItem())) {
						throw new SameNameException();
					}
				}

				artistaAserAlterado = (Artista) artistaSelecionado.getSelectedItem();
				informacoesAntigas = new String[2];
				informacoesAntigas[0] = artistaAserAlterado.getNomeKidol();
				informacoesAntigas[1] = artistaAserAlterado.getPapel();

				artistaAserAlterado.setNomeKidol(nomeKidol.getText());
				artistaAserAlterado.setPapel(papel.getSelectedItem().toString());

				atualizarTela();
				artistaSelecionado.setSelectedIndex(-1);
				nomeKidol.setText("");
				papel.setSelectedIndex(-1);

				JOptionPane.showMessageDialog(null, "Artista atualizado com sucesso");
			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (InvalidNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
				erroAtualizar();
			}
			catch (SameNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
		});
		tela.add(atualizar);
		
	}

	private void erroAtualizar() {
		try {

			artistaAserAlterado.setNomeKidol(informacoesAntigas[0]);	
			artistaAserAlterado.setPapel(informacoesAntigas[1]);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void definicoesTela() {

		this.atualizarArtista = new DefaultComboBoxModel<Artista>(vetorArtistas);
		this.artistaSelecionado = new JComboBox<Artista>(atualizarArtista);
		this.artistaSelecionado.setSelectedIndex(-1);
		this.selecionarArtista = new JPanel();
		this.selecionarArtista.setLayout(new FlowLayout());
		this.selecionarArtista.add(new JLabel("Artista a ser atualizado"));
		this.selecionarArtista.add(artistaSelecionado);
		this.tela.add(selecionarArtista);
		
		this.nomeKidol = new JTextField(40);
		this.cadastrarNomeKidol = new JPanel();
		this.cadastrarNomeKidol.setLayout(new FlowLayout());
		this.cadastrarNomeKidol.add(new JLabel("Nome:"));
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

	private void atualizarTela() {

		this.vetorArtistas = new Artista[arrayArtistaAtualizaTela.size()];
		for (int i = 0; i < arrayArtistaAtualizaTela.size(); i++) {
			vetorArtistas[i] = arrayArtistaAtualizaTela.get(i);
		}

		if (atualizarArtista != null) {
			for (int i = atualizarArtista.getSize() - 1; i >= 0; i--) {
				atualizarArtista.removeElementAt(i);
			}

			for (int i = 0; i < vetorArtistas.length; i++) {
				atualizarArtista.addElement(vetorArtistas[i]);
			}
		}

	}
	
	@Override
	public JPanel getTela() {
		atualizarTela();
		artistaSelecionado.setSelectedIndex(-1);
		nomeKidol.setText("");
		papel.setSelectedIndex(-1);

		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Atualizando um artista...";
	}

}
