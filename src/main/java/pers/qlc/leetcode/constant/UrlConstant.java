package pers.qlc.leetcode.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlConstant {

    public static final String YES_4S = "1";

    public static final String NO_4S = "0";

    /**
     * 4s店集合
     */
    public static final List<String> ARR_OF_4s = new ArrayList<>(Arrays.asList("A010102", "A010101"));

    /**
     * PROD签名地址
     */
    public static final String PROD_SIGNATURE_URL = HttpConstant.PREFIX + HttpConstant.PROD_HOST + HttpConstant.SIGNATURE;

    /**
     * PROD同步协议地址
     */
    public static final String PROD_PROTOCOL_URL = HttpConstant.PREFIX + HttpConstant.PROD_HOST + HttpConstant.PROTOCOL;

    /**
     * UAT签名地址
     */
    public static final String UAT_SIGNATURE_URL = HttpConstant.PREFIX + HttpConstant.UAT_HOST + HttpConstant.SIGNATURE;

    /**
     * UAT同步协议地址
     */
    public static final String UAT_PROTOCOL_URL = HttpConstant.PREFIX + HttpConstant.UAT_HOST + HttpConstant.PROTOCOL;

    /**
     * 合作伙伴生产域名
     */
    public final static String ECOLOGY_PROD_URL = "http://platform.cic.inter/api/oyjr44z0rjwd7uwv";

    /**
     * 合约域生产域名
     */
    public final static String AGR_PROD_URL = "http://platform.cic.inter/api/4vw91mvuuudedjwe";

    /**
     * 合作伙伴PT域名
     */
    public final static String ECOLOGY_PT_URL = "http://h6uwg7zeev6zqkxx.apigateway.ant-test.res.cloud.cic.inter";

    /**
     * 合约域PT域名
     */
    public final static String AGR_PT_URL = "http://yvcoowi3iblr72lh.apigateway.ant-test.res.cloud.cic.inter";

    /**
     * 根据合约号和合约版本查询合约信息
     */
    public final static String GET_AGREEMENT_BY_NO_VERSION = "/platform/api/agr/agr/get-agreement-by-no-version";

    /**
     * 提交审批
     */
    public final static String COMMIT_APPROVAL = "/platform/api/ecology/approval/commit-approval";

    /**
     * 审批通过
     */
    public final static String APPROVAL_RESULT = "/platform/api/ecology/approval/approval-result";

    /**
     * 根据roleId查询代理挂靠
     */
    public final static String AFFILIATED_QUERY = "/platform/api/ecology/performance/query-affiliated-partner-list";
}
