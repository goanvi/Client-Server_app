package dto;

import java.io.Serializable;

public enum FormOfEducationDTO implements Serializable{
    DISTANCE_EDUCATION("Дистанционно"),
    FULL_TIME_EDUCATION("Очно"),
    EVENING_CLASSES("Вечер");

    private final String name;

    FormOfEducationDTO(String name){
        this.name = name;
    }

//    public static FormOfEducationDTO convert(String name) throws IncorrectNameEnumException {
//        switch (name.toLowerCase()) {
//            case "дистанционно": return FormOfEducationDTO.DISTANCE_EDUCATION;
//            case "очно": return FormOfEducationDTO.FULL_TIME_EDUCATION;
//            case "вечер": return FormOfEducationDTO.EVENING_CLASSES;
//            default: throw new IncorrectNameEnumException();
//        }
//    }

    public String getName() {
        return name;
    }
}
