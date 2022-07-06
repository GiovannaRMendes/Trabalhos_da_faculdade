import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaPrincipal extends JFrame {
	private JMenuBar menuBar;
	private JMenu menuArtista;
	private JMenu menuEmpresa;
	private JMenu menuGrupo;
	private JMenu menuShow;
	private JMenu menuSolista;

	private JMenuItem atualizarArtista;
	private JMenuItem criarArtista;
	private JMenuItem deletarArtista;
	private JMenuItem listarArtista;
	
	private JMenuItem atualizarEmpresa;
	private JMenuItem criarEmpresa;
	private JMenuItem deletarEmpresa;
	private JMenuItem listarEmpresa;
	
	private JMenuItem atualizarGrupo;
	private JMenuItem criarGrupo;
	private JMenuItem deletarGrupo;
	private JMenuItem listarGrupo;
	
	private JMenuItem atualizarShow;
	private JMenuItem criarShow;
	private JMenuItem deletarShow;
	private JMenuItem listarShow;
	
	private JMenuItem atualizarSolista;
	private JMenuItem criarSolista;
	private JMenuItem deletarSolista;
	private JMenuItem listarSolista;

	private JLabel status;
	
	private ArrayList<Janela> janelas;
	private Janela janelaAtual;
	
	private ArrayList<Artista> artistas;
	private ArrayList<EmpresaDeKpop> empresas;
	private ArrayList<Grupo> grupos;
	private ArrayList<Show> shows;
	private ArrayList<Solista> solistas;
	
	public TelaPrincipal() {
		super("Gerenciamento de artistas de K-POP");
		setLayout(new BorderLayout());
		
		configuraVetoresDeEntidades();
		
		configuraMenuStatus();
		
		configuraTelas(); 
		
		configuraListeners();
		
		setExtendedState(JFrame. MAXIMIZED_BOTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setVisible(true);
	}
	
	private void configuraVetoresDeEntidades() {

		artistas = new ArrayList<Artista>();
		empresas = new ArrayList<EmpresaDeKpop>();
		grupos = new ArrayList<Grupo>();
		shows = new ArrayList<Show>();
		solistas = new ArrayList<Solista>();

	}
	
	
	private void configuraMenuStatus() {
		menuBar = new JMenuBar();
		
		menuArtista = new JMenu("Artista");
		atualizarArtista = new JMenuItem("Atualizar");
		criarArtista = new JMenuItem("Criar");
		deletarArtista = new JMenuItem("Deletar");
		listarArtista = new JMenuItem("Listar");
		
		menuArtista.add(atualizarArtista);
		menuArtista.add(criarArtista);
		menuArtista.add(deletarArtista);
		menuArtista.add(listarArtista);
		menuBar.add(menuArtista);
		
		menuEmpresa = new JMenu("Empresa");
		atualizarEmpresa = new JMenuItem("Atualizar");
		criarEmpresa = new JMenuItem("Criar");
		deletarEmpresa = new JMenuItem("Deletar");
		listarEmpresa = new JMenuItem("Listar");
		
		menuEmpresa.add(atualizarEmpresa);
		menuEmpresa.add(criarEmpresa);
		menuEmpresa.add(deletarEmpresa);
		menuEmpresa.add(listarEmpresa);
		menuBar.add(menuEmpresa);
		
		menuGrupo = new JMenu("Grupo");
		atualizarGrupo = new JMenuItem("Atualizar");
		criarGrupo = new JMenuItem("Criar");
		deletarGrupo = new JMenuItem("Deletar");
		listarGrupo = new JMenuItem("Listar");
		
		menuGrupo.add(atualizarGrupo);
		menuGrupo.add(criarGrupo);
		menuGrupo.add(deletarGrupo);
		menuGrupo.add(listarGrupo);
		menuBar.add(menuGrupo);

		menuSolista = new JMenu("Solista");
		atualizarSolista = new JMenuItem("Atualizar");
		criarSolista = new JMenuItem("Criar");
		deletarSolista = new JMenuItem("Deletar");
		listarSolista = new JMenuItem("Listar");

		menuSolista.add(atualizarSolista);
		menuSolista.add(criarSolista);
		menuSolista.add(deletarSolista);
		menuSolista.add(listarSolista);
		menuBar.add(menuSolista);
		
		menuShow = new JMenu("Show");
		atualizarShow = new JMenuItem("Atualizar");
		criarShow = new JMenuItem("Criar");
		deletarShow = new JMenuItem("Deletar");
		listarShow = new JMenuItem("Listar");
		
		menuShow.add(atualizarShow);
		menuShow.add(criarShow);
		menuShow.add(deletarShow);
		menuShow.add(listarShow);
		menuBar.add(menuShow);

		setJMenuBar(menuBar);
		
		status = new JLabel("Status");
		add(status, BorderLayout.SOUTH);
	}
	
	private void configuraTelas() {
		janelas = new ArrayList<Janela>();
		
		janelas.add(new TelaAtualizarArtista(atualizarArtista, artistas));
		janelas.add(new TelaCriarArtista(criarArtista, artistas));
		janelas.add(new TelaDeletarArtista(deletarArtista, artistas));
		janelas.add(new TelaListarArtista(listarArtista, artistas));

		janelas.add(new TelaAtualizarEmpresaDeKpop(atualizarEmpresa, empresas));
		janelas.add(new TelaCriarEmpresaDeKpop(criarEmpresa, empresas));
		janelas.add(new TelaDeletarEmpresaDeKpop(deletarEmpresa, empresas)); 
		janelas.add(new TelaListarEmpresaDeKpop(listarEmpresa, empresas));

		janelas.add(new TelaAtualizarGrupo(atualizarGrupo, grupos, empresas, artistas));
		janelas.add(new TelaCriarGrupo(criarGrupo, grupos, empresas, artistas));
		janelas.add(new TelaDeletarGrupo(deletarGrupo, grupos, empresas));
		janelas.add(new TelaListarGrupo(listarGrupo, grupos));

		janelas.add(new TelaAtualizarShow(atualizarShow, shows, solistas, grupos));
		janelas.add(new TelaCriarShow(criarShow, shows, solistas, grupos));
		janelas.add(new TelaDeletarShow(deletarShow, solistas, grupos, shows));
		janelas.add(new TelaListarShow(listarShow, shows));

		janelas.add(new TelaAtualizarSolista(atualizarSolista, solistas));
		janelas.add(new TelaCriarSolista(criarSolista, solistas, empresas, artistas));
		janelas.add(new TelaDeletarSolista(deletarSolista, solistas, artistas, empresas));
		janelas.add(new TelaListarSolista(listarSolista, solistas));
		
	}

	private void configuraListeners() {

		ActionListener ouvidoTrocaTela = (e) -> {

			if (janelaAtual != null) {
				remove(janelaAtual.getTela());
			}
			
			for (int i = 0 ; i < janelas.size() ; i++) {

				if (janelas.get(i).getSource().equals(e.getSource())) {
					janelaAtual = janelas.get(i);
					break;
				}

			}
			
			add(janelaAtual.getTela(), BorderLayout.CENTER);
			status.setText(janelaAtual.getMensagemStatus());
			setVisible(true);

		};

		atualizarArtista.addActionListener(ouvidoTrocaTela);
		criarArtista.addActionListener(ouvidoTrocaTela);
		deletarArtista.addActionListener(ouvidoTrocaTela);
		listarArtista.addActionListener(ouvidoTrocaTela);

		atualizarEmpresa.addActionListener(ouvidoTrocaTela);
		criarEmpresa.addActionListener(ouvidoTrocaTela);
		deletarEmpresa.addActionListener(ouvidoTrocaTela);
		listarEmpresa.addActionListener(ouvidoTrocaTela);

		atualizarGrupo.addActionListener(ouvidoTrocaTela);
		criarGrupo.addActionListener(ouvidoTrocaTela);
		deletarGrupo.addActionListener(ouvidoTrocaTela);
		listarGrupo.addActionListener(ouvidoTrocaTela);

		atualizarShow.addActionListener(ouvidoTrocaTela);
		criarShow.addActionListener(ouvidoTrocaTela); 
		deletarShow.addActionListener(ouvidoTrocaTela);
		listarShow.addActionListener(ouvidoTrocaTela);

		atualizarSolista.addActionListener(ouvidoTrocaTela);
		criarSolista.addActionListener(ouvidoTrocaTela);
		deletarSolista.addActionListener(ouvidoTrocaTela);
		listarSolista.addActionListener(ouvidoTrocaTela);

	}

}
