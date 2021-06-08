package com.wujiuye.jsonparser.core;

import com.wujiuye.jsonparser.core.util.XssFilterUtils;

public class XssJsonTest {

    public static void main(String[] args) {
        String html = "<p></p><p><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">小号sssssssssssssssssssss</font></font></p>";
        String htmlSplit = XssFilterUtils.xssFilter(html);
        System.out.println(htmlSplit);
        System.out.println(XssFilterUtils.xssFilter("http://asdsad.com/sasada?xxxx<xxxx&sadasd=asas<xml>"));
    }


}
