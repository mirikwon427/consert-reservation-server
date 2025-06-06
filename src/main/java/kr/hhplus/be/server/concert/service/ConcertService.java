package kr.hhplus.be.server.concert.service;

import java.util.List;
import kr.hhplus.be.server.concert.dto.response.ConcertWithDatesResponse;
import kr.hhplus.be.server.concert.repository.ConcertDateRepository;
import kr.hhplus.be.server.concert.repository.ConcertRepository;
import org.springframework.stereotype.Service;

@Service
public class ConcertService {

  private final ConcertRepository concertRepository;
  private final ConcertDateRepository concertDateRepository;

  public List<ConcertWithDatesResponse> getAllConcertsWithDates() {

  }
}
