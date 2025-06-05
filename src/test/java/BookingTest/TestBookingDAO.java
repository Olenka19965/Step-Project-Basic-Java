package BookingTest;

import org.example.Booking;
import org.example.BookingDAO.CollectionBookingDao;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBookingDAO {

    private CollectionBookingDao dao;
    private Booking booking1;
    private Booking booking2;

    @BeforeEach
    void setUp() {
        dao = new CollectionBookingDao();

        booking1 = new Booking();
        booking1.setId(1);

        booking2 = new Booking();
        booking2.setId(2);

        dao.saveBooking(booking1);
        dao.saveBooking(booking2);
    }

    @AfterEach
    void tearDown() {
        File file = new File(CollectionBookingDao.fileBooking);
        if (file.exists()) file.delete();
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = dao.getAllBookings();
        assertEquals(2, bookings.size());
    }

    @Test
    void testGetBookingById_Found() {
        Booking found = dao.getBookingById(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    void testGetBookingById_NotFound() {
        Booking found = dao.getBookingById(999);
        assertNull(found);
    }

    @Test
    void testDeleteBookingById_Success() {
        boolean deleted = dao.deleteBookingById(2);
        assertTrue(deleted);
        assertNull(dao.getBookingById(2));
    }

    @Test
    void testDeleteBookingById_Fail() {
        boolean deleted = dao.deleteBookingById(999);
        assertFalse(deleted);
    }

    @Test
    void testDeleteBookingByBooking_Success() {
        boolean deleted = dao.deleteBookingByBooking(booking1);
        assertTrue(deleted);
        assertNull(dao.getBookingById(1));
    }

    @Test
    void testDeleteBookingByBooking_NotFound() {
        Booking fake = new Booking();
        fake.setId(100);
        boolean deleted = dao.deleteBookingByBooking(fake);
        assertFalse(deleted);
    }

    @Test
    void testSaveBooking_New() {
        Booking newBooking = new Booking();
        newBooking.setId(3);
        boolean saved = dao.saveBooking(newBooking);
        assertTrue(saved);
        assertNotNull(dao.getBookingById(3));
    }

    @Test
    void testSaveBookingToFile_AndLoad() {
        assertTrue(dao.saveBookingToFile());

        CollectionBookingDao newDao = new CollectionBookingDao();
        assertTrue(newDao.loadBookingData());

        assertEquals(2, newDao.getAllBookings().size());
    }

    @Test
    void testLoadBookingData_FileNotExists() {
        File file = new File(CollectionBookingDao.fileBooking);
        if (file.exists()) file.delete();

        CollectionBookingDao newDao = new CollectionBookingDao();
        assertFalse(newDao.loadBookingData());
    }
}