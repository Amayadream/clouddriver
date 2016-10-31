package com.amayadream.clouddriver.utils;

/**
 * 定义常量
 * @author :  Amayadream
 * @date :  2016.10.18 09:25
 */
public class Constants {

    /** SESSION 中的用户名 */
    public static final String SESSION_USERID = "userId";
    /** SESSION 中的用户信息 */
    public static final String SESSION_USER = "user";

    /** 账号状态: 正常 */
    public static final int ACCOUNT_ACTIVE = 1;
    /** 账号状态: 待验证邮箱 */
    public static final int ACCOUNT_WAITFORVALID = 0;
    /** 账号状态: 禁用 */
    public static final int ACCOUNT_DISABLED = -1;

    /** 文件类型 */
    public static final String DRIVER_FILE = "file";
    /** 文件夹类型 */
    public static final String DRIVER_FOLDER = "folder";


    public static final String EXCEPTION_MSG_AUTH = "未登陆或者登陆已过期!";
    public static final String EXCEPTION_MSG_DOWNLOAD = "下载中出现错误!";
    public static final String EXCEPTION_MSG_FILECOMMON_NOT_FOUND = "指定文件未找到!";
    public static final String EXCEPTION_MSG_FILELIBRARY_NOT_FOUND = "指定文件库未找到";
    public static final String EXCEPTION_MSG_FLODER_NOT_FOUND = "文件夹未找到!";
    public static final String EXCEPTION_MSG_USER = "用户未找到!";
    public static final String EXCEPTION_MSG_ZIPPACKAGE= "打包过程中出现错误!";

}
