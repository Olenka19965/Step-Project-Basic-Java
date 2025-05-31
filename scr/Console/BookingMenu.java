package scr.Console;

import static scr.Console.MainMenu.scanner;

public class BookingMenu {
    public static boolean runBookingMenu(){
        System.out.print("Введіть місце призначення: ");
        String endDestination = scanner.nextLine();
        System.out.print("Введіть дату польоту (в форматі dd/mm/yyyy): ");
        String dateOfFlight = scanner.nextLine();
        boolean isNumber = false;
        do {
            try {
                System.out.print("Введіть кількість квитків, що ви бажаєте забронювати: ");
                int bookingNum = Integer.parseInt(scanner.nextLine());
                if (bookingNum <= 0) {
                    System.out.println("Кількість квитків не може бути менша за 1!");
                } else isNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Введена кількість квитків для пошуку бронювання не є числом!");
            }
        } while (!isNumber);
        // вивід на екран знайдені варіанти

        // вибір ID польоту або повернення в головне меню вибравши 0
        //Користувач вводить ім'я та прізвище для кількості заброньованих осіб

        //
    }
}
