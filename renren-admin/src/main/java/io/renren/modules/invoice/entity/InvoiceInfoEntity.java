package io.renren.modules.invoice.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email kjustsun@gmail.com
 * @date 2018-07-31 17:01:04
 */
@TableName("invoice_info")
public class InvoiceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 发票代码(长度10位或者12位)
	 */
	private String invoiceCode;
	/**
	 * 发票号码(长度8位)
	 */
	private String invoiceNumber;
	/**
	 * 开票时间(时间格式必须为：2017-05-11)
	 */
	private String billTime;
	/**
	 * 校验码(检验码必须是后六位)
	 */
	private String checkCode;
	/**
	 * 开具金额、不含税价（单位为分）
	 */
	private BigDecimal invoiceAmount;
	/**
	 * 授权码
	 */
	private String token;
	/**
	 * 状态(0:删除 1:正常)
	 */
	private String state;
	/**
	 * 创建人
	 */
	private String crtUsr;
	/**
	 * 创建时间(时间格式必须为：2017-05-11)
	 */
	private String crtDt;
	/**
	 * 最后修改人
	 */
	private String lastChgUsr;
	/**
	 * 最后修改时间(时间格式必须为：2017-05-11)
	 */
	private String lastChgDt;

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
	 * 设置：发票代码(长度10位或者12位)
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * 获取：发票代码(长度10位或者12位)
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}
	/**
	 * 设置：发票号码(长度8位)
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	/**
	 * 获取：发票号码(长度8位)
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	/**
	 * 设置：开票时间(时间格式必须为：2017-05-11)
	 */
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	/**
	 * 获取：开票时间(时间格式必须为：2017-05-11)
	 */
	public String getBillTime() {
		return billTime;
	}
	/**
	 * 设置：校验码(检验码必须是后六位)
	 */
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	/**
	 * 获取：校验码(检验码必须是后六位)
	 */
	public String getCheckCode() {
		return checkCode;
	}
	/**
	 * 设置：开具金额、不含税价（单位为分）
	 */
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	/**
	 * 获取：开具金额、不含税价（单位为分）
	 */
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
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
	 * 设置：状态(0:删除 1:正常)
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态(0:删除 1:正常)
	 */
	public String getState() {
		return state;
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
	 * 设置：创建时间(时间格式必须为：2017-05-11)
	 */
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	/**
	 * 获取：创建时间(时间格式必须为：2017-05-11)
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
	 * 设置：最后修改时间(时间格式必须为：2017-05-11)
	 */
	public void setLastChgDt(String lastChgDt) {
		this.lastChgDt = lastChgDt;
	}
	/**
	 * 获取：最后修改时间(时间格式必须为：2017-05-11)
	 */
	public String getLastChgDt() {
		return lastChgDt;
	}
}
