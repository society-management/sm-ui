package com.sm.common;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseModel implements Serializable {

    public static final String MODEL_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public boolean isNew() {
        return Objects.isNull(getId());
    }

    public boolean isEditing() {
        return Objects.nonNull(getId());
    }

    protected String getName() {
        return "ID:" + getId();
    }
}
