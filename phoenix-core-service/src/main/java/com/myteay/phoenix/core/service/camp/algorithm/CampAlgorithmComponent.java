/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.util.List;

import com.myteay.phoenix.core.service.camp.algorithm.handles.SinglePrizeChecker;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmResult;

/**
 * 抽奖算法组件
 * 
 * @author danlley
 * @version $Id: CampAlgorithmComponent.java, v 0.1 Dec 21, 2018 12:41:48 AM danlley Exp $
 */
public interface CampAlgorithmComponent {

    /**
     * 抽奖算法组件抽奖入口
     * <pre>
     * 
     * 抽奖算法规则：
     *              1、奖品基础数据中的prizeLevel属性值越大，越有限参与抽奖，如果存在值相同的奖品，则从中进行随机选取，选取的几率在prizeLevel的奖品之间均等
     *              
     *              2、抽奖算法首先选取prizeLevel最大的奖品进行抽取，如果抽中，则不再抽取其他奖品
     *              
     *              3、抽奖算法在当前prizeLevel未抽到奖品，则从prizeLevel值仅次于（或等于，如果存在）当前值的奖品中进行抽取，如只有一个奖品，则直接返回抽奖结果
     *              
     *              4、算法通过prizeLevel选中奖品后，优先考虑percent属性，通过计算一个100以内的随机值与当前percent进行比较，如果小于percent，则不中奖
     *              
     *              5、一旦通过percent选中了奖品，则需要进一步通过奖位段进行抽取
     *              
     *              6、通过奖位段进行奖品抽取时，如果奖位段配置为： GP:{P:5/30} 则表示每半小时出5个将，此时当前组件会去检查当前奖品在半小时内的出奖数量，
     *                 符合要求则中奖，否则选取prizeLevel仅次于当前奖品的奖品继续执行如上操作。
     *              
     *              7、通过奖位段进行奖品抽取时，如奖位段配置为：GD:{D:3/14,D:80/9} 则表示每天下午14点至15点（不包含）出3个奖品，每天早上9点至10点（不包含）出80个奖品
     *                 此时，组件出奖也会检查当前奖品在限定时段是否已经达到出奖量，如果没有，则直接出奖，如果达到，则选取prizeLevel仅次于当前奖品的奖品继续上述操作
     *                 
     *              8、当前组件目前暂不支持按月、按年、按周的时间段划分抽奖方式
     *              
     *              9、通过奖位段进行奖品抽取时，如奖位段配置为：GFD:{{D:3/14,P:1/20},{D:8/9,P:1/15}} 含义如下：
     *                                                      a、每天14点出3个奖，每20分钟出一个
     *                                                      b、每天9点出8个奖，每15分钟出一个
     *                                                      
     *              10、如果所有的奖品抽取均未中奖，则返回抽奖失败
     *              
     *              11、如当前活动在抽奖中途进行过暂停并追加新奖品，则新的奖品将按上述规则在活动启动后参与抽奖过程
     *              
     *              12、如当前活动的某个奖品数量、等级、几率、奖位段等关键属性在基础数据中发生过变更，在活动启动后，这种变更不会在抽奖算法中生效。
     *              
     *              13、如果抽奖活动运行过程中，发现奖品已经不够了，可以创建一个相同奖品属性的奖品，活动启动后可起到替补作用
     * 
     * 
     * @param campId    活动ID
     * @param checker   个性化抽奖检查器
     * @return
     */
    public CampAlgorithmResult<CampAlgorithmModel> execute(String campId, List<SinglePrizeChecker> checkers);

    /**
     * 初始化抽奖组件
     * 
     * <pre>
     * 说明：
     *      1、活动启动后，调用该组件接口对出奖模块进行初始化
     *      
     *      2、当模块发现奖品已经在当前模块中完成了初始化，同时收到操作等级为1，则直接忽略初始化动作，直接打开奖品开关参与抽奖，对于当前奖品是否发生过重要参数变更均不做检查直接忽略
     *      
     *      3、操作等级传参错误的情况下，对当前请求不做任何处理
     *      
     *      4、系统重新启动或当前接口收到请求数据均会对抽奖缓存进行再次刷新
     * 
     * @param campAlgorithmModel    出奖算法模型，用于出奖模块数据落地
     * @param operationLevel        操作等级：1标识初始化，2标识暂停奖品（如活动暂停，或运行中的活动中奖品下架），3标识删除当前奖品不再参与相应的抽奖活动
     * @return
     */
    public CampAlgorithmResult<CampAlgorithmModel> initAlgorithm(CampAlgorithmModel campAlgorithmModel, int operationLevel);
}
