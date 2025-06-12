package kr.hhplus.be.server.point.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointChargeResponse {
  private Long userId;
  private BigDecimal balanceAfterCharge;
}
