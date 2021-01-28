package com.guigehling.healthcare.service;

import com.guigehling.healthcare.mocks.WalletMocks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ExamServiceTest {

    @InjectMocks
    private ExamService examService;

    @Test
    public void mustHaveInsufficientBalance() {
        // GIVEN
        var walletDTO = WalletMocks.getWalletWithoutBalance();

        // THEN
        assertTrue(examService.hasInsufficientBalance(walletDTO));
    }

    @Test
    public void mustHaveSufficientBalance() {
        // GIVEN
        var walletDTO = WalletMocks.getWalletWitBalance();

        // THEN
        assertFalse(examService.hasInsufficientBalance(walletDTO));
    }

}

