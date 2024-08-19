package com.example.demo.hello;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitConvertorTest {

	private BigDecimal result = BigDecimal.valueOf(1023).setScale(3, RoundingMode.CEILING);

	@Test
	void getUnit1023B() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1023);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("B");
	}

	@Test
	void getUnitB1023KB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024*1023);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("KB");
	}

	@Test
	void getUnitBTo1023MB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024*1024*1023);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("MB");
	}

	@Test
	void getUnitBTo1023GB() {
		long input = 1024L*1024L*1024L*1023L;
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(input);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("GB");
	}

	@Test
	void getUnitBTo1023TB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024*1024*1024*1023);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("TB");
	}

	@Test
	void getUnitBTo1023PB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024*1024*1024*1024*1023);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("PB");
	}

	@Test
	void getUnitWithKBTo01KB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1023L, UnitConvertor.KB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("KB");
	}

	@Test
	void getUnitWithKBToMB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1023L, UnitConvertor.KB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("MB");
	}

	@Test
	void getUnitWithKBToGB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024L*1023L, UnitConvertor.KB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("GB");
	}

	@Test
	void getUnitWithKBToTB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024L*1024L*1023L, UnitConvertor.KB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("TB");
	}

	@Test
	void getUnitWithKBToPB() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024L*1024L*1024L*1023L, UnitConvertor.KB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(result);
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("PB");
	}

	@Test
	void getUnitKBToTB() {
		// 1024 * 1023 KB -> 1024 * 1023 * 1024 B => 1024 * 1023 KB => 1023 MB => 1.023 GB => 0.001023 TB => 0.000001023 PB
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1023L, UnitConvertor.KB, UnitConvertor.TB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(BigDecimal.valueOf(0.001).setScale(3, RoundingMode.CEILING));
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo("TB");
	}

	@Test
	void getUnitKBToTB2() {
		// 1024 1024 1023 KB => 1024 1024 1024 1023 B => 1023 GB => 1.023 TB(1000 으로 나누는 경우, 근데 1024 로 나누므로 0.9990....이 나오게 됨)
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024L*1023L, UnitConvertor.KB, UnitConvertor.TB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(BigDecimal.valueOf(1.000).setScale(3, RoundingMode.CEILING));
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo(UnitConvertor.TB);
	}

	@Test
	void getUnitKBToTB3() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024L*1024L*1023L, UnitConvertor.KB, UnitConvertor.TB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(BigDecimal.valueOf(1023.000).setScale(3, RoundingMode.CEILING));
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo(UnitConvertor.TB);
	}

	@Test
	void getUnitKBToTB4() {
		UnitConvertor.UnitDto storageUnit = UnitConvertor.getStorageUnit(1024L*1024L*1024L*1024L*1023L, UnitConvertor.KB, UnitConvertor.TB);

		Assertions.assertThat(storageUnit.getUsage()).isEqualTo(BigDecimal.valueOf(1047552.000).setScale(3, RoundingMode.CEILING));
		Assertions.assertThat(storageUnit.getUnit()).isEqualTo(UnitConvertor.TB);
	}
}