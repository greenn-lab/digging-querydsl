package learning.diggingquerydsl.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"team"})
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  private int age;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "TEAM_ID")
  private Team team;

  public Member(String username, int age) {
    this.username = username;
    this.age = age;
  }

}
