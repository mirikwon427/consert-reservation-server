package kr.hhplus.be.server.concert.repository;

import java.util.List;
import kr.hhplus.be.server.concert.entity.ConcertDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDateRepository extends JpaRepository<ConcertDate, Long> {

  public List<ConcertDate> findAllByConcertId(Long concertId);
}
