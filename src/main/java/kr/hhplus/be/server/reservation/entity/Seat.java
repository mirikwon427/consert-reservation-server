package kr.hhplus.be.server.reservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.hhplus.be.server.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long concertDateId;

  @Column(nullable = false)
  private int seatNumber;

  @Column(nullable = false, length = 50)
  private String section;

  @Column(nullable = false)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SeatStatus status;

  private Long heldByUserId;

  private LocalDateTime heldUntil;

  @Builder
  public Seat(Long concertDateId, int seatNumber, String section, BigDecimal price) {
    this.concertDateId = concertDateId;
    this.seatNumber = seatNumber;
    this.section = section;
    this.price = price;
    this.status = SeatStatus.AVAILABLE;
  }

  public void hold(Long userId, long holdDurationMinutes) {
    if (this.status != SeatStatus.AVAILABLE) {
      throw new IllegalStateException("이미 선택된 좌석입니다.");
    }
    this.status = SeatStatus.HOLD;
    this.heldByUserId = userId;
    this.heldUntil = LocalDateTime.now().plusMinutes(holdDurationMinutes);
  }

  private void reserve() {
    if (this.status != SeatStatus.HOLD) {
      throw new IllegalStateException("선택한 좌석만 예약할 수 있습니다.");
    }
    this.status = SeatStatus.RESERVED;
  }

}
