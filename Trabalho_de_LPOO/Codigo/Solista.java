import java.util.ArrayList;

public class Solista {
	private Artista solista; 
	private int quantidadeMiniAlbuns; 
	private int quantidadeDancarinos; 
	private ArrayList<Show> shows;

	public Solista(Artista solista, int quantidadeMiniAlbuns, int quantidadeDancarinos) throws InvalidDancerNumberException {
		setSolista(solista);
		setQuantidadeMiniAlbuns(quantidadeMiniAlbuns);
		setQuantidadeDancarinos(quantidadeDancarinos);
		this.shows = new ArrayList<Show>();
	}

	public void setSolista(Artista solista) {
		this.solista = solista;
	}

	public void setQuantidadeMiniAlbuns(int quantidadeMiniAlbuns) {
		this.quantidadeMiniAlbuns = quantidadeMiniAlbuns;
	}

	public void setQuantidadeDancarinos(int quantidadeDancarinos) throws InvalidDancerNumberException {
        if (quantidadeDancarinos >= 6 && quantidadeDancarinos <= 10) {
            this.quantidadeDancarinos = quantidadeDancarinos;
        }
        else {
            throw new InvalidDancerNumberException();
        }
	}

	public Artista getSolista() {
		return solista;
	}

	public int getQuantidadeMiniAlbuns() {
		return quantidadeMiniAlbuns;
	}

	public int getQuantidadeDancarinos() {
		return quantidadeDancarinos;
	}

	public ArrayList<Show> getShows() {
		return shows;
	}

	@Override
	public String toString() {
		return solista.getNomeKidol() + " possui " + getQuantidadeMiniAlbuns() + " mini-albuns e " + getQuantidadeDancarinos() + " dançarinos.";
	}
}
