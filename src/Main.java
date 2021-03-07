import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        /**
         * Находим количество несовершеннолетних
         */
        long personStream = persons.stream()
                .filter(s -> s.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + personStream + " человек");

        /**
         * Получить список фамилий призывников
         * Дополнительно убрал дубликаты фамилий с помощью метода "distinct".
         * Без метода "distinct" вывод фамилий в консоль получается очень громосткий.
         */
        List<String> personStreamFamily = persons.stream()
                .filter(s -> s.getAge() >= 18 && s.getAge() <= 27)
                .map(Person::getFamily)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + personStreamFamily);

        /**
         * Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
         */

        List<Person> personStreamWork = persons.stream()
                .filter(s -> s.getEducation() == Education.HIGHER)
                .filter(s -> s.getAge() >= 18)
                .filter(s -> (s.getSex() == Sex.WOMEN) ? s.getAge() <= 60 : s.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Cписок потенциально работоспособных людей: " + personStreamWork);







    }
}
