package by.fin.module.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weekends")
public class Weekend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "weekend_id")
  private Long weekendId;

  @Column(name = "calendar_date")
  private Date calendarDate;

  @Column(name = "is_day_off")
  private boolean isDayOff;
}
