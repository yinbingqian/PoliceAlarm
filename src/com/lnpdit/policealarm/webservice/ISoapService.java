package com.lnpdit.policealarm.webservice;

/**
 * webService请求接口
 * @author huanyu 类名称：ISoapService 创建时间:2014-11-4 下午7:08:50
 */
public interface ISoapService extends IASoapService{
	/**
	 * 用户登录--警员编号|密码
	 * 
	 * @param property_va
	 */
	void CheckPoUserLogin(Object[] property_va);
	
	/**
     * 手机警务取得已处理数据-登陆验证--页码|页数
     * 
     * @param property_va
     */
    void GetDateList(Object[] property_va, boolean isPage);
    
    /**
     * 手机警务取得未处理数据-登陆验证--页码|页数
     * 
     * @param property_va
     */
    void GetDateListUn(Object[] property_va, boolean isPage);
    
    /**
     * 手机警务取得各前10项数据-登陆验证
     * 
     * @param property_va
     */
    void GetDateListDefault(Object[] property_va);
    
    /**
     * 手机警务取得数据-详细信息
     * 
     * @param property_va
     */
    void GetDateDetail(Object[] property_va);
    
    /**
     * 手机警务状态由未处理变已处理
     * 
     * @param property_va
     */
    void ChangeDataStatus(Object[] property_va);
    
    /**
     * 手机警务状态由未处理变无效报警
     * 
     * @param property_va
     */
    void ChangeDataStatusInvalid(Object[] property_va);
	
}
