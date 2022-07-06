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


public class TelaAtualizarEmpresaDeKpop implements Janela {
	private JPanel tela;
	private JPanel selecionarEmpresaDeKpop;
	private JPanel cadastrarNomeDono;
	private JPanel cadastrarNomeEmpresaDeKpop;

	private JMenuItem botaoOrigem;

	private DefaultComboBoxModel<EmpresaDeKpop> atualizarEmpresaDeKpop;
	private JComboBox<EmpresaDeKpop> empresaDeKpopSelecionado;

	private JTextField nomeDono;
	private JTextField nomeEmpresaDeKpop;

	private ArrayList<EmpresaDeKpop> arrayEmpresaDeKpopAtualizaTela;
	private EmpresaDeKpop[] vetorEmpresaDeKpop;

	private EmpresaDeKpop empresaAserAlterada;

	private JButton atualizar;

	private String[] informacoesAntigas;
	
	public TelaAtualizarEmpresaDeKpop(JMenuItem botaoOrigem, ArrayList<EmpresaDeKpop> arrayEmpresaDeKpop) {
		this.botaoOrigem = botaoOrigem;
		this.arrayEmpresaDeKpopAtualizaTela = arrayEmpresaDeKpop;
		tela = new JPanel();
		tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));

		atualizarTela();

		definicoesTela();

		empresaDeKpopSelecionado.addActionListener((f) -> {
			if (empresaDeKpopSelecionado.getSelectedIndex() != -1) {
				EmpresaDeKpop empresa = (EmpresaDeKpop)empresaDeKpopSelecionado.getSelectedItem();
				nomeDono.setText(empresa.getNomeDono());
				nomeEmpresaDeKpop.setText(empresa.getNomeEmpresa());

			}
		});

		atualizar = new JButton("Atualizar");
		atualizar.addActionListener((f) -> {
			try {

				if (empresaDeKpopSelecionado.getSelectedIndex() == -1 ||
					nomeDono.getText().strip().equals("") ||
					nomeEmpresaDeKpop.getText().strip().equals("") ||
					empresaDeKpopSelecionado.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}

				empresaAserAlterada = (EmpresaDeKpop) empresaDeKpopSelecionado.getSelectedItem();
				informacoesAntigas = new String[2];
				informacoesAntigas[0] = empresaAserAlterada.getNomeDono();
				informacoesAntigas[1] = empresaAserAlterada.getNomeEmpresa();

				empresaAserAlterada.setNomeDono(nomeDono.getText());
				empresaAserAlterada.setNomeEmpresa(nomeEmpresaDeKpop.getText());

				atualizarTela();

				empresaDeKpopSelecionado.setSelectedIndex(-1);
				nomeDono.setText("");
				nomeEmpresaDeKpop.setText("");

				JOptionPane.showMessageDialog(null, "Empresa de K-Pop atualizada com sucesso");
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
		});
		tela.add(atualizar);
		
	}

	private void erroAtualizar() {
		try {

			empresaAserAlterada.setNomeDono(informacoesAntigas[0]);	
			empresaAserAlterada.setNomeEmpresa(informacoesAntigas[1]);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void definicoesTela() {

		this.atualizarEmpresaDeKpop = new DefaultComboBoxModel<EmpresaDeKpop>(vetorEmpresaDeKpop);
		this.empresaDeKpopSelecionado = new JComboBox<EmpresaDeKpop>(atualizarEmpresaDeKpop);
		this.empresaDeKpopSelecionado.setSelectedIndex(-1);
		this.selecionarEmpresaDeKpop = new JPanel();
		this.selecionarEmpresaDeKpop.setLayout(new FlowLayout());
		this.selecionarEmpresaDeKpop.add(new JLabel("Empresa de K-Pop a ser atualizado"));
		this.selecionarEmpresaDeKpop.add(empresaDeKpopSelecionado);
		this.tela.add(selecionarEmpresaDeKpop);
		
		this.nomeDono = new JTextField(20);
		this.cadastrarNomeDono = new JPanel();
		this.cadastrarNomeDono.setLayout(new FlowLayout());
		this.cadastrarNomeDono.add(new JLabel("Nome do dono da empresa"));
		this.cadastrarNomeDono.add(nomeDono);
		this.tela.add(cadastrarNomeDono);

		this.nomeEmpresaDeKpop = new JTextField(20);
		this.cadastrarNomeEmpresaDeKpop = new JPanel();
		this.cadastrarNomeEmpresaDeKpop.setLayout(new FlowLayout());
		this.cadastrarNomeEmpresaDeKpop.add(new JLabel("Nome da empresa de kpop"));
		this.cadastrarNomeEmpresaDeKpop.add(nomeEmpresaDeKpop);
		this.tela.add(cadastrarNomeEmpresaDeKpop);

	}

	private void atualizarTela() {

		this.vetorEmpresaDeKpop = new EmpresaDeKpop[arrayEmpresaDeKpopAtualizaTela.size()];
		for (int i = 0; i < arrayEmpresaDeKpopAtualizaTela.size(); i++) {
			vetorEmpresaDeKpop[i] = arrayEmpresaDeKpopAtualizaTela.get(i);
		}

		if (atualizarEmpresaDeKpop != null) {
			for (int i = atualizarEmpresaDeKpop.getSize() - 1; i >= 0; i--) {
				atualizarEmpresaDeKpop.removeElementAt(i);
			}

			for (int i = 0; i < vetorEmpresaDeKpop.length; i++) {
				atualizarEmpresaDeKpop.addElement(vetorEmpresaDeKpop[i]);
			}
		}

	}
	
	@Override
	public JPanel getTela() {
		atualizarTela();
		empresaDeKpopSelecionado.setSelectedIndex(-1);
		nomeDono.setText("");
		nomeEmpresaDeKpop.setText("");

		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Atualizando uma empresa de kpop...";
	}

}
