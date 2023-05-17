package pers.qlc.leetcode.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author :QLC
 * @Date :2023/5/16 17:30
 * @Description :
 */
@Data
@TableName("r_desk")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DeskModel extends Model {

    /**
     * 餐桌id
     */
    @TableId
    private String deskId;

    /**
     * 餐桌号
     */
    private String deskCode;

    /**
     * 餐桌座位数
     */
    private Integer peopleCount;

    /**
     * 空闲状态(0空闲，1有人，2待清理)
     */
    private Integer idleStatus;

    /**
     * 修改时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 删除标志
     */
    private Integer del;
}
