package exception;

/**
 * Ce sont les exceptions liées à la factory et à l'accès à la base de données xml
 *
 */
public class FactoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/*
	 * Constructeurs
	 */
	public FactoryException(String message) {
		super(message);
	}

	public FactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public FactoryException(Throwable cause) {
		super(cause);
	}
}