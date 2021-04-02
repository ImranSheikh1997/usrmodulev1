
package com.usermodule.locationutil.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
/*
    @CreatedBy
    @Getter
    @Setter
    @JsonIgnore
    protected U createdBy;

    @Getter
    @Setter
    @CreatedDate
//    @JsonIgnore
    @Column(columnDefinition = "TIMESTAMP")
    protected LocalDateTime creationDate;

    @Getter
    @Setter
    @LastModifiedBy
    @JsonIgnore
    protected U lastModifiedBy;


    @Getter
    @Setter
    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP")
//    @JsonIgnore
    protected LocalDateTime lastModifiedDate;

    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "is_active")
    protected boolean active = true;

 */
}
