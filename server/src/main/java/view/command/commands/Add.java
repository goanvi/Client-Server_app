package view.command.commands;

import controller.CollectionManager;
import dto.StudyGroupDTO;
import model.*;
import model.Exceptions.IncorrectNameEnumException;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

public class Add extends AbstractCommand {
    private CollectionManager collectionManager;

    public Add (CollectionManager collectionManager){
        super("add", "Добавляет новый элемент в коллекцию");
        this. collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        try{
            StudyGroupDTO groupDTO = request.getGroupDto();
            if(groupDTO.getGroupAdmin().getWeight()==0){
            collectionManager.addToCollection(new StudyGroup(
                    groupDTO.getName(),
                    new Coordinates(groupDTO.getCoordinates().getX(),groupDTO.getCoordinates().getY()),
                    groupDTO.getStudentsCount(),
                    groupDTO.getAverageMark(),
                    FormOfEducation.convert(groupDTO.getFormOfEducation().getName()),
                    Semester.equals(groupDTO.getSemesterEnum().getName()),
                    null));
            }else {collectionManager.addToCollection(new StudyGroup(
                    groupDTO.getName(),
                    new Coordinates(groupDTO.getCoordinates().getX(),groupDTO.getCoordinates().getY()),
                    groupDTO.getStudentsCount(),
                    groupDTO.getAverageMark(),
                    FormOfEducation.convert(groupDTO.getFormOfEducation().getName()),
                    Semester.equals(groupDTO.getSemesterEnum().getName()),
                    new Person(groupDTO.getGroupAdmin().getName(),groupDTO.getGroupAdmin().getBirthday(),groupDTO.getGroupAdmin().getWeight())));}
            return new Response(true, "Группа успешно создана!");
        } catch (IncorrectNameEnumException e) {
            return new Response(false, "Данные введены некорректно!");
        }
    }

    public String getMessage(){
        return "addCommand {element} - Добавляет новый элемент в коллекцию";
    }
}
