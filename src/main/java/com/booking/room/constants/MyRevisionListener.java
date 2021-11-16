package com.booking.room.constants;

import org.hibernate.envers.RevisionListener;

public class MyRevisionListener implements RevisionListener {
    Revision rev = null;
    @Override
    public void newRevision(Object revisionEntity) {
        rev = (Revision) revisionEntity;
        rev.setUserId(1L);//Session UserID needed to be set from session
    }
}
