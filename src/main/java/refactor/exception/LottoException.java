package refactor.exception;

public class LottoException extends IllegalArgumentException {
    private static final String ERROR_PREFIX_MESSAGE = "[ERROR] ";
    public LottoException(String exceptionMessaage) {
        super(ERROR_PREFIX_MESSAGE + exceptionMessaage);
    }
}
