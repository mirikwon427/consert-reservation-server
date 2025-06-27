package kr.hhplus.be.server.reservation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.server.entity.user.User;
import kr.hhplus.be.server.reservation.ReservationTestFixture.ReservationFixture;
import kr.hhplus.be.server.reservation.ReservationTestFixture.SeatFixture;
import kr.hhplus.be.server.reservation.ReservationTestFixture.UserFixture;
import kr.hhplus.be.server.reservation.adapter.in.web.ReservationController;
import kr.hhplus.be.server.reservation.adapter.in.web.dto.request.ReservationRequest;
import kr.hhplus.be.server.reservation.application.port.in.ReservationResult;
import kr.hhplus.be.server.reservation.application.port.in.ReserveSeatUseCase;
import kr.hhplus.be.server.reservation.domain.Reservation;
import kr.hhplus.be.server.reservation.domain.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ReserveSeatUseCase reserveSeatUseCase;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("좌석 예약 API 호출에 성공한다.")
  void 좌석_예약_API_호출에_성공한다() throws Exception {
    // given
    Long userId = 1L;
    Long seatId = 1L;
    Long reservationId = 1L;
    ReservationRequest requestDto = new ReservationRequest(userId,seatId);
    String requestBody = objectMapper.writeValueAsString(requestDto);

    User user = UserFixture.createUser(userId);
    Seat seat = SeatFixture.createSeat(seatId);
    Reservation reservation = ReservationFixture.createPendingReservation(user, seat, reservationId);
    ReservationResult reservationResult = ReservationResult.from(reservation);

    given(reserveSeatUseCase.reserve(any())).willReturn(reservationResult);

    // when & then
    mockMvc.perform(
            post("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andDo(print()) 
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.reservationId").value(reservationResult.getReservationId()))
        .andExpect(jsonPath("$.seatNumber").value(seat.getSeatNumber()));
  }
}
