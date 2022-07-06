public class Show {
    private String local;
    private int duracao;
	private Object[] vetorObj;
	
    public Show(String local, int duracao, Object[] vetorObj) throws NotANameException, InvalidDurationException {
        setLocal(local);
        setDuracao(duracao);
		setVetorObj(vetorObj);
    }
	
    public void setLocal(String local) throws NotANameException {

        if (local.matches("[a-zA-Zçáéíóúàõâêîôûã ]+") == false) {
			throw new NotANameException();
        } 
        else {

            if (local.length() > 1) { //Permite siglas
                this.local = local;
            }
            else {
                throw new InvalidNameException();
            }
        }
    }

	public void setDuracao(int duracao) throws InvalidDurationException {
        if (duracao >= 1 && duracao <= 6) {
            this.duracao = duracao;
        }
		else {
            throw new InvalidDurationException();
        }
	}
    
    public void setVetorObj(Object[] vetorObj) {
		this.vetorObj = vetorObj;
	}


	public String getLocal() {
		return local;
	}

	public int getDuracao() {
		return duracao;
	}

	public Object[] getVetorObj() {
		return vetorObj;
	}

	@Override
	public String toString() {
		String mensagem = "O show será em " + local + " com duração de " + duracao + " horas, no show ";
		boolean primeiro = false, segundo = false, terceiro = false;

		if (vetorObj[0] != null) {
			primeiro = true;
		}

		if (vetorObj[1] != null) {
			segundo = true;
		}

		if (vetorObj[2] != null) {
			terceiro = true;
		}
		
		if (primeiro && segundo == false && terceiro == false) {
			mensagem = mensagem + "irá se apresentar " + getNameGroupOrSolo(0);

		} else if (primeiro == false && segundo && terceiro == false) {
			mensagem = mensagem + "irá se apresentar " + getNameGroupOrSolo(1);

		} else if (primeiro == false && segundo == false && terceiro) {
			mensagem = mensagem + "irá se apresentar " + getNameGroupOrSolo(2);
		}

		if (primeiro && (segundo || terceiro)) {
			mensagem = mensagem + "irão se apresentar " + getNameGroupOrSolo(0);

			if (segundo) {
				if(terceiro == false) {
					mensagem = mensagem + " e ";
					mensagem = mensagem + getNameGroupOrSolo(1);
					
				} else {
					mensagem = mensagem + ", ";
					mensagem = mensagem + getNameGroupOrSolo(1);
					mensagem = mensagem + " e ";

				}
				
			} else {
				mensagem = mensagem + " e ";

			}
			if (terceiro) {
				mensagem = mensagem + getNameGroupOrSolo(2);
			}

		} else if (segundo && terceiro) {
			mensagem = mensagem + "irão se apresentar " + getNameGroupOrSolo(1) + " e " + getNameGroupOrSolo(2);
		}

		return mensagem + ".";
	}

	private String getNameGroupOrSolo(int index) {
		String mensagem = "";
		if (vetorObj[index] instanceof Solista) {
			mensagem = mensagem + ((Solista)vetorObj[index]).getSolista().getNomeKidol();

		} else {
			mensagem = mensagem + ((Grupo)vetorObj[index]).getNomeGrupo();
		}
		return mensagem;
	}
}