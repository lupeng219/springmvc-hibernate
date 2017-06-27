package com.lupeng.web.repository;

import java.util.List;

public interface PersonaPowerRepository {

    public List<Long> findPowerId(long personaId);
}
