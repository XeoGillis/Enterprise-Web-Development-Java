package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springBoot.Fifa.FifaCommand;

public class FifaValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FifaCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FifaCommand fifaCommand = (FifaCommand) target;

		String email = fifaCommand.getEmail();
		String ticket = fifaCommand.getTicket();
		String voetbalCode1 = fifaCommand.getVoetbalCode1();
		String voetbalCode2 = fifaCommand.getVoetbalCode2();

		if (email == null || email.isEmpty() || email.isBlank())
			errors.rejectValue("email", "lengthOfEmail.fifa.email");
		else if (!email.contains("@"))
			errors.rejectValue("email", "emailFormat.fifa.email");

		if (ticket == null || ticket.isEmpty() || ticket.isBlank())
			errors.rejectValue("ticket", "lengthOfEmail.fifa.email");
		else {
			if (!ticket.matches("-?(0|[1-9]\\d*)"))
				errors.rejectValue("ticket", "ticketNotInteger.fifa.ticket");
			else {
				if (Integer.parseInt(ticket) < 1)
					errors.rejectValue("ticket", "ticketToSmall");
				if (Integer.parseInt(ticket) > 25)
					errors.rejectValue("ticket", "ticketToBig");
			}
		}

		if (voetbalCode1 == null || voetbalCode1.isEmpty() || voetbalCode1.isBlank())
			errors.rejectValue("voetbalCode1", "lengthOfEmail.fifa.email");
		else if (!voetbalCode1.matches("(0|[1-9]\\d*)"))
			errors.rejectValue("voetbalCode1", "ticketNotInteger.fifa.ticket");

		if (voetbalCode2 == null || voetbalCode2.isEmpty() || voetbalCode2.isBlank())
			errors.rejectValue("voetbalCode2", "lengthOfEmail.fifa.email");
		else if (!voetbalCode2.matches("(0|[1-9]\\d*)"))
			errors.rejectValue("voetbalCode2", "ticketNotInteger.fifa.ticket");

		if (voetbalCode1 != null && !voetbalCode1.isEmpty() && !voetbalCode1.isBlank() && voetbalCode2 != null
				&& !voetbalCode2.isEmpty() && !voetbalCode2.isBlank())
			if (voetbalCode1.matches("(0|[1-9]\\d*)") && voetbalCode2.matches("(0|[1-9]\\d*)"))
				if (Integer.parseInt(voetbalCode1) > Integer.parseInt(voetbalCode2))
					errors.rejectValue("voetbalCode1", "voetbalCode1ToBig");
	}

}
