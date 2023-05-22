package by.fin.service.impl;

import by.fin.module.dto.CurrencyDto;
import by.fin.module.entity.Currency;
import by.fin.module.mapper.CurrencyMapper;
import by.fin.module.repository.CurrencyRepository;
import by.fin.service.CurrencyService;
import by.fin.service.exception.CurrencyException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

  private final CurrencyRepository currencyRepository;
  private final CurrencyMapper currencyMapper;

  @Override
  public List<CurrencyDto> getCurrency(String currencyType, LocalDate startDate,
      LocalDate endDate) {
    List<Currency> currencyList = currencyRepository.findAllByCurAbbreviationAndDateBetween(
        currencyType, startDate, endDate);
    checkCurrencyListForNull(currencyList);
    return currencyMapper.convertCurrencyListToCurrencyDtoList(currencyList);
  }

  @Override
  public List<CurrencyDto> getCurrencyByType(String currencyType) {
    List<Currency> currencyList = currencyRepository.findCurrenciesByCurAbbreviation(
        currencyType);
    checkCurrencyListForNull(currencyList);
    return currencyMapper.convertCurrencyListToCurrencyDtoList(currencyList);
  }

  @Override
  public Double getAvgCurrencyRateForMonth(String currencyType, int month) {
    Double avgCurrency = currencyRepository.getCurrenciesForMonth(currencyType, month);
    if (avgCurrency == null) {
      throw new CurrencyException("Average currency is not found", HttpStatus.NOT_FOUND.value());
    }
    return avgCurrency;
  }

  @Override
  public void saveCurrency(List<CurrencyDto> currency) {
    if (Objects.nonNull(currency)) {
      currencyRepository.saveAll(currencyMapper.convertCurrencyDtoListToCurrencyList(currency));
    }
  }

  private void checkCurrencyListForNull(List<Currency> currencyList) {
    if (currencyList == null) {
      throw new CurrencyException("Currency is not found", HttpStatus.NOT_FOUND.value());
    }
  }
}
