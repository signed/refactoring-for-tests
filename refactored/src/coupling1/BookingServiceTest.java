package coupling1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.mockito.stubbing.Stubber;

import coupling.BookingException;
import coupling.BookingResult;

public class BookingServiceTest {

  // mocked collaborators
  private final ConferencingServer server = mock(ConferencingServer.class);
  private final MeetingCalendar calendar = mock(MeetingCalendar.class);

  // object under test
  private final BookingService bookingService = new BookingService(server,
                                                                   calendar);

  @Test
  public void testConferenceBooking_HappyPath() throws Exception {
    // arrange
    Date startDate = new Date();
    when(calendar.nextPossibleDate()).thenReturn(startDate);

    // act
    BookingResult result = bookingService.bookConference("Test Conference");

    // assert
    verify(server).bookConference("Test Conference", startDate);
    assertTrue(result.isSuccess());
    assertEquals(startDate, result.getStartDate());
  }

  @Test
  public void testConferenceBooking_ServerFailure() throws Exception {
    // arrange
    arrangeBookingException("HTTP 500");

    // act
    BookingResult result = bookingService.bookConference("Test Conference");

    // assert
    assertFalse(result.isSuccess());
    assertEquals("HTTP 500", result.getError().getMessage());
  }

  private void arrangeBookingException(String message) throws BookingException {
    Stubber stubber = doThrow(new BookingException(message));
    stubber.when(server).bookConference(anyString(), (Date) any());
  }
}
