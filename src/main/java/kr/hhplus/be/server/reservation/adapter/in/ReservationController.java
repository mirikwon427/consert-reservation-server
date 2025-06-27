package kr.hhplus.be.server.reservation.adapter.in;

import jakarta.validation.Valid;
import kr.hhplus.be.server.reservation.adapter.in.dto.request.ReservationRequest;
import kr.hhplus.be.server.reservation.adapter.in.dto.response.ReservationResponse;
import kr.hhplus.be.server.reservation.application.port.in.ReservationResult;
import kr.hhplus.be.server.reservation.application.port.in.ReserveSeatCommand;
import kr.hhplus.be.server.reservation.application.port.in.ReserveSeatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

  private final ReserveSeatUseCase reserveSeatUseCase;

  @PostMapping
  public ResponseEntity<ReservationResponse> reserve(
      @Valid @RequestBody ReservationRequest request
  ) {

    ReserveSeatCommand command = new ReserveSeatCommand(request.getUserId(), request.getSeatId());
    ReservationResult result = reserveSeatUseCase.reserve(command);

    ReservationResponse response = ReservationResponse.from(result);

    return ResponseEntity.ok(response);
  }
}
