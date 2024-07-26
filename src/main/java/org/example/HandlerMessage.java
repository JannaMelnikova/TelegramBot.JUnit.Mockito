package org.example;

public class HandlerMessage {
    public String getAnswer(String request) {
        switch (request) {
            case "/start": return "Этот бот поможет вам с выбором фильма ";
            case "/action": return "Боевик";
            case "/adventure": return "Приключеческий фильм";
            case "/animation": return "Анимация";
            case "/biography": return "Биография";
            case "/comedy": return "Комедия";
            case "/crime": return "Криминальный фильм";
            case "/documentary": return "Документальный фильм";
            case "/drama": return "Драма";
            case "/family": return "Семейный фильм";
            case "/fantasy": return "Фэнтези";
            case "/film Noir": return "Фильм-нуар";
            case "/history": return "Исторический фильм";
            case "/horror": return "Ужасы";
            case "/musical": return "Мюзикл";
            case "/mystery": return "Детектив";
            case "/romance": return "Романтика";
            case "/science fiction": return "Научная фантастика";
            case "/sport": return "Кровавый спорт";
            case "/thriller": return "Триллер";
            case "/war": return "Военный фильм";
            case "/western": return "Вестерн";
            default:return "нет пункта меню";
        }
    }
}
