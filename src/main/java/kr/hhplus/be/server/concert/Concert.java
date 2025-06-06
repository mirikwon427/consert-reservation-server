package kr.hhplus.be.server.concert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.hhplus.be.server.BaseTimeEntity;

@Entity
public class Concert extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "concert_id")
  private Long id;

  @Column(name = "concert_name", nullable = false)
  private String name;

  @Column(name = "concert_artist", nullable = false)
  private String artist;

  @Column(name = "concert_venue", nullable = false)
  private String venue;

  @Column(name = "concert_description", columnDefinition = "TEXT")
  private String description;

  protected Concert() {}

  public Concert(Long id, String name, String artist, String venue, String description) {
    this.id = id;
    this.name = name;
    this.artist = artist;
    this.venue = venue;
    this.description = description;
  }

}
