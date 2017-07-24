package com.carpool.visitormanagement.dataaccess.impl.dao;

import java.sql.Timestamp;

import javax.inject.Named;

import com.carpool.general.dataaccess.base.dao.ApplicationDaoImpl;
import com.carpool.visitormanagement.dataaccess.api.AccessCodeEntity;
import com.carpool.visitormanagement.dataaccess.api.dao.AccessCodeDao;
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.mysema.query.alias.Alias;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * This is the implementation of {@link AccessCodeDao}.
 */
@Named
public class AccessCodeDaoImpl extends ApplicationDaoImpl<AccessCodeEntity> implements AccessCodeDao {

  /**
   * The constructor.
   */
  public AccessCodeDaoImpl() {

    super();
  }

  @Override
  public Class<AccessCodeEntity> getEntityClass() {

    return AccessCodeEntity.class;
  }

  @Override
  public PaginatedListTo<AccessCodeEntity> findAccessCodes(AccessCodeSearchCriteriaTo criteria) {

    AccessCodeEntity accesscode = Alias.alias(AccessCodeEntity.class);
    EntityPathBase<AccessCodeEntity> alias = Alias.$(accesscode);
    JPAQuery query = new JPAQuery(getEntityManager()).from(alias);

    String code = criteria.getCode();
    if (code != null) {
      query.where(Alias.$(accesscode.getCode()).eq(code));
    }
    Timestamp dateAndTime = criteria.getDateAndTime();
    if (dateAndTime != null) {
      query.where(Alias.$(accesscode.getDateAndTime()).eq(dateAndTime));
    }
    Long visitor = criteria.getVisitorId();
    if (visitor != null) {
      if (accesscode.getVisitor() != null) {
        query.where(Alias.$(accesscode.getVisitor().getId()).eq(visitor));
      }
    }
    return findPaginated(criteria, query, alias);
  }

}