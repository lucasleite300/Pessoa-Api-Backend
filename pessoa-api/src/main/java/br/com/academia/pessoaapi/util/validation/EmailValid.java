package br.com.academia.pessoaapi.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = br.com.academia.pessoaapi.util.validation.EmailValidator.class)
public @interface EmailValid {
	
	String message() default "Email com formato inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
