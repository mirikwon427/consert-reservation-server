package kr.hhplus.be.server.reservation.application.service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.entity.user.User;
import kr.hhplus.be.server.reservation.application.port.in.ReservationResult;
import kr.hhplus.be.server.reservation.application.port.in.ReserveSeatCommand;
import kr.hhplus.be.server.reservation.application.port.in.ReserveSeatUseCase;
import kr.hhplus.be.server.reservation.application.port.out.LoadSeatPort;
import kr.hhplus.be.server.reservation.application.port.out.LoadUserPort;
import kr.hhplus.be.server.reservation.application.port.out.SaveReservationPort;
import kr.hhplus.be.server.reservation.application.port.out.UpdateSeatPort;
import kr.hhplus.be.server.reservation.entity.Reservation;
import kr.hhplus.be.server.reservation.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReserveSeatUseCase {

  private final LoadUserPort loadUserPort;
  private final LoadSeatPort loadSeatPort;
  private final UpdateSeatPort updateSeatPort;
  private final SaveReservationPort saveReservationPort;

  @Transactional
  @Override
  public ReservationResult reserve(ReserveSeatCommand command) {

    User user = loadUserPort.findById(command.getUserId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    Seat seat = loadSeatPort.findById(command.getSeatId())
        .orElseThrow(() -> new RuntimeException("좌석을 찾을 수 없습니다."));

    seat.hold(user.getId(), 5L);

    updateSeatPort.update(seat);

    Reservation reservation = Reservation.builder()
        .user(user)
        .seat(seat)
        .holdDurationMinutes(5L)
        .build();

    Reservation savedReservation = saveReservationPort.save(reservation);

    return ReservationResult.from(savedReservation);
  }
}
