package kr.hhplus.be.server.concert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import kr.hhplus.be.server.concert.dto.response.ConcertWithDatesResponse;
import kr.hhplus.be.server.concert.entity.Concert;
import kr.hhplus.be.server.concert.entity.ConcertDate;
import kr.hhplus.be.server.concert.repository.ConcertDateRepository;
import kr.hhplus.be.server.concert.repository.ConcertRepository;
import kr.hhplus.be.server.concert.service.ConcertService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConcertServiceTest {

  @Test
  @DisplayName("콘서트 목록 조회 성공")
  void 콘서트_목록_조회_성공() {
    ConcertRepository concertRepository = mock(ConcertRepository.class);
    ConcertDateRepository concertDateRepository = mock(ConcertDateRepository.class);
    ConcertService concertService = new ConcertService(concertRepository, concertDateRepository);
    // given
    List<Concert> concerts = List.of(
        new Concert(1L, "2025 BTS", "BTS", "잠실올림픽경기장", "BTS 월드투어"),
        new Concert(2L, "2025 IU", "IU", "상암월드컵경기장", "아이유 앙코르 콘서트"),
        new Concert(3L, "2025 NewJeans", "NewJeans", "상암월드컵경기장", "NewJeans 단독 콘서트")
    );
    given(concertRepository.findAll()).willReturn(concerts);

    // when
    List<ConcertWithDatesResponse> result = concertService.getAllConcertsWithDates();

    // then
    assertThat(result).hasSize(3);
    assertThat(result.get(0).getName()).isEqualTo("2025 BTS");
    assertThat(result.get(1).getName()).isEqualTo("2025 IU");
    assertThat(result.get(2).getName()).isEqualTo("2025 NewJeans");
    assertThat(result.get(0).getVenue()).isEqualTo("잠실올림픽경기장");
    assertThat(result.get(1).getVenue()).isEqualTo("상암월드컵경기장");
    assertThat(result.get(2).getVenue()).isEqualTo("상암월드컵경기장");
  }

  @Test
  @DisplayName("각 콘서트 공연 날짜 리스트 반환")
  void 콘서트_날짜_목록_조회_성공() {
    ConcertRepository concertRepository = mock(ConcertRepository.class);
    ConcertDateRepository concertDateRepository = mock(ConcertDateRepository.class);
    ConcertService concertService = new ConcertService(concertRepository, concertDateRepository);

    // given
    List<Concert> concerts = List.of(
        new Concert(1L, "2025 BTS", "BTS", "잠실올림픽경기장", "BTS 월드투어"),
        new Concert(2L, "2025 IU", "IU", "상암월드컵경기장", "아이유 앙코르 콘서트"),
        new Concert(3L, "2025 NewJeans", "NewJeans", "상암월드컵경기장", "NewJeans 단독 콘서트")
    );
    List<ConcertDate> btsDates = List.of(
        new ConcertDate(1L, 1L, LocalDate.of(2025, 7, 25), LocalDateTime.of(2025, 7, 25, 18, 0), LocalDateTime.of(2025, 7, 25, 21, 0)),
        new ConcertDate(2L, 1L, LocalDate.of(2025, 7, 26), LocalDateTime.of(2025, 7, 26, 18, 0), LocalDateTime.of(2025, 7, 26, 21, 0))
    );
    List<ConcertDate> iuDates = List.of(
        new ConcertDate(3L, 2L, LocalDate.of(2025, 8, 10), LocalDateTime.of(2025, 8, 10, 19, 0), LocalDateTime.of(2025, 8, 10, 22, 0)),
        new ConcertDate(4L, 2L, LocalDate.of(2025, 8, 11), LocalDateTime.of(2025, 8, 11, 19, 0), LocalDateTime.of(2025, 8, 11, 22, 0))
    );
    List<ConcertDate> newJeansDates = List.of(
        new ConcertDate(5L, 3L, LocalDate.of(2025, 9, 10), LocalDateTime.of(2025, 9, 10, 19, 0), LocalDateTime.of(2025, 9, 10, 22, 0)),
        new ConcertDate(6L, 3L, LocalDate.of(2025, 9, 11), LocalDateTime.of(2025, 9, 11, 19, 0), LocalDateTime.of(2025, 9, 11, 22, 0))
    );

    given(concertRepository.findAll()).willReturn(concerts);
    given(concertDateRepository.findAllByConcertId(1L)).willReturn(btsDates);
    given(concertDateRepository.findAllByConcertId(2L)).willReturn(iuDates);
    given(concertDateRepository.findAllByConcertId(3L)).willReturn(newJeansDates);

    // when
    List<ConcertWithDatesResponse> result = concertService.getAllConcertsWithDates();

    // then
    assertThat(result.get(0).getConcertDates()).hasSize(2)
        .extracting(ConcertDate::getConcertDate)
        .containsExactly(LocalDate.of(2025, 7, 25), LocalDate.of(2025, 7, 26));

    assertThat(result.get(1).getConcertDates()).hasSize(2)
        .extracting(ConcertDate::getConcertDate)
        .containsExactly(LocalDate.of(2025, 8, 10), LocalDate.of(2025, 8, 11));

    assertThat(result.get(2).getConcertDates()).hasSize(2)
        .extracting(ConcertDate::getConcertDate)
        .containsExactly(LocalDate.of(2025, 9, 10), LocalDate.of(2025, 9, 11));
  }

}
