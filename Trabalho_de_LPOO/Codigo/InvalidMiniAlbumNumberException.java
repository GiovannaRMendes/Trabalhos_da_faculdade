public class InvalidMiniAlbumNumberException extends RuntimeException {
    InvalidMiniAlbumNumberException() {
        super("A quantidade de mini-álbuns é menor que a anterior. Isso não pode ocorrer.\nPor favor, coloque um valor maior.");
    }
}