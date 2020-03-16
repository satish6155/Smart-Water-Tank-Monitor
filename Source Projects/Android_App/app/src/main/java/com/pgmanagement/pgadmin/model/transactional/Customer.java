package com.pgmanagement.pgadmin.model.transactional;

import com.pgmanagement.pgadmin.model.master.GenericParameter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ADMIN on 2/6/2019.
 */

public class Customer implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private Date dateOfjoining;

    private String firstName;

    private String lastName;

    private GenericParameter gender;

    private Date dateOfBirth;

    private long aadhar;

    private long mobileNumber;

    private String email;

    private String oragnizationName;

    private Timestamp creationTimeStamp;

    private Timestamp lastUpdatedTimeStamp;

    private Long createdBy;

    private Long lastUpdatedBy;

    private Integer status;

    private List<Address> addresses;
}
