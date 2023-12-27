package leetcode;

import com.alibaba.fastjson.JSON;
import com.aliyun.fsi.insurance.agreement.facade.dto.AgreementDTO;
import com.aliyun.fsi.insurance.agreement.facade.dto.DepartmentDTO;
import com.aliyun.fsi.insurance.agreement.facade.dto.ParticipantDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pers.qlc.leetcode.ApplicationStartUp;
import pers.qlc.leetcode.dto.AgreementSyncExcelDTO;
import pers.qlc.leetcode.dto.param.*;
import pers.qlc.leetcode.dto.request.AffiliatedQueryRequestDTO;
import pers.qlc.leetcode.dto.request.AgreementQueryRequestDTO;
import pers.qlc.leetcode.dto.request.ApprovalResultRequestDTO;
import pers.qlc.leetcode.dto.request.ProtocolRequestDataDTO;
import pers.qlc.leetcode.dto.response.*;
import pers.qlc.leetcode.enums.PartnerChannelMappingEnum;
import pers.qlc.leetcode.enums.PartnerTypeEnum;
import pers.qlc.leetcode.syncAgr.*;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qlc.leetcode.constant.UrlConstant.*;

/**
 * @Author QLC
 * @Date 2022/12/27 11:01
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStartUp.class)
class ApplicationStartUpTest {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 环境(填UAT或PROD)
     */
    private static final String ENV = "PT";
    /**
     * 文件地址
     */
    private static final String SYNC_AGREEMENT_PATH_NAME = "E:\\xxx.xlsx";

    /**
     * 批量同步协议到议价平台(使用前更新环境和文件地址)
     */
    @Test
    public void syncAgreement() {

        String SIGNATURE_URL = null;
        String PROTOCOL_URL = null;
        switch (ENV) {
            case "PROD":
                SIGNATURE_URL = "http://10.206.192.117:80/irdp/sign/getSignature";
                PROTOCOL_URL = "http://10.206.192.117:80/irdp/nwAgentAssoProtocol/insertProtocolByPartnerSystem";
                break;
            case "UAT":
                SIGNATURE_URL = "http://10.207.132.176:8000/irdp/sign/getSignature";
                PROTOCOL_URL = "http://10.207.132.176:8000/irdp/nwAgentAssoProtocol/insertProtocolByPartnerSystem";
                break;
            case "PT":
                SIGNATURE_URL = "http://10.207.132.171:8000/irdp/sign/getSignature";
                PROTOCOL_URL = "http://10.207.132.171:8000/irdp/nwAgentAssoProtocol/insertProtocolByPartnerSystem";
                break;
        }

        // 解析Excel
        List<AgreementExcel> agreementList = ExcelUtils.readExcelWithCheck(
                AgreementExcel.class,
                new File(SYNC_AGREEMENT_PATH_NAME)
        );

        // 计数
        int count = 1;
        int size = agreementList.size();
        SignatureRequestDTO signatureRequestDTO = new SignatureRequestDTO(100);

        // 遍历Excel数据
        for (AgreementExcel agreement : agreementList) {

            // 获取签名
            ResponseEntity<SignatureResponseDTO> response = restTemplate.postForEntity(SIGNATURE_URL, signatureRequestDTO, SignatureResponseDTO.class);

            if (response.getBody() == null) {
                log.info("获取第{}/{}条签名失败, 协议号为{}！", count, size, agreement.getAgreementNo());
                continue;
            }

            // 填充签名数据
            PartnerProtocolRequestDTO protocolRequestDTO = getPartnerProtocolRequestDTO(response);

            // 填充合约数据
            setAgreement(agreement, protocolRequestDTO);

            try {
                // 同步到议价
                log.info("同步第{}/{}条合作伙伴协议信息到议价平台" + ENV + "环境, request: {}", count, size, JSON.toJSONString(protocolRequestDTO));
                ResponseEntity<PartnerAgrSyncResponseDTO> postForEntity = restTemplate.postForEntity(PROTOCOL_URL, protocolRequestDTO, PartnerAgrSyncResponseDTO.class);
                log.info("第{}/{}条{}, response:{}", count, size, protocolRequestDTO.getData().getAgentProtocolCode(), JSON.toJSONString(postForEntity.getBody()));
            } catch (Exception e) {
                log.error("同步第{}/{}条合作伙伴协议信息到议价平台" + ENV + "环境失败", count, size);
            }

            count++;
        }
    }

    private final static String COOKIE_PT = "";
    private final static String COOKIE_PROD = "";
    private static final String AGREEMENT_SYNC_PATH_NAME = "E:\\xxx.xlsx";

    /**
     * 根据代理合约补充合作合约(使用之前确认环境，并更新COOKIE和文件地址)
     */
    @Test
    public void agreementSync() {
        String str = "xxx";

        String AGR_URL = null;
        String ECO_URL = null;
        String COOKIE = null;
        if ("prod".equals(str)) {
            AGR_URL = AGR_PROD_URL;
            ECO_URL = ECOLOGY_PROD_URL;
            COOKIE = COOKIE_PROD;
        } else if ("pt".equals(str)) {
            AGR_URL = AGR_PT_URL;
            ECO_URL = ECOLOGY_PT_URL;
            COOKIE = COOKIE_PT;
        }

        // 解析EXCEL
        List<AgreementSyncExcelDTO> agreementList = ExcelUtils.readExcelWithCheck(
                AgreementSyncExcelDTO.class,
                new File(AGREEMENT_SYNC_PATH_NAME)
        );

        int count = 1;
        int size = agreementList.size();

        // 遍历excel数据
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

            // 创建pageDTO
            ApprovalDocOdPageDTO approvalDocOdPageDTO = getApprovalDocOdPageDTO(ECO_URL, count, agreement, agreementDTO, participant);

            log.info("第{}/{}条，commitApproval入参：{}", count, size, JSON.toJSONString(approvalDocOdPageDTO));

            // 组装commitApproval接口入参
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

            // 创建approvalResultDTO
            ApprovalResultRequestDTO approvalResultRequestDTO = getApprovalResultRequestDTO(approvalDocCode);

            // 组装approvalResult接口入参
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

    private void setAgreement(AgreementExcel agreement, PartnerProtocolRequestDTO protocolRequestDTO) {
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
        data.setPartnerLv4TypeCode(partnerLv4TypeCode);
        data.setPartnerLv4TypeName(PartnerTypeEnum.getNameByCode(partnerLv4TypeCode));
        data.setPartnerSystemType(new ArrayList<>(Arrays.asList("A010102", "A010101")).contains(partnerLv4TypeCode) ? "1" : "0");
        protocolRequestDTO.setData(data);
    }

    private PartnerProtocolRequestDTO getPartnerProtocolRequestDTO(ResponseEntity<SignatureResponseDTO> response) {
        PartnerProtocolRequestDTO protocolRequestDTO = new PartnerProtocolRequestDTO();
        protocolRequestDTO.setHost(response.getBody().getData().getHost());
        protocolRequestDTO.setPolicy(response.getBody().getData().getPolicy());
        protocolRequestDTO.setAccessKey(response.getBody().getData().getAccessKey());
        protocolRequestDTO.setSignature(response.getBody().getData().getSignature());
        protocolRequestDTO.setExpiretime(response.getBody().getData().getExpiretime());
        return protocolRequestDTO;
    }

    /**
     * 创建pageDTO
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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

    @Test
    public void t0() {
        // 解析Excel
        List<AssessorDTO> assessorDTOS = ExcelUtils.readExcelWithCheck(
                AssessorDTO.class,
                new File("E:\\公估人员导入模板.xlsx")
        );
        assessorDTOS = assessorDTOS.stream().filter(assessorDTO -> StringUtils.isNotBlank(assessorDTO.getPhone())).collect(Collectors.toList());
        System.out.println(assessorDTOS);
    }

    @Test
    public void t1() {
        // 解析EXCEL并根据注解完成必填校验及正则规则校验
        List<AssessorDTO> assessorDTOList = ExcelUtils.readExcelWithCheck(AssessorDTO.class, new File("E:\\公估人员导入模板.xlsx"));
        // 去掉第一行(填写要求)
        assessorDTOList.remove(0);
        // 校验
        for (AssessorDTO assessorDTO : assessorDTOList) {
            if (StringUtils.isBlank(assessorDTO.getName())
                    && StringUtils.isBlank(assessorDTO.getWorkAreas())
                    && StringUtils.isBlank(assessorDTO.getIsConcentrateCic())
                    && StringUtils.isBlank(assessorDTO.getQualifyNo())
                    && StringUtils.isBlank(assessorDTO.getIdentityNo())
                    && StringUtils.isBlank(assessorDTO.getPhone())
                    && StringUtils.isBlank(assessorDTO.getMail())
                    && StringUtils.isBlank(assessorDTO.getOrgCode())
                    && StringUtils.isBlank(assessorDTO.getExternalHead())
                    && StringUtils.isBlank(assessorDTO.getResponsibleInsurance())) {
                continue;
            }
            if (StringUtils.isBlank(assessorDTO.getName())
                    || StringUtils.isBlank(assessorDTO.getWorkAreas())
                    || StringUtils.isBlank(assessorDTO.getIsConcentrateCic())
                    || StringUtils.isBlank(assessorDTO.getIdentityNo())
                    || StringUtils.isBlank(assessorDTO.getPhone())
                    || StringUtils.isBlank(assessorDTO.getOrgCode())
                    || StringUtils.isBlank(assessorDTO.getResponsibleInsurance())) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("缺少必填参数!");
                continue;
            }
            if (!assessorDTO.getWorkAreas().matches("^[0-9;；]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("作业行政区域格式不正确,请使用分号隔开!");
                continue;
            }
            if (!isCardID(assessorDTO.getIdentityNo())) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("身份证格式不正确!");
                continue;
            }
            if (!assessorDTO.getPhone().matches("^1\\d{10}$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("手机号格式不正确!");
                continue;
            }
            if (StringUtils.isNotBlank(assessorDTO.getMail()) && !assessorDTO.getMail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("邮箱格式不正确!");
                continue;
            }
            if (!assessorDTO.getOrgCode().matches("^[0-9]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("归属机构格式不正确!");
                continue;
            }
            if (StringUtils.isNotBlank(assessorDTO.getExternalHead()) && !assessorDTO.getExternalHead().matches("^[0-9]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("负责人格式不正确!");
                continue;
            }
            if (!assessorDTO.getResponsibleInsurance().matches("^[0-9;；]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("负责产品大类格式不正确,请使用分号隔开!");
                continue;
            }
            assessorDTO.setResult("成功");
        }
        System.out.println("ok");
    }

    public boolean isCardID(String sId) {
        String pattern = "^(\\d{15}$)|(\\d{17}(\\d|X|x)$)";
        if (!Pattern.matches(pattern, sId)) {
            return false;
        }
        // 身份证号码校验
        int sum = 0;
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        String codes = "10X98765432";
        for (int i = 0; i < sId.length() - 1; i++) {
            sum += Character.getNumericValue(sId.charAt(i)) * weights[i];
        }
        // 计算出来的最后一位身份证号码
        char last = codes.charAt(sum % 11);
        return sId.charAt(sId.length() - 1) == last;
    }

    @Test
    public void t2() {
        // 解析EXCEL并根据注解完成必填校验及正则规则校验
        List<AssessorDTO> assessorDTOList = ExcelUtils.readExcelWithCheck(AssessorDTO.class, new File("E:\\公估人员导入模板.xlsx"));
        // 去掉第一行(填写要求)
        assessorDTOList.remove(0);
        // 校验
        for (AssessorDTO assessorDTO : assessorDTOList) {
            if (StringUtils.isBlank(assessorDTO.getName())
                    && StringUtils.isBlank(assessorDTO.getWorkAreas())
                    && StringUtils.isBlank(assessorDTO.getIsConcentrateCic())
                    && StringUtils.isBlank(assessorDTO.getQualifyNo())
                    && StringUtils.isBlank(assessorDTO.getIdentityNo())
                    && StringUtils.isBlank(assessorDTO.getPhone())
                    && StringUtils.isBlank(assessorDTO.getMail())
                    && StringUtils.isBlank(assessorDTO.getOrgCode())
                    && StringUtils.isBlank(assessorDTO.getExternalHead())
                    && StringUtils.isBlank(assessorDTO.getResponsibleInsurance())) {
                continue;
            }
            if (StringUtils.isBlank(assessorDTO.getName())
                    || StringUtils.isBlank(assessorDTO.getWorkAreas())
                    || StringUtils.isBlank(assessorDTO.getIsConcentrateCic())
                    || StringUtils.isBlank(assessorDTO.getIdentityNo())
                    || StringUtils.isBlank(assessorDTO.getPhone())
                    || StringUtils.isBlank(assessorDTO.getOrgCode())
                    || StringUtils.isBlank(assessorDTO.getResponsibleInsurance())) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("缺少必填参数!");
                continue;
            }
            if (!assessorDTO.getWorkAreas().matches("^[0-9;；]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("作业行政区域格式不正确,请使用分号隔开!");
                continue;
            }
            if (!isCardID(assessorDTO.getIdentityNo())) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("身份证格式不正确!");
                continue;
            }
            if (!assessorDTO.getPhone().matches("^1\\d{10}$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("手机号格式不正确!");
                continue;
            }
            if (StringUtils.isNotBlank(assessorDTO.getMail()) && !assessorDTO.getMail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("邮箱格式不正确!");
                continue;
            }
            if (!assessorDTO.getOrgCode().matches("^[0-9]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("归属机构格式不正确!");
                continue;
            }
            if (StringUtils.isNotBlank(assessorDTO.getExternalHead()) && !assessorDTO.getExternalHead().matches("^[0-9]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("负责人格式不正确!");
                continue;
            }
            if (!assessorDTO.getResponsibleInsurance().matches("^[0-9;；]+$")) {
                assessorDTO.setResult("失败");
                assessorDTO.setFailReason("负责产品大类格式不正确,请使用分号隔开!");
                continue;
            }
            assessorDTO.setResult("成功");
        }
        System.out.println("ok");
    }

    private List<AssessorImportResponseAppDTO> toAssessorImportResponseAppDTOList(List<AssessorDTO> assessorDTOS, AssessImportRequestAppDTO appDTO) {
        List<AssessorImportResponseAppDTO> appDTOS = new ArrayList<>();
        for (AssessorDTO assessorDTO : assessorDTOS) {
            if (StringUtils.isBlank(assessorDTO.getName())
                    || StringUtils.isBlank(assessorDTO.getWorkAreas())
                    || StringUtils.isBlank(assessorDTO.getIsConcentrateCic())
                    || StringUtils.isBlank(assessorDTO.getIdentityNo())
                    || StringUtils.isBlank(assessorDTO.getPhone())
                    || StringUtils.isBlank(assessorDTO.getOrgCode())
                    || StringUtils.isBlank(assessorDTO.getResponsibleInsurance())
                    // 员工重复
                    || (appDTO != null && CollectionUtils.isNotEmpty(appDTO.getAssessorRequestDTOList())
                    && org.apache.commons.collections.CollectionUtils.isNotEmpty(appDTO.getAssessorRequestDTOList().stream().map(AssessorRequestAppDTO::getPhone).collect(Collectors.toList()))
                    && appDTO.getAssessorRequestDTOList().stream().map(AssessorRequestAppDTO::getPhone).collect(Collectors.toList()).contains(assessorDTO.getPhone()))
                    // 作业行政区域校验
                    || !assessorDTO.getWorkAreas().matches("^[0-9;；]+$")
                    // 身份证号校验
                    || !isCardID(assessorDTO.getIdentityNo())
                    // 手机号校验
                    || !assessorDTO.getPhone().matches("^1\\d{10}$")
                    // 邮箱校验
                    || (StringUtils.isNotBlank(assessorDTO.getMail()) && !assessorDTO.getMail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
                    // 归属机构校验
                    || !assessorDTO.getOrgCode().matches("^[0-9]+$")
                    // 负责人校验
                    || (StringUtils.isNotBlank(assessorDTO.getExternalHead()) && !assessorDTO.getExternalHead().matches("^[0-9]+$"))
                    // 负责产品大类校验
                    || !assessorDTO.getResponsibleInsurance().matches("^[0-9;；]+$")) {
                continue;
            }
            AssessorImportResponseAppDTO dto = new AssessorImportResponseAppDTO();
            dto.setName(assessorDTO.getName());
            dto.setWorkAreas(toWorkAreas(assessorDTO.getWorkAreas()));
            if (assessorDTO.getIsConcentrateCic() != null) {
                dto.setIsConcentrateCic(assessorDTO.getIsConcentrateCic().equals("是"));
            }
            dto.setQualifyNo(assessorDTO.getQualifyNo());
            dto.setIdentityNo(assessorDTO.getIdentityNo());
            dto.setPhone(assessorDTO.getPhone());
            dto.setMail(assessorDTO.getMail());
            dto.setOrgCode(assessorDTO.getOrgCode());
            dto.setExternalHead(assessorDTO.getExternalHead());
            dto.setResponsibleInsurance(toResponsibleInsurance(assessorDTO.getResponsibleInsurance()));
            appDTOS.add(dto);
        }
        return appDTOS;
    }

    private List<InsuranceResponseAppDTO> toResponsibleInsurance(String responsibleInsurance) {
        if (!responsibleInsurance.matches(".*[;；].*")) {
            InsuranceResponseAppDTO insurance = new InsuranceResponseAppDTO();
            insurance.setInsuranceCode(responsibleInsurance);
            return new ArrayList<InsuranceResponseAppDTO>(){{
                add(insurance);
            }};
        }
        String[] insuranceCodeList = responsibleInsurance.split("[;；]");
        if (insuranceCodeList == null || insuranceCodeList.length == 0) {
            return null;
        }
        List<InsuranceResponseAppDTO> appDTOS = new ArrayList<>();
        for (String insuranceCode : insuranceCodeList) {
            if (StringUtils.isNotBlank(insuranceCode)) {
                InsuranceResponseAppDTO appDTO = new InsuranceResponseAppDTO();
                appDTO.setInsuranceCode(insuranceCode);
                appDTOS.add(appDTO);
            }
        }
        return appDTOS;
    }

    private List<AreaResponseAppDTO> toWorkAreas(String workAreas) {
        if (!workAreas.matches(".*[;；].*")) {
            AreaResponseAppDTO appDTO = new AreaResponseAppDTO();
            appDTO.setAreaCode(workAreas);
            return new ArrayList<AreaResponseAppDTO>(){{
                add(appDTO);
            }};
        }
        String[] areaCodeList = workAreas.split("[;；]");
        if (areaCodeList == null || areaCodeList.length == 0) {
            return null;
        }
        List<AreaResponseAppDTO> appDTOS = new ArrayList<>();
        for (String areaCode : areaCodeList) {
            if (StringUtils.isNotBlank(areaCode)) {
                AreaResponseAppDTO appDTO = new AreaResponseAppDTO();
                appDTO.setAreaCode(areaCode);
                appDTOS.add(appDTO);
            }
        }
        return appDTOS;
    }

    @Test
    public void t3() {
        // 解析EXCEL并根据注解完成必填校验及正则规则校验
        List<AssessorDTO> assessorDTOList = ExcelUtils.readExcelWithCheck(AssessorDTO.class, new File("E:\\公估人员导入模板.xlsx"));
        List<AssessorImportResponseAppDTO> appDTOS = toAssessorImportResponseAppDTOList(assessorDTOList, null);
        System.out.println(appDTOS);
    }
}
