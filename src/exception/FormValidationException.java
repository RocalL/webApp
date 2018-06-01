package exception;

/**
 * Ce sont les exceptions liées à des erreurs de saisies sur les champs de formulaire
 *
 */
public class FormValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	/*
	 * Constructeurs
	 */
	public FormValidationException(String message) {
		super(message);
	}

	public FormValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public FormValidationException(Throwable cause) {
		super(cause);
	}

}
