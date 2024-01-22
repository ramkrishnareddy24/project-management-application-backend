package com.project.projectMgmtApp.team.service;

import com.project.projectMgmtApp.team.exceptions.AssignedValueNotFound;
import com.project.projectMgmtApp.team.exceptions.TeamNotFoundException;
import com.project.projectMgmtApp.team.model.Assigned;
import com.project.projectMgmtApp.team.model.Task;
import com.project.projectMgmtApp.team.repository.AssignedRepository;
import com.project.projectMgmtApp.team.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigningServiceImpl {
    @Autowired
    private AssignedRepository assignedRepository;

    @Autowired
    private TaskRepository taskRepository;


    public void createTeamAssignment(){

    }

    public List<Assigned> getAssignmentsByTeamId(String teamId) throws TeamNotFoundException {
        Task dbTask = taskRepository.findById(teamId).stream().findFirst().orElse(null);
        if(dbTask != null){
            throw new TeamNotFoundException("Team not found for given id");
        }else {
            List<Assigned> assignedLists = assignedRepository.findAllByTeamId(teamId);
            return assignedLists;
        }
    }

    public Assigned getAssigned(String id) throws AssignedValueNotFound {
        Assigned dbAssigned = assignedRepository.findById(id).stream().findFirst().orElse(null);
        if(dbAssigned != null) return dbAssigned;
        else throw new AssignedValueNotFound("Assigned details not found");
    }
}
