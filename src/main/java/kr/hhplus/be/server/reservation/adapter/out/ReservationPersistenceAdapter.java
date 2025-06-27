package kr.hhplus.be.server.reservation.adapter.out;

import kr.hhplus.be.server.reservation.application.port.out.SaveReservationPort;
import kr.hhplus.be.server.reservation.domain.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReservationPersistenceAdapter implements SaveReservationPort {
  private final ReservationRepository resRepo;
  public ReservationPersistenceAdapter(ReservationRepository resRepo) {
    this.resRepo = resRepo;
  }
  @Override public Reservation save(Reservation r) {
    return resRepo.save(r);
  }
}
