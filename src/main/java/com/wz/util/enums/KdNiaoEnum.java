package com.wz.util.enums;

import lombok.Getter;

/**
 * KdNiao Enum
 * @author Administrator
 *
 */
public class KdNiaoEnum {

	/**
	 * 快递公司类型
	 * @author Administrator
	 *
	 */
	@Getter
	public static enum KdType {
		
		EMS(0, "EMS"),
		顺丰速运(1, "SF"),
		邮政国内标快(2, "YZBK"),
		邮政快递包裹(3, "YZPY"),
		宅急送(4, "ZJS"),
		全一快递(5, "UAPEX"),
		联昊通速递(6, "LHT"),
		申通快递(7, "STO"),
		德邦快递(8, "DBL"),
		京东快递(9, "JD"),
		信丰物流(10, "XFEX"),
		天天快递(11, "HHTT"),
		速尔快递(12, "SURE"),
		跨越速运(13, "KYSY"),
		品骏快递(14, "PJ"),
		承诺达(15, "CND"),
		中通快递(16, "ZTO"),
		韵达速递(17, "YD"),
		百世快递(18, "HTKY"),
		圆通速递(19, "YTO"),
		远成快运(20, "YCWL"),
		优速快递(21, "UC"),
		安能快递(22, "ANE");
		
		KdType(Integer value, String text) {
            this.value = value;
            this.text = text;
        }
		
		private Integer value;
		
		private String text;
		
		public static KdType getByValue(int value) {
			for (KdType t : values()) {
				if(t.getValue().intValue() == value){
					return t;
				}
			}
			return null;
		}
		
	}
	
	/**
	 * 快运公司类型
	 * @author Administrator
	 *
	 */
	@Getter
	public static enum KyType {
		
		天地华宇(0, "HOAU"),
		腾林物流(1, "TLWL"),
		德邦快运(2, "DBLKY"),
		安能快运(3, "ANEKY"),
		京东快运(4, "JDKY"),
		龙邦快运(5, "LB"),
		百世快运(6, "HTKYKY"),
		中通快运(7, "ZTOKY"),
		佳吉快运(8, "CNEX"),
		韵达快运(9, "YDKY");
		
		KyType(Integer value, String text) {
            this.value = value;
            this.text = text;
        }
		
		private Integer value;
		
		private String text;
		
		public static KyType getByValue(int value) {
			for (KyType t : values()) {
				if(t.getValue().intValue() == value){
					return t;
				}
			}
			return null;
		}
		
	}
	
}
