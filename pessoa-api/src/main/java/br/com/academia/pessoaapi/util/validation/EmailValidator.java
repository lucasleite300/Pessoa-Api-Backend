package br.com.academia.pessoaapi.util.validation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.InputMismatchException;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		if (email != null && !email.isBlank())
			return isValidEmailAddress(email);

		return true;
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
