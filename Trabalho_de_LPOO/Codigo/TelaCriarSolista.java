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

public class TelaCriarSolista implements Janela {

	private JPanel tela;
	private JMenuItem botaoDeOrigem;

	private JPanel cadastrarEmpresaDeKpop;
    private EmpresaDeKpop[] vetorEmpresas;
    private DefaultComboBoxModel<EmpresaDeKpop> modelEmpresas;
	private JComboBox<EmpresaDeKpop> empresaSolista;

    private ArrayList<EmpresaDeKpop> arrayEmpresasAtualizar;
    private ArrayList<Artista> arrayArtistasAtualizar;
	
    private JPanel cadastrarSolista;
    private Artista[] vetorArtistas;
	private DefaultComboBoxModel<Artista> modelArtista;
	private JComboBox<Artista> artistaSolo;

	private JPanel cadastrarQuantidadeMiniAlbuns;
	private JTextField quantidadeMiniAlbuns;

	private JPanel cadastrarQuantidadeDancarinos;
	private JTextField quantidadeDancarinos;

	private JButton salvar;
	
	public TelaCriarSolista(JMenuItem botaoDeOrigem, ArrayList<Solista> solistas, ArrayList<EmpresaDeKpop> vetorEmpresas, ArrayList<Artista> vetorArtistas) {
		this.botaoDeOrigem = botaoDeOrigem;
        this.arrayEmpresasAtualizar = vetorEmpresas;
		this.arrayArtistasAtualizar = vetorArtistas;

		this.tela = new JPanel(); // Essa tela inteira
		this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));
		
		atualizarTela();

		definicoesTela();

		this.salvar = new JButton("Salvar");
		this.salvar.addActionListener((f) -> {

			try {

				if (empresaSolista.getSelectedIndex() == -1 || artistaSolo.getSelectedIndex() == -1 || quantidadeMiniAlbuns.getText().strip().equals("") || quantidadeDancarinos.getText().strip().equals("")) {
					throw new MissingInformationException();
				}
				
				Artista artista = (Artista) artistaSolo.getSelectedItem();
				EmpresaDeKpop empresa = (EmpresaDeKpop) empresaSolista.getSelectedItem();

				if (artista.getTemCarreira()) {
					throw new KIdolWorkingException();
				}

				Solista solista = new Solista(artista, Integer.parseInt(quantidadeMiniAlbuns.getText()), Integer.parseInt(quantidadeDancarinos.getText()));

				solistas.add(solista);
				empresa.getSolistas().add(solista);
				solista.getSolista().setTemCarreira(true);
				
				atualizarTela();

				empresaSolista.setSelectedIndex(-1);
				artistaSolo.setSelectedIndex(-1);
				quantidadeMiniAlbuns.setText("");
				quantidadeDancarinos.setText("");

				JOptionPane.showMessageDialog(null, "Solista cadastrado", "Success", 1);
			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (InvalidDancerNumberException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Campo numeral recebeu letra(s)", "Error", 0);
			}
			catch (KIdolWorkingException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
			}

		});
		tela.add(salvar);
		
	}

	private void atualizarTela() {

		this.vetorEmpresas = new EmpresaDeKpop[this.arrayEmpresasAtualizar.size()];

		for(int i = 0; i < this.arrayEmpresasAtualizar.size(); i++) {
			this.vetorEmpresas[i] = this.arrayEmpresasAtualizar.get(i);
		}

		if (this.modelEmpresas != null) {
			for (int i = this.modelEmpresas.getSize() - 1; i >= 0; i--) {
				this.modelEmpresas.removeElementAt(i);
			}

			for (int i = 0; i < this.vetorEmpresas.length; i++) {
				this.modelEmpresas.addElement(this.vetorEmpresas[i]);
			}
		}

		this.vetorArtistas = new Artista[this.arrayArtistasAtualizar.size()];
		for (int i = 0; i < this.arrayArtistasAtualizar.size(); i++) {
			this.vetorArtistas[i] = this.arrayArtistasAtualizar.get(i);
		}

		if (this.modelArtista != null) {
			for (int i = this.modelArtista.getSize() - 1; i >= 0; i--) {
				this.modelArtista.removeElementAt(i);
			}

			for (int i = 0; i < this.vetorArtistas.length; i++) {
				this.modelArtista.addElement(this.vetorArtistas[i]);
			}
		}
	}

	@Override
	public void definicoesTela() {

		this.modelEmpresas = new DefaultComboBoxModel<EmpresaDeKpop>(this.vetorEmpresas);
		this.empresaSolista = new JComboBox<EmpresaDeKpop>(modelEmpresas);
		this.empresaSolista.setSelectedIndex(-1);
		this.cadastrarEmpresaDeKpop = new JPanel();
		this.cadastrarEmpresaDeKpop.setLayout(new FlowLayout());
		this.cadastrarEmpresaDeKpop.add(new JLabel("Empresa do solista"));
		this.cadastrarEmpresaDeKpop.add(empresaSolista);
		this.tela.add(cadastrarEmpresaDeKpop);

		this.modelArtista = new DefaultComboBoxModel<Artista>(this.vetorArtistas);
		this.artistaSolo = new JComboBox<Artista>(modelArtista);
		this.artistaSolo.setSelectedIndex(-1);
		this.cadastrarSolista = new JPanel();
		this.cadastrarSolista.setLayout(new FlowLayout());
		this.cadastrarSolista.add(new JLabel("Solista"));
		this.cadastrarSolista.add(artistaSolo);
		this.tela.add(cadastrarSolista);

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
		
		empresaSolista.setSelectedIndex(-1);
		artistaSolo.setSelectedIndex(-1);
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
		return "Criando um novo solista...";
	}

}
