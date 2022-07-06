import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAtualizarSolista implements Janela {

	private JPanel tela;
	private JMenuItem botaoDeOrigem;

	private JPanel selecionarSolista;
	private Solista[] vetorSolista;
	private DefaultComboBoxModel<Solista> modelSolista;
	private JComboBox<Solista> solista;

	private ArrayList<Solista> arraySolistaAtualizar;

	private JPanel cadastrarQuantidadeMiniAlbuns;
	private JTextField quantidadeMiniAlbuns;

	private JPanel cadastrarQuantidadeDancarinos;
	private JTextField quantidadeDancarinos;

	int velhaQuantidadeMiniAlbuns;
	int velhaQuantidadeDancarinos;

	private JButton atualizar;
	
	public TelaAtualizarSolista(JMenuItem botaoDeOrigem, ArrayList<Solista> solistas) {
		this.botaoDeOrigem = botaoDeOrigem;
		this.arraySolistaAtualizar = solistas;

		this.tela = new JPanel();
		this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));
		
		atualizarTela();

		definicoesTela();

		solista.addActionListener((e) -> {
			if (solista.getSelectedIndex() != -1) {
				Solista auxiliar = (Solista) solista.getSelectedItem();

				quantidadeMiniAlbuns.setText(auxiliar.getQuantidadeMiniAlbuns() + "");
				quantidadeDancarinos.setText(auxiliar.getQuantidadeDancarinos() + "");
			}
		});

		this.atualizar = new JButton("Atualizar");
		this.atualizar.addActionListener((f) -> {

			try {

				if (solista.getSelectedIndex() == -1 || quantidadeMiniAlbuns.getText().strip().equals("") || quantidadeDancarinos.getText().strip().equals("")) {
					throw new MissingInformationException();
				}
				
				Solista solistaAserAlterado = (Solista) solista.getSelectedItem();				
				velhaQuantidadeMiniAlbuns = solistaAserAlterado.getQuantidadeMiniAlbuns();
				velhaQuantidadeDancarinos = solistaAserAlterado.getQuantidadeDancarinos();

				if (velhaQuantidadeMiniAlbuns > Integer.parseInt(quantidadeMiniAlbuns.getText())) {
					throw new InvalidMiniAlbumNumberException();
				}
				
				solistaAserAlterado.setQuantidadeMiniAlbuns(Integer.parseInt(quantidadeMiniAlbuns.getText()));
				solistaAserAlterado.setQuantidadeDancarinos(Integer.parseInt(quantidadeDancarinos.getText()));
				
				atualizarTela();

				solista.setSelectedIndex(-1);
				quantidadeMiniAlbuns.setText("");
				quantidadeDancarinos.setText("");

				JOptionPane.showMessageDialog(null, "Solista atualizado com sucesso", "Success", 1);
			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (InvalidDancerNumberException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
				erroAtualizar();
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Campo numeral recebeu letra(s)", "Error", 0);
				erroAtualizar();
			}
			catch (KIdolWorkingException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
			}
			catch (InvalidMiniAlbumNumberException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}

		});
		tela.add(atualizar);
		
	}

	private void erroAtualizar() {
		Solista solistaVelho = (Solista) solista.getSelectedItem();

		solistaVelho.setQuantidadeMiniAlbuns(velhaQuantidadeMiniAlbuns);
		solistaVelho.setQuantidadeDancarinos(velhaQuantidadeDancarinos);

	}

	private void atualizarTela() {

		this.vetorSolista = new Solista[this.arraySolistaAtualizar.size()];
		for (int i = 0; i < this.arraySolistaAtualizar.size(); i++) {
			this.vetorSolista[i] = this.arraySolistaAtualizar.get(i);
		}

		if (this.modelSolista != null) {
			for (int i = this.modelSolista.getSize() - 1; i >= 0; i--) {
				this.modelSolista.removeElementAt(i);
			}

			for (int i = 0; i < this.vetorSolista.length; i++) {
				this.modelSolista.addElement(this.vetorSolista[i]);
			}
		}

	}

	@Override
	public void definicoesTela() {

		this.modelSolista = new DefaultComboBoxModel<Solista>(this.vetorSolista);
		this.solista = new JComboBox<Solista>(modelSolista);
		this.solista.setSelectedIndex(-1);
		this.selecionarSolista = new JPanel();
		this.selecionarSolista.setLayout(new FlowLayout());
		this.selecionarSolista.add(new JLabel("Solista para atualizar"));
		this.selecionarSolista.add(solista);
		this.tela.add(selecionarSolista);

		this.quantidadeMiniAlbuns = new JTextField(40);
		this.cadastrarQuantidadeMiniAlbuns = new JPanel(); 
		this.cadastrarQuantidadeMiniAlbuns.setLayout(new FlowLayout());
		this.cadastrarQuantidadeMiniAlbuns.add(new JLabel("Quantidade de mini-álbuns"));
		this.cadastrarQuantidadeMiniAlbuns.add(quantidadeMiniAlbuns);
		this.tela.add(cadastrarQuantidadeMiniAlbuns);

		this.quantidadeDancarinos = new JTextField(40);
		this.cadastrarQuantidadeDancarinos = new JPanel(); 
		this.cadastrarQuantidadeDancarinos.setLayout(new FlowLayout());
		this.cadastrarQuantidadeDancarinos.add(new JLabel("Quantidade de dançarinos"));
		this.cadastrarQuantidadeDancarinos.add(quantidadeDancarinos);
		this.tela.add(cadastrarQuantidadeDancarinos);
		
	}
	
	@Override
	public JPanel getTela() {
		atualizarTela();
		solista.setSelectedIndex(-1);
		quantidadeMiniAlbuns.setText("");
		quantidadeDancarinos.setText("");

		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoDeOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Atualizando um solista...";
	}

}
