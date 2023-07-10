package leetcode;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pers.qlc.leetcode.ApplicationStartUp;
import pers.qlc.leetcode.constant.HttpConstant;
import pers.qlc.leetcode.dto.AgreementDTO;
import pers.qlc.leetcode.dto.request.PartnerProtocolRequestDTO;
import pers.qlc.leetcode.dto.request.ProtocolRequestDataDTO;
import pers.qlc.leetcode.dto.request.SignatureRequestDTO;
import pers.qlc.leetcode.dto.response.PartnerAgrSyncResponseDTO;
import pers.qlc.leetcode.dto.response.SignatureResponseDTO;
import pers.qlc.leetcode.enums.PartnerChannelMappingEnum;
import pers.qlc.leetcode.model.DeskModel;
import pers.qlc.leetcode.service.IDeskService;
import pers.qlc.leetcode.util.ExcelUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<AgreementDTO> agreementList = ExcelUtils.readExcelWithCheck(
                AgreementDTO.class,
                new File(PATH_NAME),
                (t, value) -> ((AgreementDTO) t).setResult(value),
                (t, value) -> ((AgreementDTO) t).setFailReason(value)
        );

        // 计数
        int count = 1;

        // 遍历Excel数据
        for (AgreementDTO agreement : agreementList) {
            // 获取签名
            SignatureRequestDTO signatureRequestDTO = new SignatureRequestDTO(100);
            ResponseEntity<SignatureResponseDTO> response = restTemplate.postForEntity(PROD_SIGNATURE_URL, signatureRequestDTO, SignatureResponseDTO.class);
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
                ResponseEntity<PartnerAgrSyncResponseDTO> postForEntity = restTemplate.postForEntity(PROD_PROTOCOL_URL, protocolRequestDTO, PartnerAgrSyncResponseDTO.class);
                log.info("第{}条, response:{}", count, JSON.toJSONString(postForEntity.getBody()));
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

}
