package kr.hhplus.be.server.reservation.adapter.out;

import java.util.Optional;
import kr.hhplus.be.server.entity.user.User;
import kr.hhplus.be.server.entity.user.UserRepository;
import kr.hhplus.be.server.reservation.application.port.out.LoadUserPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class UserPersistenceAdapter implements LoadUserPort {
  private final UserRepository userRepo;
  public UserPersistenceAdapter(UserRepository userRepo) { this.userRepo = userRepo; }
  @Override public Optional<User> findById(Long id) {
    return userRepo.findById(id);
  }
}
