package com.btb.groupsservice.client.kafka;


import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String email;

    private String subject;

    private String message;


}
