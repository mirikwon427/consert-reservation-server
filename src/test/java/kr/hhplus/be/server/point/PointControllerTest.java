package kr.hhplus.be.server.point;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import kr.hhplus.be.server.point.controller.PointController;
import kr.hhplus.be.server.point.dto.request.PointChargeRequest;
import kr.hhplus.be.server.point.service.PointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(PointController.class)
public class PointControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private PointService pointService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("포인트 충전 API 호출에 성공한다.")
  void 포인트_충전_성공한다() throws Exception {
    // given
    Long userId = 1L;
    BigDecimal chargeAmount = new BigDecimal("10000");
    PointChargeRequest requestDto = new PointChargeRequest(userId, chargeAmount);

    String requestBody = objectMapper.writeValueAsString(requestDto);

    // when & then
    mockMvc.perform(
        post("/api/point/charge")
            .contentType("application/json")
            .content(requestBody)
    )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(userId))
        .andExpect(jsonPath("$.balanceAfterCharge").value(10000));

  }

}
