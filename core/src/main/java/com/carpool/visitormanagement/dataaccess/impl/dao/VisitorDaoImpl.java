package com.carpool.visitormanagement.dataaccess.impl.dao;

import javax.inject.Named;

import com.carpool.general.dataaccess.base.dao.ApplicationDaoImpl;
import com.carpool.visitormanagement.dataaccess.api.VisitorEntity;
import com.carpool.visitormanagement.dataaccess.api.dao.VisitorDao;
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;
import com.mysema.query.alias.Alias;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * This is the implementation of {@link VisitorDao}.
 */
@Named
public class VisitorDaoImpl extends ApplicationDaoImpl<VisitorEntity> implements VisitorDao {

  /**
   * The constructor.
   */
  public VisitorDaoImpl() {

    super();
  }

  @Override
  public Class<VisitorEntity> getEntityClass() {

    return VisitorEntity.class;
  }

  @Override
  public PaginatedListTo<VisitorEntity> findVisitors(VisitorSearchCriteriaTo criteria) {

    VisitorEntity visitor = Alias.alias(VisitorEntity.class);
    EntityPathBase<VisitorEntity> alias = Alias.$(visitor);
    JPAQuery query = new JPAQuery(getEntityManager()).from(alias);

    String name = criteria.getName();
    if (name != null) {
      query.where(Alias.$(visitor.getName()).eq(name));
    }
    String email = criteria.getEmail();
    if (email != null) {
      query.where(Alias.$(visitor.getEmail()).eq(email));
    }
    String phone = criteria.getPhone();
    if (phone != null) {
      query.where(Alias.$(visitor.getPhone()).eq(phone));
    }
    Long code = criteria.getCodeId();
    if (code != null) {
      if (visitor.getCode() != null) {
        query.where(Alias.$(visitor.getCode().getId()).eq(code));
      }
    }
    return findPaginated(criteria, query, alias);
  }

}