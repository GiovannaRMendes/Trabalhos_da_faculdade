public class Artista {
	private String nomeKidol;
	private String papel; 
	private boolean temCarreira; 

	public Artista(String nomeKidol, String papel) throws InvalidNameException {
		setNomeKidol(nomeKidol);
		setPapel(papel);
		setTemCarreira(false);
	}

	public void setNomeKidol(String nomeKidol) throws InvalidNameException {
        if (nomeKidol.length() > 1) {
            this.nomeKidol = nomeKidol;
        }
        else {
            throw new InvalidNameException();
        }
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public void setTemCarreira(boolean temCarreira) {
		this.temCarreira = temCarreira;
	}

	public String getNomeKidol() {
		return nomeKidol;
	}

	public String getPapel() {
		return papel;
	}

	public boolean getTemCarreira() {
		return temCarreira;
	}

	@Override
	public String toString() {
		return getNomeKidol() + " seu estilo é " + getPapel() + ".";
	}
}
