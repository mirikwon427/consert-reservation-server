package kr.hhplus.be.server.point.service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import kr.hhplus.be.server.entity.user.User;
import kr.hhplus.be.server.entity.user.UserRepository;
import kr.hhplus.be.server.point.dto.response.PointChargeResponse;
import kr.hhplus.be.server.point.entity.PointChargeHistory;
import kr.hhplus.be.server.point.entity.TransactionStatus;
import kr.hhplus.be.server.point.entity.TransactionType;
import kr.hhplus.be.server.point.repository.PointChargeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

  private final PointChargeHistoryRepository pointChargeHistoryRepository;
  private final UserRepository userRepository;

  @Transactional
  public PointChargeResponse charge(Long userId, BigDecimal amount) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

    user.addBalance(amount);

    PointChargeHistory pointChargeHistory = PointChargeHistory.builder()
        .user(user)
        .transactionType(TransactionType.CHARGE)
        .amount(amount)
        .balanceAfterTransaction(user.getBalance())
        .transactionStatus(TransactionStatus.SUCCESS)
        .build();

    pointChargeHistoryRepository.save(pointChargeHistory);

    return new PointChargeResponse(userId, amount);
  }
}
