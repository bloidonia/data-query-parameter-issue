package data.query.parameter.issue;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@Sql("classpath:data.sql")
@Property(name = "property.value", value = "woo")
class DataQueryParameterIssueTest {

    private final static List<DataRepository.Result> EXPECTED = List.of(
            new DataRepository.Result("name1", "woo"),
            new DataRepository.Result("name2", "woo")
    );

    @Inject
    DataRepository repository;

    /**
     * This fails with 'Expandable query parts size should be the same as parameters size + 1. 3 != 1 + 1 SELECT name, 'woo' FROM data WHERE id IN ? [SELECT name,  'woo' FROM data WHERE id IN , ]'
     */
    @Test
    void thisShouldWorkButFails() {
        assertEquals(EXPECTED, repository.findDataById(Set.of("1", "2")));
    }

    /**
     * This passes
     */
    @Test
    void theWorkaroundWorksAsExpected() {
        assertEquals(EXPECTED, repository.findDataByIdWorkaround(Set.of("1", "2")));
    }
}
