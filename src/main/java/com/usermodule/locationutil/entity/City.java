package com.usermodule.locationutil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usermodule.locationutil.model.audit.Auditable;
import com.usermodule.locationutil.model.constant.RelationshipConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "city")
public class City extends Auditable<String> implements Serializable {

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = RelationshipConstants.STATE_ID)
    private State state;

}


