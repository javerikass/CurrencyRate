package by.fin.module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CurrencyDto implements Serializable {

  @JsonProperty("Cur_ID")
  private int currencyId;

  @JsonProperty("Cur_Abbreviation")
  private String curAbbreviation;

  @JsonProperty("Cur_Name")
  private String curNameRu;

  @JsonProperty("Cur_Scale")
  private int curScale;

  @JsonProperty("Date")
  private LocalDate date;

  @JsonProperty("Cur_OfficialRate")
  private double rate;
}
