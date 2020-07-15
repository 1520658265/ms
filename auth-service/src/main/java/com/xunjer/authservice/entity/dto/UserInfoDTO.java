package com.xunjer.authservice.entity.dto;

import com.xunjer.authservice.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.util.List;

/**
 * @author yuansheng
 * @Title: UserInfoDTO
 * @Description:
 * @date 2020/7/1518:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO extends UserInfo {

    private String token;

    private List<String> role;
}
