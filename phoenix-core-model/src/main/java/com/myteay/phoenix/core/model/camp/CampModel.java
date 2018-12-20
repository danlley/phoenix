/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 营销活动模型
 * 
 * @author danlley
 * @version $Id: CampModel.java, v 0.1 Dec 16, 2018 10:50:26 PM danlley Exp $
 */
public class CampModel implements Serializable {

    /** serialVersionUID */
    private static final long    serialVersionUID   = -1688949748454481158L;

    /** 活动ID */
    private String               campId;

    /** 营销活动基本模型 */
    private CampBaseModel        campBaseModel      = new CampBaseModel();

    /** 营销活动对应的奖品列表 */
    private List<CampPrizeModel> campPrizeModelList = new ArrayList<CampPrizeModel>();

    /**
     * Getter method for property <tt>campId</tt>.
     * 
     * @return property value of campId
     */
    public String getCampId() {
        return campId;
    }

    /**
     * Setter method for property <tt>campId</tt>.
     * 
     * @param campId value to be assigned to property campId
     */
    public void setCampId(String campId) {
        this.campId = campId;
    }

    /**
     * Getter method for property <tt>campPrizeModelList</tt>.
     * 
     * @return property value of campPrizeModelList
     */
    public List<CampPrizeModel> getCampPrizeModelList() {
        return campPrizeModelList;
    }

    /**
     * Setter method for property <tt>campPrizeModelList</tt>.
     * 
     * @param campPrizeModelList value to be assigned to property campPrizeModelList
     */
    public void setCampPrizeModelList(List<CampPrizeModel> campPrizeModelList) {
        this.campPrizeModelList = campPrizeModelList;
    }

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
