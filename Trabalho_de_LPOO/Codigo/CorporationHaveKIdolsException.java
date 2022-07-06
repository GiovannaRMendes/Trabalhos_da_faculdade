public class CorporationHaveKIdolsException extends RuntimeException {

    CorporationHaveKIdolsException() {
        super("Essa empresa possui Grupos e/ou Solistas associados a ela e como não queremos deixá-los desempregados,\nvocê deve primeiro deletar o grupo e/ou os solistas associados a essa empresa.");
    }


}