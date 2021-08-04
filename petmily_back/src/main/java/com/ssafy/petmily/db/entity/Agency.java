package com.ssafy.petmily.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
public class Agency extends BaseEntity {
    String email;
    String username;
    String phone;
    String img;
    String agencycode;
    String agencyname;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name="Agency_no")
//    Collection<AnimalWait> AnimalWait;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

}
