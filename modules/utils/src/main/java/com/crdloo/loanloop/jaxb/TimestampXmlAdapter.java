package com.crdloo.loanloop.jaxb;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampXmlAdapter extends XmlAdapter<Long, Timestamp> {

    @Override
    public Timestamp unmarshal(Long v) throws Exception {
        return new Timestamp(v);
    }

    @Override
    public Long marshal(Timestamp v) throws Exception {
        return v.getTime();
    }
}
