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

public class TelaDeletarShow implements Janela {
    private JPanel tela;
    private JPanel selecionarShow;

    private JMenuItem botaoDeOrigem;

    DefaultComboBoxModel<Show> modelShow;
    private JComboBox<Show> showSelecionado;

    private ArrayList<Show> arrayShowAtualizaTela;
    private Show[] vetorShow;

    private JButton deletar;

    public TelaDeletarShow(JMenuItem botaoDeOrigem, ArrayList<Solista> vetorSolistas, ArrayList<Grupo> vetorGrupos, ArrayList<Show> vetorShow) {

        this.botaoDeOrigem = botaoDeOrigem;
        this.arrayShowAtualizaTela = vetorShow;

        this.tela = new JPanel();
        this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

        atualizarTela();

        definicoesTela();

        this.deletar = new JButton("Deletar");
        this.deletar.addActionListener((f) -> {
            try {

                if (showSelecionado.getSelectedIndex() == -1) {
                    throw new MissingInformationException();
                }

                Show showDelete = (Show) showSelecionado.getSelectedItem();

                for (int cont = 0; cont < vetorSolistas.size(); cont++) {

                    if (vetorSolistas.get(cont).getShows().contains(showDelete)) {
                        vetorSolistas.get(cont).getShows().remove(showDelete);
                    }

                }

                for (int cont = 0; cont < vetorGrupos.size(); cont++) {

                    if (vetorGrupos.get(cont).getShows().contains(showDelete)) {
                        vetorGrupos.get(cont).getShows().remove(showDelete);
                        
                    }
                    
                }

                vetorShow.remove(showDelete);

                atualizarTela();
                this.showSelecionado.setSelectedIndex(-1);

                JOptionPane.showMessageDialog(null, "Show deletado.\nVamos jogar Uno em casa mesmo", "Success", 1);

            } catch (MissingInformationException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);

            }
        });
        this.tela.add(deletar);

    }

    private void atualizarTela() {

		this.vetorShow = new Show[arrayShowAtualizaTela.size()];

		for (int i = 0; i < arrayShowAtualizaTela.size(); i++) {
			vetorShow[i] = arrayShowAtualizaTela.get(i);

		}
    
        if (modelShow != null) {

            for (int i = modelShow.getSize() - 1; i >= 0; i--) {
                
                modelShow.removeElementAt(i);
				
            }

            for (int i = 0; i < vetorShow.length; i++) {

                modelShow.addElement(vetorShow[i]);

            }
        }
    }

	@Override
    public void definicoesTela() {
        
        this.modelShow = new DefaultComboBoxModel<Show>(this.vetorShow);
        this.showSelecionado = new JComboBox<Show>(modelShow);
        this.selecionarShow = new JPanel();
        this.selecionarShow.setLayout(new FlowLayout());
        this.selecionarShow.add(new JLabel("Show a ser deletado"));
		this.showSelecionado.setSelectedIndex(-1);
        this.selecionarShow.add(showSelecionado);
        this.tela.add(selecionarShow);
		
    }

    @Override
    public JPanel getTela() {
        atualizarTela();
        this.showSelecionado.setSelectedIndex(-1);
        return this.tela;
    }

    @Override
    public JMenuItem getSource() {

        return this.botaoDeOrigem;

    }

    @Override
    public String getMensagemStatus() {
        return "Deletando um show...";
    }

}
