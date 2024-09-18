package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDao;
import com.lavacorp.inven3.model.Location;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.springframework.stereotype.Repository;

@Repository
@JdbiRepository
@RegisterBeanMapper(Location.class)
public interface LocationDao extends NamedDao<Location> {
}
