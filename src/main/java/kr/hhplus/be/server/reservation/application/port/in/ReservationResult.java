package kr.hhplus.be.server.reservation.application.port.in;

import kr.hhplus.be.server.reservation.entity.Reservation;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReservationResult {
  private final Long reservationId;
  private final String seatSection;
  private final int seatNumber;
  private final LocalDateTime expiresAt;

  private ReservationResult(Long reservationId, String seatSection, int seatNumber, LocalDateTime expiresAt) {
    this.reservationId = reservationId;
    this.seatSection = seatSection;
    this.seatNumber = seatNumber;
    this.expiresAt = expiresAt;
  }

  public static ReservationResult from(Reservation reservation) {
    return new ReservationResult(
        reservation.getId(),
        reservation.getSeat().getSection(),
        reservation.getSeat().getSeatNumber(),
        reservation.getExpiresAt()
    );
  }
}
