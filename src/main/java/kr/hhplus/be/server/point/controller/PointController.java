package kr.hhplus.be.server.point.controller;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import kr.hhplus.be.server.point.dto.request.PointChargeRequest;
import kr.hhplus.be.server.point.dto.response.PointChargeResponse;
import kr.hhplus.be.server.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController {

  private final PointService pointService;

  @PostMapping("/charge")
  public ResponseEntity<PointChargeResponse> charge(@Valid @RequestBody PointChargeRequest request) {
    return ResponseEntity.ok(pointService.charge(request.getUserId(), request.getAmount()));
  }

}
