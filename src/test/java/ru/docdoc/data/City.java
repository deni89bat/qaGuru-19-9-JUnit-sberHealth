package ru.docdoc.data;

public enum City {
    ALMETEVSK("Альметьевск"),
    PENZA("Пенза"),
    TULA("Тула");
    private final String desc;

    City(String desc) {
        this.desc = desc;
    }

    public  String getDesc() {
        return desc;
    }
}
