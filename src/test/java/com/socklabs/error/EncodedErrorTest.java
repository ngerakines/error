package com.socklabs.error;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by ngerakines on 1/30/14.
 */
public class EncodedErrorTest {

	@Test
	public void encode() {
		Assert.assertEquals(KitchenError.NAMESPACE, "KHN");
		Assert.assertEquals(KitchenError.OVEN_TOO_HOT.code(), "KHNAAAAB");
		Assert.assertTrue(KitchenError.fromCode("KHNAAAAB").isPresent());
		Assert.assertEquals(KitchenError.fromCode("KHNAAAAB").get(), KitchenError.OVEN_TOO_HOT);
	}

	@Test
	public void hashId() {
		Assert.assertEquals(ErrorUtil.hashId(1), "AAAAB");
		Assert.assertEquals(ErrorUtil.hashId(10), "AAAAL");
		Assert.assertEquals(ErrorUtil.hashId(100), "AAADE");
		Assert.assertEquals(ErrorUtil.hashId(1000), "AAA9J");
		Assert.assertEquals(ErrorUtil.hashId(10000), "AAK2S");
		Assert.assertEquals(ErrorUtil.hashId(100000), "ADBXA");
		Assert.assertEquals(ErrorUtil.hashId(1000000), "A8SUA");
		Assert.assertEquals(ErrorUtil.hashId(10000000), "KTFWA");
		Assert.assertEquals(ErrorUtil.hashId(33554431), "99999");
	}

	@Test(expected = IllegalArgumentException.class)
	public void hashNegative() {
		ErrorUtil.hashId(-1);
	}

}
