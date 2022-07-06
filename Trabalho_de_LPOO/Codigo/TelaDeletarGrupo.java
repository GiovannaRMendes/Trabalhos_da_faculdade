import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;

public class TelaDeletarGrupo implements Janela {
    private JPanel tela;
    private JPanel selecionarGrupo;

    private JMenuItem botaoDeOrigem;

    DefaultComboBoxModel<Grupo> modelGrupo;
    private JComboBox<Grupo> grupoSelecionado;

    private ArrayList<Grupo> arrayGrupoAtualizaTela;
    private Grupo[] vetorGrupo;

    private JButton deletar;

    public TelaDeletarGrupo(JMenuItem botaoDeOrigem, ArrayList<Grupo> vetorGrupo, ArrayList<EmpresaDeKpop> vetorEmpresa) {

        this.botaoDeOrigem = botaoDeOrigem;
        this.arrayGrupoAtualizaTela = vetorGrupo;

        this.tela = new JPanel();
        this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

        atualizarTela();

        definicoesTela();

        this.deletar = new JButton("Deletar");

        this.deletar.addActionListener((f) -> {
            try {

                if (grupoSelecionado.getSelectedIndex() == -1) {
                    throw new MissingInformationException();
                }                

                Grupo grupoDelete = (Grupo) grupoSelecionado.getSelectedItem();

                for(int cont = 0; cont < vetorGrupo.size(); cont++) {

                    if(vetorGrupo.get(cont).equals(grupoDelete)) {
                        
                        if(vetorGrupo.get(cont).getShows().size() > 0) {
                            throw new InvalidExclusionException();
                        }

                    }
                }

                vetorGrupo.remove(grupoDelete);
                
                Artista[] vetorArtistas = grupoDelete.getVetorArtistas();

                for (int i = 0; i < vetorArtistas.length; i++) {
                    vetorArtistas[i].setTemCarreira(false);
                }

                for (int i = 0; i < vetorEmpresa.size(); i++) {
                    ArrayList<Grupo> vetorGrupos = vetorEmpresa.get(i).getGrupos();

                    for (int j = 0; j < vetorGrupos.size(); j++){
                        if (vetorGrupos.get(j).getNomeGrupo().equals(grupoDelete.getNomeGrupo())) {
                            vetorGrupos.remove(j);
                        }
                    }

                }

                atualizarTela();
                this.grupoSelecionado.setSelectedIndex(-1);

 
                JOptionPane.showMessageDialog(null, "Grupo deletado!\nParabens, o mundo está mais triste hoje por sua causa.\nSim, somos fans de bandas mortas.", "Success", 1);

            } catch (MissingInformationException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);

            } catch(InvalidExclusionException e)  {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
            }
        });
        this.tela.add(deletar);

    }

    private void atualizarTela() {

		this.vetorGrupo = new Grupo[arrayGrupoAtualizaTela.size()];

		for (int i = 0; i < arrayGrupoAtualizaTela.size(); i++) {
			vetorGrupo[i] = arrayGrupoAtualizaTela.get(i);

		}
    
        if (modelGrupo != null) { // Se ele já esta criado e precisa ser atualizado

            for (int i = modelGrupo.getSize() - 1; i >= 0; i--) {
                
                modelGrupo.removeElementAt(i);
				
            }

            for (int i = 0; i < vetorGrupo.length; i++) {

                modelGrupo.addElement(vetorGrupo[i]);

            }
        }
    }

	@Override
    public void definicoesTela() {
        
        this.modelGrupo = new DefaultComboBoxModel<Grupo>(this.vetorGrupo);
        this.grupoSelecionado = new JComboBox<Grupo>(modelGrupo);
        this.selecionarGrupo = new JPanel();
        this.selecionarGrupo.setLayout(new FlowLayout());
        this.selecionarGrupo.add(new JLabel("Grupo a ser deletado"));
		this.grupoSelecionado.setSelectedIndex(-1);
        this.selecionarGrupo.add(grupoSelecionado);
        this.tela.add(selecionarGrupo);
		
    }

    @Override
    public JPanel getTela() {
        atualizarTela();
        this.grupoSelecionado.setSelectedIndex(-1);
        return this.tela;
    }

    @Override
    public JMenuItem getSource() {

        return this.botaoDeOrigem;

    }

    @Override
    public String getMensagemStatus() {
        return "Deletando um grupo de Kpop...";
    }

}
