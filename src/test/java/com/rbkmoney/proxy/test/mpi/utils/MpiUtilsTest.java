package com.rbkmoney.proxy.test.mpi.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MpiUtilsTest {

    @Test
    public void maskNumber() throws Exception {
        assertEquals("4242********8989", MpiUtils.maskNumber("4242545467678989"));
        assertEquals("4253*********8449", MpiUtils.maskNumber("42535444557678449"));
    }

}