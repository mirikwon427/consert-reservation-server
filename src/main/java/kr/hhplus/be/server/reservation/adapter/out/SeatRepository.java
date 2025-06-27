package kr.hhplus.be.server.reservation.adapter.out;

import kr.hhplus.be.server.reservation.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
