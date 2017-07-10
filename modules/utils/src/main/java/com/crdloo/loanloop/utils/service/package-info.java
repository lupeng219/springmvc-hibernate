@XmlJavaTypeAdapters({ @XmlJavaTypeAdapter(PageXmlAdapter.class),
        @XmlJavaTypeAdapter(PageableXmlAdapter.class) })
package com.crdloo.loanloop.utils.service;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import com.crdloo.loanloop.jaxb.*;
