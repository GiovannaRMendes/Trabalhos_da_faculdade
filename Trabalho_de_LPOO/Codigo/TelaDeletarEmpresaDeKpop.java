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

public class TelaDeletarEmpresaDeKpop implements Janela {
    private JPanel tela;
    private JPanel selecionarEmpresaDeKpop;

    private JMenuItem botaoDeOrigem;

    DefaultComboBoxModel<EmpresaDeKpop> modelEmpresaDeKpop;
    private JComboBox<EmpresaDeKpop> empresaDeKpopSelecionada;

    private ArrayList<EmpresaDeKpop> arrayEmpresaDeKpopAtualizaTela;
    private EmpresaDeKpop[] vetorEmpresaDeKpop;

    private JButton deletar;

    public TelaDeletarEmpresaDeKpop(JMenuItem botaoDeOrigem, ArrayList<EmpresaDeKpop> vetorEmpresaDeKpop) {

        this.botaoDeOrigem = botaoDeOrigem;
        this.arrayEmpresaDeKpopAtualizaTela = vetorEmpresaDeKpop;

        this.tela = new JPanel();
        this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

        atualizarTela();

        definicoesTela();

        this.deletar = new JButton("Deletar");
        this.deletar.addActionListener((f) -> {
            try {

                if (empresaDeKpopSelecionada.getSelectedIndex() == -1) {
                    throw new MissingInformationException();
                }

                EmpresaDeKpop empresaDeKpopDelete = (EmpresaDeKpop) empresaDeKpopSelecionada.getSelectedItem();

                if ((empresaDeKpopDelete.getGrupos().size() != 0) || (empresaDeKpopDelete.getSolistas().size() != 0))  {
                    throw new CorporationHaveKIdolsException();
                }

                vetorEmpresaDeKpop.remove(empresaDeKpopDelete);

                atualizarTela();

                this.empresaDeKpopSelecionada.setSelectedIndex(-1);

                JOptionPane.showMessageDialog(null, "Empresa deletada\nDezenas de desempregados!", "Success", 1);

            } catch (MissingInformationException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);

            } catch (CorporationHaveKIdolsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);

            }
        });
        this.tela.add(deletar);

    }

    private void atualizarTela() {

		this.vetorEmpresaDeKpop = new EmpresaDeKpop[arrayEmpresaDeKpopAtualizaTela.size()];

		for (int i = 0; i < arrayEmpresaDeKpopAtualizaTela.size(); i++) {
			vetorEmpresaDeKpop[i] = arrayEmpresaDeKpopAtualizaTela.get(i);

		}
    
        if (modelEmpresaDeKpop != null) {

            for (int i = modelEmpresaDeKpop.getSize() - 1; i >= 0; i--) {
                
                modelEmpresaDeKpop.removeElementAt(i);
				
            }

            for (int i = 0; i < vetorEmpresaDeKpop.length; i++) {

               modelEmpresaDeKpop.addElement(vetorEmpresaDeKpop[i]);

            }
        }
    }

	@Override
    public void definicoesTela() {
        
        this.modelEmpresaDeKpop = new DefaultComboBoxModel<EmpresaDeKpop>(this.vetorEmpresaDeKpop);
        this.empresaDeKpopSelecionada = new JComboBox<EmpresaDeKpop>(modelEmpresaDeKpop);
        this.selecionarEmpresaDeKpop = new JPanel();
        this.selecionarEmpresaDeKpop.setLayout(new FlowLayout());
        this.selecionarEmpresaDeKpop.add(new JLabel("Empresa ser deletado"));
		this.empresaDeKpopSelecionada.setSelectedIndex(-1);
        this.selecionarEmpresaDeKpop.add(empresaDeKpopSelecionada);
        this.tela.add(selecionarEmpresaDeKpop);
		
    }

    @Override
    public JPanel getTela() {
        atualizarTela();
        this.empresaDeKpopSelecionada.setSelectedIndex(-1);
        return this.tela;
    }

    @Override
    public JMenuItem getSource() {

        return this.botaoDeOrigem;

    }

    @Override
    public String getMensagemStatus() {
        return "Deletando uma empresa de Kpop...";
    }

}
