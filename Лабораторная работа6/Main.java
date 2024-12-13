import java.util.ArrayList;

enum TaskStatus {
    ВЫПОЛНЕН("Выполнено"),
    НЕ_ВЫПОЛНЕН("Не выполнено");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

class Task {
    int idCounter;
    String description;
    String category;
    TaskStatus status;

    public Task(int idCounter, String description, String category, TaskStatus status) {
        this.idCounter = idCounter;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    void displayInfo() {
        System.out.println(idCounter + ". " + description + " (Категория: " + category + ", Статус: " + status.getStatus() + ")");
    }
}

class TodoList {
    ArrayList<Task> tasks;
    int nextIdCounter;

    public TodoList() {
        this.tasks = new ArrayList<>();
        this.nextIdCounter = 1;
    }

    void addTask(String description, String category, TaskStatus status) {
        Task task = new Task(nextIdCounter++, description, category, status);
        tasks.add(task);
        System.out.println("Задача \"" + description + "\" добавлена.");
    }

    void removeTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Таски не существует!");
        }
        tasks.remove(task);
        System.out.println("Задача \"" + task.description + "\" удалена");
        updateTaskId();
    }

    void displayAllTasks() {
        for (Task task : tasks) {
            task.displayInfo();
        }
    }

    void markTaskAsDone(int idCounter) {
        for (Task task : tasks) {
            if (task.idCounter == idCounter) {
                task.status = TaskStatus.ВЫПОЛНЕН;
                System.out.println("Задача " + task.description + " отмечена как выполненная.");
            }
        }
    }

    void searchTasks(String keyword) {
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                task.displayInfo();
            }
        }
    }

    void displayTasksByCategory(String category) {
        for (Task task : tasks) {
            if (task.category.equalsIgnoreCase(category)) {
                System.out.println("Категория: " + task.category);
                task.displayInfo();
                System.out.println();
            }
        }
    }

    void displayStatistics() {
        int total = tasks.size();
        int completed = 0;
        int incompleted = 0;
        for (Task task : tasks) {
            if (task.status == TaskStatus.ВЫПОЛНЕН) {
                completed++;
            } else {
                incompleted++;
            }
        }
        System.out.println("Всего задач: " + total + "\nВыполнено: " + completed + "\nНе выполнено: " + incompleted);
    }

    void updateTaskId() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).idCounter = i + 1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();

        System.out.println("=== Добавление задач ===");
        todoList.addTask("Купить молоко", "Покупки", TaskStatus.НЕ_ВЫПОЛНЕН);
        todoList.addTask("Позвонить маме", "Личное", TaskStatus.НЕ_ВЫПОЛНЕН);
        todoList.addTask("Подготовить отчет", "Работа", TaskStatus.НЕ_ВЫПОЛНЕН);

        System.out.println("\n=== Вывод всех задач ===");
        todoList.displayAllTasks();

        System.out.println("\n=== Отметка задачи как выполненной ===");
        todoList.markTaskAsDone(1);

        System.out.println("\n=== Поиск задач по ключевому слову \"отчёт\" ===");
        todoList.searchTasks("отчет");

        System.out.println("\n=== Вывод задач по категориям ===");
        todoList.displayTasksByCategory("Покупки");
        todoList.displayTasksByCategory("Личное");
        todoList.displayTasksByCategory("Работа");

        System.out.println("\n=== Статистика ===");
        todoList.displayStatistics();

        System.out.println("\n# Доделайте\n");
        todoList.addTask("Сходить в спортзал", "Здоровье", TaskStatus.НЕ_ВЫПОЛНЕН);

        System.out.println("\n=== Обновлённый список всех задач ===");
        todoList.displayAllTasks();

        System.out.println("\n=== Удаление задачи ===");
        todoList.removeTask(todoList.tasks.get(1));

        System.out.println("\n=== финальный список всех задач ===");
        todoList.displayAllTasks();

        System.out.println("\n=== Обновлённая статистика ===");
        todoList.displayStatistics();
    }
}
