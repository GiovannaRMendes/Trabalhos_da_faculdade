public class KIdolHaveCarreiraException extends RuntimeException {

    KIdolHaveCarreiraException() {

        super("O artista já faz parte de um grupo ou já possui carreira solo. Dessa forma, sua agenda está lotadíssima e não tem tempo para uma nova carreira!");
 
	}

}