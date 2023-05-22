package by.fin.service;

import by.fin.module.dto.CurrencyDto;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CurrencyService {

  void saveCurrency(List<CurrencyDto> currency);

  List<CurrencyDto> getCurrencyByType(String currencyType);

  Double getAvgCurrencyRateForMonth(String currencyType, int month);

  List<CurrencyDto> getCurrency(String currencyType, LocalDate startDate, LocalDate endDate);

}
