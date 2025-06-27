package kr.hhplus.be.server.reservation.application.port.out;

import java.util.Optional;
import kr.hhplus.be.server.reservation.domain.Seat;

public interface LoadSeatPort {
  Optional<Seat> findById(Long seatId);
}