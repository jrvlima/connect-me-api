package io.zeecode.connectmeapi.services.connection.model;

import io.zeecode.connectmeapi.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author João Vinagre Lima
 * @version 1.0
 * @created 17-nov-2024 17:08:48
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "friendships")
public class Friendship extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Person friend;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person me;

    public Friendship() {

    }


}