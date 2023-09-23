package org.example.app.db;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Customer {
    private long id;
    private String fio;
    private String phone;
    private String address;
    private LocalDateTime created;
}