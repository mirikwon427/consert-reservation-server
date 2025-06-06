package kr.hhplus.be.server.concert.dto.response;

import java.util.List;
import kr.hhplus.be.server.concert.entity.ConcertDate;

public class ConcertWithDatesResponse {
  private Long concertId;
  private String name;
  private String artist;
  private String venue;
  private List<ConcertDate> concertDates;

  public ConcertWithDatesResponse(
      Long concertId, String name, String artist, String venue, List<ConcertDate> concertDates
  ) {
    this.concertId = concertId;
    this.name = name;
    this.artist = artist;
    this.venue = venue;
    this.concertDates = concertDates;
  }

  public Long getConcertId() {
    return concertId;
  }

  public String getName() {
    return name;
  }

  public String getArtist() {
    return artist;
  }

  public String getVenue() {
    return venue;
  }

  public List<ConcertDate> getConcertDates() {
    return concertDates;
  }
}

