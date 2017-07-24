package com.carpool.visitormanagement.logic.impl;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carpool.general.logic.base.AbstractComponentFacade;
import com.carpool.visitormanagement.dataaccess.api.AccessCodeEntity;
import com.carpool.visitormanagement.dataaccess.api.dao.AccessCodeDao;
import com.carpool.visitormanagement.dataaccess.api.dao.VisitorDao;
import com.carpool.visitormanagement.logic.api.Visitormanagement;
import com.carpool.visitormanagement.logic.api.to.AccessCodeEto;
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.carpool.visitormanagement.logic.api.to.VisitorEto;
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;
import com.carpool.visitormanagement.logic.api.usecase.UcFindVisitor;
import com.carpool.visitormanagement.logic.api.usecase.UcManageVisitor;

import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Implementation of component interface of visitormanagement
 */
@Named
public class VisitormanagementImpl extends AbstractComponentFacade implements Visitormanagement {

  @Inject
  private UcFindVisitor ucFindVisitor;

  @Inject
  private UcManageVisitor ucManageVisitor;

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(VisitormanagementImpl.class);

  private static final int MAXIMUM_HIT_LIMIT = 0;

  /**
   * @see #getVisitorDao()
   */
  @Inject
  private VisitorDao visitorDao;

  /**
   * @see #getAccessCodeDao()
   */
  @Inject
  private AccessCodeDao accessCodeDao;

  /**
   * The constructor.
   */
  public VisitormanagementImpl() {

    super();
  }

  @Override
  public VisitorEto findVisitor(Long id) {

    return this.ucFindVisitor.findVisitor(id);
  }

  @Override
  public PaginatedListTo<VisitorEto> findVisitorEtos(VisitorSearchCriteriaTo criteria) {

    return this.ucFindVisitor.findVisitorEtos(criteria);
  }

  @Override
  public VisitorEto saveVisitor(VisitorEto visitor) {

    return this.ucManageVisitor.saveVisitor(visitor);
  }

  @Override
  public boolean deleteVisitor(Long id) {

    return this.ucManageVisitor.deleteVisitor(id);
  }

  /**
   * Returns the field 'visitorDao'.
   *
   * @return the {@link VisitorDao} instance.
   */
  public VisitorDao getVisitorDao() {

    return this.visitorDao;
  }

  @Override
  public AccessCodeEto findAccessCode(Long id) {

    LOG.debug("Get AccessCode with id {} from database.", id);
    return getBeanMapper().map(getAccessCodeDao().findOne(id), AccessCodeEto.class);
  }

  @Override
  public PaginatedListTo<AccessCodeEto> findAccessCodeEtos(AccessCodeSearchCriteriaTo criteria) {

    criteria.limitMaximumPageSize(MAXIMUM_HIT_LIMIT);
    PaginatedListTo<AccessCodeEntity> accesscodes = getAccessCodeDao().findAccessCodes(criteria);
    return mapPaginatedEntityList(accesscodes, AccessCodeEto.class);
  }

  @Override
  public boolean deleteAccessCode(Long accessCodeId) {

    AccessCodeEntity accessCode = getAccessCodeDao().find(accessCodeId);
    getAccessCodeDao().delete(accessCode);
    LOG.debug("The accessCode with id '{}' has been deleted.", accessCodeId);
    return true;
  }

  @Override
  public AccessCodeEto saveAccessCode(AccessCodeEto accessCode) {

    Objects.requireNonNull(accessCode, "accessCode");
    AccessCodeEntity accessCodeEntity = getBeanMapper().map(accessCode, AccessCodeEntity.class);

    // initialize, validate accessCodeEntity here if necessary
    getAccessCodeDao().save(accessCodeEntity);
    LOG.debug("AccessCode with id '{}' has been created.", accessCodeEntity.getId());

    return getBeanMapper().map(accessCodeEntity, AccessCodeEto.class);
  }

  /**
   * Returns the field 'accessCodeDao'.
   *
   * @return the {@link AccessCodeDao} instance.
   */
  public AccessCodeDao getAccessCodeDao() {

    return this.accessCodeDao;
  }

}
