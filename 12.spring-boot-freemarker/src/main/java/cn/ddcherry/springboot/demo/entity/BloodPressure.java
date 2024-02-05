package cn.ddcherry.springboot.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BloodPressure {
	private Integer minValue;
	private Integer maxValue;
}
