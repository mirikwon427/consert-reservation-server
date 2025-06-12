package kr.hhplus.be.server.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Optional;
import kr.hhplus.be.server.entity.user.User;
import kr.hhplus.be.server.entity.user.UserRepository;
import kr.hhplus.be.server.point.PointTestFixture.UserFixture;
import kr.hhplus.be.server.point.entity.PointChargeHistory;
import kr.hhplus.be.server.point.entity.TransactionStatus;
import kr.hhplus.be.server.point.entity.TransactionType;
import kr.hhplus.be.server.point.repository.PointChargeHistoryRepository;
import kr.hhplus.be.server.point.service.PointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PointServiceTest {
  @Mock
  private UserRepository userRepository;
  @Mock
  private PointChargeHistoryRepository pointChargeHistoryRepository;
  @InjectMocks
  private PointService pointService;
  @Captor
  private ArgumentCaptor<PointChargeHistory> pointChargeHistoryCaptor;

  @Test
  @DisplayName("사용자가 포인트를 성공적으로 충전한다.")
  void 사용자가_포인트를_성공적으로_충전한다() {

    // given
    Long userId = 1L;
    User user = UserFixture.createDefaultUser();
    BigDecimal chargeAmount = new BigDecimal("10000");

    given(userRepository.findById(userId)).willReturn(Optional.of(user));
    // when
    pointService.charge(userId, chargeAmount);

    // then
    assertThat(user.getBalance()).isEqualTo(new BigDecimal("10000"));
    then(pointChargeHistoryRepository).should(times(1)).save(pointChargeHistoryCaptor.capture());

    PointChargeHistory chargeHistory = pointChargeHistoryCaptor.getValue();

    assertThat(chargeHistory.getUser()).isEqualTo(user);
    assertThat(chargeHistory.getTransactionType()).isEqualTo(TransactionType.CHARGE);
    assertThat(chargeHistory.getTransactionStatus()).isEqualTo(TransactionStatus.SUCCESS);
    assertThat(chargeHistory.getAmount()).isEqualTo(chargeAmount);
  }
}
