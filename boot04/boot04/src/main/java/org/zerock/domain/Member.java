package org.zerock.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_members")
@EqualsAndHashCode(of = "uid")
public class Member {

    @Id
    private String uid;

    private String uwd;

    private String uname;
}
