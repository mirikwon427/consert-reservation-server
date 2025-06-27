package kr.hhplus.be.server.reservation.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationRequest {

  @NotNull(message = "사용자 ID를 필수입니다.")
  private Long userId;

  @NotNull(message = "좌석 ID는 필수입니다.")
  private Long seatId;

  public ReservationRequest(Long userId, Long seatId) {
    this.userId = userId;
    this.seatId = seatId;
  }
}
