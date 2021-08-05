package com.siam.springboot.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private long seq;
}
