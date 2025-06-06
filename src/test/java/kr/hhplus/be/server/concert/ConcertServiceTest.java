package kr.hhplus.be.server.concert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConcertServiceTest {

  @Test
  @DisplayName("콘서트 목록 조회 성공")
  void 콘서트_목록_조회_성공() {
    // given
    List<Concert> concerts = List.of(
        new Concert(1L, "2025 BTS", "BTS", "잠실올림픽경기장", "BTS 월드투어", LocalDateTime.now(), LocalDateTime.now()),
        new Concert(2L, "2025 IU", "IU", "상암월드컵경기장", "아이유 앙코르 콘서트", LocalDateTime.now(), LocalDateTime.now()),
        new Concert(3L, "2025 NewJeans", "NewJeans", "상암월드컵경기장", "NewJeans 단독 콘서트", LocalDateTime.now(), LocalDateTime.now())
    );
    given(concertRepository.findAll()).willReturn(concerts);

    // when
    List<ConcertWithDatesResponse> result = concertService.getAllConcertsWithDates();

    // then
    assertThat(result).hasSize(3);
    assertThat(result.get(0).getConcertName()).isEqualTo("2025 BTS");
    assertThat(result.get(1).getConcertName()).isEqualTo("2025 IU");
    assertThat(result.get(2).getConcertName()).isEqualTo("2025 NewJeans");
    assertThat(result.get(0).getVenue()).isEqualTo("잠실올림픽경기장");
    assertThat(result.get(1).getVenue()).isEqualTo("상암월드컵경기장");
    assertThat(result.get(2).getVenue()).isEqualTo("상암월드컵경기장");
  }


}
