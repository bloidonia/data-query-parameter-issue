package data.query.parameter.issue;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.model.DataType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "data")
@Introspected
public record TestEntity(
        @Id
        @TypeDef(type = DataType.STRING)
        String id,

        @Column(name = "name")
        String name
) {
}
