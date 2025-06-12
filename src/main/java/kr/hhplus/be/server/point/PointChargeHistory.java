package kr.hhplus.be.server.point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import kr.hhplus.be.server.entity.BaseTimeEntity;
import kr.hhplus.be.server.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointChargeHistory extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TransactionType transactionType;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private BigDecimal balanceAfterTransaction;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TransactionStatus transactionStatus;

  @Builder
  public PointChargeHistory(User user, TransactionType transactionType, BigDecimal amount, BigDecimal balanceAfterTransaction, TransactionStatus transactionStatus, String paymentUid) {
    this.user = user;
    this.transactionType = transactionType;
    this.amount = amount;
    this.balanceAfterTransaction = balanceAfterTransaction;
    this.transactionStatus = transactionStatus;
  }
}
