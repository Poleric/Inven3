package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDao;
import com.lavacorp.inven3.model.Supplier;
import org.jdbi.v3.spring5.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository
@JdbiRepository
@RegisterBeanMapper(Supplier.class)
public interface SupplierDao extends NamedDao<Supplier> {
    @SqlQuery("select")
    Stream<Supplier> selectAllByItemId(@Bind @Define int itemId);

    @SqlQuery("select")
    int selectAllByItemId(@Bind @Define int itemId, @Define boolean count);

    @SqlQuery("select")
    Stream<Supplier> selectAllByItemId(@Bind @Define int itemId, @Define String orderColumn, @Define OrderDirection orderDirection);

    @SqlQuery("select")
    Stream<Supplier> selectAllByItemId(@Bind @Define int itemId, @Define int page, @Define int pageSize);

    @SqlQuery("select")
    Stream<Supplier> selectAllByItemId(@Bind @Define int itemId, @Define String orderColumn, @Define OrderDirection orderDirection, @Define int page, @Define int pageSize);
}
