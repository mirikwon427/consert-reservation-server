package kr.hhplus.be.server.reservation.adapter.out;

import kr.hhplus.be.server.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
