package org.dselent.scheduling.server.service;

import org.dselent.scheduling.server.dto.DepartmentAddDto;
import org.dselent.scheduling.server.dto.DepartmentModifyDto;
import org.dselent.scheduling.server.dto.DepartmentRemoveDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface DepartmentService {
  