package by.fin.module.mapper;

import by.fin.module.dto.CurrencyDto;
import by.fin.module.entity.Currency;
import java.lang.reflect.Type;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyMapper {

  private final ModelMapper modelMapper = new ModelMapper();
  private final Type currencyListType = new TypeToken<List<Currency>>() {}.getType();
  private final Type currencyDtoListType = new TypeToken<List<CurrencyDto>>() {}.getType();

  public List<Currency> convertCurrencyDtoListToCurrencyList(List<CurrencyDto> list) {
    return modelMapper.map(list, currencyListType);
  }

  public List<CurrencyDto> convertCurrencyListToCurrencyDtoList(List<Currency> list) {
    return modelMapper.map(list, currencyDtoListType);
  }
}
