package com.zovlanik.crud.controller;

import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;

public class SkillController {
    private SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();
}
