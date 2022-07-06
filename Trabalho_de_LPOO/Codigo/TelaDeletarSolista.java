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

public class TelaDeletarSolista implements Janela {
    private JPanel tela;
    private JPanel selecionarSolista;

    private JMenuItem botaoDeOrigem;

    DefaultComboBoxModel<Solista> modelSolista;
    private JComboBox<Solista> solistaSelecionado;

    private ArrayList<Solista> arraySolistaAtualizaTela;

    DefaultComboBoxModel<Show> modelShow;
    

    private Solista[] vetorSolista;
   

    private JButton deletar;

    public TelaDeletarSolista(JMenuItem botaoDeOrigem, ArrayList<Solista> vetorSolistas, ArrayList<Artista> vetorArtistas, ArrayList<EmpresaDeKpop> vetorEmpresaDeKpop) {

        this.botaoDeOrigem = botaoDeOrigem;
        this.arraySolistaAtualizaTela = vetorSolistas;

        this.tela = new JPanel();
        this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

        atualizarTela();

        definicoesTela();

        this.deletar = new JButton("Deletar");
        this.deletar.addActionListener((f) -> {

            try {

                if (solistaSelecionado.getSelectedIndex() == -1) {
                    throw new MissingInformationException();
                }

                Solista solistaDelete = (Solista) solistaSelecionado.getSelectedItem();

                for (int cont = 0; cont < vetorSolistas.size(); cont++) {

                    if (vetorSolistas.get(cont).equals(solistaDelete)) {
                        
                        if (vetorSolistas.get(cont).getShows().size() > 0) {
                            throw new InvalidExclusionException();
                        }

                    }
                }

                for (int cont = 0; cont < vetorEmpresaDeKpop.size(); cont++) {

                    for (int aux = 0; aux < vetorEmpresaDeKpop.get(cont).getSolistas().size(); aux++) {
                        if (vetorEmpresaDeKpop.get(cont).getSolistas().get(aux).equals(solistaDelete)) {
                            vetorEmpresaDeKpop.get(cont).getSolistas().remove(solistaDelete);
                        }
                    }
                   
                }

                for (int cont = 0; cont < vetorArtistas.size(); cont++) {

                    if (vetorArtistas.get(cont).equals(solistaDelete.getSolista())) {
                        solistaDelete.getSolista().setTemCarreira(false);
                    }

                }

                vetorSolistas.remove(solistaDelete);

                atualizarTela();
                this.solistaSelecionado.setSelectedIndex(-1);

                JOptionPane.showMessageDialog(null, "Solista deletado\nAgora ele realmente está solo...", "Success", 1);

            } catch (MissingInformationException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);

            } catch (KIdolWorkingException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);

            } catch(InvalidExclusionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
            }
        });
        this.tela.add(deletar);

    }

    private void atualizarTela() {

		this.vetorSolista = new Solista[arraySolistaAtualizaTela.size()];

		for (int i = 0; i < arraySolistaAtualizaTela.size(); i++) {
			vetorSolista[i] = arraySolistaAtualizaTela.get(i);

		}
    
        if (modelSolista != null) {

            for (int i = modelSolista.getSize() - 1; i >= 0; i--) {
                
                modelSolista.removeElementAt(i);
				
            }

            for (int i = 0; i < vetorSolista.length; i++) {

                modelSolista.addElement(vetorSolista[i]);

            }
        }

    }

	@Override
    public void definicoesTela() {
        
        this.modelSolista = new DefaultComboBoxModel<Solista>(this.vetorSolista);
        this.solistaSelecionado = new JComboBox<Solista>(modelSolista);
        this.selecionarSolista = new JPanel();
        this.selecionarSolista.setLayout(new FlowLayout());
        this.selecionarSolista.add(new JLabel("Solista a ser deletado"));
		this.solistaSelecionado.setSelectedIndex(-1);
        this.selecionarSolista.add(solistaSelecionado);
        this.tela.add(selecionarSolista);
		
    }

    @Override
    public JPanel getTela() {
        atualizarTela();
        this.solistaSelecionado.setSelectedIndex(-1);
        return this.tela;
    }

    @Override
    public JMenuItem getSource() {

        return this.botaoDeOrigem;

    }

    @Override
    public String getMensagemStatus() {
        return "Deletando um Solista...";
    }

}
