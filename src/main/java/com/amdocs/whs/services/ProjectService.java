package com.amdocs.whs.services;

import java.util.List;
import com.amdocs.whs.bean.Project;
import com.amdocs.whs.dao.ProjectDaoImpl;

public class ProjectService {

	private final ProjectDaoImpl projectDao;

	public ProjectService() {
		this.projectDao = new ProjectDaoImpl();
	}

	public boolean postProject(Project project) {
		if (project.getTitle() == null || project.getBudget() <= 0 || project.getDeadline() == null) {
			System.out.println("Invalid project details.");
			return false;
		}
		return projectDao.postProject(project);
	}

	public List<Project> getAllProjects() {
		return projectDao.getAllProjects();
	}

	public List<Project> getClientProjects(int clientId) {
		return projectDao.getProjectsByClientId(clientId);
	}
	
	public boolean updateProjectStatus(int projectId, String status) {
		return projectDao.updateProjectStatus(projectId, status);
	}

}
