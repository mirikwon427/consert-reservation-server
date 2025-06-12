package kr.hhplus.be.server.point.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointChargeRequest {
  @NotNull(message = "사용자 ID를 입력해주세요.")
  private Long userId;

  @NotNull(message = "충전 금액을 입력해주세요.")
  @Positive(message = "충전 금액은 0보다 작을 수 없습니다.")
  private BigDecimal amount;

  public PointChargeRequest(Long userId, BigDecimal amount) {
    this.userId = userId;
    this.amount = amount;
  }
}
