package com.example.demo.repository;

import com.example.demo.model.ESApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompareESRepository extends ElasticsearchRepository<ESApplication, String> {
    @Query("{\"bool\":{\"must\":[{\"query_string\":{\"query\":\"*?0*\",\"fields\":[\"brand^1.0\",\"productname^1.0\",\"os^1.0\",\"network^1.0\"],\"type\":\"best_fields\",\"default_operator\":\"or\",\"max_determinized_states\":10000,\"enable_position_increments\":true,\"fuzziness\":\"AUTO\",\"fuzzy_prefix_length\":0,\"fuzzy_max_expansions\":50,\"phrase_slop\":0,\"analyze_wildcard\":true,\"escape\":false,\"auto_generate_synonyms_phrase_query\":true,\"fuzzy_transpositions\":true,\"boost\":1.0}}],\"adjust_pure_negative\":true,\"boost\":1.0}},\"version\":true}")
    List<ESApplication> findByApplication(String words, Pageable pageable);

}

