import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCriarEmpresaDeKpop implements Janela {

	private JPanel tela;
	private JMenuItem botaoDeOrigem;

	private JPanel cadastrarNomeDono;
	private JTextField nomeDono;

	private JPanel cadastrarNomeEmpresa;
	private JTextField nomeEmpresa;

	private JButton salvar;
	
	public TelaCriarEmpresaDeKpop(JMenuItem botaoDeOrigem, ArrayList<EmpresaDeKpop> empresas) {
		this.botaoDeOrigem = botaoDeOrigem;

		this.tela = new JPanel(); // Essa tela inteira
		this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));
		
		definicoesTela();
		
		this.salvar = new JButton("Salvar");
		this.salvar.addActionListener((f) -> {
			try {

				if (nomeDono.getText().strip().equals("") || nomeEmpresa.getText().strip().equals("")) {
					throw new MissingInformationException();
				}
				
				EmpresaDeKpop empresa = new EmpresaDeKpop(nomeDono.getText(), nomeEmpresa.getText());

				empresas.add(empresa);
				
				nomeDono.setText("");
				nomeEmpresa.setText("");

				JOptionPane.showMessageDialog(null, "Empresa cadastrada", "Success", 1);
			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);

			}
			catch (NotANameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);

			}
			catch (InvalidNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);

			}

		});
		tela.add(salvar);
		
	}

	@Override
	public void definicoesTela() {
		this.nomeDono = new JTextField(40);
		this.cadastrarNomeDono = new JPanel(); 
		this.cadastrarNomeDono.setLayout(new FlowLayout());
		this.cadastrarNomeDono.add(new JLabel("Nome do dono na empresa"));
		this.cadastrarNomeDono.add(nomeDono);
		this.tela.add(cadastrarNomeDono);

		this.nomeEmpresa = new JTextField(40);
		this.cadastrarNomeEmpresa = new JPanel(); 
		this.cadastrarNomeEmpresa.setLayout(new FlowLayout());
		this.cadastrarNomeEmpresa.add(new JLabel("Nome da empresa de Kpop"));
		this.cadastrarNomeEmpresa.add(nomeEmpresa);
		this.tela.add(cadastrarNomeEmpresa);
	}
	
	@Override
	public JPanel getTela() {
		nomeDono.setText("");
		nomeEmpresa.setText("");

		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoDeOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Criando uma nova empresa de Kpop...";
	}

}
