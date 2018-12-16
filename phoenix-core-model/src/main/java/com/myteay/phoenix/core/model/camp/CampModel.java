/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 营销活动模型
 * 
 * @author danlley
 * @version $Id: CampModel.java, v 0.1 Dec 16, 2018 10:50:26 PM danlley Exp $
 */
public class CampModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1688949748454481158L;

    /** 营销活动基本模型 */
    private CampBaseModel     campBaseModel;

    /**
     * Getter method for property <tt>campBaseModel</tt>.
     * 
     * @return property value of campBaseModel
     */
    public CampBaseModel getCampBaseModel() {
        return campBaseModel;
    }

    /**
     * Setter method for property <tt>campBaseModel</tt>.
     * 
     * @param campBaseModel value to be assigned to property campBaseModel
     */
    public void setCampBaseModel(CampBaseModel campBaseModel) {
        this.campBaseModel = campBaseModel;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
