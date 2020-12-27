package com.github.eugene.msv.elastic.repository;

import com.github.eugene.msv.elastic.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {


}
