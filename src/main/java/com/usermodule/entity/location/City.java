package com.usermodule.entity.location;

import com.usermodule.model.audit.Auditable;
import com.usermodule.model.constant.RelationshipConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City extends Auditable<String> implements Serializable {

    private String name;

    @ManyToOne
    @JoinColumn(name = RelationshipConstants.STATE_ID)
    private State state;

}


