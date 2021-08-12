package com.jessy.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@RevisionEntity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "revinfo")
public class CustomRevisionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @RevisionNumber
    @EqualsAndHashCode.Include
    @Column(name = "rev")
    private Long id;

    @RevisionTimestamp
    @EqualsAndHashCode.Include
    @Column(name = "revtstmp")
    private Long timestamp;


    @Transient
    public Date getRevisionDate() {
        return new Date(timestamp);
    }

    @Override
    public String toString() {
        return String.format("LongRevisionEntity(id = %d, revisionDate = %s)",
                id, DateFormat.getDateTimeInstance().format(getRevisionDate()));
    }

}