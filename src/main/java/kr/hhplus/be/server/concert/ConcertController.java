package kr.hhplus.be.server.concert;

import java.util.List;
import kr.hhplus.be.server.concert.dto.response.ConcertWithDatesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert")
public class ConcertController {

  private final ConcertService concertService;

  public ConcertController(ConcertService concertService) {
    this.concertService = concertService;
  }

  @GetMapping
  public List<ConcertWithDatesResponse> getAllConcertsWithDates() {
    return concertService.getAllConcertsWithDates();
  }

}
