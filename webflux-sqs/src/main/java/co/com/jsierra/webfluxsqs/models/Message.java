package co.com.jsierra.webfluxsqs.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Message {
    String message;
}
