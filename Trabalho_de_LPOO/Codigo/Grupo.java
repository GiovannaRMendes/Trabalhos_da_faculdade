import java.util.ArrayList;

public class Grupo {
	private String nomeGrupo;
	private Artista[] vetorArtistas; 
	private ArrayList<Show> shows;

	public Grupo(String nomeGrupo, Artista[] vetorArtistas) {
		setNomeGrupo(nomeGrupo);
		setVetorArtista(vetorArtistas);
		this.shows = new ArrayList<Show>();
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public void setVetorArtista(Artista[] vetorArtistas) {
		this.vetorArtistas = vetorArtistas;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public Artista[] getVetorArtistas() {
		return vetorArtistas;
	}
	
	public ArrayList<Show> getShows() {
		return shows;
	}

	@Override
	public String toString() {
		return "O nome do grupo é " + nomeGrupo + " e seus integrantes são: " + vetorArtistas[0].getNomeKidol() + ", " + vetorArtistas[1].getNomeKidol() + ", " + vetorArtistas[2].getNomeKidol() + " e " + vetorArtistas[3].getNomeKidol() + ".";
	}
}
