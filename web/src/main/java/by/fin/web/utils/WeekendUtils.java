package by.fin.web.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeekendUtils {

  public static final String API_URI = "https://api.nbrb.by/exrates/rates?periodicity=0";
  public static final int UPDATE_INTERVAL = 86_400_000; // Раз в день

}
