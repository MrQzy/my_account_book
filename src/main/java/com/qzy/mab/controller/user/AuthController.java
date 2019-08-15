package com.qzy.mab.controller.user;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qzy.mab.config.AuthenticFactory;
import com.qzy.mab.mapper.UserMapper;
import com.qzy.mab.service.UserService;
import com.qzy.mab.support.RequestToken;
import com.qzy.mab.support.WebContext;
import com.qzy.mab.util.CookiesUtil;
import com.qzy.mab.util.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qzy.mab.exception.MabRemoteBusinessException;
import com.qzy.mab.support.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api("用户模块")
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", dataType = "String", required = true, paramType = "query"),
        @ApiImplicitParam(name = "passWord", dataType = "String", required = true, paramType = "query"),})
    @PostMapping(value = "/login")
    public ResponseEntity login(HttpServletRequest request, @RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        Assert.isTrue(!StringUtils.isBlank(userName), "登录失败：用户名不能为空!");
        Assert.isTrue(!StringUtils.isBlank(passWord), "登录失败: 密码不能为空!");
        Subject subject = SecurityUtils.getSubject();
        RequestToken token = new RequestToken(userName, passWord);
        if (!subject.isAuthenticated()) {
            subject.login(token);
        }
        Session session = subject.getSession();
        String domain = "localhost";
        redisUtil.set(session.getId().toString(), userMapper.getByUsernameAndPassword(userName, DigestUtils.md5Hex(passWord)), 2 * 3600);
        writeCookie4Session(domain, session.getId().toString(), WebContext.currentResponse());
        return new ResponseEntity();
    }

    @ApiOperation("退出登录")
    @GetMapping(value = "/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResponseEntity();
    }

//    @ApiOperation("修改密码")
//    @ApiImplicitParams({@ApiImplicitParam(name = "form", dataType = "ForgetPwdForm", required = true, paramType = "body")})
//    @PatchMapping(value = "/password")
//    public ResponseEntity forgetPassword(@RequestBody ForgetPwdForm form) throws UserCenterRemoteBusinessException {
//        Preconditions.checkNotNull(form, "修改密码参数对象不能为空");
//        Preconditions.checkNotNull(form.getOldPwd(), "旧密码不能为空");
//        Preconditions.checkNotNull(form.getNewPwd(), "新密码不能为空");
//        Preconditions.checkNotNull(form.getConfirmNewPwd(), "确认新密码不能为空");
//
//        Long loginId = (Long)AuthenticContextUtil.getInstance().getCurrentPripacilId();
//        UserVo userVo = userService.get(loginId);
//        if (null == userVo) {
//            throw new UserCenterRemoteBusinessException("账户信息异常");
//        }
//        if (userVo.getAvailable().equals(0)) {
//            throw new UserCenterRemoteBusinessException("账户不可用");
//        }
//        checkPassword(form.getNewPwd(), form.getConfirmNewPwd());
//        userVo.setPassword(form.getNewPwd());
//        UpdatePasswordVo updatePasswordVo = new UpdatePasswordVo();
//        updatePasswordVo.setUserId(loginId);
//        updatePasswordVo.setOldPwd(form.getOldPwd());
//        updatePasswordVo.setNewPwd(form.getNewPwd());
//        userService.updateUserPassword(updatePasswordVo);
//        return new ResponseEntity(userVo, CustomHttpStatus.OK, "修改成功");
//    }


    /**
     * 检查密码
     *
     * @param password
     * @param password2
     * @throws MabRemoteBusinessException
     */
    private void checkPassword(String password, String password2) throws MabRemoteBusinessException {
        if (!password.equals(password2)) {
            throw new MabRemoteBusinessException(String.format("失败: 两次密码不一致!"));
        }
        if (password.length() < 8 || password.length() > 20) {
            throw new MabRemoteBusinessException(String.format("失败: 密码长度应在8-20位!"));
        }
        if (!password.matches("(?i)^(?!([a-z]*|\\d*)$)[a-z\\d]+$")) {
            throw new MabRemoteBusinessException(String.format("失败: 密码应由数字+字母组合!"));
        }
    }

    /**
     * 将标识写入cookie
     *
     * @param sessionKey
     */
    public void writeCookie4Session(String domain , String sessionKey, HttpServletResponse response) {
        String cookieName = AuthenticFactory.getInstance().getAuthenticationConfig().getCookieName();
        String salt = AuthenticFactory.getInstance().getAuthenticationConfig().getSalt();
        String hashSessoin = sessionKey;
        Cookie cookie = new Cookie(cookieName, hashSessoin);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*2);//单位为秒，设置2小时失效
        CookiesUtil.writeCookie(response, cookie);
    }

}
