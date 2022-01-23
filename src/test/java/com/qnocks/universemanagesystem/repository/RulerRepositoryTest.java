package com.qnocks.universemanagesystem.repository;

import com.qnocks.universemanagesystem.domain.Ruler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(value = {"classpath:sql/data-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:sql/data-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RulerRepositoryTest {

    @Autowired
    private RulerRepository underTest;

    private List<Ruler> testRulers;

    @BeforeEach
    void setup() {
        testRulers = underTest.findAll();
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    @DisplayName("Should find all rulers where is planets is null")
    void shouldFindAllRulersWhereIsPlanetsIsNull() {
        List<Ruler> expected = testRulers.stream()
                .filter(ruler -> ruler.getPlanets().size() == 0)
                .collect(Collectors.toList());

        List<Ruler> actual = underTest.findAllByPlanetsIsNull();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should find top 10 youngest rulers")
    void shouldFindTop10YoungestRulers() {
        List<Ruler> expected = testRulers.stream()
                .sorted(Comparator.comparing(Ruler::getAge))
                .limit(10)
                .collect(Collectors.toList());

        List<Ruler> actual = underTest.findTop10Youngest();

        assertThat(actual).isEqualTo(expected);
    }
}
