package com.guigehling.healthcare.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class InstitutionServiceTest {

    @InjectMocks
    private InstitutionService institutionService;

    @Test
    public void entryMessageAndDecodedMessageShouldBeEquals() {
        //GIVEN
        final var entryMessage = "73428949000160";

        //WHEN
        var messageEncoded = institutionService.generateAccessKey(entryMessage);
        var messageDecode = institutionService.decodeAccessKey(messageEncoded);

        //THEN
        assertTrue(entryMessage.equals(messageDecode));
    }
}

