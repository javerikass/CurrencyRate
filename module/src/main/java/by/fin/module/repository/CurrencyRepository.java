package by.fin.module.repository;

import by.fin.module.entity.Currency;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  List<Currency> findAllByCurAbbreviationAndDateBetween(String currencyType, LocalDate startDate,
      LocalDate endDate);

  List<Currency> findCurrenciesByCurAbbreviation(String currencyType);

  @Query("SELECT AVG(c.rate) FROM Currency c "
      + "WHERE c.curAbbreviation=:currencyType AND FUNCTION('MONTH', c.date) = :month "
      + "AND c.date NOT IN"
      + "(SELECT w.calendarDate FROM Weekend w "
      + "WHERE FUNCTION('MONTH', w.calendarDate) = :month AND w.isDayOff=false)"
      + "GROUP BY c.date")
  Double getCurrenciesForMonth(@Param("currencyType") String currencyType, @Param("month") int month);

}
