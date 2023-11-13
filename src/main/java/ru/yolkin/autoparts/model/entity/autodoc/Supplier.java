package ru.yolkin.autoparts.model.entity.autodoc;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record Supplier(
   String name,
   Boolean isTodayOrderDate,
   LocalDate nextOrderDate,
   String nextOrderDateString,
   String schedule,
   List<ScheduleDay> scheduleDays,
   Boolean isAutodocMoscow,
   Boolean isMagazine,
   String description
) {

  private record ScheduleDay(
      String schedule
  ) {

  }
}
