package com.android.mytest;

interface ITestService {
    String getName(int num);
    String getPid(boolean enable);
    String testNoPara();
    void testNoRet(String para);
    String getAppInfo();
    String getAppIcon(String pkgName);
}