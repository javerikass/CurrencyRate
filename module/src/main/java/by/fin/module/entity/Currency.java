package by.fin.module.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currencies")
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "cur_ID")
  private int currencyId;

  @Column(name = "cur_abbreviation")
  private String curAbbreviation;

  @Column(name = "cur_name")
  private String curNameRu;

  @Column(name = "cur_scale")
  private int curScale;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "cur_official_rate")
  private double rate;

}
