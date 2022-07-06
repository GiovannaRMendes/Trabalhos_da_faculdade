public class KIdolWorkingException extends RuntimeException {

    KIdolWorkingException() {
        super("Esse KIdol já possui carreira, ou seja, faz parte de algum grupo ou é solista.\nAssim sendo, não vamos poder deletar seu artista por causa do hating que iríamos sofrer no Twitter!\nPara efetuar essa ação: Delete o solista ou grupo, antes de deletar o artista.");
    }

}