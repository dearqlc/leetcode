package leetcode;

import com.alibaba.fastjson.JSON;
import com.aliyun.fsi.insurance.agreement.facade.dto.AgreementDTO;
import com.aliyun.fsi.insurance.agreement.facade.dto.DepartmentDTO;
import com.aliyun.fsi.insurance.agreement.facade.dto.ParticipantDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pers.qlc.leetcode.ApplicationStartUp;
import pers.qlc.leetcode.constant.HttpConstant;
import pers.qlc.leetcode.dto.AgreementExcel;
import pers.qlc.leetcode.dto.AgreementSyncExcelDTO;
import pers.qlc.leetcode.dto.param.*;
import pers.qlc.leetcode.dto.request.*;
import pers.qlc.leetcode.dto.response.*;
import pers.qlc.leetcode.enums.PartnerChannelMappingEnum;
import pers.qlc.leetcode.model.DeskModel;
import pers.qlc.leetcode.service.IDeskService;
import pers.qlc.leetcode.util.ExcelUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author QLC
 * @Date 2022/12/27 11:01
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStartUp.class)
class ApplicationStartUpTest {

    public static final String YES_4S = "1";
    public static final String NO_4S = "0";
    // 4s店集合
    public static final List<String> ARR_OF_4s = new ArrayList<>(Arrays.asList("A010102", "A010101"));
    /**
     * PROD签名地址
     */
    private static final String PROD_SIGNATURE_URL = HttpConstant.PREFIX + HttpConstant.PROD_HOST + HttpConstant.SIGNATURE;
    /**
     * PROD同步协议地址
     */
    private static final String PROD_PROTOCOL_URL = HttpConstant.PREFIX + HttpConstant.PROD_HOST + HttpConstant.PROTOCOL;
    /**
     * UAT签名地址
     */
    private static final String UAT_SIGNATURE_URL = HttpConstant.PREFIX + HttpConstant.UAT_HOST + HttpConstant.SIGNATURE;
    /**
     * UAT同步协议地址
     */
    private static final String UAT_PROTOCOL_URL = HttpConstant.PREFIX + HttpConstant.UAT_HOST + HttpConstant.PROTOCOL;
    /**
     * 获取restTemplate
     */
    private final RestTemplate restTemplate = new RestTemplate();
    /**
     * Excel文件地址
     */
    private static final String PATH_NAME = "E:\\xxx.xlsx";

    @Autowired
    private IDeskService deskService;

    /**
     * 批量同步协议到议价平台
     */
    @Test
    public void syncAgreement() {
        // 解析Excel
        List<AgreementExcel> agreementList = ExcelUtils.readExcelWithCheck(
                AgreementExcel.class,
                new File(PATH_NAME),
                (t, value) -> ((AgreementExcel) t).setResult(value),
                (t, value) -> ((AgreementExcel) t).setFailReason(value)
        );

        // 计数
        int count = 1;

        // 遍历Excel数据
        for (AgreementExcel agreement : agreementList) {

//            // 每一百条延时1分钟
//            if (count % 100 == 0) {
//                try {
//                    log.info("第{}条签名, 延时一分钟-------------------------------------------", count);
//                    TimeUnit.MINUTES.sleep(1);
//                    log.info("延时结束-------------------------------------------------------");
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();
//                }
//            }

            // 获取签名
            SignatureRequestDTO signatureRequestDTO = new SignatureRequestDTO(100);
            ResponseEntity<SignatureResponseDTO> response = restTemplate.postForEntity(UAT_SIGNATURE_URL, signatureRequestDTO, SignatureResponseDTO.class);
            if (response.getBody() == null) {
                log.info("获取第{}条签名失败, 协议号为{}！", count, agreement.getAgreementNo());
                continue;
            }

            // 填充签名数据
            PartnerProtocolRequestDTO protocolRequestDTO = new PartnerProtocolRequestDTO();
            protocolRequestDTO.setHost(response.getBody().getData().getHost());
            protocolRequestDTO.setPolicy(response.getBody().getData().getPolicy());
            protocolRequestDTO.setAccessKey(response.getBody().getData().getAccessKey());
            protocolRequestDTO.setSignature(response.getBody().getData().getSignature());
            protocolRequestDTO.setExpiretime(response.getBody().getData().getExpiretime());

            // 填充合约数据
            ProtocolRequestDataDTO data = new ProtocolRequestDataDTO();
            data.setCarCount(1);
            data.setOrgcd(agreement.getOrgCode());
            data.setPartnerCode(agreement.getRoleId());
            data.setPartnerName(agreement.getPartnerName());
            data.setAgentProtocolCode(agreement.getAgreementNo());
            data.setEndDate(agreement.getDateEnd().substring(0, 10));
            data.setStartDate(agreement.getDateStart().substring(0, 10));
            data.setPartnerVersionNo("0");
            data.setIsPriceFee("2");
            String partnerLv4TypeCode = agreement.getRoleId().substring(0, 7);
            data.setOrgChannel(PartnerChannelMappingEnum.getChannelByLevel4(partnerLv4TypeCode));
            data.setPartnerSystemType(ARR_OF_4s.contains(partnerLv4TypeCode) ? YES_4S : NO_4S);
            protocolRequestDTO.setData(data);

            try {
                // 同步到议价
                log.info("同步第{}条合作伙伴协议信息到议价平台, request: {}", count, JSON.toJSONString(protocolRequestDTO));
                ResponseEntity<PartnerAgrSyncResponseDTO> postForEntity = restTemplate.postForEntity(UAT_PROTOCOL_URL, protocolRequestDTO, PartnerAgrSyncResponseDTO.class);
                log.info("第{}条{}, response:{}", count, protocolRequestDTO.getData().getAgentProtocolCode(), JSON.toJSONString(postForEntity.getBody()));
            } catch (Exception e) {
                log.error("同步第{}合作伙伴协议信息到议价平台失败", count);
            }

            count++;
        }
    }

    /**
     * 数据库测试方法
     */
    @Test
    public void dbTest() {
        LambdaQueryWrapper<DeskModel> deskQueryWrapper = new LambdaQueryWrapper<>();
        deskQueryWrapper.eq(DeskModel::getDeskId, 1);
        List<DeskModel> list = deskService.list(deskQueryWrapper);
        System.out.println(list);
    }

    /**
     * 合作伙伴生产域名
     */
    private final static String ECOLOGY_PROD_URL = "http://platform.cic.inter/api/oyjr44z0rjwd7uwv";

    /**
     * 合约域生产域名
     */
    private final static String AGR_PROD_URL = "http://platform.cic.inter/api/4vw91mvuuudedjwe";

    /**
     * 合作伙伴PT域名
     */
    private final static String ECOLOGY_PT_URL = "http://h6uwg7zeev6zqkxx.apigateway.ant-test.res.cloud.cic.inter";

    /**
     * 合约域PT域名
     */
    private final static String AGR_PT_URL = "http://yvcoowi3iblr72lh.apigateway.ant-test.res.cloud.cic.inter";

    /**
     * 根据合约号和合约版本查询合约信息
     */
    private final static String GET_AGREEMENT_BY_NO_VERSION = "/platform/api/agr/agr/get-agreement-by-no-version";

    /**
     * 提交审批
     */
    private final static String COMMIT_APPROVAL = "/platform/api/ecology/approval/commit-approval";

    /**
     * 审批通过
     */
    private final static String APPROVAL_RESULT = "/platform/api/ecology/approval/approval-result";

    /**
     * 根据roleId查询代理挂靠
     */
    private final static String AFFILIATED_QUERY = "/platform/api/ecology/performance/query-affiliated-partner-list";

    private final static String COOKIE_PT = "csrfToken=TAyH0UvTQ3DDcnQnOAFmk5Sq; cic-ctoken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcHBJZCIsImFwcElkIjoiNjg3NGFlYjM3N2Y5MGVlZGJiMzQyNWY2Yjc0Zjc0MWVzTFRrNldlRGpoeSIsImFjY2Vzc1Rva2VuIjoiZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmhkV1FpT2xzaVpXNTBaWEp3Y21selpWOXRiMkpwYkdWZmNtVnpiM1Z5WTJVaUxDSmlabVpmWVhCcFgzSmxjMjkxY21ObElsMHNJbVY0Y0NJNk1UWTVNakF4T0RRek1Dd2lkWE5sY2w5dVlXMWxJam9pTVRBeE1EQXdNVE01TXlJc0ltcDBhU0k2SWpJelpXRm1OamRoTFRNMU1EUXROREUwWWkwNVptWXdMV0l3Tm1NeFpqTmxOekptT1NJc0ltTnNhV1Z1ZEY5cFpDSTZJalk0TnpSaFpXSXpOemRtT1RCbFpXUmlZak0wTWpWbU5tSTNOR1kzTkRGbGMweFVhelpYWlVScWFIa2lMQ0p6WTI5d1pTSTZXeUp5WldGa0lsMTkuaUZlNGc5SEt3bFJTUUxmQ29ZQmVOWkdKX05wMXBWd3pGaU5QdWg0MGtWTSIsImV4cCI6MTY5MjAxODQzMCwiaWF0IjoxNjkxOTc1MjMwLCJqdGkiOiIwNmZlYzk2Yy01MmRlLTQ0NGMtODU4MS00MzA2YzJmZWM2MDUifQ.GDqHMx2xcAYWDqpIr0Z5OuRB8UEIYIKX1x2CNfe9294";

    private final static String COOKIE_PROD = "undefined__tenant=AYEOSUMI; undefined__project=AYEOSUMI; undefined__workspace=prod; undefined__region=0000000001; 0000020314__tenant=AYEOSUMI; 0000020314__project=AYEOSUMI; 0000020314__workspace=prod; 0000020314__region=0000000001; ctoken=bigfish_ctoken_18i40igh85; csrfToken=Mv5l9z3vy1B7JA9lxF5zwNS8; cic-ctoken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcHBJZCIsImFwcElkIjoiZGIzZWIyNWQ4MWRmMDQ3YWZmNmZjM2JmZjAwZjUxM2FSazBlVTdOVUxtbSIsImFjY2Vzc1Rva2VuIjoiZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmhkV1FpT2xzaVpXNTBaWEp3Y21selpWOXRiMkpwYkdWZmNtVnpiM1Z5WTJVaUxDSmlabVpmWVhCcFgzSmxjMjkxY21ObElsMHNJbVY0Y0NJNk1UWTVNakF4T1RVeE5Td2lkWE5sY2w5dVlXMWxJam9pTVRBeE1EQXdNVFV4TUNJc0ltcDBhU0k2SW1NMU5qVmtaV0UwTFRBM09XRXRORFF3TnkxaVpUSXhMV1JoWVdJMk4ySm1ZV1kzTVNJc0ltTnNhV1Z1ZEY5cFpDSTZJbVJpTTJWaU1qVmtPREZrWmpBME4yRm1aalptWXpOaVptWXdNR1kxTVROaFVtc3daVlUzVGxWTWJXMGlMQ0p6WTI5d1pTSTZXeUp5WldGa0lsMTkuTEhtSWNoQmdxWUdBV1Fpdy0wQ0JXb1FSMThtTnZzWXFiV1R3TXVCNnp0VSIsImV4cCI6MTY5MjAxOTUxNCwiaWF0IjoxNjkxOTc2MzE1LCJqdGkiOiIwMjVmZGI3Zi1mNmUwLTRmNzMtODIyZC1iM2VjM2VlNGRiNGIifQ.GpZ1yhYvZ0Ge9ZX-PS1Zk49fAk88Nq6UBLRWXdLXcv0";

    private static final String AGREEMENT_SYNC_PATH_NAME = "E:\\代理合约814.xlsx";

    /**
     * 根据代理合约补充合作合约
     */
    @Test
    public void agreementSync() {
        String str = "prod";

        String AGR_URL = null;
        String ECO_URL = null;
        String COOKIE = null;
        if (str.equals("prod")) {
            AGR_URL = AGR_PROD_URL;
            ECO_URL = ECOLOGY_PROD_URL;
            COOKIE = COOKIE_PROD;
        } else if (str.equals("pt")) {
            AGR_URL = AGR_PT_URL;
            ECO_URL = ECOLOGY_PT_URL;
            COOKIE = COOKIE_PT;
        }

        // 解析Excel
        List<AgreementSyncExcelDTO> agreementList = getAgreementSyncExcelDTOS();

        int count = 1;
        int size = agreementList.size();

        // 遍历Excel数据
        for (AgreementSyncExcelDTO agreement : agreementList) {

            // 获取合约号版本号
            AgreementQueryRequestDTO agreementQueryDTO = getAgreementQueryRequestDTO(agreement);

            // 根据合约号版本号获取合约域信息
            ResponseEntity<AgreementQueryResponseDTO> agrEntity = restTemplate.postForEntity(AGR_URL + GET_AGREEMENT_BY_NO_VERSION, agreementQueryDTO, AgreementQueryResponseDTO.class);

            if (agrEntity.getBody().getData() == null) {
                log.info("第{}/{}条，根据合约号：{}，版本号：{}，查询合约域合约失败！", count, size, agreementQueryDTO.getAgreementNo(), agreementQueryDTO.getAgreementMajorVersionNo());
                count++;
                continue;
            }

            // 获取到合约域合约
            AgreementDTO agreementDTO = agrEntity.getBody().getData();

            log.info("第{}/{}条，根据合约号：{}，版本号：{}，得到合约域合约：{}", count, size, agreementQueryDTO.getAgreementNo(), agreementQueryDTO.getAgreementMajorVersionNo(), JSON.toJSONString(agreementDTO));

            // 获取甲乙方
            AgreementParticipantDTO participant = getAgreementParticipant(agreementDTO);

            if (participant == null) {
                log.info("第{}/{}条，获取甲乙方失败！", count, size);
                count++;
                continue;
            }

            // 创建PagrDTO
            ApprovalDocOdPageDTO approvalDocOdPageDTO = getApprovalDocOdPageDTO(ECO_URL, count, agreement, agreementDTO, participant);

            log.info("第{}/{}条，commitApproval入参：{}", count, size, JSON.toJSONString(approvalDocOdPageDTO));

            // 组装CommitApproval接口入参
            HttpEntity<String> pageHttpEntity = getCommitApprovalHttpEntity(COOKIE, approvalDocOdPageDTO);

            // 调用commitApproval接口
            ResponseEntity<CommitApprovalResponseDTO> commitApprovalEntity = restTemplate.exchange(ECO_URL + COMMIT_APPROVAL, HttpMethod.POST, pageHttpEntity, CommitApprovalResponseDTO.class);

            if (commitApprovalEntity.getBody().getData() == null) {
                log.info("第{}/{}条，提交审批失败！", count, size);
                count++;
                continue;
            }

            // 得到审批单号approvalDocCode
            String approvalDocCode = commitApprovalEntity.getBody().getData();
            log.info("第{}/{}条，审批单号为{}！", count, size, approvalDocCode);

            // 创建ApprovalResultDTO
            ApprovalResultRequestDTO approvalResultRequestDTO = getApprovalResultRequestDTO(approvalDocCode);

            // 组装ApprovalResult接口入参
            HttpEntity<String> approvalHttpEntity = getApprovalResultHttpEntity(COOKIE, approvalResultRequestDTO);

            // 调用approvalResult接口
            ResponseEntity<ApprovalResultResponseDTO> approvalResultEntity = restTemplate.exchange(ECO_URL + APPROVAL_RESULT, HttpMethod.POST, approvalHttpEntity, ApprovalResultResponseDTO.class);

            if (approvalResultEntity.getBody().getData() != null && approvalResultEntity.getBody().getData().equals("success")) {
                log.info("同步第{}/{}条成功", count, size);
            } else {
                log.info("同步第{}/{}条失败", count, size);
            }

            count++;

        }
    }

    /**
     * 创建pageDTO
     * @param ECO_URL
     * @param count
     * @param agreement
     * @param agreementDTO
     * @param participant
     * @return
     */
    private ApprovalDocOdPageDTO getApprovalDocOdPageDTO(String ECO_URL, int count, AgreementSyncExcelDTO agreement, AgreementDTO agreementDTO, AgreementParticipantDTO participant) {
        ApprovalDocOdPageDTO approvalDocOdPageDTO = getApprovalDocOdPageDTO(agreement);
        // buDTO
        setBuDTO(participant, approvalDocOdPageDTO);
        // agrDTO
        setAgrDTO(agreementDTO, participant, approvalDocOdPageDTO);
        // strategyDTO
        setStrategyDTO(ECO_URL, count, agreement, approvalDocOdPageDTO);
        return approvalDocOdPageDTO;
    }

    /**
     * 创建ApprovalResultRequestDTO
     * @param approvalDocCode
     * @return
     */
    private ApprovalResultRequestDTO getApprovalResultRequestDTO(String approvalDocCode) {
        ApprovalResultRequestDTO approvalResultRequestDTO = new ApprovalResultRequestDTO();
        approvalResultRequestDTO.setApprovalDocCode(approvalDocCode);
        approvalResultRequestDTO.setApprovalRemark("1");
        approvalResultRequestDTO.setApprovalResult(true);
        return approvalResultRequestDTO;
    }

    /**
     * 组装ApprovalResult接口入参
     * @param COOKIE
     * @param approvalResultRequestDTO
     * @return
     */
    private HttpEntity<String> getApprovalResultHttpEntity(String COOKIE, ApprovalResultRequestDTO approvalResultRequestDTO) {
        String approvalCotent = JSON.toJSONString(approvalResultRequestDTO);
        HttpHeaders approvalHttpHeaders = new HttpHeaders();
        approvalHttpHeaders.setContentType(MediaType.parseMediaType("application/json"));
        approvalHttpHeaders.add("Accept", MediaType.ALL_VALUE);
        approvalHttpHeaders.add("Cookie", COOKIE);
        return new HttpEntity<>(approvalCotent, approvalHttpHeaders);
    }

    /**
     * 组装CoomitApproval接口入参
     * @param COOKIE
     * @param approvalDocOdPageDTO
     * @return
     */
    private HttpEntity<String> getCommitApprovalHttpEntity(String COOKIE, ApprovalDocOdPageDTO approvalDocOdPageDTO) {
        String pageContent = JSON.toJSONString(approvalDocOdPageDTO);
        HttpHeaders pageHttpHeaders = new HttpHeaders();
        pageHttpHeaders.setContentType(MediaType.parseMediaType("application/json"));
        pageHttpHeaders.add("Accept", MediaType.ALL_VALUE);
        pageHttpHeaders.add("Cookie", COOKIE);
        return new HttpEntity<>(pageContent, pageHttpHeaders);
    }

    /**
     * 获取合约号版本号
     * @param agreement
     * @return
     */
    private AgreementQueryRequestDTO getAgreementQueryRequestDTO(AgreementSyncExcelDTO agreement) {
        AgreementQueryRequestDTO agreementQueryDTO = new AgreementQueryRequestDTO();
        agreementQueryDTO.setAgreementNo(agreement.getAgreementNo());
        agreementQueryDTO.setAgreementMajorVersionNo(getMajorVersion(agreement));
        agreementQueryDTO.setAgreementMinorVersionNo("0");
        return agreementQueryDTO;
    }

    private List<AgreementSyncExcelDTO> getAgreementSyncExcelDTOS() {
        return ExcelUtils.readExcelWithCheck(
                AgreementSyncExcelDTO.class,
                new File(AGREEMENT_SYNC_PATH_NAME),
                (t, value) -> ((AgreementSyncExcelDTO) t).setResult(value),
                (t, value) -> ((AgreementSyncExcelDTO) t).setFailReason(value)
        );
    }

    private void setStrategyDTO(String ECO_URL, int count, AgreementSyncExcelDTO agreement, ApprovalDocOdPageDTO approvalDocOdPageDTO) {
        ApprovalDocStrategyDTO approvalDocStrategyDTO = new ApprovalDocStrategyDTO();
        // 根据roleId查询代理挂靠
        AffiliatedQueryRequestDTO affiliatedQueryRequestDTO = new AffiliatedQueryRequestDTO();
        affiliatedQueryRequestDTO.setRoleId(agreement.getRoleId());
        ResponseEntity<AffiliatedQueryResposeDTO> affiliatedEntity = restTemplate.postForEntity(ECO_URL + AFFILIATED_QUERY, affiliatedQueryRequestDTO, AffiliatedQueryResposeDTO.class);
        if (CollectionUtils.isNotEmpty(affiliatedEntity.getBody().getData())) {
            List<AffiliatedDTO> affiliatedDTOS = affiliatedEntity.getBody().getData();
            List<ApprovalDocAgencyDTO> agencyDTOS = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(affiliatedDTOS)) {
                List<AffiliatedDTO> affiliatedDTOList = affiliatedDTOS.stream().filter(affiliatedDTO -> affiliatedDTO.getRoleId().equals(affiliatedDTO.getRoleIdDepend())).collect(Collectors.toList());
                affiliatedDTOList.forEach(affiliatedDTO -> {
                    ApprovalDocAgencyDTO agencyDTO = new ApprovalDocAgencyDTO();
                    agencyDTO.setAgencyName(affiliatedDTO.getPartnerNameDepend());
                    agencyDTO.setAgencyPartnerAgrNo(affiliatedDTO.getAgreementNoDepend());
                    agencyDTO.setDependDateStart(affiliatedDTO.getDependDateStart());
                    agencyDTO.setDependDateEnd(affiliatedDTO.getDependDateEnd());
                    agencyDTO.setInsuranceProductCode(affiliatedDTO.getInsuranceProductCode());
                    agencyDTO.setInsuranceProductName(affiliatedDTO.getInsuranceProductName());
                    agencyDTO.setIsDefaultStr(false);
                    agencyDTOS.add(agencyDTO);
                });
            }
            if (CollectionUtils.isNotEmpty(agencyDTOS)) {
                approvalDocStrategyDTO.setPartnerAgency(agencyDTOS);
            } else {
                log.info("第{}条，未查询到挂靠到自己的机构！", count);
            }
        } else {
            log.info("第{}条，未查询到代理挂靠！", count);
        }
        approvalDocOdPageDTO.setStrategyDTO(approvalDocStrategyDTO);
    }

    private void setAgrDTO(AgreementDTO agreementDTO, AgreementParticipantDTO participant, ApprovalDocOdPageDTO approvalDocOdPageDTO) {
        ApprovalDocAgrDTO approvalDocAgrDTO = new ApprovalDocAgrDTO();
        approvalDocAgrDTO.setAgrSignComCode(participant.getOrgCode());
        approvalDocAgrDTO.setAgrName(agreementDTO.getAgreementName());
        approvalDocAgrDTO.setDateStart(Date.from(LocalDateTime.of(LocalDate.from(agreementDTO.getAgreementElement().getPeriod().getStartDt()), LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant()));
        approvalDocAgrDTO.setDateEnd(Date.from(LocalDateTime.of(LocalDate.from(agreementDTO.getAgreementElement().getPeriod().getEndDt()), LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant()));
        approvalDocAgrDTO.setAgrSignDate(Date.from(LocalDateTime.of(LocalDate.from(agreementDTO.getAgreementElement().getPeriod().getSignDt()), LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant()));
        approvalDocAgrDTO.setAgrIsHead(agreementDTO.getIsGeneralAgreement());
        approvalDocAgrDTO.setAuthorizeRanges(getAgreementAuthorizeRanges(agreementDTO));
        approvalDocOdPageDTO.setAgrDTO(approvalDocAgrDTO);
    }

    private void setBuDTO(AgreementParticipantDTO participant, ApprovalDocOdPageDTO approvalDocOdPageDTO) {
        ApprovalDocBuDTO approvalDocBuDTO = new ApprovalDocBuDTO();
        approvalDocBuDTO.setRoleId(participant.getRoleId());
        approvalDocBuDTO.setPartnerName(participant.getPartnerName());
        approvalDocBuDTO.setIsRepair(false);
        approvalDocOdPageDTO.setBuDTO(approvalDocBuDTO);
    }

    private ApprovalDocOdPageDTO getApprovalDocOdPageDTO(AgreementSyncExcelDTO agreement) {
        ApprovalDocOdPageDTO approvalDocOdPageDTO = new ApprovalDocOdPageDTO();
        approvalDocOdPageDTO.setApprovalDocType("agr_strategy_add");
        approvalDocOdPageDTO.setBusinessUnitId(agreement.getBusinessId());
        approvalDocOdPageDTO.setBuType(agreement.getBusinessTypeCode());
        approvalDocOdPageDTO.setApprovalSource(agreement.getBusinessTypeCode());
        return approvalDocOdPageDTO;
    }

    private String getMajorVersion(AgreementSyncExcelDTO agreement) {
        String version = agreement.getAgreementVersion();
        String majorVersion = version;
        if (version.contains(".")) {
            String[] split = version.split("\\.");
            version = split[0];
        }
        if (version.contains("V")) {
            majorVersion = version.replace("V", "");
        }
        return majorVersion;
    }

    /**
     * 获取甲乙方
     * @param agreementDTO
     * @return
     */
    private AgreementParticipantDTO getAgreementParticipant(AgreementDTO agreementDTO) {
        AgreementParticipantDTO dto = new AgreementParticipantDTO();
        List<ParticipantDTO> participants = agreementDTO.getParticipants();
        if (CollectionUtils.isEmpty(participants)) {
            return null;
        }
        participants.forEach(c -> {
            if ("3".equals(c.getParticipantTypeCd())) {
                // 甲方签署机构
                dto.setOrgCode(c.getOrgCode());
                dto.setOrgName(c.getParticipantName());
            } else if ("4".equals(c.getParticipantTypeCd())) {
                // 乙方签署机构
                dto.setRoleId(c.getOrgCode());
                dto.setPartnerName(c.getParticipantName());
            }
        });
        return dto;
    }

    private List<ApprovalDocOrgDTO> getAgreementAuthorizeRanges(AgreementDTO agreementDTO) {
        List<ApprovalDocOrgDTO> approvalDocOrgDTOS = new ArrayList<>();
        List<DepartmentDTO> authorizationScope = agreementDTO.getAuthorizationScope();
        if (CollectionUtils.isNotEmpty(authorizationScope)) {
            authorizationScope.forEach(departmentDTO -> {
                ApprovalDocOrgDTO dto = new ApprovalDocOrgDTO();
                dto.setOrgCode(departmentDTO.getOrgCode());
                dto.setOrgName(departmentDTO.getOrgName());
                approvalDocOrgDTOS.add(dto);
            });
        }
        if (CollectionUtils.isNotEmpty(approvalDocOrgDTOS)) {
            return approvalDocOrgDTOS;
        }
        return null;
    }

}
