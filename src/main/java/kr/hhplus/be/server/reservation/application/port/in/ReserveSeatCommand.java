package kr.hhplus.be.server.reservation.application.port.in;

import lombok.Getter;

@Getter
public class ReserveSeatCommand {

  private final Long userId;
  private final Long seatId;

  public ReserveSeatCommand(Long userId, Long seatId) {
    if (userId == null || seatId == null) {
      throw new IllegalArgumentException("사용자 ID와 좌석 ID는 필수입니다.");
    }
    this.userId = userId;
    this.seatId = seatId;
  }
}
