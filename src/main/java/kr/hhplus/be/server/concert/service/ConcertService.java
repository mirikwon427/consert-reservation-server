package kr.hhplus.be.server.concert.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.hhplus.be.server.concert.dto.response.ConcertWithDatesResponse;
import kr.hhplus.be.server.concert.entity.Concert;
import kr.hhplus.be.server.concert.repository.ConcertDateRepository;
import kr.hhplus.be.server.concert.repository.ConcertRepository;
import org.springframework.stereotype.Service;

@Service
public class ConcertService {

  private final ConcertRepository concertRepository;
  private final ConcertDateRepository concertDateRepository;

  public ConcertService(ConcertRepository concertRepository, ConcertDateRepository concertDateRepository) {
    this.concertRepository = concertRepository;
    this.concertDateRepository = concertDateRepository;
  }

  public List<ConcertWithDatesResponse> getAllConcertsWithDates() {

    List<Concert> concerts = concertRepository.findAll();

    return concerts.stream()
        .map(concert -> new ConcertWithDatesResponse(
                  concert.getId(),
                  concert.getName(),
                  concert.getArtist(),
                  concert.getVenue(),
              concertDateRepository.findAllByConcertId(concert.getId())
        )).collect(Collectors.toList());

  }
}
