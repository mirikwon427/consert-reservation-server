package kr.hhplus.be.server.reservation.adapter.out;

import java.util.Optional;
import kr.hhplus.be.server.reservation.application.port.out.LoadSeatPort;
import kr.hhplus.be.server.reservation.application.port.out.UpdateSeatPort;
import kr.hhplus.be.server.reservation.domain.Seat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class SeatPersistenceAdapter implements LoadSeatPort, UpdateSeatPort {
  private final SeatRepository seatRepo;
  public SeatPersistenceAdapter(SeatRepository seatRepo) { this.seatRepo = seatRepo; }

  @Override public Optional<Seat> findById(Long id) {
    return seatRepo.findById(id);
  }
  @Override @Transactional public void update(Seat seat) {
    seatRepo.save(seat);
  }
}
