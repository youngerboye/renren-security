package io.renren.modules.invoice.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author sunzh
 * @email kjustsun@gmail.com
 * @date 2018-08-04 16:54:52
 */
@TableName("invo_check")
public class InvoCheckEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 发票序号
	 */
	private String invoiceSeq;
	/**
	 * 二维码字符串
	 */
	private String scanStr;
	/**
	 * 授权码
	 */
	private String token;
	/**
	 * 00：成功，99：失败
	 */
	private String rtnCode;
	/**
	 * 查询发票状态码，1000：查询到票的信息，2001：没有查询到票的信息
	 */
	private String resultCode;
	/**
	 * 失败状态码，201，210，220等
	 */
	private String invoicefalseCode;
	/**
	 * 结果提示信息
	 */
	private String resultMsg;
	/**
	 * 发票名称
	 */
	private String invoiceName;
	/**
	 * 数据查询结果
	 */
	private String invoiceResult;
	/**
	 * 查询是否免费，Y：是，N：否
	 */
	private String isFree;
	/**
	 * 创建人
	 */
	private String crtUsr;
	/**
	 * 创建日期
	 */
	private String crtDt;
	/**
	 * 最后修改人
	 */
	private String lastChgUsr;
	/**
	 * 最后修改时间
	 */
	private String lastChgDt;
	/**
	 * 备用字段1
	 */
	private String ext1;
	/**
	 * 备用字段2
	 */
	private String ext2;
	/**
	 * 备用字段3
	 */
	private String ext3;
	/**
	 * 备用字段4
	 */
	private String ext4;
	/**
	 * 备用字段5
	 */
	private String ext5;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：发票序号
	 */
	public void setInvoiceSeq(String invoiceSeq) {
		this.invoiceSeq = invoiceSeq;
	}
	/**
	 * 获取：发票序号
	 */
	public String getInvoiceSeq() {
		return invoiceSeq;
	}
	/**
	 * 设置：二维码字符串
	 */
	public void setScanStr(String scanStr) {
		this.scanStr = scanStr;
	}
	/**
	 * 获取：二维码字符串
	 */
	public String getScanStr() {
		return scanStr;
	}
	/**
	 * 设置：授权码
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：授权码
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：00：成功，99：失败
	 */
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	/**
	 * 获取：00：成功，99：失败
	 */
	public String getRtnCode() {
		return rtnCode;
	}
	/**
	 * 设置：查询发票状态码，1000：查询到票的信息，2001：没有查询到票的信息
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * 获取：查询发票状态码，1000：查询到票的信息，2001：没有查询到票的信息
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * 设置：失败状态码，201，210，220等
	 */
	public void setInvoicefalseCode(String invoicefalseCode) {
		this.invoicefalseCode = invoicefalseCode;
	}
	/**
	 * 获取：失败状态码，201，210，220等
	 */
	public String getInvoicefalseCode() {
		return invoicefalseCode;
	}
	/**
	 * 设置：结果提示信息
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	/**
	 * 获取：结果提示信息
	 */
	public String getResultMsg() {
		return resultMsg;
	}
	/**
	 * 设置：发票名称
	 */
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	/**
	 * 获取：发票名称
	 */
	public String getInvoiceName() {
		return invoiceName;
	}
	/**
	 * 设置：数据查询结果
	 */
	public void setInvoiceResult(String invoiceResult) {
		this.invoiceResult = invoiceResult;
	}
	/**
	 * 获取：数据查询结果
	 */
	public String getInvoiceResult() {
		return invoiceResult;
	}
	/**
	 * 设置：查询是否免费，Y：是，N：否
	 */
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	/**
	 * 获取：查询是否免费，Y：是，N：否
	 */
	public String getIsFree() {
		return isFree;
	}
	/**
	 * 设置：创建人
	 */
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	/**
	 * 获取：创建人
	 */
	public String getCrtUsr() {
		return crtUsr;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	/**
	 * 获取：创建日期
	 */
	public String getCrtDt() {
		return crtDt;
	}
	/**
	 * 设置：最后修改人
	 */
	public void setLastChgUsr(String lastChgUsr) {
		this.lastChgUsr = lastChgUsr;
	}
	/**
	 * 获取：最后修改人
	 */
	public String getLastChgUsr() {
		return lastChgUsr;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setLastChgDt(String lastChgDt) {
		this.lastChgDt = lastChgDt;
	}
	/**
	 * 获取：最后修改时间
	 */
	public String getLastChgDt() {
		return lastChgDt;
	}
	/**
	 * 设置：备用字段1
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	/**
	 * 获取：备用字段1
	 */
	public String getExt1() {
		return ext1;
	}
	/**
	 * 设置：备用字段2
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	/**
	 * 获取：备用字段2
	 */
	public String getExt2() {
		return ext2;
	}
	/**
	 * 设置：备用字段3
	 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	/**
	 * 获取：备用字段3
	 */
	public String getExt3() {
		return ext3;
	}
	/**
	 * 设置：备用字段4
	 */
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	/**
	 * 获取：备用字段4
	 */
	public String getExt4() {
		return ext4;
	}
	/**
	 * 设置：备用字段5
	 */
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	/**
	 * 获取：备用字段5
	 */
	public String getExt5() {
		return ext5;
	}
}
