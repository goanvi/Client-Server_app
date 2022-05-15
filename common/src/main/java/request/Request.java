package request;

import dto.StudyGroupDTO;

import java.io.Serializable;

public class Request implements Serializable {
    StudyGroupDTO groupDto;
    String name;
    String argument;
    public Request (StudyGroupDTO dto, String name, String argument){
        this.name = name;
        this.argument = argument;
        this.groupDto = dto;
    }

    public StudyGroupDTO getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(StudyGroupDTO groupDto) {
        this.groupDto = groupDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
