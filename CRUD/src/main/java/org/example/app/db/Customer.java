package org.example.app.db;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private long id;
    private String fio;
    private String phone;
    private String address;
    private LocalDateTime created;
}