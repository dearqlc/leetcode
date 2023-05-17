package pers.qlc.leetcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.qlc.leetcode.mapper.DeskMapper;
import pers.qlc.leetcode.model.DeskModel;
import pers.qlc.leetcode.service.IDeskService;

/**
 * @Author :QLC
 * @Date :2023/5/16 17:30
 * @Description :
 */
@Service("deskServiceImpl")
public class DeskServiceImpl extends ServiceImpl<DeskMapper, DeskModel> implements IDeskService {

}
