package com.wapwag.woss.modules.ticket.Entity;


import com.wapwag.woss.common.persistence.DataEntity;

/**
 * <p>
 * 产品配件信息表（bom清单总表）
 * </p>
 *
 * @author AutoGeneration
 * @since 2017-11-07
 */
public class ProductComponentData extends DataEntity<ProductComponentData> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
	private String componentName;
    /**
     * 规格
     */
	private String componentModel;

	/**
     * 1年-故障率
     */
	private String failureRateYear1;

	/**
     * 2年-故障率
     */
	private String failureRateYear2;

	/**
     * 3年-故障率
     */
	private String failureRateYear3;

	/**
     * 4年-故障率
     */
	private String failureRateYear4;

	/**
     * 5年-故障率
     */
	private String failureRateYear5;

	/**
     * 6年-故障率
     */
	private String failureRateYear6;

	/**
     * 7年-故障率
     */
	private String failureRateYear7;

	/**
     * 8年-故障率
     */
	private String failureRateYear8;

	/**
     * 9年-故障率
     */
	private String failureRateYear9;

	/**
     * 10年-故障率
     */
	private String failureRateYear10;

	/**
     * 排序
     */
	private int sort;

	/**
	 * 模糊查询关键字
	 */
	private String keyWord;

	/**
     * 权重
     */
	private String weight;

	private String  wbReason;
	private String wbProgramme;

	public ProductComponentData() {
		super();
	}

	public ProductComponentData(String id){
		super(id);
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentModel() {
		return componentModel;
	}

	public void setComponentModel(String componentModel) {
		this.componentModel = componentModel;
	}

	public String getFailureRateYear1() {
		return failureRateYear1;
	}

	public void setFailureRateYear1(String failureRateYear1) {
		this.failureRateYear1 = failureRateYear1;
	}

	public String getFailureRateYear2() {
		return failureRateYear2;
	}

	public void setFailureRateYear2(String failureRateYear2) {
		this.failureRateYear2 = failureRateYear2;
	}

	public String getFailureRateYear3() {
		return failureRateYear3;
	}

	public void setFailureRateYear3(String failureRateYear3) {
		this.failureRateYear3 = failureRateYear3;
	}

	public String getFailureRateYear4() {
		return failureRateYear4;
	}

	public void setFailureRateYear4(String failureRateYear4) {
		this.failureRateYear4 = failureRateYear4;
	}

	public String getFailureRateYear5() {
		return failureRateYear5;
	}

	public void setFailureRateYear5(String failureRateYear5) {
		this.failureRateYear5 = failureRateYear5;
	}

	public String getFailureRateYear6() {
		return failureRateYear6;
	}

	public void setFailureRateYear6(String failureRateYear6) {
		this.failureRateYear6 = failureRateYear6;
	}

	public String getFailureRateYear7() {
		return failureRateYear7;
	}

	public void setFailureRateYear7(String failureRateYear7) {
		this.failureRateYear7 = failureRateYear7;
	}

	public String getFailureRateYear8() {
		return failureRateYear8;
	}

	public void setFailureRateYear8(String failureRateYear8) {
		this.failureRateYear8 = failureRateYear8;
	}

	public String getFailureRateYear9() {
		return failureRateYear9;
	}

	public void setFailureRateYear9(String failureRateYear9) {
		this.failureRateYear9 = failureRateYear9;
	}

	public String getFailureRateYear10() {
		return failureRateYear10;
	}

	public void setFailureRateYear10(String failureRateYear10) {
		this.failureRateYear10 = failureRateYear10;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getWbReason() {
		return wbReason;
	}

	public void setWbReason(String wbReason) {
		this.wbReason = wbReason;
	}

	public String getWbProgramme() {
		return wbProgramme;
	}

	public void setWbProgramme(String wbProgramme) {
		this.wbProgramme = wbProgramme;
	}

	@Override
	public String toString() {
		return "ProductComponent{" +
			", id=" + id +
			", componentName=" + componentName +
			", componentModel=" + componentModel +
			", weight=" + weight +
			", sort=" + sort +
			"}";
	}
}
