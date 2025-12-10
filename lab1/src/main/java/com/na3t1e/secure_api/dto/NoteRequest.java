package com.na3t1e.secure_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class NoteRequest {
    private Long id;
    private String content;
    private String profile;
}
