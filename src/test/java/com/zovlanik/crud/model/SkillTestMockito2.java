package com.zovlanik.crud.model;

import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkillTestMockito2 {
    @Test
    public void testSkill(){
        Skill skill = mock(Skill.class);

        when(skill.getName()).thenReturn("Abrakadabra");

        System.out.println(skill.getName());

    }
}
