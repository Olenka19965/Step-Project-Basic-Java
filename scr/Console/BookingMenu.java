package scr.Console;

import static scr.Console.MainMenu.scanner;

public class BookingMenu {
    public static boolean runBookingMenu(){
        System.out.print("Введіть місце призначення: ");
        String endDestination = scanner.nextLine();
        System.out.print("Введіть дату польоту (в форматі dd/mm/yyyy): ");
        String dateOfFlight = scanner.nextLine();
        boolean isNumber = false;
        int bookingNum = 0;
        do {
            try {
                System.out.print("Введіть кількість квитків, що ви бажаєте забронювати: ");
                bookingNum = Integer.parseInt(scanner.nextLine());
                if (bookingNum <= 0) {
                    System.out.println("Кількість квитків не може бути менша за 1!");
                } else isNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Введена кількість квитків для пошуку бронювання не є числом!");
            }
        } while (!isNumber);
        // вивід на екран знайдені варіанти
        // вибір ID польоту або повернення в головне меню вибравши 0
        int bookingID;
        System.out.print("Введіть ID рейсу, на який ви бажаєте забронювати квитки, або '0' для виходу з меню бронювання: ");
        bookingID = Integer.parseInt(scanner.nextLine());
        if (bookingID == 0) {
            return false;
        } else {
            for (int i = 0; i < bookingNum; i++){
                //Користувач вводить ім'я та прізвище для кількості заброньованих осіб
                System.out.printf("Введіть ім'я %d-го пасажира: ", i + 1);
                String namePassenger = scanner.nextLine();
                System.out.printf("Введіть прізвище %d-го пасажира: ", i + 1);
                String surnamePassenger = scanner.nextLine();
            }
        }


        //
        return false;
    }

}
