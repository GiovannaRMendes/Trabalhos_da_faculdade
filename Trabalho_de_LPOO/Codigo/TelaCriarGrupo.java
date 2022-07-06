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

public class TelaCriarGrupo implements Janela {

	private JPanel tela;
	private JMenuItem botaoDeOrigem;


    private EmpresaDeKpop[] vetorEmpresas;
    private DefaultComboBoxModel<EmpresaDeKpop> modelEmpresas;

    private Artista[] vetorArtistas;
    private ArrayList<EmpresaDeKpop> arrayEmpresasAtualizar;
    private ArrayList<Artista> arrayArtistasAtualizar;

	private DefaultComboBoxModel<Artista> primeiroModelArtistas;
    private DefaultComboBoxModel<Artista> segundoModelArtistas;
    private DefaultComboBoxModel<Artista> terceiroModelArtistas;
    private DefaultComboBoxModel<Artista> quartoModelArtistas;

	private JPanel cadastrarNomeGrupo;
	private JTextField nomeGrupo;

    private JPanel cadastrarPrimeiroKIdol;
	private JComboBox<Artista> primeiroKIdol;

    private JPanel cadastrarSegundoKIdol;
	private JComboBox<Artista> segundoKIdol;

    private JPanel cadastrarTerceiroKIdol;
	private JComboBox<Artista> terceiroKIdol;

    private JPanel cadastrarQuartoKIdol;
	private JComboBox<Artista> quartoKIdol;

    private JPanel cadastrarEmpresa;
	private JComboBox<EmpresaDeKpop> empresaGrupo;

	private JButton salvar;
	
	public TelaCriarGrupo(JMenuItem botaoDeOrigem, ArrayList<Grupo> grupos, ArrayList<EmpresaDeKpop> vetorEmpresas, ArrayList<Artista> vetorArtistas) {

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
                
                Artista artistasFormamGrupo[] = new Artista[4];
                artistasFormamGrupo[0] = (Artista) this.primeiroKIdol.getSelectedItem();
                artistasFormamGrupo[1] = (Artista) this.segundoKIdol.getSelectedItem();
                artistasFormamGrupo[2] = (Artista) this.terceiroKIdol.getSelectedItem();
                artistasFormamGrupo[3] = (Artista) this.quartoKIdol.getSelectedItem();

				if(this.nomeGrupo.getText().strip().equals("")) {
					throw new MissingInformationException();
				}

				if(this.empresaGrupo.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}
				
				if(this.primeiroKIdol.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}

				if(this.segundoKIdol.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}
				
				if(this.terceiroKIdol.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}

				if(this.quartoKIdol.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}
            
				for (int cont = 0; cont < grupos.size(); cont++){
					if(grupos.get(cont).getNomeGrupo().equals(this.nomeGrupo.getText())){
						throw new SameNameException();
					}
				}

                for(int cont = 0; cont < 4; cont++) {
                    
                    for(int aux = 0; aux < cont; aux++) {
                        if(artistasFormamGrupo[cont].getNomeKidol() == artistasFormamGrupo[aux].getNomeKidol()) {
                            throw new KIdolNameEqualException();

                        } else if (artistasFormamGrupo[cont].getTemCarreira()) {
							throw new KIdolHaveCarreiraException();
						}
                    }
                    
                }
				
				Grupo grupo = new Grupo(this.nomeGrupo.getText(), artistasFormamGrupo);

				grupos.add(grupo);

				for(int cont = 0; cont < 4; cont++) {
					artistasFormamGrupo[cont].setTemCarreira(true);
				}
				EmpresaDeKpop empresa = (EmpresaDeKpop) empresaGrupo.getSelectedItem();
				empresa.getGrupos().add(grupo);
				
				atualizarTela();
				this.nomeGrupo.setText("");
				this.primeiroKIdol.setSelectedIndex(-1);
                this.segundoKIdol.setSelectedIndex(-1);
                this.terceiroKIdol.setSelectedIndex(-1);
                this.quartoKIdol.setSelectedIndex(-1);
                this.empresaGrupo.setSelectedIndex(-1);

				JOptionPane.showMessageDialog(null, "Grupo cadastrado", "Success", 1);

			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);

			}
			catch (InvalidNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
			}
            catch (KIdolNameEqualException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
            }
			catch (KIdolHaveCarreiraException e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (SameNameException e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}

		});
		tela.add(salvar);
		
	}

	@Override
	public void definicoesTela() {

		this.nomeGrupo = new JTextField(10);
		this.cadastrarNomeGrupo = new JPanel(); 
		this.cadastrarNomeGrupo.setLayout(new FlowLayout());
		this.cadastrarNomeGrupo.add(new JLabel("Nome do Grupo"));
		this.cadastrarNomeGrupo.add(nomeGrupo);
		this.tela.add(cadastrarNomeGrupo);

        this.primeiroModelArtistas = new DefaultComboBoxModel<Artista>(this.vetorArtistas);
        this.segundoModelArtistas = new DefaultComboBoxModel<Artista>(this.vetorArtistas);
        this.terceiroModelArtistas = new DefaultComboBoxModel<Artista>(this.vetorArtistas);
        this.quartoModelArtistas = new DefaultComboBoxModel<Artista>(this.vetorArtistas);
		
		this.primeiroKIdol = new JComboBox<Artista>(this.primeiroModelArtistas);
        this.segundoKIdol = new JComboBox<Artista>(this.segundoModelArtistas);
        this.terceiroKIdol = new JComboBox<Artista>(this.terceiroModelArtistas);
        this.quartoKIdol = new JComboBox<Artista>(this.quartoModelArtistas);


        this.modelEmpresas = new DefaultComboBoxModel<EmpresaDeKpop>(this.vetorEmpresas); // Aq

        this.empresaGrupo = new JComboBox<EmpresaDeKpop>(this.modelEmpresas);

        this.empresaGrupo.setSelectedIndex(-1);

		this.cadastrarEmpresa = new JPanel();
		this.cadastrarEmpresa.setLayout(new FlowLayout());
		this.cadastrarEmpresa.add(new JLabel("Empresa do grupo"));
		this.cadastrarEmpresa.add(empresaGrupo);
		this.tela.add(cadastrarEmpresa);

		this.primeiroKIdol.setSelectedIndex(-1);
        this.segundoKIdol.setSelectedIndex(-1);
        this.terceiroKIdol.setSelectedIndex(-1);
        this.quartoKIdol.setSelectedIndex(-1);

		this.cadastrarPrimeiroKIdol = new JPanel();
		this.cadastrarPrimeiroKIdol.setLayout(new FlowLayout());
		this.cadastrarPrimeiroKIdol.add(new JLabel("Primeiro K-idol"));
		this.cadastrarPrimeiroKIdol.add(primeiroKIdol);
		this.tela.add(cadastrarPrimeiroKIdol);

        this.cadastrarSegundoKIdol = new JPanel();
		this.cadastrarSegundoKIdol.setLayout(new FlowLayout());
		this.cadastrarSegundoKIdol.add(new JLabel("Segundo K-idol"));
		this.cadastrarSegundoKIdol.add(segundoKIdol);
		this.tela.add(cadastrarSegundoKIdol);


        this.cadastrarTerceiroKIdol = new JPanel();
		this.cadastrarTerceiroKIdol.setLayout(new FlowLayout());
		this.cadastrarTerceiroKIdol.add(new JLabel("Terceiro K-idol"));
		this.cadastrarTerceiroKIdol.add(terceiroKIdol);
		this.tela.add(cadastrarTerceiroKIdol);


        this.cadastrarQuartoKIdol = new JPanel();
		this.cadastrarQuartoKIdol.setLayout(new FlowLayout());
		this.cadastrarQuartoKIdol.add(new JLabel("Quarto K-idol"));
		this.cadastrarQuartoKIdol.add(quartoKIdol);
		this.tela.add(cadastrarQuartoKIdol);

	}

    private void atualizarTela() {

		this.vetorEmpresas = new EmpresaDeKpop[this.arrayEmpresasAtualizar.size()];
		for (int i = 0; i < this.arrayEmpresasAtualizar.size(); i++) {
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

		if (this.primeiroModelArtistas != null) {
			for (int i = this.primeiroModelArtistas.getSize() - 1; i >= 0; i--) {
				this.primeiroModelArtistas.removeElementAt(i);
			}

			for (int i = 0; i < this.vetorArtistas.length; i++) {
				this.primeiroModelArtistas.addElement(this.vetorArtistas[i]);
			}
		}
		
		if (this.segundoModelArtistas != null) {
			for (int i = this.segundoModelArtistas.getSize() - 1; i >= 0; i--) {
				this.segundoModelArtistas.removeElementAt(i);
			}

			for (int i = 0; i < this.vetorArtistas.length; i++) {
				this.segundoModelArtistas.addElement(this.vetorArtistas[i]);
			}
		}

		if (this.terceiroModelArtistas != null) {
			for (int i = this.terceiroModelArtistas.getSize() - 1; i >= 0; i--) {
				this.terceiroModelArtistas.removeElementAt(i);
			}

			for (int i = 0; i < this.vetorArtistas.length; i++) {
				this.terceiroModelArtistas.addElement(this.vetorArtistas[i]);
			}
		}

		if (this.quartoModelArtistas != null) {
			for (int i = this.quartoModelArtistas.getSize() - 1; i >= 0; i--) {
				this.quartoModelArtistas.removeElementAt(i);
			}

			for (int i = 0; i < this.vetorArtistas.length; i++) {
				this.quartoModelArtistas.addElement(this.vetorArtistas[i]);
			}
		}

	}

	
	@Override
	public JPanel getTela() {
		atualizarTela();
		nomeGrupo.setText("");
		empresaGrupo.setSelectedIndex(-1);
		primeiroKIdol.setSelectedIndex(-1);
        segundoKIdol.setSelectedIndex(-1);
        terceiroKIdol.setSelectedIndex(-1);
        quartoKIdol.setSelectedIndex(-1);

		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoDeOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Criando um novo grupo de Kpop...";
	}

}
