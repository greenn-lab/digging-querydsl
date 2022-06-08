package learning.diggingquerydsl.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
class MemberTest {

  @PersistenceContext
  EntityManager entityManager;


  @Test
  void shouldCreated() {
    final Team teamA = new Team("A");
    final Team teamB = new Team("B");

    entityManager.persist(teamA);
    entityManager.persist(teamB);

    final Member one = new Member("one", 12);
    one.setTeam(teamA);
    final Member two = new Member("two", 12);
    two.setTeam(teamB);
    final Member three = new Member("three", 12);
    three.setTeam(teamB);

    entityManager.persist(one);
    entityManager.persist(two);
    entityManager.persist(three);

    entityManager.flush();
    entityManager.clear();

    final List<Member> members = entityManager
        .createQuery("select m from Member m", Member.class)
        .getResultList();

    assertThat(members).size().isEqualTo(3);
  }

}
