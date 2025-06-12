package kr.hhplus.be.server.point;

import java.math.BigDecimal;
import java.util.UUID;
import kr.hhplus.be.server.entity.user.Role;
import kr.hhplus.be.server.entity.user.User;

public class PointTestFixture {

  private PointTestFixture() {}

  /**
   * User 관련 픽스처
   */
  public static class UserFixture {
    public static final String DEFAULT_EMAIL = "testuser@example.com";
    public static final String DEFAULT_NAME = "테스트유저";
    public static final String DEFAULT_PHONE = "010-1234-5678";
    public static final String ENCODED_PASSWORD = "encoded_password_123";

    public static User createDefaultUser() {
      return User.builder()
          .uuid(UUID.randomUUID())
          .email(DEFAULT_EMAIL)
          .password(ENCODED_PASSWORD)
          .name(DEFAULT_NAME)
          .phoneNumber(DEFAULT_PHONE)
          .role(Role.USER)
          .build();
    }

  }

  /**
   * Point 관련 픽스처
   */
  public static class PointFixture {
    public static PointChargeHistory createChargeHistory(User user, BigDecimal amount) {
      return PointChargeHistory.builder()
          .user(user)
          .transactionType(TransactionType.CHARGE)
          .amount(amount)
          .balanceAfterTransaction(user.getBalance())
          .transactionStatus(TransactionStatus.SUCCESS)
          .paymentUid("payment_uid_" + UUID.randomUUID().toString().substring(0, 8))
          .build();
    }

    public static PointChargeHistory createUseHistory(User user, BigDecimal amount) {
      BigDecimal balanceAfter = user.getBalance().subtract(amount);

      return PointChargeHistory.builder()
          .user(user)
          .transactionType(TransactionType.USE)
          .amount(amount.negate()) // 사용 금액은 음수로 표현
          .balanceAfterTransaction(balanceAfter)
          .transactionStatus(TransactionStatus.SUCCESS)
          .build();
    }
  }
}

