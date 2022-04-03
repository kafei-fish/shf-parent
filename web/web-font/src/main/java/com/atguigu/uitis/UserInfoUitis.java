package com.atguigu.uitis;

import com.atguigu.entity.UserInfo;
import com.atguigu.service.UserInfoService;
import com.atguigu.uitl.MD5;
import com.atguigu.vo.LoginVo;
import com.atguigu.vo.RegisterVo;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户工具类
 */
public class UserInfoUitis {
    /**
     * 判断参数是否有效
     * @param registerVo registerVo
     * @return boolean
     */
    public static boolean isEmpty(RegisterVo registerVo){
        //将参数获取出来
        String code = registerVo.getCode();
        String nickName = registerVo.getNickName();
        String password = registerVo.getPassword();
        String phone = registerVo.getPhone();

        return StringUtils.isEmpty(code)
                || StringUtils.isEmpty(nickName)||
                StringUtils.isEmpty(password) || StringUtils.isEmpty(phone);
    }
    public static boolean isEmpty(LoginVo loginVo){
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        return StringUtils.isEmpty(password) || StringUtils.isEmpty(phone);
    }

    /**
     * 验证码判断是否存在
     * @param code 验证吗
     * @param request 请求
     * @return boolean
     */
    public static boolean codeIsEmpty(String code, HttpServletRequest request){
        String codeNum = (String) request.getSession().getAttribute("CODE");

        return StringUtils.isEmpty(codeNum) || code.equals(codeNum);
    }

    /**
     * 校验密码
     * @param userInfoService userInfoService
     * @param phone 手机号
     * @return boolean
     */
    public static boolean isPhoneExist(UserInfoService userInfoService, String  phone){
        Integer samePhone = userInfoService.findSamePhone(phone);
        //>0 为ture
        return samePhone > 0;
    }

    /**
     * 校验密码
     * @param userInfoService  userInfoService
     * @param loginVo loginVo
     * @return boolean
     */
    public static UserInfo checkPassword(UserInfoService userInfoService,LoginVo loginVo){
        String password = MD5.encrypt(loginVo.getPassword());
        UserInfo login = userInfoService.login(loginVo.getPhone(), password);
        return login;
    }
}
