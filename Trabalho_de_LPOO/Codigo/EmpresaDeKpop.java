import java.util.ArrayList;

public class EmpresaDeKpop {
    private String nomeDono; // A partir de 3 letras
	private String nomeEmpresa; // A partir de 2 letras
	private ArrayList<Solista> vetorSolista;
	private ArrayList<Grupo> vetorGrupos; 

    public EmpresaDeKpop(String nomeDono, String nomeEmpresa) throws NotANameException, InvalidNameException {
        setNomeDono(nomeDono);
        setNomeEmpresa(nomeEmpresa);
        setVetorGrupo();
        setVetorSolista();
    }

    public void setNomeDono(String nomeDono) throws NotANameException, InvalidNameException { 
        nomeDono = nomeDono.strip();
        if (nomeDono.matches("[a-zA-Zçáéíóúàõâêîôûã ]+") == false) { 
            throw new NotANameException();
        }
        else {
            if (nomeDono.length() > 2) { 
                this.nomeDono = nomeDono;
            }
            else {
                throw new InvalidNameException();
            }
        }
    }

    public void setNomeEmpresa(String nomeEmpresa) throws NotANameException, InvalidNameException {
        nomeEmpresa = nomeEmpresa.strip();
        
        if (nomeEmpresa.matches("[a-zA-Zçáéíóúàõâêîôûã ]+") == false) { 
            throw new NotANameException();
        }
        else {
            if (nomeEmpresa.length() > 1) {
                this.nomeEmpresa = nomeEmpresa;
            }
            else {
                throw new InvalidNameException();
            }
        }
        
    }

	private void setVetorSolista() {
		this.vetorSolista = new ArrayList<Solista>();
	}

    private void setVetorGrupo() {
        this.vetorGrupos = new ArrayList<Grupo>();
    }

    public String getNomeDono() {
        return this.nomeDono;
    }

    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

	public ArrayList<Solista> getSolistas() {
		return this.vetorSolista;
	}

	public ArrayList<Grupo> getGrupos() {
		return this.vetorGrupos;
	}

	@Override
	public String toString() {
		return "A empresa " + this.nomeEmpresa + " é gerenciada por " + this.nomeDono + ", possui " + vetorGrupos.size() + " grupos e " + vetorSolista.size() + " solistas associados a ela.";
	}
}