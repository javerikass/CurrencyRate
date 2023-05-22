package by.fin.web;

import by.fin.module.dto.CurrencyDto;
import by.fin.module.entity.Currency;
import by.fin.module.mapper.CurrencyMapper;
import by.fin.service.CurrencyService;
import by.fin.web.utils.WeekendUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyScheduler {

  /**
   * Данный класс ежедневно получает данные о курсах валют и сохраняет их в базу данных.
   */

  private final OkHttpClient client = new OkHttpClient();
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final CurrencyService currencyService;

  @PostConstruct
  private void init() {
    registerTimeModuleForJSON();
  }

  @Scheduled(fixedRate = WeekendUtils.UPDATE_INTERVAL)
  private void saveCurrency() {
    currencyService.saveCurrency(getCurrency());
  }

  private List<CurrencyDto> getCurrency() {
    try (Response response = client.newCall(new Request.Builder().url(WeekendUtils.API_URI).build())
        .execute()) {
      return objectMapper.readValue(
          Objects.requireNonNull(response.body()).string(), new TypeReference<List<CurrencyDto>>() {
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  private void registerTimeModuleForJSON() {
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }
}
