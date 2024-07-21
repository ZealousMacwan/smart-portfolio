package studio.zealous.smartportfolio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String angelUserId;
    private String userName;
    private String emailId;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
