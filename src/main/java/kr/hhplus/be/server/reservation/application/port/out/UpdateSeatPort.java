package kr.hhplus.be.server.reservation.application.port.out;

import kr.hhplus.be.server.reservation.entity.Seat;

public interface UpdateSeatPort {
  void update(Seat seat);
}
