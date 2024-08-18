package studio.zealous.smartportfolio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_tokens_mapping")
public class UserTokensMapping {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "angel_user_id", referencedColumnName = "angelUserId")
    private User angelUserId;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "jwt_token")
    private String jwtToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
