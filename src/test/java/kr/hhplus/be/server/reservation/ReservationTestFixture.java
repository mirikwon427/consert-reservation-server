package kr.hhplus.be.server.reservation;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.UUID;
import kr.hhplus.be.server.entity.user.Role;
import kr.hhplus.be.server.entity.user.User;
import kr.hhplus.be.server.reservation.domain.Reservation;
import kr.hhplus.be.server.reservation.domain.Seat;

public final class ReservationTestFixture {

  private ReservationTestFixture() {}

  public static class UserFixture {

    public static User createUser(Long userId) {
      User user = User.builder()
          .uuid(UUID.randomUUID())
          .email("test@example.com")
          .password("encoded_password")
          .name("테스트유저")
          .phoneNumber("010-1234-5678")
          .role(Role.USER)
          .build();

      try {
        Field idField = User.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, userId);
      } catch (Exception e) {
        throw new RuntimeException("테스트용 User ID 설정 중 오류 발생", e);
      }
      return user;
    }
  }

  public static class SeatFixture {

    public static Seat createSeat(Long seatId) {
      Seat seat = Seat.builder()
          .concertDateId(1L)
          .seatNumber(10)
          .section("A")
          .price(new BigDecimal("50000"))
          .build();
      try {
        Field idField = Seat.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(seat, seatId);
      } catch (Exception e) {
        throw new RuntimeException("테스트용 Seat ID 설정 중 오류 발생", e);
      }
      return seat;
    }
  }

  public static class ReservationFixture {

    private static final long DEFAULT_HOLD_MINUTES = 5;

    public static Reservation createPendingReservation(User user, Seat seat, Long reservationId) {
      Reservation reservation = Reservation.builder()
          .user(user)
          .seat(seat)
          .holdDurationMinutes(DEFAULT_HOLD_MINUTES)
          .build();

      try {
        Field idField = Reservation.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(reservation, reservationId);
      } catch (Exception e) {
        throw new RuntimeException("테스트용 Reservation ID 설정 중 오류 발생", e);
      }
      return reservation;
    }

    public static Reservation createConfirmedReservation(User user, Seat seat, Long reservationId) {
      Reservation reservation = createPendingReservation(user, seat, reservationId);
      reservation.confirm();
      return reservation;
    }

  }
}

