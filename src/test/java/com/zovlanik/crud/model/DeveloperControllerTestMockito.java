package com.zovlanik.crud.model;

import com.zovlanik.crud.controller.DeveloperController;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DeveloperControllerTestMockito {
    @Mock
    Developer developer = Mockito.mock(Developer.class);

    DeveloperController developerController = Mockito.mock(DeveloperController.class);
    Set<Long> idSkills = new HashSet<Long>(Arrays.asList(1L,2L));


    @Test
    public void checkDeveloperCreate(){
        when(developerController.create("TestDeveloperName",idSkills,2L)).thenReturn(true);

        assertThat(developerController.create("TestDeveloperName",idSkills,2L)).isTrue();
    }

    @Test
    public void checkDeveloperGetByID(){
        when(developerController.getById(1L)).thenReturn(developer);
        assertThat(developerController.getById(1L)).isEqualTo(developer);
    }
    @Test
    public void checkDeveloperDeleteById(){
        when(developerController.deleteById(1L)).thenReturn(true);
        when(developerController.deleteById(1111L)).thenReturn(false);
        assertThat(developerController.deleteById(1L)).isEqualTo(true);
        assertThat(developerController.deleteById(1111L)).isFalse();

    }

}
