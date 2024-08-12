package com.wly.user.utils;

public class PasswordValidator {

    public static boolean validatePassword(String password) {
        // 最小长度为8个字符
        if (password.length() < 8) {
            return false;
        }

        // 至少包含一个小写字母
        if (!password.matches(".*[a-z]+.*")) {
            return false;
        }

        // 至少包含一个大写字母
        if (!password.matches(".*[A-Z]+.*")) {
            return false;
        }

        // 至少包含一个数字
        if (!password.matches(".*\\d+.*")) {
            return false;
        }

        // 至少包含一个特殊字符
        if (!password.matches(".*[!@#$%^&*()_+\\-=<>?|:,./]+.*")) {
            return false;
        }

        // 所有检查通过，密码符合要求
        return true;
    }

    public static String validateMsg(String password) {
        String msg = "";
        // 最小长度为8个字符
        if (password.length() < 8) {
            return msg+="密码最小长度为8个字符";
        }

        //
        if (!password.matches(".*[a-z]+.*")) {
            return msg+="至少包含一个小写字母!";
        }

        //
        if (!password.matches(".*[A-Z]+.*")) {
            return msg+"至少包含一个大写字母";
        }

        //
        if (!password.matches(".*\\d+.*")) {
            return msg+"至少包含一个数字";
        }

        //
        if (!password.matches(".*[!@#$%^&*()_+\\-=<>?|:,./]+.*")) {
            return msg+"至少包含一个特殊字符";
        }

        // 所有检查通过，密码符合要求
        return null;
    }
}
