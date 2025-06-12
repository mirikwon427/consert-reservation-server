package kr.hhplus.be.server.reservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import kr.hhplus.be.server.entity.BaseTimeEntity;
import kr.hhplus.be.server.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seat_id", nullable = false)
  private Seat seat;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 50)
  private ReservationStatus status;

  private LocalDateTime expiresAt;

  @Builder
  public Reservation(User user, Seat seat, long holdDurationMinutes) {
    this.user = user;
    this.seat = seat;
    this.status = ReservationStatus.PENDING_PAYMENT;
    this.expiresAt = LocalDateTime.now().plusMinutes(holdDurationMinutes);
  }

  public void confirm() {
    if (this.status != ReservationStatus.PENDING_PAYMENT) {
      throw new IllegalStateException("예약 상태가 아닙니다.");
    }
    if (LocalDateTime.now().isAfter(this.expiresAt)) {
      throw new IllegalStateException("예약 유효 시간이 만료되었습니다.");
    }
    this.status = ReservationStatus.CONFIRMED;
    this.expiresAt = null;
  }

  public void cancel() {
    this.status = ReservationStatus.CANCELLED;
  }
}
