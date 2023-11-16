package pers.qlc.leetcode.constant;

public class UrlConstant {

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
