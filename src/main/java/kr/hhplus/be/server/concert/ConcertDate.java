package kr.hhplus.be.server.concert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import kr.hhplus.be.server.BaseTimeEntity;

@Entity
public class ConcertDate extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "concert_date_id")
  private Long id;

  @Column(name = "concert_id", nullable = false)
  private Long concertId;

  @Column(name = "concert_date", nullable = false)
  private LocalDate concertDate;

  @Column(name = "start_time", nullable = false)
  private LocalDateTime startTime;

  @Column(name = "end_time", nullable = false)
  private LocalDateTime endTime;

  protected ConcertDate() {}

  public ConcertDate(Long id, Long concertId, LocalDate concertDate, LocalDateTime startTime, LocalDateTime endTime) {
    this.id = id;
    this.concertId = concertId;
    this.concertDate = concertDate;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Long getId() {
    return id;
  }

  public Long getConcertId() {
    return concertId;
  }

  public LocalDate getConcertDate() {
    return concertDate;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }
}
