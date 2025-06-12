package kr.hhplus.be.server.reservation.adapter.in.web.dto.response;

import java.time.LocalDateTime;
import kr.hhplus.be.server.reservation.application.port.in.ReservationResult;
import lombok.Getter;

@Getter
public class ReservationResponse {

  private final Long reservationId;
  private final String seatSection;
  private final int seatNumber;
  private final LocalDateTime expiresAt;

  private ReservationResponse(Long reservationId, String seatSection, int seatNumber, LocalDateTime expiresAt) {
    this.reservationId = reservationId;
    this.seatSection = seatSection;
    this.seatNumber = seatNumber;
    this.expiresAt = expiresAt;
  }

  public static ReservationResponse from(ReservationResult result) {
    return new ReservationResponse(
        result.getReservationId(),
        result.getSeatSection(),
        result.getSeatNumber(),
        result.getExpiresAt()
    );
  }
}
