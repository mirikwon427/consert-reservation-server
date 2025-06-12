package kr.hhplus.be.server.reservation.application.port.out;

import java.util.Optional;
import kr.hhplus.be.server.entity.user.User;

public interface LoadUserPort {
  Optional<User> findById(Long userId);
}
