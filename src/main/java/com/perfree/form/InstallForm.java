package com.perfree.form;

import cn.hutool.core.util.IdUtil;
import com.perfree.model.Peers;
import com.perfree.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @description 安装时的参数
 * @author Perfree
 * @date 2021/3/23 11:50
 */
public class InstallForm implements Serializable {
    private static final long serialVersionUID = -6474666305055871893L;
    @NotBlank(message = "集群名称不能为空且在50字以内")
    @Size(max = 50, message = "集群名称不能为空且在50字以内")
    private String name;
    @Size(max =50, message = "组名称应在50字以内")
    private String groupName;
    @NotBlank(message = "集群服务地址不能为空且在100字以内")
    @Size(max = 100, message = "集群服务地址不能为空且在100字以内")
    private String serverAddress;

    @Size(max = 100, message = "访问域名应在50字以内")
    private String showAddress;

    @NotBlank(message = "账户不能为空且在30字以内")
    @Size(max = 30, message = "账户不能为空且在30字以内")
    private String account;
    @NotBlank(message = "密码不能为空且在30字以内")
    @Size(max = 30, message = "密码不能为空且在30字以内")
    private String password;
    @NotBlank(message = "用户名不能为空且在30字以内")
    @Size(max = 30, message = "用户名不能为空且在30字以内")
    private String userName;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "请检查邮箱格式是否正确")
    private String email;

    public Peers getPeers() {
        Peers peers = new Peers();
        peers.setGroupName(this.groupName);
        peers.setName(this.name);
        peers.setServerAddress(this.serverAddress);
        peers.setShowAddress(this.showAddress);
        peers.setCreateTime(new Date());
        return peers;
    }

    public User getUser() {
        User user = new User();
        String uuid = IdUtil.simpleUUID();
        Md5Hash md5Hash = new Md5Hash(this.password, uuid);
        user.setPassword(md5Hash.toString());
        user.setCredentialsSalt(uuid);
        user.setAccount(this.account);
        user.setEmail(this.email);
        user.setName(this.userName);
        user.setCreateTime(new Date());
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getShowAddress() {
        return showAddress;
    }

    public void setShowAddress(String showAddress) {
        this.showAddress = showAddress;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
