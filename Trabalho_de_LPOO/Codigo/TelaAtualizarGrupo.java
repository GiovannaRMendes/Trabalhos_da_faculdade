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


public class TelaAtualizarGrupo implements Janela {

	private JPanel tela;
	private JPanel selecionarGrupo;
	private JPanel cadastrarNomeGrupo;

	private String nomeAntigo;
	private Artista[] kidolsAnteriores;

	private DefaultComboBoxModel<Artista> primeiroModelArtistas;
    private DefaultComboBoxModel<Artista> segundoModelArtistas;
    private DefaultComboBoxModel<Artista> terceiroModelArtistas;
    private DefaultComboBoxModel<Artista> quartoModelArtistas;


    private JPanel cadastrarPrimeiroKIdol;
	private JComboBox<Artista> primeiroKIdol;

    private JPanel cadastrarSegundoKIdol;
	private JComboBox<Artista> segundoKIdol;

    private JPanel cadastrarTerceiroKIdol;
	private JComboBox<Artista> terceiroKIdol;

    private JPanel cadastrarQuartoKIdol;
	private JComboBox<Artista> quartoKIdol;

	private JMenuItem botaoOrigem;

	private DefaultComboBoxModel<Grupo> atualizarGrupo;
	private JComboBox<Grupo> grupoSelecionado;

	private JTextField nomeGrupo;

	private ArrayList<Grupo> arrayGrupoAtualizaTela;
	private Grupo[] vetorGrupo;

    private ArrayList<Artista> arrayArtistaAtualizaTela;
	private Artista[] todosArtista;

	private Grupo grupoAserAlterada;

	private JButton atualizar;
	
	public TelaAtualizarGrupo(JMenuItem botaoOrigem, ArrayList<Grupo> arrayGrupo, ArrayList<EmpresaDeKpop> vetorEmpresas, ArrayList<Artista> arrayArtistas) {
		
        this.botaoOrigem = botaoOrigem;

		this.arrayGrupoAtualizaTela = arrayGrupo;

        this.arrayArtistaAtualizaTela = arrayArtistas;

		this.kidolsAnteriores = new Artista[4];

		tela = new JPanel();

		tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

		atualizarTela();

		definicoesTela();

		grupoSelecionado.addActionListener((f) -> {

			if (grupoSelecionado.getSelectedIndex() != -1) {

				Grupo grupo = (Grupo)grupoSelecionado.getSelectedItem();

				nomeGrupo.setText(grupo.getNomeGrupo());
				primeiroKIdol.setSelectedItem(grupo.getVetorArtistas()[0]);
				segundoKIdol.setSelectedItem(grupo.getVetorArtistas()[1]);
				terceiroKIdol.setSelectedItem(grupo.getVetorArtistas()[2]);
				quartoKIdol.setSelectedItem(grupo.getVetorArtistas()[3]);

			}


		});

		atualizar = new JButton("Atualizar");


		atualizar.addActionListener((f) -> {


			try {

				if (grupoSelecionado.getSelectedIndex() == -1 ||
					nomeGrupo.getText().strip().equals("") ||
					primeiroKIdol.getSelectedIndex() == -1 || 
					segundoKIdol.getSelectedIndex() == -1 ||
					terceiroKIdol.getSelectedIndex() == -1 ||
					quartoKIdol.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}

				for (int cont = 0; cont < arrayGrupo.size(); cont++) {
					if (arrayGrupo.get(cont).getNomeGrupo().equals(nomeGrupo.getText()) && arrayGrupo.get(cont).equals((Grupo)grupoSelecionado.getSelectedItem()) == false) {
						throw new SameNameException();
					}
				}

				grupoAserAlterada = (Grupo) grupoSelecionado.getSelectedItem();

				this.nomeAntigo = grupoAserAlterada.getNomeGrupo();

				this.kidolsAnteriores[0] = grupoAserAlterada.getVetorArtistas()[0];
				this.kidolsAnteriores[1] = grupoAserAlterada.getVetorArtistas()[1];
				this.kidolsAnteriores[2] = grupoAserAlterada.getVetorArtistas()[2];
				this.kidolsAnteriores[3] = grupoAserAlterada.getVetorArtistas()[3];

				Artista primeiro = (Artista) primeiroKIdol.getSelectedItem();
				Artista segundo = (Artista) segundoKIdol.getSelectedItem();
				Artista terceiro = (Artista) terceiroKIdol.getSelectedItem();
				Artista quarto = (Artista) quartoKIdol.getSelectedItem();

				
				if(primeiro.equals(kidolsAnteriores[0]) == false && primeiro.equals(kidolsAnteriores[1]) == false &&
				   primeiro.equals(kidolsAnteriores[2]) == false && primeiro.equals(kidolsAnteriores[3]) == false &&
					primeiro.getTemCarreira()){

					throw new KIdolWorkingException();
				}
				
				if(segundo.equals(kidolsAnteriores[0]) == false && segundo.equals(kidolsAnteriores[1]) == false &&
				   segundo.equals(kidolsAnteriores[2]) == false && segundo.equals(kidolsAnteriores[3]) == false &&
					segundo.getTemCarreira()){

					throw new KIdolWorkingException();
				}
				
				if(terceiro.equals(kidolsAnteriores[0]) == false && terceiro.equals(kidolsAnteriores[1]) == false &&
				   terceiro.equals(kidolsAnteriores[2]) == false && terceiro.equals(kidolsAnteriores[3]) == false &&
					terceiro.getTemCarreira()){

					throw new KIdolWorkingException();
				}
				
				if(quarto.equals(kidolsAnteriores[0]) == false && quarto.equals(kidolsAnteriores[1]) == false &&
				   quarto.equals(kidolsAnteriores[2]) == false && quarto.equals(kidolsAnteriores[3]) == false &&
					quarto.getTemCarreira()){

					throw new KIdolWorkingException();
				}
			
				for (int i = 0; i < kidolsAnteriores.length; i++) {
					kidolsAnteriores[i].setTemCarreira(false);
				}

				Artista[] novosArtistas = {primeiro, segundo, terceiro, quarto};
 
				grupoAserAlterada.setNomeGrupo(nomeGrupo.getText());
				grupoAserAlterada.setVetorArtista(novosArtistas);

				for (int i = 0; i < novosArtistas.length; i++){
					novosArtistas[i].setTemCarreira(true);
				}

				atualizarTela();

				grupoSelecionado.setSelectedIndex(-1);
				nomeGrupo.setText("");
				primeiroKIdol.setSelectedIndex(-1);
				segundoKIdol.setSelectedIndex(-1);
				terceiroKIdol.setSelectedIndex(-1);
				quartoKIdol.setSelectedIndex(-1);

				JOptionPane.showMessageDialog(null, "Grupo de K-Pop atualizado com sucesso");

			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (InvalidNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
				erroAtualizar();
			}
			catch (NotANameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
				erroAtualizar();
			}
			catch (SameNameException e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
		});
		tela.add(atualizar);
		
	}

	private void erroAtualizar() {

		try {

			grupoAserAlterada.setNomeGrupo(nomeAntigo);
			grupoAserAlterada.setVetorArtista(this.kidolsAnteriores);
			for (int i = 0; i < kidolsAnteriores.length; i++) {
				kidolsAnteriores[i].setTemCarreira(true);
			}	

		} 
        
        catch(Exception e) {

			e.printStackTrace();

		}
	}

	@Override
	public void definicoesTela() {

		this.atualizarGrupo = new DefaultComboBoxModel<Grupo>(this.vetorGrupo);
		this.grupoSelecionado = new JComboBox<Grupo>(this.atualizarGrupo);
		this.grupoSelecionado.setSelectedIndex(-1);
		this.selecionarGrupo = new JPanel();
		this.selecionarGrupo.setLayout(new FlowLayout());
		this.selecionarGrupo.add(new JLabel("Grupo de K-Pop a ser atualizado"));
		this.selecionarGrupo.add(grupoSelecionado);
		this.tela.add(selecionarGrupo);
		
		this.nomeGrupo = new JTextField(20);
		this.cadastrarNomeGrupo = new JPanel();
		this.cadastrarNomeGrupo.setLayout(new FlowLayout());
		this.cadastrarNomeGrupo.add(new JLabel("Nome do grupo"));
		this.cadastrarNomeGrupo.add(this.nomeGrupo);
		this.tela.add(cadastrarNomeGrupo);

		this.primeiroModelArtistas = new DefaultComboBoxModel<Artista>(this.todosArtista);
		this.primeiroKIdol = new JComboBox<Artista>(primeiroModelArtistas);
		this.primeiroKIdol.setSelectedIndex(-1);
		this.cadastrarPrimeiroKIdol = new JPanel();
		this.cadastrarPrimeiroKIdol.setLayout(new FlowLayout());
		this.cadastrarPrimeiroKIdol.add(new JLabel("Primeiro K-idol"));
		this.cadastrarPrimeiroKIdol.add(primeiroKIdol);
		this.tela.add(cadastrarPrimeiroKIdol);

		this.segundoModelArtistas = new DefaultComboBoxModel<Artista>(this.todosArtista);
		this.segundoKIdol = new JComboBox<Artista>(segundoModelArtistas);
		this.segundoKIdol.setSelectedIndex(-1);
        this.cadastrarSegundoKIdol = new JPanel();
		this.cadastrarSegundoKIdol.setLayout(new FlowLayout());
		this.cadastrarSegundoKIdol.add(new JLabel("Segundo K-idol"));
		this.cadastrarSegundoKIdol.add(segundoKIdol);
		this.tela.add(cadastrarSegundoKIdol);
		
		this.terceiroModelArtistas = new DefaultComboBoxModel<Artista>(this.todosArtista);
		this.terceiroKIdol = new JComboBox<Artista>(terceiroModelArtistas);
		this.terceiroKIdol.setSelectedIndex(-1);
        this.cadastrarTerceiroKIdol = new JPanel();
		this.cadastrarTerceiroKIdol.setLayout(new FlowLayout());
		this.cadastrarTerceiroKIdol.add(new JLabel("Terceiro K-idol"));
		this.cadastrarTerceiroKIdol.add(terceiroKIdol);
		this.tela.add(cadastrarTerceiroKIdol);
		
		this.quartoModelArtistas = new DefaultComboBoxModel<Artista>(this.todosArtista);
		this.quartoKIdol = new JComboBox<Artista>(quartoModelArtistas);
		this.quartoKIdol.setSelectedIndex(-1);
        this.cadastrarQuartoKIdol = new JPanel();
		this.cadastrarQuartoKIdol.setLayout(new FlowLayout());
		this.cadastrarQuartoKIdol.add(new JLabel("Quarto K-idol"));
		this.cadastrarQuartoKIdol.add(quartoKIdol);
		this.tela.add(cadastrarQuartoKIdol);
        
	}

	private void atualizarTela() {

		this.vetorGrupo = new Grupo[arrayGrupoAtualizaTela.size()];
		for (int i = 0; i < arrayGrupoAtualizaTela.size(); i++) {
			vetorGrupo[i] = arrayGrupoAtualizaTela.get(i);
		}

		if (atualizarGrupo != null) {
			for (int i = atualizarGrupo.getSize() - 1; i >= 0; i--) {
				atualizarGrupo.removeElementAt(i);
			}

			for (int i = 0; i < vetorGrupo.length; i++) {
				atualizarGrupo.addElement(vetorGrupo[i]);
			}
		}

		this.todosArtista = new Artista[arrayArtistaAtualizaTela.size()];
		for (int i = 0; i < arrayArtistaAtualizaTela.size(); i++) {
			todosArtista[i] = arrayArtistaAtualizaTela.get(i);
		}

		if (primeiroModelArtistas != null) {
			for (int i = primeiroModelArtistas.getSize() - 1; i >= 0; i--) {
				primeiroModelArtistas.removeElementAt(i);
				segundoModelArtistas.removeElementAt(i);
				terceiroModelArtistas.removeElementAt(i);
				quartoModelArtistas.removeElementAt(i);
			}

			for (int i = 0; i < todosArtista.length; i++) {
				primeiroModelArtistas.addElement(todosArtista[i]);
				segundoModelArtistas.addElement(todosArtista[i]);
				terceiroModelArtistas.addElement(todosArtista[i]);
				quartoModelArtistas.addElement(todosArtista[i]);
			}
		}


	}
	
	@Override
	public JPanel getTela() {

		atualizarTela();
		grupoSelecionado.setSelectedIndex(-1);
		primeiroKIdol.setSelectedIndex(-1);
		segundoKIdol.setSelectedIndex(-1);
		terceiroKIdol.setSelectedIndex(-1);
		quartoKIdol.setSelectedIndex(-1);
		nomeGrupo.setText("");

		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Atualizando um grupo de K-Pop...";
	}

}
