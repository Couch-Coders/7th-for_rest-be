package couch.forrest.domain.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @Column(updatable = false, name="created_date_time")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date_time")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
