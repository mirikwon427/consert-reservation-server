package kr.hhplus.be.server.reservation.application.port.out;

import java.util.Optional;
import kr.hhplus.be.server.reservation.entity.Seat;

public interface LoadSeatPort {
  Optional<Seat> findById(Long seatId);
}