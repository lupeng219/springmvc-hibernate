package com.crdloo.loanloop.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.crdloo.loanloop.data.Pager;

public class PageXmlAdapter extends XmlAdapter<Pager, Page> {
    @Override
    public Page unmarshal(Pager v) throws Exception {
        if (CollectionUtils.isEmpty(v.getData()))
            v.setData(new ArrayList());

        return new PageImpl(v.getData(), v.toPageable(), v.getTotalElements());
    }

    @Override
    public Pager marshal(Page v) throws Exception {
        return Pager.valueOf(v);
    }

}
