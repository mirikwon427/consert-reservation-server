package kr.hhplus.be.server.reservation;


import kr.hhplus.be.server.entity.user.User;
import kr.hhplus.be.server.reservation.application.port.in.ReservationResult;
import kr.hhplus.be.server.reservation.application.port.in.ReserveSeatCommand;
import kr.hhplus.be.server.reservation.application.port.out.LoadSeatPort;
import kr.hhplus.be.server.reservation.application.port.out.LoadUserPort;
import kr.hhplus.be.server.reservation.application.port.out.SaveReservationPort;
import kr.hhplus.be.server.reservation.application.port.out.UpdateSeatPort;
import kr.hhplus.be.server.reservation.application.service.ReservationService;
import kr.hhplus.be.server.reservation.domain.Reservation;
import kr.hhplus.be.server.reservation.domain.Seat;
import kr.hhplus.be.server.reservation.domain.SeatStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

  @Mock
  private LoadUserPort loadUserPort;
  @Mock
  private LoadSeatPort loadSeatPort;
  @Mock
  private UpdateSeatPort updateSeatPort;
  @Mock
  private SaveReservationPort saveReservationPort;

  @InjectMocks
  private ReservationService reservationService;

  @Test
  @DisplayName("좌석 예약에 성공한다.")
  void 좌석_예약에_성공한다() {
    // given (준비)
    Long userId = 1L;
    Long seatId = 1L;
    ReserveSeatCommand command = new ReserveSeatCommand(userId, seatId);

    User user = ReservationTestFixture.UserFixture.createUser(userId);
    Seat seat = ReservationTestFixture.SeatFixture.createSeat(seatId);

    given(loadUserPort.findById(userId)).willReturn(Optional.of(user));
    given(loadSeatPort.findById(seatId)).willReturn(Optional.of(seat));

    given(saveReservationPort.save(any(Reservation.class))).will(invocation -> invocation.getArgument(0));

    // when
    ReservationResult result = reservationService.reserve(command);
    // then
    assertThat(seat.getStatus()).isEqualTo(SeatStatus.HOLD);
    assertThat(seat.getHeldByUserId()).isEqualTo(userId);

    then(updateSeatPort).should(times(1)).update(seat);
    then(saveReservationPort).should(times(1)).save(any(Reservation.class));

    assertThat(result).isNotNull();
    assertThat(result.getSeatNumber()).isEqualTo(seat.getSeatNumber());
  }
}
