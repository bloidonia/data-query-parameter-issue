package data.query.parameter.issue;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.ParameterExpression;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

@JdbcRepository(dialect = Dialect.H2)
public interface DataRepository extends CrudRepository<TestEntity, String> {

    @Query(
            value = "SELECT name, '${property.value}' as property_value FROM data WHERE id = :id",
            nativeQuery = true
    )
    Result findDataByExactId(String id);

    @Query(
            value = "SELECT name, '${property.value}' as property_value FROM data WHERE id IN (:id)",
            nativeQuery = true
    )
    List<Result> findDataById(Set<String> id);

    @Query(
            value = "SELECT name, :prop as property_value FROM data WHERE id IN (:id)",
            nativeQuery = true
    )
    @ParameterExpression(name = "prop", expression = "#{ env['property.value'] }")
    List<Result> findDataByIdWorkaround(Set<String> id);

    @Introspected
    record Result(String name, String propertyValue) {
    }
}
