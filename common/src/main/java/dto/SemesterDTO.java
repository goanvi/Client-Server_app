package dto;

import java.io.Serializable;

public enum SemesterDTO implements Serializable {
    THIRD("Три"),
    FIFTH("Пять"),
    SEVENTH("Семь");

    private final String name;

    SemesterDTO(String name){
        this.name =name;
    }

//    public static SemesterDTO equals(String name) throws IncorrectNameEnumException {
//        switch (name.toLowerCase()) {
//            case "три" : return SemesterDTO.THIRD;
//            case "пять" : return SemesterDTO.FIFTH;
//            case "семь" : return SemesterDTO.SEVENTH;
//            default : throw new IncorrectNameEnumException();
//        }
//    }

    public String getName() {
        return name;
    }
}