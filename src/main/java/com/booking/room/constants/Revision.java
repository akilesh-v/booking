package com.booking.room.constants;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

@Entity
@RevisionEntity(MyRevisionListener.class)
@Getter
@Setter
public class Revision extends DefaultRevisionEntity {
    private Long userId;
}
