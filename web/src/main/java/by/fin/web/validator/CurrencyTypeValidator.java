package by.fin.web.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class CurrencyTypeValidator implements ConstraintValidator<CurrencyType, String> {

  private static final Set<String> VALID_CURRENCY_TYPES = Set.of(
      "AMD", "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR",
      "GBP", "INR", "IRR", "ISK", "JPY", "KGS", "KWD", "KZT", "MDL", "NOK",
      "NZD", "PLN", "RUB", "SEK", "SGD", "TRY", "UAH", "USD", "VND", "XDR");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && VALID_CURRENCY_TYPES.contains(value.toUpperCase());
  }
}