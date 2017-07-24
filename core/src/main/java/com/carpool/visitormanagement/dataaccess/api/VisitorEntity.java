package com.carpool.visitormanagement.dataaccess.api;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.carpool.general.dataaccess.api.ApplicationPersistenceEntity;
import com.carpool.visitormanagement.common.api.Visitor;

@Entity
@Table(name = "Visitor")
public class VisitorEntity extends ApplicationPersistenceEntity implements Visitor {

  private String name;

  private String email;

  private String phone;

  private AccessCodeEntity code;

  private static final long serialVersionUID = 1L;

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return email
   */
  public String getEmail() {

    return this.email;
  }

  /**
   * @param email new value of {@link #getemail}.
   */
  public void setEmail(String email) {

    this.email = email;
  }

  /**
   * @return phone
   */
  public String getPhone() {

    return this.phone;
  }

  /**
   * @param phone new value of {@link #getphone}.
   */
  public void setPhone(String phone) {

    this.phone = phone;
  }

  /**
   * @return code
   */
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "idCode")
  public AccessCodeEntity getCode() {

    return this.code;
  }

  /**
   * @param code new value of {@link #getcode}.
   */
  public void setCode(AccessCodeEntity code) {

    this.code = code;
  }

  @Override
  @Transient
  public Long getCodeId() {

    if (this.code == null) {
      return null;
    }
    return this.code.getId();
  }

  @Override
  public void setCodeId(Long codeId) {

    if (codeId == null) {
      this.code = null;
    } else {
      AccessCodeEntity accessCodeEntity = new AccessCodeEntity();
      accessCodeEntity.setId(codeId);
      this.code = accessCodeEntity;
    }
  }

}
