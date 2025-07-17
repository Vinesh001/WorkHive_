package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.Project;
import java.util.List;

public interface ProjectDao {
	boolean postProject(Project project);
	boolean updateProjectStatus(int projectId, String status);
	List<Project> getAllProjects();
	List<Project> getProjectsByClientId(int clientId);
	
}
