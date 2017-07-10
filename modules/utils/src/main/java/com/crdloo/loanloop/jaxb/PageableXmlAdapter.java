package com.crdloo.loanloop.jaxb;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.data.domain.Pageable;

import com.crdloo.loanloop.data.Pager;

public class PageableXmlAdapter extends XmlAdapter<Pager, Pageable> {
    @Override
    public Pageable unmarshal(Pager v) throws Exception {
        return v.toPageable();
    }

    @Override
    public Pager marshal(Pageable v) throws Exception {
        return Pager.valueOf(v);
    }
}
