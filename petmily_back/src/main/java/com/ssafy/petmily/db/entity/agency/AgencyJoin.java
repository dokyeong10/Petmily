package com.ssafy.petmily.db.entity.agency;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.petmily.db.entity.shelter.ShelterJoin;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "agency")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class AgencyJoin {
    @Id
    String agencycode;

    String email;
    String username;
    String phone;
    String img;
    String agencyname;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencycode", referencedColumnName = "agencycode", insertable = false,updatable = false)
    ShelterJoin shelterJoin;

}
