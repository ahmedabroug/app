package org.app.discovery.module.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "security", type = "token")
@Data
@NoArgsConstructor
public class JwtToken {
    @Id
    private String token;
    
    public JwtToken(String token) {
        this.token = token;
    }


}
