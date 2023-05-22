package by.fin.web.controller;

import by.fin.module.dto.CurrencyDto;
import by.fin.service.CurrencyService;
import by.fin.service.exception.CurrencyException;
import by.fin.web.controller.response.AvgCurrencyResponse;
import by.fin.web.validator.CurrencyType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/currency")
@RestController
@RequiredArgsConstructor
@Validated
public class CurrencyController {

  private final CurrencyService currencyService;

  @GetMapping("/all")
  public ResponseEntity<List<CurrencyDto>> getCurrencies(
      @RequestParam("type") @CurrencyType String currencyType,
      @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull @PastOrPresent LocalDate startDate,
      @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull @PastOrPresent LocalDate endDate) {
    if (startDate.isAfter(endDate)) {
      throw new CurrencyException("Start date cannot be after end date",
          HttpStatus.BAD_REQUEST.value());
    }
    return ResponseEntity.ok(currencyService.getCurrency(currencyType, startDate, endDate));
  }

  @GetMapping("/type")
  public ResponseEntity<List<CurrencyDto>> getCurrenciesByType(
      @RequestParam("type") @CurrencyType String currencyType) {
    return ResponseEntity.ok(currencyService.getCurrencyByType(currencyType));
  }

  @GetMapping("/average")
  public ResponseEntity<AvgCurrencyResponse> getAverageCurrency(
      @RequestParam("type") @CurrencyType @NotBlank String currencyType,
      @RequestParam("month") @Min(1) @Max(12) int month) {
    return ResponseEntity.ok(AvgCurrencyResponse.builder()
        .avgCurrency(currencyService.getAvgCurrencyRateForMonth(currencyType, month))
        .build());
  }
}

