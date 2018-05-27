package com.sdut.trade.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sdut.trade.bean.UserInfoVO;
import com.sdut.trade.dao.UserInfoDao;
import com.sdut.trade.entity.UserInfo;
import com.sdut.trade.httpmodel.request.LoginRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：登陆业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
@Component
@Slf4j
public class UserServiceImp implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 登陆请求
     *
     * @param loginRequest
     *
     * @param request
     * @return
     */
    @Override
    public ResponseVO login(LoginRequest loginRequest, HttpServletRequest request) {

        ResponseVO responseVO = new ResponseVO();

        UserInfo userInfo = new UserInfo();
        userInfo.setName(loginRequest.getUsername());
        userInfo.setPassword(loginRequest.getPassword());

        UserInfo userInDB = userInfoDao.getByName(userInfo.getName());

        UserInfoVO userInfoVO = new UserInfoVO();

        if (userInDB == null || !comparePassword(userInfo, userInDB)) {
            responseVO.setResultMsg("账号或密码错误");
            responseVO.setResultCode(200);
        } else {

            String token = getToken(userInDB);
            request.getSession().setAttribute("token", token);

            userInfoVO.setUsername(userInDB.getName());
            userInfoVO.setPassword(userInDB.getPassword());
        }

        responseVO.setData(userInfoVO);
        return responseVO;
    }

    private String getToken(UserInfo userInDB) {

        String token = "";

        try {
            token = JWT.create()
                    .withAudience(userInDB.getId().toString())          // 将 user id 保存到 token 里面
                    .sign(Algorithm.HMAC256(userInDB.getPassword()));   // 以 password 作为 token 的密钥
        } catch (UnsupportedEncodingException ignore) {
        }

        return token;
    }

    /**
     * 比较密码的hash值是否相同
     * @param user
     * @param userInDataBase
     * @return
     */
    public boolean comparePassword(UserInfo user, UserInfo userInDataBase) {
        return userInDataBase.getPassword()
                .equals(passwordToHash(user.getPassword()));
    }


    /**
     * 获得密码的hash值
     * @param password
     * @return
     */
    private String passwordToHash(String password) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            digest.update(password.getBytes());

            byte[] src = digest.digest();

            StringBuilder stringBuilder = new StringBuilder();

            // 字节数组转16进制字符串
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException ignore) {
        }

        return null;
    }

}
