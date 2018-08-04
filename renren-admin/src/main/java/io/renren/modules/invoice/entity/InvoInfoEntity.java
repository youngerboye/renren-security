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
@TableName("invo_info")
public class InvoInfoEntity implements Serializable {
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
	 * 发票代码
	 */
	private String invoiceCode;
	/**
	 * 发票号码
	 */
	private String invoiceNumber;
	/**
	 * 开票时间
	 */
	private String billTime;
	/**
	 * 校验码
	 */
	private String checkCode;
	/**
	 * 开具金额
	 */
	private String invoiceAmount;
	/**
	 * 是否校验通过
	 */
	private String isChecked;
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
	 * 设置：发票代码
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * 获取：发票代码
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}
	/**
	 * 设置：发票号码
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	/**
	 * 获取：发票号码
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	/**
	 * 设置：开票时间
	 */
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	/**
	 * 获取：开票时间
	 */
	public String getBillTime() {
		return billTime;
	}
	/**
	 * 设置：校验码
	 */
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	/**
	 * 获取：校验码
	 */
	public String getCheckCode() {
		return checkCode;
	}
	/**
	 * 设置：开具金额
	 */
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	/**
	 * 获取：开具金额
	 */
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	/**
	 * 设置：是否校验通过
	 */
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	/**
	 * 获取：是否校验通过
	 */
	public String getIsChecked() {
		return isChecked;
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
