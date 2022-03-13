package com.foonk.listener;

import com.foonk.entity.Audit;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

public class AuditTableListener implements PreDeleteEventListener, PreInsertEventListener {
    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        var build = Audit.builder()

                .entityId(event)
                .build();
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        return false;
    }
}
