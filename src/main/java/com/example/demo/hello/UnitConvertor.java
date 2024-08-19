package com.example.demo.hello;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class UnitConvertor {
	private UnitConvertor() {
	}

	public static final String B = "B";
	public static final String KB = "KB";
	public static final String MB = "MB";
	public static final String GB = "GB";
	public static final String TB = "TB";
	public static final String PB = "PB";
	private static final int DEFAULT_SCALE = 2;
	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.UNNECESSARY;
	private static final String[] UNITS = {B, KB, MB, GB, TB, PB};
	private static final Map<String, Integer> unitIndexes = new HashMap<>();

	static {
		for (int i = 0; i < UNITS.length; i++) {
			unitIndexes.put(UNITS[i], i);
		}
	}

	/**
	 *
	 * @param size 변환할 사이즈
	 * @return 기본으로 지정된 단위로 변환해 반환합니다. (기본 단위 B)
	 */
	public static UnitDto getStorageUnit(long size) {
		return getStorageUnit(size, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	public static UnitDto getStorageUnit(long size, int scale) {
		return getStorageUnit(size, scale, DEFAULT_ROUNDING_MODE);
	}

	public static UnitDto getStorageUnit(long size, int scale, RoundingMode roundingMode) {
		int baseUnitIdx = 0;
		int resultUnitIdx = getUnitIdx(size, baseUnitIdx);
		double usage = getResultUnit(size, baseUnitIdx, resultUnitIdx);

		return new UnitDto(scaleResult(usage, scale, roundingMode), UNITS[resultUnitIdx]);
	}

	/**
	 *
	 * @param size 변환시킬 사이즈
	 * @param baseUnit 사이즈의 단위
	 * @return
	 */
	public static UnitDto getStorageUnit(long size, String baseUnit) {
		return getStorageUnit(size, baseUnit, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	public static UnitDto getStorageUnit(long size, String baseUnit, int scale) {
		return getStorageUnit(size, baseUnit, scale, DEFAULT_ROUNDING_MODE);
	}

	public static UnitDto getStorageUnit(long size, String baseUnit, int scale, RoundingMode roundingMode) {
		int baseUnitIdx = unitIndexes.get(baseUnit);
		int resultUnitIdx = getUnitIdx(size, baseUnitIdx);
		double usage = getResultUnit(size, baseUnitIdx, resultUnitIdx);

		return new UnitDto(scaleResult(usage, scale, roundingMode), UNITS[resultUnitIdx]);
	}


	/**
	 *
	 * @param size 변환시킬 크기
	 * @param baseUnit size 의 단위
	 * @param resultUnit 반환될 데이터의 단위
	 * @return
	 */
	public static UnitDto getStorageUnit(long size, String baseUnit, String resultUnit) {
		return getStorageUnit(size, baseUnit, resultUnit, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	public static UnitDto getStorageUnit(long size, String baseUnit, String resultUnit, int scale) {
		return getStorageUnit(size, baseUnit, resultUnit, scale, DEFAULT_ROUNDING_MODE);
	}

	public static UnitDto getStorageUnit(long size, String baseUnit, String resultUnit, int scale, RoundingMode roundingMode) {
		int baseUnitIdx = unitIndexes.get(baseUnit);
		int resultUnitIdx = unitIndexes.get(resultUnit);
		double usage = getResultUnit(size, baseUnitIdx, resultUnitIdx);

		return new UnitDto(scaleResult(usage, scale, roundingMode), UNITS[resultUnitIdx]);
	}

	private static int getUnitIdx(long size, int baseUnitIdx) {
		return (int)(Math.log(size * Math.pow(1024, baseUnitIdx)) / Math.log(1024));
	}

	private static double getResultUnit(long size, int baseUnitIdx, int resultUnitIdx) {
		return size / Math.pow(1024, resultUnitIdx - baseUnitIdx);
	}

	private static BigDecimal scaleResult(double result, int scale, RoundingMode roundingMode) {
		return BigDecimal.valueOf(result).setScale(scale, roundingMode);
	}

	@Getter
	public static class UnitDto {
		private BigDecimal usage;
		private String unit;

		public UnitDto(BigDecimal usage, String unit) {
			this.usage = usage;
			this.unit = unit;
		}
	}
}
