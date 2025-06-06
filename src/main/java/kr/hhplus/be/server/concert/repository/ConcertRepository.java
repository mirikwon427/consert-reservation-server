package kr.hhplus.be.server.concert.repository;

import java.util.List;
import kr.hhplus.be.server.concert.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
  public List<Concert> findAll();
}
