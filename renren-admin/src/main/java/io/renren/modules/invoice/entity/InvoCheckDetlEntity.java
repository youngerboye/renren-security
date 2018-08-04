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
@TableName("invo_check_detl")
public class InvoCheckDetlEntity implements Serializable {
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
	private String invoiceDataCode;
	/**
	 * 发票号码
	 */
	private String invoiceNumber;
	/**
	 * 发票类型名称
	 */
	private String invoiceTypeName;
	/**
	 * 发票类型，01：增值税专票，03：机动车销售统一发票，04：增值税普通发票，10：电子发票，11：卷式普通发票，14:电子普通[通行费]发票，15：二手车统一发票
	 */
	private String invoiceTypeCode;
	/**
	 * 开票时间
	 */
	private String billingTime;
	/**
	 * 查询日期
	 */
	private String checkDate;
	/**
	 * 查验次数
	 */
	private String checkNum;
	/**
	 * 校验码
	 */
	private String checkCode;
	/**
	 * 机器码
	 */
	private String taxDiskCode;
	/**
	 * 购方名称
	 */
	private String purchaserName;
	/**
	 * 购方纳税人识别号
	 */
	private String taxpayerNumber;
	/**
	 * 购方银行账号
	 */
	private String taxpayerBankAccount;
	/**
	 * 购方地址，电话
	 */
	private String taxpayerAddressOrId;
	/**
	 * 销方名称
	 */
	private String salesName;
	/**
	 * 销方纳税人识别号
	 */
	private String salesTaxpayerNum;
	/**
	 * 销方银行，账号
	 */
	private String salesTaxpayerBankAccount;
	/**
	 * 销方地址，电话
	 */
	private String salesTaxpayerAddress;
	/**
	 * 价税合计
	 */
	private String totalTaxSum;
	/**
	 * 税额
	 */
	private String totalTaxNum;
	/**
	 * 不含税价（金额）
	 */
	private String totalAmount;
	/**
	 * 备注
	 */
	private String invoiceRemarks;
	/**
	 * 是否为清单票，Y：是，N：否
	 */
	private String isBillMark;
	/**
	 * 作废标志，0：正常，1：作废
	 */
	private String voidMark;
	/**
	 * 收货员（卷式发票新增字段，其他票可以不用）
	 */
	private String goodsClerk;
	/**
	 * 收费标志字段（06：可抵扣通行费 07：不可抵扣通行费，08：成品油）
	 */
	private String tollSign;
	/**
	 * 收费标志名称（没有为空）
	 */
	private String tollSignName;
	/**
	 * 发票详情（清单票首行号为0，折扣清单票行号从-2开始，正常行号都是从1开始
	 */
	private String invoiceDetailData;
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
	 * 最后修改日期
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
	 * 设置：发票代码
	 */
	public void setInvoiceDataCode(String invoiceDataCode) {
		this.invoiceDataCode = invoiceDataCode;
	}
	/**
	 * 获取：发票代码
	 */
	public String getInvoiceDataCode() {
		return invoiceDataCode;
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
	 * 设置：发票类型名称
	 */
	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}
	/**
	 * 获取：发票类型名称
	 */
	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}
	/**
	 * 设置：发票类型，01：增值税专票，03：机动车销售统一发票，04：增值税普通发票，10：电子发票，11：卷式普通发票，14:电子普通[通行费]发票，15：二手车统一发票
	 */
	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}
	/**
	 * 获取：发票类型，01：增值税专票，03：机动车销售统一发票，04：增值税普通发票，10：电子发票，11：卷式普通发票，14:电子普通[通行费]发票，15：二手车统一发票
	 */
	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}
	/**
	 * 设置：开票时间
	 */
	public void setBillingTime(String billingTime) {
		this.billingTime = billingTime;
	}
	/**
	 * 获取：开票时间
	 */
	public String getBillingTime() {
		return billingTime;
	}
	/**
	 * 设置：查询日期
	 */
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	/**
	 * 获取：查询日期
	 */
	public String getCheckDate() {
		return checkDate;
	}
	/**
	 * 设置：查验次数
	 */
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	/**
	 * 获取：查验次数
	 */
	public String getCheckNum() {
		return checkNum;
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
	 * 设置：机器码
	 */
	public void setTaxDiskCode(String taxDiskCode) {
		this.taxDiskCode = taxDiskCode;
	}
	/**
	 * 获取：机器码
	 */
	public String getTaxDiskCode() {
		return taxDiskCode;
	}
	/**
	 * 设置：购方名称
	 */
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}
	/**
	 * 获取：购方名称
	 */
	public String getPurchaserName() {
		return purchaserName;
	}
	/**
	 * 设置：购方纳税人识别号
	 */
	public void setTaxpayerNumber(String taxpayerNumber) {
		this.taxpayerNumber = taxpayerNumber;
	}
	/**
	 * 获取：购方纳税人识别号
	 */
	public String getTaxpayerNumber() {
		return taxpayerNumber;
	}
	/**
	 * 设置：购方银行账号
	 */
	public void setTaxpayerBankAccount(String taxpayerBankAccount) {
		this.taxpayerBankAccount = taxpayerBankAccount;
	}
	/**
	 * 获取：购方银行账号
	 */
	public String getTaxpayerBankAccount() {
		return taxpayerBankAccount;
	}
	/**
	 * 设置：购方地址，电话
	 */
	public void setTaxpayerAddressOrId(String taxpayerAddressOrId) {
		this.taxpayerAddressOrId = taxpayerAddressOrId;
	}
	/**
	 * 获取：购方地址，电话
	 */
	public String getTaxpayerAddressOrId() {
		return taxpayerAddressOrId;
	}
	/**
	 * 设置：销方名称
	 */
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	/**
	 * 获取：销方名称
	 */
	public String getSalesName() {
		return salesName;
	}
	/**
	 * 设置：销方纳税人识别号
	 */
	public void setSalesTaxpayerNum(String salesTaxpayerNum) {
		this.salesTaxpayerNum = salesTaxpayerNum;
	}
	/**
	 * 获取：销方纳税人识别号
	 */
	public String getSalesTaxpayerNum() {
		return salesTaxpayerNum;
	}
	/**
	 * 设置：销方银行，账号
	 */
	public void setSalesTaxpayerBankAccount(String salesTaxpayerBankAccount) {
		this.salesTaxpayerBankAccount = salesTaxpayerBankAccount;
	}
	/**
	 * 获取：销方银行，账号
	 */
	public String getSalesTaxpayerBankAccount() {
		return salesTaxpayerBankAccount;
	}
	/**
	 * 设置：销方地址，电话
	 */
	public void setSalesTaxpayerAddress(String salesTaxpayerAddress) {
		this.salesTaxpayerAddress = salesTaxpayerAddress;
	}
	/**
	 * 获取：销方地址，电话
	 */
	public String getSalesTaxpayerAddress() {
		return salesTaxpayerAddress;
	}
	/**
	 * 设置：价税合计
	 */
	public void setTotalTaxSum(String totalTaxSum) {
		this.totalTaxSum = totalTaxSum;
	}
	/**
	 * 获取：价税合计
	 */
	public String getTotalTaxSum() {
		return totalTaxSum;
	}
	/**
	 * 设置：税额
	 */
	public void setTotalTaxNum(String totalTaxNum) {
		this.totalTaxNum = totalTaxNum;
	}
	/**
	 * 获取：税额
	 */
	public String getTotalTaxNum() {
		return totalTaxNum;
	}
	/**
	 * 设置：不含税价（金额）
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：不含税价（金额）
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：备注
	 */
	public void setInvoiceRemarks(String invoiceRemarks) {
		this.invoiceRemarks = invoiceRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getInvoiceRemarks() {
		return invoiceRemarks;
	}
	/**
	 * 设置：是否为清单票，Y：是，N：否
	 */
	public void setIsBillMark(String isBillMark) {
		this.isBillMark = isBillMark;
	}
	/**
	 * 获取：是否为清单票，Y：是，N：否
	 */
	public String getIsBillMark() {
		return isBillMark;
	}
	/**
	 * 设置：作废标志，0：正常，1：作废
	 */
	public void setVoidMark(String voidMark) {
		this.voidMark = voidMark;
	}
	/**
	 * 获取：作废标志，0：正常，1：作废
	 */
	public String getVoidMark() {
		return voidMark;
	}
	/**
	 * 设置：收货员（卷式发票新增字段，其他票可以不用）
	 */
	public void setGoodsClerk(String goodsClerk) {
		this.goodsClerk = goodsClerk;
	}
	/**
	 * 获取：收货员（卷式发票新增字段，其他票可以不用）
	 */
	public String getGoodsClerk() {
		return goodsClerk;
	}
	/**
	 * 设置：收费标志字段（06：可抵扣通行费 07：不可抵扣通行费，08：成品油）
	 */
	public void setTollSign(String tollSign) {
		this.tollSign = tollSign;
	}
	/**
	 * 获取：收费标志字段（06：可抵扣通行费 07：不可抵扣通行费，08：成品油）
	 */
	public String getTollSign() {
		return tollSign;
	}
	/**
	 * 设置：收费标志名称（没有为空）
	 */
	public void setTollSignName(String tollSignName) {
		this.tollSignName = tollSignName;
	}
	/**
	 * 获取：收费标志名称（没有为空）
	 */
	public String getTollSignName() {
		return tollSignName;
	}
	/**
	 * 设置：发票详情（清单票首行号为0，折扣清单票行号从-2开始，正常行号都是从1开始
	 */
	public void setInvoiceDetailData(String invoiceDetailData) {
		this.invoiceDetailData = invoiceDetailData;
	}
	/**
	 * 获取：发票详情（清单票首行号为0，折扣清单票行号从-2开始，正常行号都是从1开始
	 */
	public String getInvoiceDetailData() {
		return invoiceDetailData;
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
	 * 设置：最后修改日期
	 */
	public void setLastChgDt(String lastChgDt) {
		this.lastChgDt = lastChgDt;
	}
	/**
	 * 获取：最后修改日期
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
