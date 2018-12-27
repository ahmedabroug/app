package org.app.discovery.module.repository;

import org.app.discovery.module.model.JwtToken;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends ElasticsearchRepository<JwtToken, String> {

}
