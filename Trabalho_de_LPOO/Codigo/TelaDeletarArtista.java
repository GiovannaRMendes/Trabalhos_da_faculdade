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

public class TelaDeletarArtista implements Janela {
    private JPanel tela;
    private JPanel selecionarArtista;

    private JMenuItem botaoDeOrigem;

    DefaultComboBoxModel<Artista> modelArtista;
    private JComboBox<Artista> artistaSelecionado;

    private ArrayList<Artista> arrayArtistaAtualizaTela;
    private Artista[] vetorArtistas;

    private JButton deletar;

    public TelaDeletarArtista(JMenuItem botaoDeOrigem, ArrayList<Artista> vetorArtistas) {

        this.botaoDeOrigem = botaoDeOrigem;
        this.arrayArtistaAtualizaTela = vetorArtistas;

        this.tela = new JPanel();
        this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

        atualizarTela();

        definicoesTela();

        this.deletar = new JButton("Deletar");
        this.deletar.addActionListener((f) -> {
            try {

                if (artistaSelecionado.getSelectedIndex() == -1) {
                    throw new MissingInformationException();
                }

                Artista artistaDelete = (Artista) artistaSelecionado.getSelectedItem();

                if (artistaDelete.getTemCarreira()) {
                    throw new KIdolWorkingException();
                }

                vetorArtistas.remove(artistaDelete);

                atualizarTela();
                this.artistaSelecionado.setSelectedIndex(-1);

                JOptionPane.showMessageDialog(null, "Artista deletado", "Success", 1);

            } catch (MissingInformationException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);

            } catch (KIdolWorkingException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);

            }
        });
        this.tela.add(deletar);

    }

    private void atualizarTela() {

		this.vetorArtistas = new Artista[arrayArtistaAtualizaTela.size()];

		for (int i = 0; i < arrayArtistaAtualizaTela.size(); i++) {
			vetorArtistas[i] = arrayArtistaAtualizaTela.get(i);

		}
    
        if (modelArtista != null) {

            for (int i = modelArtista.getSize() - 1; i >= 0; i--) {
                
                modelArtista.removeElementAt(i);
				
            }

            for (int i = 0; i < vetorArtistas.length; i++) {

                modelArtista.addElement(vetorArtistas[i]);

            }
        }
    }

	@Override
    public void definicoesTela() {
        
        this.modelArtista = new DefaultComboBoxModel<Artista>(this.vetorArtistas);
        this.artistaSelecionado = new JComboBox<Artista>(modelArtista);
        this.selecionarArtista = new JPanel();
        this.selecionarArtista.setLayout(new FlowLayout());
        this.selecionarArtista.add(new JLabel("Artista a ser deletado"));
		this.artistaSelecionado.setSelectedIndex(-1);
        this.selecionarArtista.add(artistaSelecionado);
        this.tela.add(selecionarArtista);
		
    }

    @Override
    public JPanel getTela() {
        atualizarTela();
        this.artistaSelecionado.setSelectedIndex(-1);
        return this.tela;
    }

    @Override
    public JMenuItem getSource() {

        return this.botaoDeOrigem;

    }

    @Override
    public String getMensagemStatus() {
        return "Deletando um artista...";
    }

}
