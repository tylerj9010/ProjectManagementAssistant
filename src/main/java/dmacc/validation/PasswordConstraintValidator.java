package dmacc.validation;

import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;



public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0) {

    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        // @formatter:off
        final PasswordValidator validator = new PasswordValidator(
		// length between 8 and 16 characters
		new LengthRule(8, 16),
		
		// at least one upper-case character
		new CharacterRule(EnglishCharacterData.UpperCase, 1),
		
		// at least one lower-case character
		new CharacterRule(EnglishCharacterData.LowerCase, 1),
		
		// at least one digit character
		new CharacterRule(EnglishCharacterData.Digit, 1),
		
		// at least one symbol (special character)
		new CharacterRule(EnglishCharacterData.Special, 1),
		
		// define some illegal sequences that will fail when >= 5 chars long
		// alphabetical is of the form 'abcde', numerical is '34567', qwerty is 'asdfg'
		// the false parameter indicates that wrapped sequences are allowed; e.g. 'xyzabc'
		new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
		new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
		new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
		
		// no whitespace
		new WhitespaceRule());
        
        final RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(validator.getMessages(result).stream().map(Object::toString).collect(Collectors.joining(", "))).addConstraintViolation();
        return false;
    }

}
