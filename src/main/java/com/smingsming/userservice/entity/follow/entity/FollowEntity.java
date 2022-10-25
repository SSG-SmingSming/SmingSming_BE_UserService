package com.smingsming.userservice.entity.follow.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "follow")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long fromUserId;

    @NotNull
    private Long toUserId;

}
