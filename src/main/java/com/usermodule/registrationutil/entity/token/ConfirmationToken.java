package com.usermodule.registrationutil.entity.token;

import com.usermodule.registrationutil.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
            generator ="confirmation_token_sequence"
    )
    private Long id;

    @NotNull
    private String token;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
//            nullable = false,
            name = "user_id"
    )
    private User user;

    public ConfirmationToken(@NotNull String token,
                             @NotNull LocalDateTime createdAt,
                             @NotNull LocalDateTime expiresAt,
                             User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
