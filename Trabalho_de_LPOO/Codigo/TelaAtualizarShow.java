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

public class TelaAtualizarShow implements Janela {

	private JPanel tela;
	private JMenuItem botaoDeOrigem;

	private JPanel selecionarShow;
	private DefaultComboBoxModel<Show> modelShow;
	private JComboBox<Show> show;
	private Show[] vetorShows;

	private JPanel cadastrarLocal;
	private JTextField local;

	private JPanel cadastrarDuracao;
	private JTextField duracao;

	private JPanel cadastrarObj1;
	private DefaultComboBoxModel<Object> modelObj1;
	private JComboBox<Object> selecionarObj1;
	private JButton botaoLimparObj1;

	private JPanel cadastrarObj2;
	private DefaultComboBoxModel<Object> modelObj2;
	private JComboBox<Object> selecionarObj2;
	private JButton botaoLimparObj2;
	
	private JPanel cadastrarObj3;
	private DefaultComboBoxModel<Object> modelObj3;
	private JComboBox<Object> selecionarObj3;
	private JButton botaoLimparObj3;

	private Object[] vetorObj;
	private Object[] vetorObjAntigo;

	private ArrayList<Show> shows;
	private ArrayList<Solista> solistas;
	private ArrayList<Grupo> grupos;

	private String antigoLocal;
	private int antigaDuracao;

	private JButton atualizar;
	
	public TelaAtualizarShow(JMenuItem botaoDeOrigem, ArrayList<Show> shows, ArrayList<Solista> solistas, ArrayList<Grupo> grupos) {
		this.botaoDeOrigem = botaoDeOrigem;
		this.shows = shows;
		this.solistas = solistas;
		this.grupos = grupos;

		this.tela = new JPanel(); 
		this.tela.setLayout(new BoxLayout(tela, BoxLayout.Y_AXIS));
		
		atualizarTela();
		
		definicoesTela();
		
		show.addActionListener( (e) -> {
			if (show.getSelectedIndex() != -1) {

				Show showAtual = (Show) show.getSelectedItem();

				this.local.setText(showAtual.getLocal());
				this.duracao.setText(showAtual.getDuracao() + "");
				this.selecionarObj1.setSelectedItem(showAtual.getVetorObj()[0]);
				this.selecionarObj2.setSelectedItem(showAtual.getVetorObj()[1]);
				this.selecionarObj3.setSelectedItem(showAtual.getVetorObj()[2]);

			}
		});

		this.atualizar = new JButton("Atualizar");
		this.atualizar.addActionListener((f) -> {

			try {
				if (show.getSelectedIndex() == -1 || local.getText().strip().equals("") || duracao.getText().strip().equals("")) {
					throw new MissingInformationException();

				} else if (selecionarObj1.getSelectedIndex() == -1 && selecionarObj2.getSelectedIndex() == -1 && selecionarObj3.getSelectedIndex() == -1) {
					throw new MissingInformationException();
				}

				if (selecionarObj1.getSelectedIndex() != -1) {

					if (selecionarObj1.getSelectedIndex() == selecionarObj2.getSelectedIndex() || selecionarObj1.getSelectedIndex() == selecionarObj3.getSelectedIndex()) {
						throw new SameGroupOrSoloException();
					}	

				} 
				if (selecionarObj2.getSelectedIndex() != -1) {
					
					if (selecionarObj2.getSelectedIndex() == selecionarObj3.getSelectedIndex() || selecionarObj2.getSelectedIndex() == selecionarObj1.getSelectedIndex()) {
						throw new SameGroupOrSoloException();
					}

				}  
				
				Show showAlterando = (Show) show.getSelectedItem();

				antigaDuracao = showAlterando.getDuracao();
				antigoLocal = showAlterando.getLocal();

				Object[] vetorObj = new Object[3];

				vetorObj[0] = selecionarObj1.getSelectedItem();
				vetorObj[1] = selecionarObj2.getSelectedItem();
				vetorObj[2] = selecionarObj3.getSelectedItem();
				
				vetorObjAntigo = showAlterando.getVetorObj();

				for (int cont = 0; cont < vetorObjAntigo.length; cont++) {
					if (vetorObjAntigo[cont] != null && vetorObjAntigo[cont] instanceof Solista) {
						
						((Solista)vetorObjAntigo[cont]).getShows().remove(showAlterando);
						
					} 
					
					else if (vetorObjAntigo[cont] != null && vetorObjAntigo[cont] instanceof Grupo) {

						((Grupo)vetorObjAntigo[cont]).getShows().remove(showAlterando);
						
					}
					
				}

				showAlterando.setLocal(local.getText());
				showAlterando.setDuracao(Integer.parseInt(duracao.getText()));
				showAlterando.setVetorObj(vetorObj);

				for (int cont = 0; cont < vetorObj.length; cont++) {
					if (vetorObj[cont] != null && vetorObj[cont] instanceof Solista) {
						
						((Solista)vetorObj[cont]).getShows().add(showAlterando);
						
					} 
					
					else if (vetorObj[cont] != null && vetorObj[cont] instanceof Grupo) {

						((Grupo)vetorObj[cont]).getShows().add(showAlterando);
						
					}
					
				}

				atualizarTela();

				show.setSelectedIndex(-1);
				local.setText("");
				duracao.setText("");
				this.selecionarObj1.setSelectedIndex(-1);
				this.selecionarObj2.setSelectedIndex(-1);
				this.selecionarObj3.setSelectedIndex(-1);

				JOptionPane.showMessageDialog(null, "Show atualizado", "Success", 1);
			}
			catch (MissingInformationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (InvalidNameException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
				erroAtualizar();
			}
            catch (InvalidDurationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
				erroAtualizar();
			}
			catch (SameGroupOrSoloException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", 2);
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "O campo duração só pode receber números", "Warning", 2);

			}

		});
		tela.add(atualizar);
		
	}

	private void erroAtualizar() {

		Show showAtual = (Show) show.getSelectedItem();

		showAtual.setLocal(antigoLocal);
		showAtual.setDuracao(antigaDuracao);
		showAtual.setVetorObj(vetorObjAntigo);

		for (int cont = 0; cont < vetorObjAntigo.length; cont++) {
			if (vetorObjAntigo[cont] != null && vetorObjAntigo[cont] instanceof Solista) {
				
				((Solista)vetorObjAntigo[cont]).getShows().add(showAtual);
				
			} 
			
			else if (vetorObjAntigo[cont] != null && vetorObjAntigo[cont] instanceof Grupo) {

				((Grupo)vetorObjAntigo[cont]).getShows().add(showAtual);
				
			}
			
		}

 	}

	private void atualizarTela() {

		vetorShows = new Show[shows.size()];

		for (int i = 0; i < vetorShows.length; i++) {
			vetorShows[i] = shows.get(i);
		}

		if (modelShow != null) {
			for (int i = modelShow.getSize() - 1; i >= 0; i--) {
				
				modelShow.removeElementAt(i);
				
			}

			for (int i = 0; i < vetorShows.length; i++) {

				modelShow.addElement(vetorShows[i]);
				
			}
		}

		vetorObj = new Object[solistas.size() + grupos.size()];

		for (int i = 0; i < vetorObj.length; i++) {
			if (i < solistas.size()) { // 0 - 9
				vetorObj[i] = solistas.get(i);

			} else { 
				vetorObj[i] = grupos.get(i - solistas.size());
			}
		}

		if (modelObj1 != null) {

			for (int i = modelObj1.getSize() - 1; i >= 0; i--) {
				
				modelObj1.removeElementAt(i);
				modelObj2.removeElementAt(i);
				modelObj3.removeElementAt(i);
				
			}

			for (int i = 0; i < vetorObj.length; i++) {

				modelObj1.addElement(vetorObj[i]);
				modelObj2.addElement(vetorObj[i]);
				modelObj3.addElement(vetorObj[i]);

			}
			
		}

	}

	@Override
	public void definicoesTela() {

		this.modelShow = new DefaultComboBoxModel<Show>(this.vetorShows);
		this.show = new JComboBox<Show>(this.modelShow);
		this.show.setSelectedIndex(-1);
		this.selecionarShow = new JPanel();
		this.selecionarShow.setLayout(new FlowLayout());
		this.selecionarShow.add(new JLabel("Show para atualizar"));
		this.selecionarShow.add(show);
		this.tela.add(selecionarShow);

		this.local = new JTextField(40);
		this.cadastrarLocal = new JPanel(); 
		this.cadastrarLocal.setLayout(new FlowLayout());
		this.cadastrarLocal.add(new JLabel("Nome do local"));
		this.cadastrarLocal.add(local);
		this.tela.add(cadastrarLocal);

		this.duracao = new JTextField(40);
		this.cadastrarDuracao = new JPanel(); 
		this.cadastrarDuracao.setLayout(new FlowLayout());
		this.cadastrarDuracao.add(new JLabel("Duração do show"));
		this.cadastrarDuracao.add(duracao);
		this.tela.add(cadastrarDuracao);

		this.modelObj1 = new DefaultComboBoxModel<Object>(this.vetorObj);
		this.selecionarObj1 = new JComboBox<Object>(this.modelObj1);
		this.selecionarObj1.setSelectedIndex(-1);
		this.cadastrarObj1 = new JPanel();
		this.cadastrarObj1.setLayout(new FlowLayout());
		this.cadastrarObj1.add(new JLabel("Solista/Grupo"));
		this.cadastrarObj1.add(selecionarObj1);
		this.botaoLimparObj1 = new JButton("Limpar");
        
		this.botaoLimparObj1.addActionListener((e) -> {
			selecionarObj1.setSelectedIndex(-1);
		});

		this.cadastrarObj1.add(botaoLimparObj1);
		this.tela.add(cadastrarObj1);

        this.modelObj2 = new DefaultComboBoxModel<Object>(this.vetorObj);
        this.selecionarObj2 = new JComboBox<Object>(this.modelObj2);
		this.selecionarObj2.setSelectedIndex(-1);
        this.cadastrarObj2 = new JPanel();
        this.cadastrarObj2.setLayout(new FlowLayout());
        this.cadastrarObj2.add(new JLabel("Solista/Grupo"));
        this.cadastrarObj2.add(selecionarObj2);
		this.botaoLimparObj2 = new JButton("Limpar");

		this.botaoLimparObj2.addActionListener((e) -> {
			selecionarObj2.setSelectedIndex(-1);
		});
        
		this.cadastrarObj2.add(botaoLimparObj2);
        this.tela.add(cadastrarObj2);

        this.modelObj3 = new DefaultComboBoxModel<Object>(this.vetorObj);
        this.selecionarObj3 = new JComboBox<Object>(this.modelObj3);
		this.selecionarObj3.setSelectedIndex(-1);
        this.cadastrarObj3 = new JPanel();
        this.cadastrarObj3.setLayout(new FlowLayout());
        this.cadastrarObj3.add(new JLabel("Solista/Grupo"));
		this.cadastrarObj3.add(selecionarObj3);
		this.botaoLimparObj3 = new JButton("Limpar");
        
		this.botaoLimparObj3.addActionListener((e) -> {
			selecionarObj3.setSelectedIndex(-1);
		});
        
		this.cadastrarObj3.add(botaoLimparObj3);
		this.tela.add(cadastrarObj3);

	}
	
	@Override
	public JPanel getTela() {
		atualizarTela();
		
		this.show.setSelectedIndex(-1);
		this.local.setText("");
		this.duracao.setText("");
		this.selecionarObj1.setSelectedIndex(-1);
		this.selecionarObj2.setSelectedIndex(-1);
		this.selecionarObj3.setSelectedIndex(-1);
		
		return tela;
	}

	@Override
	public JMenuItem getSource() {
		return botaoDeOrigem;
	}

	@Override
	public String getMensagemStatus() {
		return "Atualizando um show...";
	}

}
