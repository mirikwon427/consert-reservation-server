package kr.hhplus.be.server.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;
import kr.hhplus.be.server.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, updatable = false, columnDefinition = "BINARY(16)")
  private UUID uuid;

  @Column(nullable = false, unique = true, length = 50)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, length = 30)
  private String name;

  @Column(nullable = false, length = 20)
  private String phoneNumber;

  @Column(nullable = false)
  private BigDecimal balance;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Builder
  public User(UUID uuid, String email, String password, String name, String phoneNumber, Role role) {
    this.uuid = uuid;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.balance = BigDecimal.ZERO;
    this.role = role;
  }

  public void addBalance(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("충전 금액은 0보다 작을 수 없습니다.");
    }
    this.balance = this.balance.add(amount);
  }
}
