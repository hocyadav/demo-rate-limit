package com.example.demoratelimit.domains;

import lombok.*;

import java.time.OffsetDateTime;

/**
 * @author Hariom Yadav
 * @since 28-Dec-2023
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Requests {
    OffsetDateTime time;
}
